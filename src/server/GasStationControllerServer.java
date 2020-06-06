package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.StyledEditorKit.BoldAction;

import Entity.GasStation;
import Entity.GasStationOrder;
import Entity.StationFuel;
import Entity.Supplier;
import enums.GasStationOrderFromSupplier;
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

				orders=BuildObjectByQueryData.BuildGasStationOrder(res);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return orders;

		}
		
		//update the order status and the fuel inventory according to the order
		public static boolean updateOrderStatus(GasStationOrder orders) {
			PreparedStatement stm;
			ResultSet res;
			Float amount= (float) 0.0;
			Float newAmount;
			try {
				// return the current amount from the table
				stm = ConnectionToDB.conn
						.prepareStatement("select amount from myfueldb.stationfuel where stationId=? and fuelType=?");
				stm.setInt(1, orders.getStationID());
				stm.setString(2, orders.getFuelType());
				res = stm.executeQuery();
				if (res.next()) {
					amount = res.getFloat(1);
				}
				res.close();
				stm.close();
				newAmount= amount + orders.getQuantity();
				
				// first update fuel quantity
				stm = ConnectionToDB.conn.prepareStatement("update myfueldb.stationfuel set amount=? where stationId=? and fuelType=?");
				stm.setFloat(1, newAmount);
				stm.setInt(2, orders.getStationID());
				stm.setString(3, orders.getFuelType());
				stm.executeUpdate();
				stm.close();
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

	public static ArrayList<StationFuel> getAllStationFuel(int stationId) {

		PreparedStatement stm;
		ResultSet res;
		ArrayList<StationFuel> fuel = new ArrayList<StationFuel>();

		try {
			stm = ConnectionToDB.conn.prepareStatement("SELECT * FROM stationfuel WHERE stationId = ?");
			stm.setInt(1, stationId);
			res = stm.executeQuery();

			while (res.next()) {

				StationFuel s = new StationFuel(res.getInt(1), res.getString(2), res.getFloat(3), res.getFloat(4));
				fuel.add(s);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fuel;
	}

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

	public static Boolean createFuelStationIncmomeReport(int stationId) {

		PreparedStatement stm;
		ResultSet res;
		float f = 0;

		try {
			stm = ConnectionToDB.conn.prepareStatement(
					"select sum(priceOfPurchase)- SUM(currentPrice) from myfueldb.fuelpurchase where stationId= ? ");

			stm.setInt(1, stationId);
			res = stm.executeQuery();
			res.next();
			f = res.getFloat(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("the incomes of statis = " + f);

		return true;

	}

	public static Boolean createFuelStationPurchasesReport(int stationId) {
		PreparedStatement stm;
		ResultSet res;

		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select DISTINCT(c.fuelType),sum(f.fuelQuantity) from myfueldb.car as c"
							+ " LEFT JOIN myfueldb.fuelpurchase as f ON c.carNumber = f.CarNumber "
							+ " where f.stationId=? group by(c.fuelType)");
			stm.setInt(1, stationId);
			res = stm.executeQuery();

			while (res.next()) {
				System.out.println(res.getString(1));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

}
