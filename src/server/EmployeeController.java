package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Car;
import Entity.Customer;

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
			
			if(car==null)return null;
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
			
			if(customer==null)return null;
			return customer.get(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
