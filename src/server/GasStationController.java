package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.StyledEditorKit.BoldAction;

import Entity.GasStation;
import Entity.GasStationOrder;
import Entity.StationFuel;
import Entity.Supplier;
import enums.GasStationOrderFromSupplier;

public class GasStationController {

	public static ArrayList<GasStationOrder> getAllstationOrdersByStatus(int stationId, String status) {

		PreparedStatement stm;
		ResultSet res;
		ArrayList<GasStationOrder> orders = new ArrayList<GasStationOrder>();

		try {
			stm = ConnectionToDB.conn
					.prepareStatement("SELECT *" + " FROM gasstationorder " + "where gasStationID = ? and status = ?");
			stm.setInt(1, stationId);
			stm.setString(2, status);
			res = stm.executeQuery();

			while (res.next()) {

				GasStationOrder ord = new GasStationOrder(res.getInt(1), res.getString(2), res.getInt(3),
						res.getString(4), res.getString(5), res.getFloat(6), res.getString(7));

				orders.add(ord);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return orders;

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
