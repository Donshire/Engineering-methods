package server;


import Entity.GasOrder;

import enums.OrderStatus;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerConrtollerServer  {

	/**
	 * 
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
