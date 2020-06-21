
package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Car;
import Entity.Customer;
import Entity.Employee;
import Entity.StationManager;
import Entity.Supplier;
import enums.Commands;

/**
 * The Class LogINController controls the users log in if the user is logged in it displays
 * appropriate message to the user
 * else it logs in.
 */
public class LogINController {

	/** The stm. */
	private static PreparedStatement stm;
	
	/**
	 * Log in.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return the object
	 */
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
					else
						updateUserOnlineStatus("customer", customer.getId(),1);
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
					else
						updateUserOnlineStatus("employee", employee.getId(),1);
				res.close();
				if (employee.getRole().toLowerCase().compareTo("station manager") == 0) {
					return getStationManagerById(employee);
				}

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
						else
							updateUserOnlineStatus("supplier", supplier.getId(),1);
					res.close();
					return supplier;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Log in.
	 *
	 * @param tableName the table name
	 * @param userName the user name
	 * @param password the password
	 * @return the result set
	 * @throws SQLException the SQL exception
	 */
	private static ResultSet logIn(String tableName, String userName, String password) throws SQLException {
		stm = ConnectionToDB.conn.prepareStatement("select * from "+ tableName +" where userName = ? and passWord = ?");
		stm.setString(1, userName);
		stm.setString(2, password);
		return stm.executeQuery();
	}

	/**
	 * Gets the station manager by id.
	 *
	 * @param emp the emp
	 * @return the station manager by id
	 * @throws SQLException the SQL exception
	 */
	private static StationManager getStationManagerById(Employee emp) throws SQLException {
		ResultSet res;
		stm = ConnectionToDB.conn.prepareStatement("select stationId from stationmanager " + "where workerId = ?");
		stm.setInt(1, emp.getWorkerID());
		res = stm.executeQuery();

		if (res.next())
			return new StationManager(emp.getUserName(), emp.getPassword(), emp.getFirstName(), emp.getLastName(),
					emp.getMail(), emp.getId(), emp.getPhoneNumber(), emp.getDepartment(), emp.getRole(),
					emp.getOnline(), emp.getWorkerID(), emp.getCompanyName(), res.getInt(1));

		return null;
	}

	/**
	 * Update user online status.
	 *
	 * @param tableName the table name
	 * @param userID the user ID
	 * @param status the status
	 * @throws SQLException the SQL exception
	 */
	public static void updateUserOnlineStatus(String tableName, String userID, int status) throws SQLException {
		stm = ConnectionToDB.conn.prepareStatement("update " + tableName + " set online = ? where id = ?");
		stm.setInt(1, status);
		stm.setString(2, userID);
		stm.executeUpdate();
		stm.close();
	}

}