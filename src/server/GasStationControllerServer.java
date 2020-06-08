package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import Entity.GasStationOrder;
import Entity.Supplier;
import enums.SupplierOrderStatus;

public class GasStationControllerServer {

	//return all the orders to the supplier according to the supplierId and the status
	public static ArrayList<GasStationOrder> getAllOrdersByStatus(String supplierId, String status) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<GasStationOrder> orders = new ArrayList<GasStationOrder>();
		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select * from myfueldb.gasstationorder where status = ? and supplierid = ?");
			stm.setString(1, status);
			stm.setString(2, supplierId);
			res = stm.executeQuery();

			while (res.next() == true) {
				GasStationOrder order = new GasStationOrder(res.getInt(1), res.getString(2), res.getInt(3),
						res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getFloat(8));
				orders.add(order);
			}

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

}
