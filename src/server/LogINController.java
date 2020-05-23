package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Customer;
import Entity.Employee;
import Entity.Supplier;
import enums.Commands;

public class LogINController {

	private static PreparedStatement stm;

	public static Object LogIn(String userName, String password) {
		ResultSet res;

		try {
			res = logIn("customer", userName, password);

			if (res.next() == true) {
				Customer customer = new Customer(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9),
						res.getInt(10), res.getInt(11), res.getInt(12), res.getInt(13), res.getString(14));
				res.close();
				if (customer.getOnline() == 1)
					return Commands.UserAlreadyConnected;
				else
					updateUserOnlineStatus("customer", customer.getId(),1);
				return customer;
			}

			res = logIn("employee", userName, password);
			if (res.next() == true) {
				Employee employee = new Employee(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9),
						res.getInt(10), res.getInt(11), res.getString(12));
				res.close();
				System.out.println(employee);
				if (employee.getOnline() == 1)
					return Commands.UserAlreadyConnected;
				else
					updateUserOnlineStatus("employee", employee.getId(),1);
				return employee;
			}

			res = logIn("supplier", userName, password);
			if (res.next() == true) {
				Supplier supplier = new Supplier(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9));
				res.close();
				if (supplier.getOnline() == 1)
					return Commands.UserAlreadyConnected;
				else
					updateUserOnlineStatus("supplier", supplier.getId(),1);
				return supplier;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static ResultSet logIn(String tableName, String userName, String password) throws SQLException {
		stm = ConnectionToDB.conn.prepareStatement("select * from "+ tableName +" where userName = ? and passWord = ?");
		stm.setString(1, userName);
		stm.setString(2, password);
		return stm.executeQuery();
	}

	public static void updateUserOnlineStatus(String tableName, String userID,int status) throws SQLException {
		stm = ConnectionToDB.conn.prepareStatement("update "+tableName +" set online = ? where id = ?");
		stm.setInt(1, status);
		stm.setString(2, userID);
		stm.executeUpdate();
		stm.close();
	}

}
