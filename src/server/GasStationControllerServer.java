package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.mysql.cj.protocol.Resultset;

import Entity.Employee;
import Entity.GasStation;
import Entity.GasStationOrder;
import Entity.StationFuel;
import Entity.Supplier;
import enums.SupplierOrderStatus;

public class GasStationControllerServer {

	//return all the orders to the supplier according to the supplierId and the status
	public static ArrayList<GasStationOrder> getAllOrdersByStatus(String supplierId, String status) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<GasStationOrder> orders=null;

		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select * from myfueldb.gasstationorder where status = ? and supplierid = ?");
			stm.setString(1, status);
			stm.setString(2, supplierId);
			res = stm.executeQuery();

			orders=BuildObjectByQueryData.BuildGasStationOrder(res);

			res.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return orders;
	}

	//update the order status and the fuel inventory according to the order
	public static boolean updateOrderStatus(GasStationOrder orders) {
		PreparedStatement stm;
		float amount;
		try {
			// return the current amount from the table
			amount=getStationFuelQuantity(orders.getStationID(),orders.getFuelType());
			amount= amount + orders.getQuantity();
			
			// first update fuel quantity
			updateFuelQuantity(amount,orders.getStationID(),orders.getFuelType());
			//update order status
			
			stm= ConnectionToDB.conn.prepareStatement("update myfueldb.gasstationorder set status=? where orderID=?");
			stm.setString(1, SupplierOrderStatus.supplied.toString());
			stm.setInt(2,orders.getOrderID());
			stm.executeUpdate();
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	/**
	 * update specific Gas Station fuel
	 * @param newAmount is what will be stored in the db
	 * @param stationID
	 * @param fuelType
	 */
	public static void updateFuelQuantity(float newAmount,int stationID,String fuelType) {
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement("update myfueldb.stationfuel set amount=? where stationId=? and fuelType=?");
			stm.setFloat(1, newAmount);
			stm.setInt(2, stationID);
			stm.setString(3, fuelType);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get Station Fuel Quantity
	 * @param stationID
	 * @param fuelType
	 * @return
	 */
	public static float getStationFuelQuantity(int stationID,String fuelType) {
		PreparedStatement stm;
		ResultSet res;
		
		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select amount from myfueldb.stationfuel where stationId=? and fuelType=?");
			stm.setInt(1, stationID);
			stm.setString(2, fuelType);
			res = stm.executeQuery();
			if (res.next()) {
				return res.getFloat(1);
			}
			res.close();
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void ReachedMinemumQuantityHandler(int stationID,String fuelType,float stationCurrInventory) {
		float stationMinQuantity = getStationMinQuantityForFuel(stationID, fuelType);
		if(stationMinQuantity>=stationCurrInventory)
				createOrder(stationID, fuelType);
	}
	
	public static float getStationMinQuantityForFuel(int stationID,String fuelType) {
		PreparedStatement stm;
		ResultSet res;
		
		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select minQuantity from myfueldb.stationfuel where stationId=? and fuelType=?");
			stm.setInt(1, stationID);
			stm.setString(2, fuelType);
			res = stm.executeQuery();
			if (res.next()) {
				return res.getFloat(1);
			}
			res.close();
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private static void createOrder(int stationID,String fuelType) {
		Supplier supplier=getSupplierByFuelType(fuelType);
		StationFuel stationFuel = getStationfuel(stationID, fuelType);
		
		if(supplier==null||stationFuel==null) {
			System.out.println("there is no supplier or stationFuel for creating Gas station Order");
			return;
		}
		
		float amountNeeded=stationFuel.getTankSize()-stationFuel.getAmount();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement stm;
		
		try {
			stm = ConnectionToDB.conn
					.prepareStatement("insert into gasstationorder (supplierID,"+
			"gasStationID,status,date,orderPrice,fuelType,fuelAmount) " + "values (?,?,?,?,?,?,?)");
			
			stm.setString(1, supplier.getId());
			stm.setInt(2, stationID);
			stm.setString(3, SupplierOrderStatus.created.toString());
			stm.setString(4, dateFormat.format(date));
			stm.setFloat(5, 0);//orderPrice
			stm.setString(6, fuelType);
			stm.setString(6, Float.toString(amountNeeded));
			
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Supplier getSupplierByFuelType (String fuelType) {
		PreparedStatement stm;
		ResultSet res;
		Supplier supplier;
		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select * from myfueldb.supplier where fuelType=?");
			stm.setString(1, fuelType);
			res = stm.executeQuery();
			supplier=BuildObjectByQueryData.BuildSupplier(res).get(0);
			res.close();
			stm.close();
			
			return supplier;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static StationFuel getStationfuel (int stationID,String fuelType) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<StationFuel> stationFuel=null;
		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.stationfuel"+
		          " where stationId = ? AND fuelType=?");
			stm.setString(1, fuelType);
			
			res = stm.executeQuery();
			stationFuel=BuildObjectByQueryData.BuildStationfuel(res,true);
			
			res.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(stationFuel!=null) return stationFuel.get(0);
		return null;
	}
	
	
	public static GasStation getGasStation(int stationID) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<GasStation> gasStation=null;
		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.gasstation where stationId = ?");
			stm.setInt(1, stationID);
			
			res = stm.executeQuery();
			gasStation=BuildObjectByQueryData.BuildGasStation(res,true);
			
			res.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(gasStation==null|| gasStation.isEmpty()) return null;
		return gasStation.get(0);
	}
	
	
	private static void sendMailToStationManager(int stationID) {
		
		GasStation gasStation = getGasStation(stationID);
		if(gasStation==null) {
			System.out.println("cloud not found station by id to create order");
			return;
		}
		Employee employee = EmployeeController.getEmployeeByWorkerID(gasStation.getStationMangerWorkerID());
		
		// Recipient's email ID needs to be mentioned.
	      String to = employee.getMail();

	      // Sender's email ID needs to be mentioned
	      String from = "iamme0ssa@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		
	}
	
}
