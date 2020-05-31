package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.crypto.KeySelector.Purpose;

import Entity.Car;
import Entity.Customer;
import Entity.CustomerModule;
import Entity.FuelPurchase;
import Entity.PricingModule;

public class FastFuelController {

	/**
	 * 
	 * @param carNumber
	 * @return ArrayList of Objects containig  objects car,customer,cutomer model,purchase model
	 */
	public static Car fastFuelingLogIn(String carNumber) {
//		ArrayList<Object> results = new ArrayList<Object>();
		
		//adding car to the result
		return EmployeeController.getCarByNumber(carNumber);
		//results.add(car);
		/*
		//adding customer to the result
		Customer customer=EmployeeController.getCutomerByCarNumber(car.getCarCustomerId());
		if(customer==null)return null;
		results.add(customer);
		//Must he have puchase model ??,adding CustomerModule to the result
		System.out.println("at FastFuelController line 32,Must he have puchase model ??");
		CustomerModule customerModule = getCutomerModel(car.getCarCustomerId());
		if(customer==null)return null;
		results.add(customerModule);
		
		return results;
		*/
		
	}
	/**
	 * calculate the current price of the company fuel and the 
	 * customer price according to his subsection,and the current sale
	 * also return percent of sale,percent of rate, and the current sales id##
	 * the calculation are done by other functions 
	 * @param companyName String
	 * @param model CustomerModule
	 * @param prcingModelNumber int
	 * @param amount float
	 * @return
	 */
	public static ArrayList<Float> priceCalculationAndPricingModel(String companyName,CustomerModule model,int prcingModelNumber) {
		//the ArrayList contains purchasePrice,currentPrice,SalePercent
		ArrayList<Float> result= new ArrayList<Float>();
		
		PricingModule rate=getPricingModule(companyName,prcingModelNumber);
		//get the current price of company
		
		//call method that calculate the price for the customer
		
		result.add(10.2f);//purchasePrice
		result.add(15.3f);//currentPrice
		result.add(0.1f);//SalePercent
		result.add(0.04f);//RatePercent
		result.add(1f);//Sale ID cloud be many
		
		return result;
	}
	
	/**
	 * call payment method and save the purchase details
	 * @param customerId String
	 * @param paymentOption if visa must contain visa number if cash "cash"
	 * @param purchase FuelPurchase
	 * @return
	 */
	public static boolean commitFuelPurchase(String customerId,String paymentOption,FuelPurchase purchase) {
		//call paymentMethod
		if(payment(customerId, paymentOption,purchase.getPriceOfPurchase()))
		//save purchase details
		return savePurchaseDetails(purchase);
		return false;
	}
	
	/**
	 * gets customer pricing model number and purchase model
	 * @param customerId
	 * @return
	 */
	public static CustomerModule getCutomerModel(String customerId) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<CustomerModule> customerModule;
		String query="Select * " + 
				"From myfueldb.customermodule as cusm " + 
				"where cusm.CustomerID = ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, customerId);
			res=stm.executeQuery();
			//create car object
			customerModule=BuildObjectByQueryData.BuildCustomerModule(res);
			
			stm.close();
			
			if(customerModule==null)return null;
			return customerModule.get(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * get company pricing percent according to modelNumber
	 * @param company
	 * @param modelNumber
	 * @return
	 */
	public static PricingModule getPricingModule(String company,int modelNumber) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<PricingModule> pricingModule;
		String query="Select * " + 
				"From myfueldb.pricingmodule as price " + 
				"where price.company = ? and " + 
				"price.modelNumber=?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, company);
			stm.setInt(2, modelNumber);
			res=stm.executeQuery();
			//create car object
			pricingModule=BuildObjectByQueryData.BuildPricingModule(res);
			
			stm.close();
			
			if(pricingModule==null)return null;
			return pricingModule.get(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * get all company stations ID
	 * @param company
	 * @return
	 */
	public static ArrayList<Integer> getAllCompanyFuelStationID(String company) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Integer> CompanyFuelStationsID = new ArrayList<Integer>();
		String query="Select stationId " + 
				"From myfueldb.gasstation as station " + 
				"where station.companyName = ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, company);
			res=stm.executeQuery();
			//
			while(res.next())
				CompanyFuelStationsID.add(res.getInt(1));
			
			res.close();
			stm.close();
			
			if(CompanyFuelStationsID.isEmpty())return null;
			return CompanyFuelStationsID;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * save Purchase Details
	 * @param carNumber String
	 * @param customerID String
	 * @param amont float
	 * @param purchasePrice float
	 * @param Date String
	 * @param time String
	 * @return in case of succed return true else false
	 */
	private static boolean savePurchaseDetails(FuelPurchase purchase) {
		PreparedStatement stm;
		
		String query="insert into myfueldb.fuelpurchase " + 
				"(stationId,CarNumber,fuelQuantity," + 
				"priceOfPurchase,time,date,saleID,currentPrice) " + 
				"values (?,?,?,?,?,?,?,?)";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			//get the current price and set it in the purchase
			return SetQueryByObject.SetFuelPurchaseTable(stm,purchase);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean payment(String customerId,String paymentOption,float priceOfPurchase) {
		return true;
	}
	
}
