package server;


import Entity.GasOrder;

import enums.OrderStatus;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * The Class CustomerConrtollerServer controls all the customer orders.
 */
public class CustomerConrtollerServer  {

	/**
	 * Gets the orders.
	 *
	 * @param customerId the customer id
	 * @return the orders
	 */


	public static Object getOrders(String customerId) {

		PreparedStatement stm;
		ResultSet res;
		ArrayList<GasOrder> orderList = null;

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from gasorder where customerID = ?");
			stm.setString(1, customerId);
			res = stm.executeQuery();

			orderList = BuildObjectByQueryData.BuildGasOrder(res);
			
			stm.close();
			res.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(orderList==null||orderList.isEmpty())return null;
		return orderList;

	}


}
