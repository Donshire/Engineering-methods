package server;

import java.sql.PreparedStatement;

import enums.Commands;
import enums.PricingModel;
import enums.PurchaseModel;
import sun.security.action.GetIntegerAction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Car;
import Entity.Customer;
import Entity.GasStationOrder;
import Entity.Message;
import Entity.Sale;
import client.ClientUI;
import client.MyFuelClient;

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

			if (car == null)
				return null;
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

			if (customer == null)
				return null;
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

	public static Customer getCustomerDetails(String id) {
		PreparedStatement stm;
		ResultSet res;
		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.customer where id = ?");
			stm.setString(1, id);
			res = stm.executeQuery();
			if (res.next()) {
				Customer customer = new Customer(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9),
						res.getInt(10), res.getInt(11), res.getInt(12), res.getInt(13), res.getString(14),
						res.getString(15), res.getString(16));
				return customer;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return null;
	}

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

	public static ArrayList<Sale> getAllSales() {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Sale> sales = new ArrayList<Sale>();
		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.sale");
			res = stm.executeQuery();
			while (res.next() == true) {
				Sale sale = new Sale(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getFloat(6), res.getString(7), res.getString(8), res.getString(9),
						res.getString(10), res.getString(11));
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

	public static boolean addNewSaleTemp(Sale sale) {
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement(
					"insert into myfueldb.sale (status,companyName,fuelType,purchaseModule,salePercent,startTime,endTime,startDate,endDate,days) "
							+ "values (?,?,?,?,?,?,?,?,?,?)");
			stm.setString(1, sale.getStatus().toString());
			stm.setString(2, sale.getCompanyName());
			stm.setString(3, sale.getFuelType());
			stm.setString(4, sale.getPurchaseModule());
			stm.setFloat(5, sale.getSalePercent());
			stm.setString(6, sale.getStartTime());
			stm.setString(7, sale.getEndTime());
			stm.setString(8, sale.getStartDate());
			stm.setString(9, sale.getEndDate());
			stm.setString(10, sale.getSaleDays());
			stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}