package server;

import Entity.Customer;
import Entity.Employee;
import Entity.GasOrder;
import Entity.Supplier;
import enums.Commands;
import enums.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MyOrderConrtollerServer {

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

}
