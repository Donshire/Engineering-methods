package server;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.xml.crypto.KeySelector.Purpose;

import Entity.Car;
import Entity.Customer;
import Entity.CustomerModule;
import Entity.FuelPurchase;
import Entity.PricingModule;
import Entity.Sale;
import client.EmployeeCC;
import enums.SaleStatus;

public class FastFuelController {

	/**
	 * 
	 * @param carNumber
	 * @return ArrayList of Objects containig objects car,customer,cutomer purchase
	 *         model
	 */
	public static ArrayList<Object> fastFuelingLogIn(String carNumber) {

		ArrayList<Object> results = new ArrayList<Object>();

		// adding car to the result
		Car car = EmployeeController.getCarByNumber(carNumber);
		if (car == null)
			return null;
		results.add(car);

		// adding customer to the result
		Customer customer = EmployeeController.getCutomerByCustomerID(car.getCarCustomerId());
		if (customer == null)
			return null;
		results.add(customer);
		// Must he have puchase model ??,adding CustomerModule to the result
		System.out.println("at FastFuelController line 32,Must he have puchase model ??");
		CustomerModule customerModule = getCutomerModel(car.getCarCustomerId());
		if (customer == null)
			return null;
		results.add(customerModule);

		return results;

	}

	/**
	 * calculate the current price of the company fuel and the customer price
	 * according to his subsection,and the current sale also return percent of
	 * sale,percent of rate, and the current sales id## the calculation are done by
	 * other functions
	 * 
	 * @param companyName       String
	 * @param model             CustomerModule
	 * @param prcingModelNumber int
	 * @param amount            float
	 * @return purchasePrice,SalePercent,RatePercent,Sale ID,pumpChoosen.
	 */
	public static ArrayList<Float> priceCalculationAndPricingModel(String companyName, String customerID,
			String fuelType, int prcingModelNumber, int pumpNum, String date, String time) {
		// the ArrayList contains purchasePrice,currentPrice,SalePercent
		ArrayList<Float> result = new ArrayList<Float>();

		PricingModule rate = getPricingModule(companyName, prcingModelNumber);
		if (rate == null) {
			System.out.println("rate has not been created");
			return null;
		}
		// calculate the rate according to the Pricing Model Number
		Float salePercentOfRate = getVAlueOfRate(rate, customerID, prcingModelNumber, companyName);
		if (salePercentOfRate == null)
			return null;
		//
		Sale sale = getCurentsSale(companyName, fuelType, date, time);
		float maxPrice = getMaxPrice(companyName, fuelType);

		DecimalFormat df = new DecimalFormat("0.00");
		float purchasePrice = maxPrice, salePercent = 0, saleID = 0;
		// user must have pricing model
		purchasePrice = purchasePrice * (salePercentOfRate);
		if (sale != null) {
			purchasePrice = purchasePrice * (1 - sale.getSalePercent());
			salePercent = sale.getSalePercent();
			saleID = sale.getSaleID();
		}

		result.add(roundTwoDecimals(purchasePrice));// purchasePrice
		result.add(maxPrice);// currentPrice
		result.add(salePercent);// SalePercent
		result.add(roundThreeDecimals(1 - salePercentOfRate));// RatePercent
		result.add(saleID);// Sale ID
		result.add((float) pumpNum);// pumpChoosen

		return result;
	}

	/**
	 * round float and get the first two decimals
	 * 
	 * @param d
	 * @return
	 */
	public static float roundTwoDecimals(float d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Float.valueOf(twoDForm.format(d));
	}

	/**
	 * round float and get the first three decimals
	 * 
	 * @param d
	 * @return
	 */
	public static float roundThreeDecimals(float d) {
		DecimalFormat twoDForm = new DecimalFormat("#.###");
		return Float.valueOf(twoDForm.format(d));
	}

	/**
	 * Calculate sale percent for each purchase model (models number start from
	 * 0):<br>
	 * the first model return 0 always.<br>
	 * the second model return the value of rate percent always.<br>
	 * the third model return the value of (1-rate percent)*(second model rate *
	 * carCount). <br>
	 * the fourth model return the value of (1-rate percent)*(third model rate(for
	 * just one car)). <br>
	 * 
	 * @param rate
	 * @param customerID
	 * @param prcingModelNumber
	 * @param companyName
	 * @return null if any rate is missing from company db.
	 */
	public static Float getVAlueOfRate(PricingModule rate, String customerID, int prcingModelNumber,
			String companyName) {

		Float returnSalePercent = 1f;

		switch (prcingModelNumber) {

		case 0:
			break;

		case 1:
			returnSalePercent = 1 - Float.valueOf(rate.getSalePercent());
			break;

		case 2:
			returnSalePercent = calculateRate2(rate, customerID, companyName);
			break;

		case 3:
			PricingModule secRate = getPricingModule(companyName, 1);
			if (secRate == null) {
				System.out.println("rate has not been created");
				return null;
			}
			returnSalePercent = (calculateRate2(secRate, customerID, companyName)) * (1 - Float.valueOf(rate.getSalePercent()));
			break;
		}
		return returnSalePercent;
	}

