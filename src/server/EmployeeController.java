package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Car;
import Entity.Customer;
import Entity.Employee;

public class EmployeeController {

	/**
	 * to check if car exist 
	 * @param carNumber
	 * @return car object if exist ,else return null in case of
	 *  exeption or car wasn't found
	 */
	public static Car getCarByNumber(String carNumber) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Car> car;
		String query="Select * " + 
				"From myfueldb.car " + 
				"where car.carNumber=?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, carNumber);
			res=stm.executeQuery();
			//create car object
			car=BuildObjectByQueryData.BuildCar(res);
			
			stm.close();
			
			if(car==null||car.isEmpty())return null;
			return car.get(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * to check if customer exist 
	 * @param customerId
	 * @return customer object if exist ,else return null in case of
	 *  exeption or car wasn't found
	 */
	public static Customer getCutomerByCarNumber(String customerId) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Customer> customer;
		String query="Select * " + 
				"From myfueldb.customer as cus " + 
				"where cus.id = ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, customerId);
			res=stm.executeQuery();
			//create car object
			customer=BuildObjectByQueryData.BuildCustomer(res);
			
			stm.close();
			
			if(customer==null||customer.isEmpty())return null;
			return customer.get(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static Employee getEmployeeByWorkerID(int workerId) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Employee> employee;
		String query="select * from myfueldb.employee where workerId = ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setInt(1, workerId);
			res=stm.executeQuery();
			//create car object
			employee=BuildObjectByQueryData.BuildEmployee(res);
			
			stm.close();
			
			if(employee==null||employee.isEmpty())return null;
			return employee.get(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
