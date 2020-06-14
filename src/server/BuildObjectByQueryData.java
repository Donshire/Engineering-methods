package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Entity.Car;
import Entity.Customer;
import Entity.CustomerModule;
import Entity.Fuel;
import Entity.PricingModule;
import enums.RatesStatus;

public class BuildObjectByQueryData {

	/**
	 * build car object and closes res
	 * 
	 * @param res
	 * @return
	 */
	public static ArrayList<Car> BuildCar(ResultSet res) {
		ArrayList<Car> carsList = new ArrayList<Car>();
		try {
			while (res.next() == true)
				carsList.add(new Car(res.getString(1), res.getString(2), res.getString(3)));
			res.close();
			return carsList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * build Customer object and closes res
	 * 
	 * @param res
	 * @return
	 */
	public static ArrayList<Customer> BuildCustomer(ResultSet res) {
		ArrayList<Customer> customersList = new ArrayList<Customer>();
		try {
			while (res.next() == true)
				customersList.add(new Customer(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9),
						res.getInt(10), res.getInt(11), res.getInt(12), res.getInt(13), res.getString(14)));
			res.close();
			return customersList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * build PricingModule object and closes res
	 * 
	 * @param res
	 * @return
	 */
	public static ArrayList<PricingModule> BuildPricingModule(ResultSet res) {
		ArrayList<PricingModule> purchaseModuleList = new ArrayList<PricingModule>();
		try {
			while (res.next() == true)
				purchaseModuleList.add(new PricingModule(res.getInt(1), res.getFloat(2)));
			res.close();
			return purchaseModuleList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * build CustomerModule object and closes res
	 * 
	 * @param res
	 * @return
	 */
	public static ArrayList<CustomerModule> BuildCustomerModule(ResultSet res) {
		ArrayList<CustomerModule> customerModuleList = new ArrayList<CustomerModule>();
		try {
			while (res.next() == true)
				customerModuleList.add(new CustomerModule(res.getString(1), res.getInt(2),
						converCompaniesToSet(res.getString(3)), res.getString(4), res.getString(5)));
			res.close();
			return customerModuleList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Convert String such as PAZ,YELLOW to SET Containing[PAZ][YELLOW]
	 * 
	 * @param companies
	 * @return
	 */
	private static Set<String> converCompaniesToSet(String companies) {
		Set<String> sets = new HashSet<String>();
		String[] words = companies.split(",");
		for (int i = 0; i < words.length; i++)
			sets.add(words[i]);
		return sets;
	}

	/**
	 * Build Pricing Model Rates according to res
	 * 
	 * @author iamme
	 * @param res
	 * @return arrayList of the object there is three cases 1)ArrayList empty
	 *         2)ArrayList not empty 3)null in case of exeption
	 */
	public static ArrayList<PricingModule> BuildPricingModelRates(ResultSet res) {
		ArrayList<PricingModule> pricingModelRatesList = new ArrayList<PricingModule>();
		try {
			while (res.next() == true)
				pricingModelRatesList.add(new PricingModule(res.getInt(1), res.getFloat(2), res.getString(3),
						RatesStatus.valueOf(res.getString(4))));
			res.close();
			return pricingModelRatesList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Fuel> BuildFuel(ResultSet res) {
		ArrayList<Fuel> maxPriceDetails = new ArrayList<Fuel>();
		try {
			while (res.next() == true)
				maxPriceDetails.add(new Fuel(res.getString(1), res.getDouble(2), res.getString(3)));
			res.close();
			return maxPriceDetails;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public static ArrayList<PricingModule> getBuildRateApprovalDetails(ResultSet res) {
		ArrayList<PricingModule> RateApprovalDetails = new ArrayList<PricingModule>();
		try {
			while (res.next() == true) {
				RateApprovalDetails.add(new PricingModule(res.getInt(1), res.getFloat(2), res.getString(3),
						RatesStatus.valueOf(res.getString(4))));
				System.out.println(RateApprovalDetails);
			}
			res.close();
			return RateApprovalDetails;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	


}