	private static Float calculateRate2(PricingModule rate, String customerID, String companyName) {
		int carCount = customerCarsNumber(customerID);
		PricingModule secRate = getPricingModule(companyName, 1);
		if (secRate == null) {
			System.out.println("rate has not been created");
			return null;
		}
		// (1-rate1*carCount)*(1-rate2)
		return (1 - carCount * Float.valueOf(secRate.getSalePercent())) * (1 - Float.valueOf(rate.getSalePercent()));
	}

	/**
	 * get customer cars number
	 * 
	 * @param customerID
	 * @return
	 */
	public static int customerCarsNumber(String customerID) {
		PreparedStatement stm;
		ResultSet res;
		int result = 0;
		String query = "select count(*)\r\n" + "from myfueldb.car\r\n" + "where car.CustomerID= ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, customerID);

			res = stm.executeQuery();
			if (res.next())
				result = res.getInt(1);

			res.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * get the max price of fuel in company
	 * 
	 * @param companyName
	 * @param fuelType
	 * @return
	 */
	public static float getMaxPrice(String companyName, String fuelType) {
		PreparedStatement stm;
		ResultSet res;
		float result = 0;
		String query = "Select maxPrice " + "from myfueldb.company " + "where companyName = ? and fuelType = ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, companyName);
			stm.setString(2, fuelType);

			res = stm.executeQuery();
			if (res.next())
				result = res.getFloat(1);

			res.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * get active sale in the current time if there is more than one <br>
	 * return the max sale percent
	 * 
	 * @param companyName
	 * @param fuelType
	 * @param dateS       in the formate of yyyy-MM-dd
	 * @param time        in the fornmate of HH:mm:ss
	 * @return
	 */
	public static Sale getCurentsSale(String companyName, String fuelType, String dateS, String time) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Sale> sales = null;
		String query = "Select * From myfueldb.sale " + "where companyName = ? And fueltype = ? "
				+ "and status = ? AND startTime<=? and endTime>=? " + "and startDate<=? and endDate >= ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, companyName);
			stm.setString(2, fuelType);
			stm.setString(3, SaleStatus.activated.toString());
			stm.setString(4, time);
			stm.setString(5, time);
			stm.setString(6, dateS);
			stm.setString(7, dateS);

			res = stm.executeQuery();
			sales = BuildObjectByQueryData.BuildSale(res);

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (sales == null || sales.isEmpty())
			return null;
		// check the days
		Date date;
		String dayOfWeek;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateS);
			dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		Set<String> days = new HashSet<String>();
		
		for (int i=0; i<sales.size();i++) {
			days = BuildObjectByQueryData.converStringToSet(sales.get(i).getSaleDays());
			if (!days.contains(dayOfWeek.toUpperCase()))
				if(!days.contains("ALL")) {
				   sales.remove(sales.get(i));
				   i--;
				}
		}

		// get the max sale percent
		int i, maxSaleIndex = 0;
		float maxSale = 0;
		for (i = 0; i < sales.size(); i++)
			if (sales.get(i).getSalePercent() > maxSale) {
				maxSale = sales.get(i).getSalePercent();
				maxSaleIndex = i;
			}
		
		if(sales.isEmpty())return null;
		return sales.get(maxSaleIndex);
	}

