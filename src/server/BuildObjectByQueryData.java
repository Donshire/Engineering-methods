package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Entity.Car;
import Entity.CompanyFuel;
import Entity.Customer;
import Entity.CustomerModule;
import Entity.GasStationOrder;
import Entity.Employee;
import Entity.GasOrder;
import Entity.GasStation;
import Entity.Fuel;
import Entity.PricingModule;
import Entity.Sale;
import Entity.StationFuel;
import Entity.Supplier;
import enums.OrderStatus;
import enums.RatesStatus;

public class BuildObjectByQueryData {

	/**
	 * build car object and closes res
	 * @author iamme
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
	 * @param res
	 * @return
	 */
	public static ArrayList<Customer> BuildCustomer(ResultSet res) {
		ArrayList<Customer> customersList = new ArrayList<Customer>();
		try {
			while (res.next() == true)
				customersList.add(new Customer(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9),
						res.getInt(10), res.getInt(11), res.getInt(12), res.getInt(13), res.getString(14),
						res.getString(15), res.getString(16), res.getString(17), res.getString(18)));
			res.close();
			return customersList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * build PricingModule object and closes res
	 * @author iamme
	 * @param res
	 * @return
	 */
	public static ArrayList<PricingModule> BuildPricingModule(ResultSet res) {
		ArrayList<PricingModule> purchaseModuleList = new ArrayList<PricingModule>();
		try {
			while (res.next() == true)
				purchaseModuleList.add(new PricingModule(res.getInt(1), Float.valueOf(res.getString(2))));
				res.close();
			return purchaseModuleList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * build CustomerModule object and closes res
	 * @author iamme
	 * @param res
	 * @return
	 */
	public static ArrayList<CustomerModule> BuildCustomerModule(ResultSet res) {
		ArrayList<CustomerModule> customerModuleList = new ArrayList<CustomerModule>();
		try {
			while (res.next() == true)
				customerModuleList.add(new CustomerModule(res.getString(1),
						res.getInt(2), converStringToSet(res.getString(3))));
				res.close();
			return customerModuleList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Convert String such as PAZ,YELLOW to SET Containing[PAZ][YELLOW] 
	 * @author iamme
	 * @param companies
	 * @return
	 */
	public static Set<String> converStringToSet (String str){
		Set<String> sets = new HashSet<String>();
		String[] words=str.split(",");
		for(int i=0;i<words.length;i++)
			sets.add(words[i].toUpperCase());
		return sets;
	}
	
	/**
	 * Build Pricing Model Rates according to res 
	 * @author iamme
	 * @param res
	 * @return arrayList of the object
	 * there is three cases 1)ArrayList empty 2)ArrayList not empty 3)null in case of exeption
	 */
	public static ArrayList<PricingModule> BuildPricingModelRates(ResultSet res){
		ArrayList<PricingModule> pricingModelRatesList = new ArrayList<PricingModule>();
		try {
			while (res.next() == true)
				pricingModelRatesList.add(new PricingModule(res.getInt(1), res.getFloat(2)
						, res.getString(3), RatesStatus.valueOf(res.getString(4))));
				res.close();
			return pricingModelRatesList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * @author iamme
	 * @param res
	 * @return
	 */
	public static ArrayList<Supplier> BuildSupplier(ResultSet res){
		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		try {
			while (res.next() == true)
				supplierList.add(new Supplier(res.getString(1), res.getString(2),
						res.getString(3), res.getString(4), res.getString(5),
						res.getString(6), res.getString(8), res.getInt(9), res.getString(7)));
				res.close();
			return supplierList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Build GasStationOrder Object
	 * @author iamme
	 * @param res
	 * @return
	 */
	public static ArrayList<GasStationOrder> BuildGasStationOrder(ResultSet res){
		ArrayList<GasStationOrder> orders = new ArrayList<GasStationOrder>();
		try {
			while (res.next() == true)
				orders.add(new GasStationOrder(res.getInt(1), res.getString(2), res.getInt(3),
						res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getFloat(8)));
				
				res.close();
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Build Stationfuel object
	 * it uses one of the two constructors according to the cunstructorType<br>
	 * if cunstructorType true uses the constructor without refrence objects<br>
	 * uses the other constructor with refrence objects
	 * @author iamme
	 * @param res
	 * @param cunstructorType
	 * @return
	 */
	public static ArrayList<StationFuel> BuildStationfuel(ResultSet res,boolean cunstructorType){
		ArrayList<StationFuel> stationFuel = new ArrayList<StationFuel>();
		try {
			if(cunstructorType)
			while (res.next() == true)
				stationFuel.add(new StationFuel(res.getString(2),
						res.getInt(1), res.getFloat(3), res.getFloat(4), res.getInt(5)));
				
			else {};//the cunstructor with refrence objects
				res.close();
			return stationFuel;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * Build Employee object
	 * @param res
	 * @return
	 */
	public static ArrayList<Employee> BuildEmployee(ResultSet res){
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			while (res.next() == true)
				employees.add(new Employee(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9),
						res.getInt(10), res.getInt(11), res.getString(12)));
				
				res.close();
			return employees;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Build GasStation object
	 * it uses one of the two constructors according to the cunstructorType<br>
	 * if cunstructorType true uses the constructor without refrence objects<br>
	 * uses the other constructor with refrence objects
	 * @param res
	 * @param cunstructorType
	 * @return
	 */
	public static ArrayList<GasStation> BuildGasStation(ResultSet res,boolean cunstructorType){
		ArrayList<GasStation> gasStation = new ArrayList<GasStation>();
		try {
			if(cunstructorType)
			while (res.next() == true)
				gasStation.add(new GasStation(res.getString(1), res.getInt(2),
						res.getString(3), res.getString(4), res.getInt(5)));
				
			else {};//the cunstructor with refrence objects
				res.close();
			return gasStation;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * build Sale object
	 * @param res
	 * @return
	 */
	public static ArrayList<Sale> BuildSale(ResultSet res){
		ArrayList<Sale> sales = new ArrayList<Sale>();
		try {
			while (res.next() == true)

				sales.add(new Sale(res.getInt(1), res.getString(2), res.getString(4), res.getString(5), res.getFloat(5),
						res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10)));
			res.close();
			return sales;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<GasOrder> BuildGasOrder(ResultSet res){
		ArrayList<GasOrder> sales = new ArrayList<GasOrder>();
		try {
			while (res.next() == true)
				sales.add(new GasOrder(res.getInt(1), res.getString(2), res.getString(3),
						res.getString(4), res.getFloat(5), res.getString(6), res.getFloat(7), res.getBoolean(8), (OrderStatus) res.getObject(9)));
				
				res.close();
			return sales;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static ArrayList<Fuel> BuildFuel(ResultSet res) {
		ArrayList<Fuel> maxPriceDetails = new ArrayList<Fuel>();
		try {
			while (res.next() == true)
				maxPriceDetails.add(new Fuel(res.getString(1), res.getDouble(2)));
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
				RateApprovalDetails.add(new PricingModule(res.getInt(1), Float.valueOf(res.getString(2)), res.getString(3),
						RatesStatus.valueOf(res.getString(4))));
			}
			res.close();
			return RateApprovalDetails;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static ArrayList<CompanyFuel> BuildCompanyFuel(ResultSet res) {
		ArrayList<CompanyFuel> companyFuels = new ArrayList<CompanyFuel>();
		try {
			//no refrence constructor
			while (res.next() == true)
				companyFuels.add(new CompanyFuel(res.getString(1), res.getString(2), res.getFloat(3)));
			res.close();
			return companyFuels;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
