package server;

import java.sql.PreparedStatement;
import enums.Commands;
import enums.PricingModel;
import enums.PurchaseModel;
import sun.security.action.GetIntegerAction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entity.Car;
import Entity.Customer;
import Entity.Employee;
import Entity.Sale;

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

			if (car == null || car.isEmpty())
				return null;

			return car.get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * to check if customer exist changed the name-saleem
	 * 
	 * @param customerId
	 * @return customer object if exist ,else return null in case of exeption or car
	 *         wasn't found
	 */
	public static Customer getCutomerByCustomerID(String customerId) {
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

			if (customer == null || customer.isEmpty())
				return null;

			return customer.get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Inert to the DB all the information for a new customer
	 * 
	 * @param customer
	 * @return true or false
	 */

	public static boolean addNewCustmer(Customer customer) {
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement(
					"insert into myfueldb.customer (userName,password,firstName,lastName,mail,id,phoneNumber,online,adress,pricingModel,purchaseModule ,visanumber,expDate,CVV,customerType,companyName) "
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
			stm.setInt(11, customer.getPurchaseModule());
			stm.setString(12, customer.getVisaNumber());
			stm.setString(13, customer.getExpDate());
			stm.setString(14, customer.getCVV());
			stm.setString(15, customer.getCustomerType());
			stm.setString(16, customer.getCompanyName());
			stm.executeUpdate();
			stm.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Check if the received customer id exist in the DB
	 * 
	 * @param id
	 * @return int res 0= doen't exist, 1= exist, -1 = error
	 */

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

	/**
	 * Inert to the DB all the information for a new car
	 * 
	 * @param car
	 * @return true or false
	 */

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

	/**
	 * Update pricing model and purchase model in the DB for the customer id that
	 * was received
	 * 
	 * @param pricing
	 * @param purchase
	 * @param id
	 * @return true or false
	 */

	public static boolean updateModels(String pricing, String purchase, String id) {
		PreparedStatement stm;
		int pricingNum = 0, purchaseNum = 0;
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
			purchaseNum = 0;
			break;
		case "MonthlysubscriptioOneCar":
			purchaseNum = 1;
			break;
		case "Monthlysubscriptio2OrMoreCars":
			purchaseNum = 2;
			break;
		case "FullMonthlysubscription":
			purchaseNum = 3;
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

	/**
	 * Select all the details of specific employee according to the received id
	 * 
	 * @param workerId
	 * @return employee object
	 */

	public static Employee getEmployeeByWorkerID(int workerId) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Employee> employee;
		String query = "select * from myfueldb.employee where workerId = ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setInt(1, workerId);
			res = stm.executeQuery();
			// create car object
			employee = BuildObjectByQueryData.BuildEmployee(res);

			stm.close();

			if (employee == null || employee.isEmpty())
				return null;
			return employee.get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Select all the details of specific customer according to the received id
	 * 
	 * @param id
	 * @return customer object
	 */

	public static Customer getCustomerDetails(String id) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Customer> customers = null;

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.customer where id = ?");
			stm.setString(1, id);
			res = stm.executeQuery();

			customers = BuildObjectByQueryData.BuildCustomer(res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (customers == null || customers.isEmpty())
			return null;
		return customers.get(0);
	}

	/**
	 * Receive data of a customer to update, and insert the changes to the DB
	 * according to the id
	 * 
	 * @param update
	 * @return true or false
	 */

	public static boolean updateCustomerDetails(ArrayList<String> update) {
		PreparedStatement stm;
		System.out.println(update);
		try {
			stm = ConnectionToDB.conn.prepareStatement(
					"update myfueldb.customer set firstName = ? , lastName = ?, mail = ? ,phoneNumber = ?, adress = ? ,visanumber = ? , expDate = ? , CVV = ? where id = ?");
			stm.setString(1, update.get(1));
			stm.setString(2, update.get(2));
			stm.setString(3, update.get(3));
			stm.setString(4, update.get(7));
			stm.setString(5, update.get(8));
			stm.setString(6, update.get(4));
			stm.setString(7, update.get(5));
			System.out.println("server @@@@@@@@@@@@@@@@@@@@@@@ " + update.get(5));
			stm.setString(8, update.get(6));
			stm.setString(9, update.get(0));
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Create ArrayList<Sale> of all the sales that are in the DB
	 * 
	 * @return ArrayList<Sale>
	 */

	public static ArrayList<Sale> getAllSales() {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Sale> sales = new ArrayList<Sale>();
		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.sale");
			res = stm.executeQuery();
			while (res.next() == true) {
				Sale sale = new Sale(res.getInt(1), res.getString(2), res.getString(4), res.getString(5),
						res.getFloat(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9),
						res.getString(10));
				sales.add(sale);
			}
			res.close();
			stm.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sales;
	}

	/**
	 * Receive sale from client and delete all the sale from DB according to Id
	 * 
	 * @param sale
	 * @return true or false
	 */

	public static boolean deleteSales(Sale sale) {
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement("delete from myfueldb.sale where saleID = ?");
			stm.setInt(1, sale.getSaleID());
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Receive sale object from the client and insert it to the DB
	 * 
	 * @param sale
	 * @return true or false
	 */

	public static boolean addNewSaleTemp(Sale sale) {
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement(
					"insert into myfueldb.sale (status,companyName,fuelType,salePercent,startTime,endTime,startDate,endDate,days) "
							+ "values (?,?,?,?,?,?,?,?,?)");
			stm.setString(1, sale.getStatus().toString());
			stm.setString(2, sale.getCompanyName());
			stm.setString(3, sale.getFuelType());
			stm.setFloat(4, sale.getSalePercent());
			stm.setString(5, sale.getStartTime());
			stm.setString(6, sale.getEndTime());
			stm.setString(7, sale.getStartDate());
			stm.setString(8, sale.getEndDate());
			stm.setString(9, sale.getSaleDays());
			stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Receive company name and return the fuel types that are related to this
	 * company name
	 * 
	 * @param companyName
	 * @return ArrayList<String>
	 */

	public static ArrayList<String> getFuelTypesByCompany(String companyName) {
		PreparedStatement stm;
		ResultSet res;

		ArrayList<String> fuelTypes = new ArrayList<String>();
		try {
			System.out.println("company = " + companyName);
			stm = ConnectionToDB.conn.prepareStatement("select fuelType from myfueldb.company where companyName=?");
			stm.setString(1, companyName);
			res = stm.executeQuery();
			while (res.next()) {
				String fuel = res.getString(1);
				if (!fuel.equals("HOME GAS"))
					fuelTypes.add(fuel);
			}
			stm.close();
			res.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return fuelTypes;
	}

	/**
	 * Receive user name and check in the customer table in the DB if exist
	 * 
	 * @param userName
	 * @return true or false
	 */

	public static boolean checkIfUserNameExist(String userName) {
		PreparedStatement stm, stm2, stm3;
		ResultSet res, res2, res3;
		String id = null, id2 = null, id3 = null;
		try {
			stm = ConnectionToDB.conn.prepareStatement("select id from myfueldb.customer where userName=?");
			stm.setString(1, userName);
			res = stm.executeQuery();
			if (res.next())
				id = res.getString(1);

			stm2 = ConnectionToDB.conn.prepareStatement("select id from myfueldb.employee where userName=?");
			stm2.setString(1, userName);
			res2 = stm2.executeQuery();
			if (res2.next())
				id2 = res2.getString(1);

			stm3 = ConnectionToDB.conn.prepareStatement("select id from myfueldb.supplier where userName=?");
			stm3.setString(1, userName);
			res3 = stm3.executeQuery();
			if (res3.next())
				id3 = res3.getString(1);

			if (id != null || id2 != null || id3 != null)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * Inert all the company names that exist in the DB (without duplicates) to
	 * ArrayList
	 * 
	 * @return ArrayList<String>
	 */

	public static ArrayList<String> getCompanyNames() {
		ArrayList<String> companyNames = new ArrayList<String>();
		PreparedStatement stm;
		ResultSet res;
		try {
			stm = ConnectionToDB.conn.prepareStatement("select distinct companyName from myfueldb.company");
			res = stm.executeQuery();
			while (res.next() == true) {
				String name = res.getString(1);
				companyNames.add(name);
			}
			res.close();
			stm.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return companyNames;
	}

	/**
	 * Receive details of purchase model and update the customermodule table in the
	 * DB
	 * 
	 * @param id
	 * @param purchM
	 * @param companyNames
	 * @return true or false
	 */

	public static boolean addModule(String id, String purchM, String companyNames) {
		PreparedStatement stm, stm1;
		ResultSet res;
		int purchaseNum = -1;
		switch (purchM) {
		case "Casualfueling":
			purchaseNum = 0;
			break;
		case "MonthlysubscriptioOneCar":
			purchaseNum = 1;
			break;
		case "Monthlysubscriptio2OrMoreCars":
			purchaseNum = 2;
			break;
		case "FullMonthlysubscription":
			purchaseNum = 3;
			break;
		}

		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select modelNumber from myfueldb.customermodule where CustomerID = ?");
			stm.setString(1, id);
			res = stm.executeQuery();
			if (res.next()) {
				System.out.println("res : " + res.getInt(1));
				stm1 = ConnectionToDB.conn.prepareStatement(
						"update myfueldb.customermodule set modelNumber=?,companyNamesSubscribed=? where CustomerID = ?");
				stm1.setInt(1, purchaseNum);
				stm1.setString(2, companyNames);
				stm1.setString(3, id);
				stm1.executeUpdate();
				return true;
			} else {
				stm1 = ConnectionToDB.conn.prepareStatement(
						"insert into myfueldb.customermodule (CustomerID,modelNumber,companyNamesSubscribed) "
								+ "values (?,?,?)");

				stm1.setString(1, id);
				stm1.setInt(2, purchaseNum);
				stm1.setString(3, companyNames);
				stm1.executeUpdate();
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return false;

	}

	/**
	 * Receive new car object and old car number and insert the new car details
	 * instead of the old one in the DB
	 * 
	 * @param car
	 * @param oldCar
	 * @return true or false
	 */

	public static boolean updateCar(Car car, String oldCar) {
		PreparedStatement stm, stm1;
		ResultSet res;
		try {
			stm1 = ConnectionToDB.conn
					.prepareStatement("select carNumber from myfueldb.car where carNumber = ? and CustomerID = ?");
			stm1.setString(1, oldCar);
			stm1.setString(2, car.getCarCustomerId());
			res = stm1.executeQuery();
			if (res.next()) {
				stm = ConnectionToDB.conn
						.prepareStatement("update myfueldb.car set carNumber=?,fuelType=? where carNumber = ?");
				stm.setString(1, car.getCarNumber());
				stm.setString(2, car.getFuelType());
				stm.setString(3, oldCar);
				stm.executeUpdate();

			} else
				return false;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Count the number of car that are connected to the received is
	 * 
	 * @param idForCount
	 * @return int count of cars
	 */

	public static int getCarCount(String idForCount) {
		PreparedStatement stm;
		ResultSet res;
		int count = 0;
		try {
			stm = ConnectionToDB.conn
					.prepareStatement("select count(carNumber) from myfueldb.car where CustomerID = ?");
			stm.setString(1, idForCount);
			res = stm.executeQuery();
			if (res.next())
				count = Integer.parseInt(res.getString(1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
}