	/**
	 * call payment method and save the purchase details, there is no need to call
	 * the payment method in <br>
	 * Pricing model number 3, because the payment is only once a month. check
	 * station fuel quantity, if there is no enough.<br>
	 * it will return the max amount for purchase <br>
	 * else it will updates the station fuel and check for a need for gas Station
	 * Order
	 * 
	 * @param customerId    String
	 * @param paymentOption if visa must contain visa number if cash "cash"
	 * @param purchase      FuelPurchase
	 * @return -1 if succeded -2 if un-succeded
	 */
	public static int commitFuelPurchase(String customerId, int pricingModelNumber, String paymentOption,
			FuelPurchase purchase, String fuelType) {
		// check if station contain the amount needed
		float amountInStation = GasStationControllerServer.getStationFuelQuantity(purchase.getStationId(), fuelType);
		if (amountInStation < purchase.getFuelQuantity())
			return (int) amountInStation;// there is no enought amount

		// update station Inventory
		amountInStation = amountInStation - purchase.getFuelQuantity();
		GasStationControllerServer.updateFuelQuantity(amountInStation, purchase.getStationId(), fuelType);

		// Check fuel Quantity
		GasStationControllerServer.ReachedMinemumQuantityHandler(purchase.getStationId(), fuelType, amountInStation);

		// in the forth model payment isn't after each purchase at the end of each month
		if (pricingModelNumber == 3) {
			if (savePurchaseDetails(purchase))
				return -1;
			else
				return -2;
		} else {
			// call paymentMethod
			if (payment(customerId, paymentOption, purchase.getPriceOfPurchase(), purchase.getDate()))
				// save purchase details
				if (savePurchaseDetails(purchase))
					return -1;

			return -2;
		}

	}

	/**
	 * gets customer pricing model number and purchase model
	 * 
	 * @param customerId
	 * @return
	 */
	public static CustomerModule getCutomerModel(String customerId) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<CustomerModule> customerModule = null;
		String query = "Select * " + "From myfueldb.customermodule as cusm " + "where cusm.CustomerID = ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, customerId);
			res = stm.executeQuery();
			// create car object
			customerModule = BuildObjectByQueryData.BuildCustomerModule(res);

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (customerModule == null || customerModule.isEmpty())
			return null;
		return customerModule.get(0);
	}

	/**
	 * get company pricing percent according to modelNumber
	 * 
	 * @param company
	 * @param modelNumber
	 * @return
	 */
	public static PricingModule getPricingModule(String company, int modelNumber) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<PricingModule> pricingModule = null;
		String query = "Select * " + "From myfueldb.pricingmodule as price " + "where price.company = ? and "
				+ "price.modelNumber=?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, company);
			stm.setInt(2, modelNumber);
			res = stm.executeQuery();
			// create car object
			pricingModule = BuildObjectByQueryData.BuildPricingModule(res);

			stm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (pricingModule == null || pricingModule.isEmpty())
			return null;
		return pricingModule.get(0);
	}

	/**
	 * get all company stations ID
	 * 
	 * @param company
	 * @return
	 */
	public static ArrayList<Integer> getAllCompanyFuelStationID(String company) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Integer> CompanyFuelStationsID = new ArrayList<Integer>();
		String query = "Select stationId " + "From myfueldb.gasstation as station " + "where station.companyName = ?";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, company);
			res = stm.executeQuery();
			//
			while (res.next())
				CompanyFuelStationsID.add(res.getInt(1));

			res.close();
			stm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (CompanyFuelStationsID.isEmpty())
			return null;
		return CompanyFuelStationsID;
	}

	/**
	 * save Purchase Details
	 * 
	 * @param carNumber     String
	 * @param customerID    String
	 * @param amont         float
	 * @param purchasePrice float
	 * @param Date          String
	 * @param time          String
	 * @return in case of succed return true else false
	 */
	private static boolean savePurchaseDetails(FuelPurchase purchase) {
		PreparedStatement stm;

		String query = "insert into myfueldb.fuelpurchase " + "(stationId,CarNumber,fuelQuantity,"
				+ "priceOfPurchase,time,date,saleID,currentPrice,pricingModel,company,fuelType) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			// get the current price and set it in the purchase
			return SetQueryByObject.SetFuelPurchaseTable(stm, purchase);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * simulating payment in CASH always return true, in visa just chech the <br>
	 * exp date if valid true else false.
	 * 
	 * @param customerId
	 * @param paymentOption
	 * @param priceOfPurchase
	 * @param currentDate
	 * @return
	 */
	public static boolean payment(String customerId, String paymentOption, float priceOfPurchase, String currentDate) {
		Customer customer = EmployeeController.getCutomerByCustomerID(customerId);
		
		String convertedFormate=String.format("%s-%s", currentDate.substring(5, 7),currentDate.substring(0, 4));
		
		if (paymentOption.compareTo("CASH") == 0)
			return true;
		if (customer != null) {
			if (customer.getExpDate().compareTo(convertedFormate) > 0)
				return true;
		}
		return false;
	}

}
