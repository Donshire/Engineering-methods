
package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Car;
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
			ArrayList<Customer> customers = BuildObjectByQueryData.BuildCustomer(res);
			Customer customer = null; 
			
			if (customers != null) {
				if(!customers.isEmpty()) {
					customer = customers.get(0);
				if (customer.getOnline() == 1)
					return Commands.UserAlreadyConnected;
//					else
//						updateUserOnlineStatus("customer", customer.getId(),1);
				res.close();
				return customer;
				}
			}

			res = logIn("employee", userName, password);
			ArrayList<Employee> employees = BuildObjectByQueryData.BuildEmployee(res);
			Employee employee = null;

			if (employees != null) {
				if(!employees.isEmpty()) {
				employee = employees.get(0);
				if (employee.getOnline() == 1)
					return Commands.UserAlreadyConnected;
//					else
//						updateUserOnlineStatus("employee", employee.getId(),1);
				res.close();
				return employee;
				}
			}

			res = logIn("supplier", userName, password);
			ArrayList<Supplier> suppliers = BuildObjectByQueryData.BuildSupplier(res);
				Supplier supplier = null;
				
			if (suppliers != null) {
				if (!suppliers.isEmpty()) {
					supplier = suppliers.get(0);
					if (supplier.getOnline() == 1)
						return Commands.UserAlreadyConnected;
//						else
//							updateUserOnlineStatus("supplier", supplier.getId(),1);
					res.close();
					return supplier;
				}
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