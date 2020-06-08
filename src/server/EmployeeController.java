package server;

import java.sql.PreparedStatement;
import enums.PricingModel;
import enums.PurchaseModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Car;
import Entity.Customer;
import Entity.Employee;

public class EmployeeController {

	/**
	 * to check if car exist
	 * 
	 * @param carNumber
	 * @return car object if exist ,else return null in case of exeption or car
	 *         wasn't found
	 */
	public static Car getCarByNumber(String carNumber) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Car> car;
		String query = "Select * " + "From myfueldb.car " + "where car.carNumber=?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, carNumber);
			res = stm.executeQuery();
			// create car object
			car = BuildObjectByQueryData.BuildCar(res);

			stm.close();

			if(car==null||car.isEmpty()) return null;
				
			return car.get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * to check if customer exist
	 * 
	 * @param customerId
	 * @return customer object if exist ,else return null in case of exeption or car
	 *         wasn't found
	 */
	public static Customer getCutomerByCarNumber(String customerId) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Customer> customer;
		String query = "Select * " + "From myfueldb.customer as cus " + "where cus.id = ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, customerId);
			res = stm.executeQuery();
			// create car object
			customer = BuildObjectByQueryData.BuildCustomer(res);

			stm.close();

			if(customer==null||customer.isEmpty()) return null;
				
			return customer.get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean addNewCustmer(Customer customer) {
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement(
					"insert into myfueldb.customer (userName,password,firstName,lastName,mail,id,phoneNumber,online,adress,pricingModel,customerTypeAnaleticRank,purchaseModule,fuelingHourAnaleticRank ,visanumber,expDate,CVV) "
							+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stm.setString(1, customer.getUserName());
			stm.setString(2, customer.getPassword());
			stm.setString(3, customer.getFirstName());
			stm.setString(4, customer.getLastName());
			stm.setString(5, customer.getMail());
			stm.setString(6, customer.getId());
			stm.setString(7, customer.getPhoneNumber());
			stm.setInt(8, customer.getOnline());
			stm.setString(9, customer.getAdress());
			stm.setInt(10, customer.getPricingModel());
			stm.setInt(11, customer.getCustomerTypeAnaleticRank());
			stm.setInt(12, customer.getPurchaseModule());
			stm.setInt(13, customer.getFuelingHourAnaleticRank());
			stm.setString(14, customer.getVisaNumber());
			stm.setString(15, customer.getExpDate());
			stm.setString(16, customer.getCVV());
			stm.executeUpdate();

			stm.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static int checkIfExist(String id) {
		PreparedStatement stm;
		ResultSet res;
		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.customer where id = ?");
			stm.setString(1, id);
			res = stm.executeQuery();
			if (res.next())
				return 1;
			else
				return 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}

	public static boolean addNewCar(Car car) {
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn
					.prepareStatement("insert into myfueldb.car (carNumber,fuelType,CustomerID) " + "values (?,?,?)");
			stm.setString(1, car.getCarNumber());
			stm.setString(2, car.getFuelType());
			stm.setString(3, car.getCarCustomerId());
			stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean updateModels(String pricing, String purchase, String id) {
		PreparedStatement stm;
		int pricingNum =0, purchaseNum=0 ;
		switch (pricing) {
		case "onlyOneStatione":
			pricingNum = 1;
			break;
		case "TwoOrThreeStations":
			pricingNum = 2;
			break;
		}
		switch (purchase) {
		case "Casualfueling":
			purchaseNum = 1;
			break;
		case "MonthlysubscriptioOneCar":
			purchaseNum = 2;
			break;
		case "Monthlysubscriptio2OrMoreCars":
			purchaseNum = 3;
			break;
		case "FullMonthlysubscription":
			purchaseNum = 4;
			break;
		}
		try {
			stm = ConnectionToDB.conn
					.prepareStatement("update myfueldb.customer set pricingModel=? ,purchaseModule=? where id=?");
			stm.setInt(1, pricingNum);
			stm.setInt(2, purchaseNum);
			stm.setString(3, id);
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
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
