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
/**
 * The Class CustomerGasOrderController controlls the home order gas for the customers.
 */
public class CustomerGasOrderController {

	/**
	 * Gets the orders.
	 * @param customerId the customer id
	 * @return the orders
	 */
	public static ArrayList<GasOrder> getOrders(String customerId) {

		PreparedStatement stm;
		ResultSet res;
		ArrayList<GasOrder> orderList = new ArrayList<GasOrder>();

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from gasorder where customerID = ?");
			stm.setString(1, customerId);
			res = stm.executeQuery();

			while(res.next() == true) {
				OrderStatus stat = OrderStatus.processing;
				switch (res.getInt(9)) {
				case 1:
					stat = OrderStatus.processing;
					break;
				case 2:
					stat = OrderStatus.onTheWay;
					break;
				case 3:
					stat = OrderStatus.arrived;
					break;
				}
				GasOrder order = new GasOrder(res.getInt(1), res.getString(2), res.getString(3),
						res.getString(4), res.getFloat(5), res.getString(6), res.getFloat(7), res.getBoolean(8), stat);
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
	 * Create new purchase of gas to home.
	 * @param obj - order to create.
	 * @return true - if the purchase was successful.
	 */
	public static boolean createNewOrder(GasOrder obj) {
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement("INSERT INTO gasorder (customerID, supplyDate, time, gasAmount, date, priceOfPurchase, urgent, status) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?); ");
			stm.setString(1, obj.getCustmoerId());
			stm.setString(2, obj.getSupplyDate());
			stm.setString(3, obj.getTime());
			stm.setFloat(4, obj.getGasAmount());
			stm.setString(5, obj.getDate());
			stm.setFloat(6, obj.getPriceOfPurchase());
			stm.setBoolean(7, obj.isUrgent());
			int stat = obj.getStatusInt();		//***
			stm.setInt(8, stat);	
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}

}
