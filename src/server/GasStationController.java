package server;

import Entity.Customer;
import Entity.Employee;
import Entity.GasOrder;
import Entity.Supplier;
import enums.Commands;
import enums.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GasStationController {

	public static Object getOrders(String customerId) {

		PreparedStatement stm;
		ResultSet res;
		ArrayList<GasOrder> orderList = new ArrayList<GasOrder>();

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from gasorder where customerID = ?");
			stm.setString(1, customerId);
			res = stm.executeQuery();

			while(res.next() == true) {
				GasOrder order = new GasOrder(res.getInt(1), res.getString(2), res.getString(3),
						res.getFloat(4), res.getString(5), res.getFloat(6), res.getBoolean(7));
				System.out.println(order);
				orderList.add(order);
			}
			

			stm.close();
			res.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderList;

	}

	/**
	 * Create new purchase of gas to home
	 * @param obj - order to create.
	 * @return true - if the purchase was successful.
	 */
	public static Object createNewOrder(GasOrder obj) {
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement("INSERT INTO gasorder (customerID, supplyDate, gasAmount, date, priceOfPurchase, urgent) " + 
					"VALUES (?, ?, ?, ?, ?, ?); ");
			stm.setString(1, obj.getCustmoerId());
			stm.setString(2, obj.getSupplyDate());
			stm.setFloat(3, obj.getGasAmount());
			stm.setString(4, obj.getDate());
			stm.setFloat(5, obj.getPriceOfPurchase());
			stm.setBoolean(6, obj.isUrgent());
			
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}

}
