package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.File;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import com.mysql.cj.protocol.Resultset;
import javax.swing.text.StyledEditorKit.BoldAction;

import Entity.GasStation;
import Entity.Employee;
import Entity.GasStation;
import Entity.GasStationOrder;
import Entity.GenericReport;
import Entity.StationFuel;
import Entity.Supplier;
import enums.GasStationOrderFromSupplier;
import enums.Quarter;
import enums.SupplierOrderStatus;

public class GasStationControllerServer {

		/**
	 * return all the orders to the supplier according to the supplierId and the
	 * status
	 * 
	 * @param supplierId
	 * @param status
	 * @return ArrayList<GasStationOrder>
	 */
	public static ArrayList<GasStationOrder> getAllOrdersByStatus(String supplierId, String status) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<GasStationOrder> orders = null;

		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select * from myfueldb.gasstationorder where status = ? and supplierid = ?");
			stm.setString(1, status);
			stm.setString(2, supplierId);
			res = stm.executeQuery();

			orders = BuildObjectByQueryData.BuildGasStationOrder(res);

			res.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return orders;
	}

	/**
	 * the function return all station orders by stationId and status
	 * 
	 * @param stationId
	 * @param status
	 * @return ArrayList<GasStationOrder>
	 */

	public static ArrayList<GasStationOrder> getAllstationOrdersByStatus(int stationId, String status) {

		PreparedStatement stm;
		ResultSet res;
		ArrayList<GasStationOrder> orders = null;

		try {
			stm = ConnectionToDB.conn
					.prepareStatement("SELECT *" + " FROM gasstationorder " + "where gasStationID = ? and status = ?");
			stm.setInt(1, stationId);
			stm.setString(2, status);
			res = stm.executeQuery();

			orders = BuildObjectByQueryData.BuildGasStationOrder(res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return orders;
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
	
// update the order status and the fuel inventory according to the order

	public static boolean updateOrderStatus(GasStationOrder orders) {
		PreparedStatement stm1, stm2, stm3;
		ResultSet res1, res2 = null;
		Employee stationMan = null;
		int workerID =0;
		float amount;
		try {
			// return the current amount from the table
			amount = getStationFuelQuantity(orders.getStationID(), orders.getFuelType());
			amount = amount + orders.getQuantity();

			// first update fuel quantity
			updateFuelQuantity(amount, orders.getStationID(), orders.getFuelType());
			// update order status

			stm1 = ConnectionToDB.conn.prepareStatement("update myfueldb.gasstationorder set status=? where orderID=?");
			stm1.setString(1, SupplierOrderStatus.supplied.toString());
			stm1.setInt(2, orders.getOrderID());
			stm1.executeUpdate();

			stm2 = ConnectionToDB.conn
					.prepareStatement("select stationManagerID from myfueldb.gasstation where stationId=?");
			stm2.setInt(1, orders.getStationID());
			res1 = stm2.executeQuery();
			if (res1.next()) {
			 workerID = res1.getInt(1);
			 System.out.println("worker id:   " + workerID);
			}
			stm3 = ConnectionToDB.conn.prepareStatement("select * from myfueldb.employee where workerId=?");
			stm3.setInt(1, workerID);
			res2 = stm3.executeQuery();
	
			
			if(res2.next()) {
			stationMan = new Employee(res2.getString(1), res2.getString(2), res2.getString(3), res2.getNString(4),
					res2.getNString(5), res2.getNString(6), res2.getNString(7), res2.getNString(8), res2.getNString(9),
					res2.getInt(10), res2.getInt(11), res2.getNString(12));
			System.out.println(stationMan);
			}
			
			Thread sendMail = new Thread(new SendMail(orders.getStationID(), orders.getFuelType(), stationMan.getMail(),
					String.format("Fuel type %s has supplied by the supplier !", orders.getFuelType()),
					String.format("Hello %s %s\nThe fuel type \"%s\" in station with id : %d"
			          		+ " has supplied, the supplier updated the order status to: Supplied.\n"
			          		+ "\n\nMYFUEL 2020 LTM",
			          		stationMan.getFirstName(),stationMan.getLastName(),orders.getFuelType(),orders.getStationID())));
			sendMail.start();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	


	/**
	 * the function updates orders status in the db and return true if Succeeded ,
	 * else false
	 * 
	 * @param orders
	 * @return true or false if succeeded or not
	 */

	public static Object changeOrdersStatus(ArrayList<GasStationOrder> orders) {

		PreparedStatement stm;

		try {

			Iterator<GasStationOrder> iterator = orders.iterator();

			while (iterator.hasNext()) {

				stm = ConnectionToDB.conn.prepareStatement("UPDATE gasstationorder SET status = ? WHERE orderID = ?");
				stm.setString(1, GasStationOrderFromSupplier.confirmed.toString());
				stm.setInt(2, iterator.next().getOrderID());
				stm.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}


	/**
	 * the function get station id and return all statin fuel types in arraylist
	 * 
	 * @param stationId
	 * @return ArrayList<StationFuel> - all fuel types of the station
	 */

	public static ArrayList<StationFuel> getAllStationFuel(int stationId) {

		PreparedStatement stm;
		ResultSet res;
		ArrayList<StationFuel> fuel = new ArrayList<StationFuel>();

		try {
			stm = ConnectionToDB.conn.prepareStatement("SELECT * FROM stationfuel WHERE stationId = ?");
			stm.setInt(1, stationId);
			res = stm.executeQuery();

			while (res.next()) {

				StationFuel s = new StationFuel(res.getString(2), res.getInt(1), res.getFloat(3), res.getFloat(4), res.getInt(5));
				fuel.add(s);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fuel;
	}


	/**
	 * the function get stationid , and new min quantity and update
	 * 
	 * @param stationid
	 * @param fueltype
	 * @param minQuantity
	 * @return Boolean - return true if update succeeded else flase
	 */

	public static Boolean updateFuelMinQuantitybyType(int stationid, String fueltype, float minQuantity) {

		PreparedStatement stm;

		try {

			stm = ConnectionToDB.conn
					.prepareStatement("update stationfuel set minQuantity = ? where stationId = ? and fuelType = ?");
			stm.setFloat(1, minQuantity);
			stm.setInt(2, stationid);
			stm.setString(3, fueltype);

			stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}


	/**
	 * the fucntion calculate the profit of the station in the demand quater and
	 * write the result into file
	 * 
	 * @param stationId
	 * @param companyName
	 * @param quarter
	 * @param year
	 * @return file if created succeeded else return null
	 */
	public static File createFuelStationIncmomeReport(int stationId, String companyName, Quarter quarter, String year) {

		PreparedStatement stm;
		File file = null;
		ResultSet res;
		String startDate = year, endDate = year, nextyear = (Integer.parseInt(year) + 1) + "";

		switch (quarter) {

		case first:
			startDate += "-01-01";
			endDate += "-04-01";
			break;

		case second:
			startDate += "-04-01";
			endDate += "-07-01";
			break;

		case third:

			startDate += "-07-01";
			endDate += "-10-01";
			break;

		case fourth:
			endDate = nextyear;
			startDate += "-10-01";
			endDate += "-01-01";
			break;

		}

		try {
			stm = ConnectionToDB.conn.prepareStatement(
					"select SUM(currentPrice) - sum(priceOfPurchase) from fuelpurchase where stationId= ? "
							+ "and date >= ? and date < ?");

			stm.setInt(1, stationId);
			stm.setString(2, startDate);
			stm.setString(3, endDate);
			res = stm.executeQuery();

			if (res.next()) {
				file = FileManagmentSys.createFile(
						FileManagmentSys.createLocation(companyName, FileManagmentSys.stationManagerReports,
								FileManagmentSys.incomeReport),
						FileManagmentSys.incomeReport, stationId, quarter + "", year);

				if (file == null)
					return null;

				FileManagmentSys.writeToQuarterReport(file,
						FileManagmentSys.incomeReportFormat(res, stationId + "", year, quarter + ""));

				CompanyFuelControllerServer.createGenericReport(new GenericReport(year, quarter.toString(),
						file.getName(), FileManagmentSys.incomeReport, companyName, stationId));
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return file;

	}
	
	
	public static int getAllAlreadyCreatedOrder(int stationId, String fuelType) {
		PreparedStatement stm;
		ResultSet res;
		int count=0;

		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select count(*) from myfueldb.gasstationorder " + 
							"where (status = ? or status = ?) and gasStationID = ?"
							+ " and fuelType = ?");
			stm.setString(1, SupplierOrderStatus.created.toString());
			stm.setString(2, SupplierOrderStatus.confirmed.toString());
			stm.setInt(3, stationId);
			stm.setString(4, fuelType);
			res = stm.executeQuery();

			if(res.next())count=res.getInt(1);

			res.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}
	


	/**
	 * the fucntion calculate How much each fuel is consumed in the Desired quarter
	 * and write the result into file
	 * 
	 * @param stationId
	 * @param companyName
	 * @param quarter
	 * @param year
	 * @return file if created succeeded else return null
	 */

	public static File createFuelStationPurchasesReport(int stationId, String companyName, Quarter quarter,
			String year) {
		PreparedStatement stm;
		ResultSet res;
		File file = null;
		String startDate = year, endDate = year, nextyear = (Integer.parseInt(year) + 1) + "";
		;

		switch (quarter) {

		case first:
			startDate += "-01-01";
			endDate += "-04-01";
			break;

		case second:
			startDate += "-04-01";
			endDate += "-07-01";
			break;

		case third:
			startDate += "-07-01";
			endDate += "-10-01";
			break;

		case fourth:
			endDate = nextyear;
			startDate += "-10-01";
			endDate += "-01-01";
			break;

		}

		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select DISTINCT(c.fuelType),sum(f.fuelQuantity) from myfueldb.car as c"
							+ " LEFT JOIN myfueldb.fuelpurchase as f ON c.carNumber = f.CarNumber "
							+ " where f.stationId=? and date >= ? and date < ? group by(c.fuelType)");
			stm.setInt(1, stationId);
			stm.setString(2, startDate);
			stm.setString(3, endDate);
			res = stm.executeQuery();

			if (res.next()) {

				file = FileManagmentSys.createFile(
						FileManagmentSys.createLocation(companyName, FileManagmentSys.stationManagerReports,
								FileManagmentSys.purchasesReport),
						FileManagmentSys.purchasesReport, stationId, quarter + "", year);

				if (file == null)
					return null;

				FileManagmentSys.writeToQuarterReport(file,
						FileManagmentSys.purchaseReportFormat(res, stationId + "", year, quarter + ""));

				CompanyFuelControllerServer.createGenericReport(new GenericReport(year, quarter + "", file.getName(),
						FileManagmentSys.purchasesReport, companyName, stationId));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

	public static void ReachedMinemumQuantityHandler(int stationID,String fuelType,float stationCurrInventory) {
		float stationMinQuantity = getStationMinQuantityForFuel(stationID, fuelType);
		//check if there is already order created
		if (getAllAlreadyCreatedOrder(stationID, fuelType) == 0)
			if (stationMinQuantity >= stationCurrInventory)
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
			stm.setString(7, Float.toString(amountNeeded));
			
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//
		GasStation gasStation = getGasStation(stationID);
		if(gasStation==null) {
			System.out.println("cloud not found station by id to create order");
			return;
		}
		Employee employee = EmployeeController.getEmployeeByWorkerID(gasStation.getStationMangerWorkerID());
		if(employee==null) {
			System.out.println("cloud not found station manager to create order");
			return;
		}
		//
		Thread sendMail = new Thread(new SendMail(stationID, fuelType, employee.getMail(),
				String.format("Fuel type %s reached minemum quantity !!!!", fuelType),
				String.format("Hello %s %s\nThe fuel type \"%s\" in station with id : %d"
		          		+ " reached minemum quantity.\n"
		          		+ "The system created the Fuel Order, and it is Waiting you'r approvale."
		          		+ "\n\nMYFUEL 2020 LTM",
		          		employee.getFirstName(),employee.getLastName(),fuelType,stationID)));
		sendMail.start();
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
			stm.setInt(1, stationID);
			stm.setString(2, fuelType);
			
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
	
		/**
	 * the fucntion calculate the quantity of each fuel in the staion and write the
	 * result into file
	 * 
	 * @param stationId
	 * @param companyName
	 * @param quarter
	 * @param year
	 * @return
	 */
	public static File createInventoryReporteport(int stationId, String companyName, Quarter quarter, String year) {
		PreparedStatement stm;
		ResultSet res;
		File file = null;

		try {
			stm = ConnectionToDB.conn.prepareStatement("select fuelType,amount from stationfuel where stationId = ?");
			stm.setInt(1, stationId);
			res = stm.executeQuery();

			if (res.next()) {
				file = FileManagmentSys.createFile(
						FileManagmentSys.createLocation(companyName, FileManagmentSys.stationManagerReports,
								FileManagmentSys.inventoryReport),
						FileManagmentSys.inventoryReport, stationId, quarter + "", year);

				if (file == null)
					return null;

				FileManagmentSys.writeToQuarterReport(file,
						FileManagmentSys.inventoryReportFormat(res, stationId + "", year, quarter + ""));

				CompanyFuelControllerServer.createGenericReport(new GenericReport(year, quarter + "", file.getName(),
						FileManagmentSys.inventoryReport, companyName, stationId));
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

	/**
	 * the function find all the report of station by id and by year and return the
	 * result in arraylist
	 * 
	 * @param year
	 * @param stationId
	 * @return ArrayList<GenericReport> - the report
	 */
	public static ArrayList<GenericReport> getAllReportByYearandStationId(String year, int stationId) {

		ArrayList<GenericReport> reports = new ArrayList<GenericReport>();
		PreparedStatement stm;
		ResultSet res;
		GenericReport r;

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from genericreport WHERE year = ? and stationId = ?");
			stm.setString(1, year);
			stm.setInt(2, stationId);
			res = stm.executeQuery();

			while (res.next()) {
				r = new GenericReport(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getInt(6));
				System.out.println(r);
				reports.add(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return reports;
	}
	
}
