package server;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import Entity.AnaliticDataReport;
import Entity.CompanyFuel;
import Entity.Fuel;
import Entity.GenericReport;
import Entity.PricingModule;
import Entity.Rates;
import Entity.Sale;
import enums.RatesStatus;
import enums.SaleStatus;


/**
 * The Class CompanyFuelControllerServer is a server controller that sends and receives data
 * from and to the database the functions use queries for that purpose.
 */
public class CompanyFuelControllerServer {

	
	/**
	 * create periodicReport and save it in a file.
	 * 
	 * @param companyName String
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param date String
	 * @param time String
	 * @return the file
	 */
	public static File periodicReport(String companyName,String startDate,String endDate,String date, String time) {
		File file = null;
		ArrayList<customerTotalPurchase> customerTotalPurchaseArray = customersTotalPurchases(startDate,endDate);
		ArrayList<customerCompaniesDiversion> customerCompaniesDiversionArray = customerCompaniesDiversion(startDate,endDate);
		ArrayList<rankedcustomer> rankedcustomerArray = new ArrayList<rankedcustomer>();
		
		for(customerCompaniesDiversion cus:customerCompaniesDiversionArray)
			System.out.println(cus.numOfPurchaeseByCompanies+","+customerCompaniesDiversion.calculateRank(cus));
		
		//sort
		Collections.sort(customerTotalPurchaseArray);
		Collections.sort(customerCompaniesDiversionArray);
		
		customerTotalPurchase customerTotalPurchasearray[];
		customerCompaniesDiversion customerCompaniesDiversionarray[];
		
		//Merge
		try {
			//convert customerTotalPurchaseArray to array
			customerTotalPurchasearray=new customerTotalPurchase[customerTotalPurchaseArray.size()];
			customerTotalPurchaseArray.toArray(customerTotalPurchasearray);
			//convert customerTotalPurchaseArray to array
			customerCompaniesDiversionarray=new customerCompaniesDiversion[customerCompaniesDiversionArray.size()];
			customerCompaniesDiversionArray.toArray(customerCompaniesDiversionarray);	
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		int j,i;
		
		for(i=0;i<customerTotalPurchasearray.length;i++) {
			for(j=0;j<customerCompaniesDiversionarray.length;j++) {
				if(customerTotalPurchasearray[i].customerId.compareTo(customerCompaniesDiversionarray[j].customerId)==0)
					break;
			}
			rankedcustomerArray.add(new rankedcustomer(customerTotalPurchasearray[i],customerCompaniesDiversionarray[j],i,j));
		}
		
		//Sort the merged Arrays
		Collections.sort(rankedcustomerArray);
		//Conver to array
		rankedcustomer rankedcustomerarray[]=new rankedcustomer[rankedcustomerArray.size()];
		rankedcustomerArray.toArray(rankedcustomerarray);
		//Write to the result file
		file=FileManagmentSys.createFile(FileManagmentSys.createLocation(companyName,
				FileManagmentSys.marketingManagerReports,FileManagmentSys.periodicReport),
				FileManagmentSys.periodicReport,0,null,null);
		
		if(file==null)return null;
		
		StringBuilder data = new StringBuilder("");
		
		for(i=0;i<rankedcustomerarray.length;i++) {
			data.append(FileManagmentSys.periodicReportFileFormate(rankedcustomerarray[i].companies.customerId,
					customerCompaniesDiversion.getPurchasePercent(rankedcustomerarray[i].companies.numOfPurchaeseByCompanies,
							rankedcustomerarray[i].companies.totalNumOfPurchaese),
					rankedcustomerarray[i].companies.numOfPurchaeseByCompanies.length,
					rankedcustomerarray[i].purchas.totalPurchase));
		}
		
		FileManagmentSys.writeToMarkitingManagerReport(file, data.toString(), FileManagmentSys.periodicReport, 0, 0, getAllCompanies());

		// connecting to the db
		//createGenericReport(new GenericReport(date, time, file.getName(), FileManagmentSys.periodicReport));
		return file;
	}

	/**
	 * Customers total purchases.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the array list
	 */
	public static ArrayList<customerTotalPurchase> customersTotalPurchases(String start,String end) {
		PreparedStatement stm;
		ResultSet res;

		ArrayList<customerTotalPurchase> result = new ArrayList<customerTotalPurchase>();

		try {
			// calculate the totale prices
			String query = "Select cus.id,sum(fu.priceOfPurchase) as countofpurchase "
					+ "from myfueldb.customer as cus " + "left join myfueldb.car as car " + "on car.CustomerID=cus.id "
					+ "left join myfueldb.fuelpurchase as fu " + "on fu.CarNumber=car.carNumber " +
					"where fu.date>=? and  fu.date<=?"+"group by cus.id";
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, start);
			stm.setString(2, end);
			res = stm.executeQuery();

			// save the data in arrayList
			while (res.next())
				result.add(new customerTotalPurchase(res.getString(1), res.getFloat(2)));

			res.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * create an ArrayList containig for each customer purchase diversion by
	 * companies.
	 *
	 * @param start the start
	 * @param end the end
	 * @return that ArrayList
	 */
	public static ArrayList<customerCompaniesDiversion> customerCompaniesDiversion(String start,String end) {
		PreparedStatement stm;
		ResultSet res;

		ArrayList<customerCompaniesDiversion> result = new ArrayList<customerCompaniesDiversion>();
		ArrayList<String> companies = CompanyFuelControllerServer.getAllCompanies();
		String curID;
		int array[] = new int[companies.size()];
		int sumOfPuchases = 0;

		try {
			// calculate the totale prices
			String query = "Select cu.id,ga.companyName,count(ga.companyName) as countofpurchase "
					+ "from myfueldb.customer as cu " + "left join myfueldb.car as ca " + "on cu.id=ca.CustomerID "
					+ "left join myfueldb.fuelpurchase as fu " + "on ca.carNumber=fu.carNumber "
					+ "left join myfueldb.gasstation as ga " + "on ga.stationId=fu.stationId "
					+"where fu.date>=? and  fu.date<=?"+ "GROUP BY cu.id,ga.companyName " + "order by cu.id";
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, start);
			stm.setString(2, end);
			res = stm.executeQuery();

			// save the data in arrayList
			if (res.next()) {
				// to get the first customer Id and compare it to the next one
				curID = res.getString(1);
				array[companies.indexOf(res.getString(2))] = res.getInt(3);
				sumOfPuchases += res.getInt(3);
				// check if the same customer
				while (res.next()) {
					// there is another cutomer save the data in the arraylist and rest all the
					// parameters
					if (res.getString(1).compareTo(curID) != 0) {
						result.add(new customerCompaniesDiversion(curID, sumOfPuchases, array));
						sumOfPuchases = 0;
						array= new int[companies.size()];
					}
					curID = res.getString(1);
					array[companies.indexOf(res.getString(2))] = res.getInt(3);
					sumOfPuchases += res.getInt(3);
				}
				//add the last one 
				result.add(new customerCompaniesDiversion(curID, sumOfPuchases,array));
			}

			res.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Gets the all companies.
	 *
	 * @return all the distinct(companyName) in myfueldb.company
	 */
	public static ArrayList<String> getAllCompanies() {
		Statement stm;
		ResultSet res;
		String query = "Select distinct(companyName) " + "From myfueldb.company";
		ArrayList<String> result = new ArrayList<String>();
		try {
			stm = ConnectionToDB.conn.createStatement();
			res = stm.executeQuery(query);
			while (res.next())
				result.add(res.getString(1));
			res.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * The Class rankedcustomer.
	 */
	private static class rankedcustomer implements Comparable<rankedcustomer> {
		
		/** The purchas. */
		customerTotalPurchase purchas;
		
		/** The companies. */
		customerCompaniesDiversion companies;

		/** The real rank. */
		int customerTotalPurchaseRank, customerCompaniesDiversionRank,realRank;
		
		/**
		 * Instantiates a new rankedcustomer.
		 *
		 * @param purchas customerTotalPurchase
		 * @param companies customerCompaniesDiversion
		 * @param customerTotalPurchaseRank int
		 * @param customerCompaniesDiversionRank int
		 */
		public rankedcustomer(customerTotalPurchase purchas,customerCompaniesDiversion companies,
				int customerTotalPurchaseRank,
				int customerCompaniesDiversionRank) {
			this.purchas = purchas;
			this.companies = companies;
			this.customerTotalPurchaseRank = customerTotalPurchaseRank;
			this.customerCompaniesDiversionRank = customerCompaniesDiversionRank;
			this.realRank=customerCompaniesDiversionRank + customerTotalPurchaseRank;
		}

		/**
		 * Compare to.
		 *
		 * @param o the o
		 * @return the int
		 */
		@Override
		public int compareTo(rankedcustomer o) {
			if (this.realRank > o.realRank)
				return 1;
			if (this.realRank < o.realRank)
				return -1;
			return 0;
		}

	}

	/**
	 * The Class customerTotalPurchase.
	 */
	private static class customerTotalPurchase implements Comparable<customerTotalPurchase> {
		
		/** The customer id. */
		String customerId;
		
		/** The total purchase. */
		float totalPurchase;

		/**
		 * Instantiates a new customer total purchase.
		 *
		 * @param customerId the customer id
		 * @param totalPurchase the total purchase
		 */
		public customerTotalPurchase(String customerId, float totalPurchase) {
			this.customerId = customerId;
			this.totalPurchase = totalPurchase;
		}

		/**
		 * Compare to.
		 *
		 * @param o the o
		 * @return the int
		 */
		@Override
		public int compareTo(customerTotalPurchase o) {
			if (this.totalPurchase > o.totalPurchase)
				return -1;
			if (this.totalPurchase < o.totalPurchase)
				return 1;
			return 0;
		}

		/**
		 * To string.
		 *
		 * @return the string
		 */
		@Override
		public String toString() {
			return "customerTotalPurchase [customerId=" + customerId + ", totalPurchase=" + totalPurchase + "]";
		}

	}

	/**
	 * The Class customerCompaniesDiversion.
	 */
	private static class customerCompaniesDiversion implements Comparable<customerCompaniesDiversion> {
		
		/** The customer id. */
		String customerId;
		
		/** The total num of purchaese. */
		int totalNumOfPurchaese;
		
		/** The num of purchaese by companies. */
		int numOfPurchaeseByCompanies[];
		
		/**
		 * Instantiates a new customer companies diversion.
		 *
		 * @param customerId the customer id
		 * @param totalNumOfPurchaese the total num of purchaese
		 * @param numOfPurchaeseByCompanies the num of purchaese by companies
		 */
		public customerCompaniesDiversion(String customerId, int totalNumOfPurchaese, int[] numOfPurchaeseByCompanies) {
			this.customerId = customerId;
			this.totalNumOfPurchaese = totalNumOfPurchaese;
			this.numOfPurchaeseByCompanies = numOfPurchaeseByCompanies;
		}

		/**
		 * Gets the purchase percent.
		 *
		 * @param purchases the purchases
		 * @param numOfPurchaese the num of purchaese
		 * @return the purchase percent
		 */
		public static float[] getPurchasePercent(int []purchases,int numOfPurchaese) {
			float[] res = new float[purchases.length];
			for(int i=0;i<purchases.length;i++)
				res[i]=(purchases[i]/(float)numOfPurchaese)*100;
			return res;
		}
		
		/**
		 * Calculate rank.
		 *
		 * @param o the o
		 * @return the int
		 */
		// The ALG
		private static int calculateRank(customerCompaniesDiversion o) {
			int numOfCompanies = o.numOfPurchaeseByCompanies.length;
			float average = o.totalNumOfPurchaese / numOfCompanies;
			int rank = 0;
			for (int i = 0; i < o.numOfPurchaeseByCompanies.length; i++)
				rank += Math.abs((average - o.numOfPurchaeseByCompanies[i]) * numOfCompanies);
			return rank;
		}

		/**
		 * Compare to.
		 *
		 * @param o the o
		 * @return the int
		 */
		@Override
		public int compareTo(customerCompaniesDiversion o) {
			if (calculateRank(this) > calculateRank(o))
				return 1;
			if (calculateRank(this) < calculateRank(o))
				return -1;
			return 0;
		}

		/**
		 * To string.
		 *
		 * @return the string
		 */
		@Override
		public String toString() {
			return "customerCompaniesDiversion [customerId=" + customerId + ", totalNumOfPurchaese="
					+ totalNumOfPurchaese + ", numOfPurchaeseByCompanies=" + Arrays.toString(numOfPurchaeseByCompanies)
					+ "]";
		}
	}

	/**
	 * Response report.
	 *
	 * @param saleid      int
	 * @param companyName String
	 * @param date        String
	 * @param time        String
	 * @return the file
	 */
	public static File responseReport(int saleid, String companyName, String date, String time) {
		PreparedStatement stm;
		ResultSet res;

		File file = null;

		StringBuilder strBRes3 = new StringBuilder();
		String strRes1, strRes2;

		try {

			// price count
			stm = ConnectionToDB.conn
					.prepareStatement("SELECT SUM(priceOfPurchase)" + " FROM fuelpurchase " + "where saleID = ?");
			stm.setInt(1, saleid);
			res = stm.executeQuery();

			res.next();
			strRes2 = res.getString(1);
//			System.out.println("SUM(priceOfPurchase)");
//			while (res1.next()) {
//				System.out.println(res1.getString(1));
//			}

			// number of customers
			stm = ConnectionToDB.conn.prepareStatement("SELECT COUNT(DISTINCT customerID) " + "FROM myfueldb.car as c "
					+ "LEFT JOIN myfueldb.fuelpurchase as f " + "ON c.carNumber = f.CarNumber " + "where saleID = ?");
			stm.setInt(1, saleid);
			res = stm.executeQuery();

			res.next();
			strRes1 = res.getString(1);
//			while (res2.next()) {
//				System.out.println(res2.getString(1));
//			}

			// for each customer total purchases
			String query3 = "SELECT customerID,SUM(priceOfPurchase) " + "FROM myfueldb.car as c "
					+ "LEFT JOIN myfueldb.fuelpurchase as f " + "ON c.carNumber = f.CarNumber " + "WHERE saleID = ? "
					+ "GROUP BY customerID";
			stm = ConnectionToDB.conn.prepareStatement(query3);
			stm.setInt(1, saleid);
			res = stm.executeQuery();

			res.next();
			while (res.next())
				strBRes3.append(FileManagmentSys.responseReportFileFormate(res.getString(1), res.getString(2)));

			if(strBRes3.toString().isEmpty())return null;
			// creating the file and saving it in the server system
			file = FileManagmentSys.createFile(FileManagmentSys.createLocation(companyName,
					FileManagmentSys.marketingManagerReports, FileManagmentSys.responseReport),
					FileManagmentSys.responseReport, 0,null,null);
			//
			if(file==null)return null;
			// fill the file with the details
			FileManagmentSys.writeToMarkitingManagerReport(file, strBRes3.toString(), FileManagmentSys.responseReport,
					Integer.valueOf(strRes1), Float.valueOf(strRes2), null);

			// connecting to the db
			//createGenericReport(new GenericReport(date, time, file.getName(), FileManagmentSys.responseReport));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;

	}

	/**
	 * Creates the generic report.
	 *
	 * @param report the report
	 * @return true, if successful
	 */
	public static boolean createGenericReport(GenericReport report) {
		String query = "insert into genericreport (year,quarter,Filename,reportType,companyname,stationId) " + "values (?,?,?,?,?,?)";
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, report.getYear());
			stm.setString(2, report.getQuarter());
			stm.setString(3, report.getFileName());
			stm.setString(4, report.getReportType());
			stm.setString(5,report.getCompanyName());
			stm.setInt(6, report.getStationId());
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Gets the company fuel.
	 *
	 * @param companyName the company name
	 * @param fuelType the fuel type
	 * @return the company fuel
	 */
	public static Object getCompanyFuel(String companyName, String fuelType) {

		PreparedStatement stm;
		ResultSet res, res2;
		CompanyFuel companyFuel = null;

		try {

			stm = ConnectionToDB.conn
					.prepareStatement("select currentPrice from company where companyName = ? " + "And fuelType = ? ");
			stm.setString(1, companyName);
			stm.setString(2, fuelType);
			res = stm.executeQuery();

			res.next();
			stm = ConnectionToDB.conn.prepareStatement("select maxPrice from fuel where fuelType = ? ");
			stm.setString(1, fuelType);
			res2 = stm.executeQuery();

			res2.next();
			Fuel f = new Fuel(fuelType, res2.getFloat(1));

			companyFuel = new CompanyFuel(companyName, f, res.getFloat(1));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return companyFuel;
	}

	/**
	 * Gets the all company fuel types.
	 *
	 * @param companyName the company name
	 * @return the all company fuel types
	 */
	public static ArrayList<CompanyFuel> getAllCompanyFuelTypes(String companyName) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<CompanyFuel> fuels =null;
		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from company where companyName = ? ");
			stm.setString(1, companyName);
			res = stm.executeQuery();
			
			fuels=BuildObjectByQueryData.BuildCompanyFuel(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(fuels==null||fuels.isEmpty())return null;
		return fuels;
	}

	/**
	 * Update pricing model status.
	 *
	 * @param pricingModule the pricing module
	 * @return true, if successful
	 */
	public static boolean updatePricingModelStatus(PricingModule pricingModule) {

		PreparedStatement stm;

		switch (pricingModule.getStatus()) {

		// ceo chage rate status to confirmed
		case confirmed:

			try {
				stm = ConnectionToDB.conn.prepareStatement("update myfueldb.pricingmodule set status = ? "+
			"where modelNumber = ? and company = ? and salePercent = ?");
				stm.setString(1, pricingModule.getStatus().toString());
				//Key
				stm.setInt(2, pricingModule.getModelNumber());
				stm.setString(3, pricingModule.getCompanyName());
				stm.setString(4, pricingModule.getSalePercent());
				
				stm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		// marketing emp update after confirmd
		case active:
			try {
				// delete the old rate
				stm = ConnectionToDB.conn
						.prepareStatement("DELETE FROM myfueldb.pricingmodule WHERE status = ? "+
				"AND company = ? AND modelNumber = ?");
				stm.setString(1, RatesStatus.active.toString());
				stm.setString(2, pricingModule.getCompanyName());
				stm.setInt(3, pricingModule.getModelNumber());
				stm.executeUpdate();

				// update the new rate status
				stm = ConnectionToDB.conn.prepareStatement("update myfueldb.pricingmodule set status=? "+
				"where modelNumber = ? and company = ? and salePercent = ?");
				stm.setString(1, pricingModule.getStatus().toString());
				//Key
				stm.setInt(2, pricingModule.getModelNumber());
				stm.setString(3, pricingModule.getCompanyName());
				stm.setString(4, pricingModule.getSalePercent());
				
				stm.executeUpdate();

				// update the current price in the company
//				stm = ConnectionToDB.conn.prepareStatement(
//						"update myfueldb.company set currentPrice=? where companyName = ? AND fuelType = ?");
//				stm.setString(1, String.valueOf(rate.getFuel().getMaxPrice() + rate.getRateValue()));
//				stm.setString(2, rate.getCompanyName());
//				stm.setString(3, rate.getFuelType());
//				stm.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

			break;
		}

		return true;

	}

	/**
	 * Gets the all company rates by status.
	 *
	 * @param companyName the company name
	 * @param status the status
	 * @return the all company rates by status
	 */
	public static ArrayList<PricingModule> getAllCompanyRatesByStatus(String companyName,RatesStatus status) {

		PreparedStatement stm;
		ResultSet res;

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.pricingmodule where company = ? and status = ?");
			stm.setString(1, companyName);
			stm.setString(2, status.toString());
			res = stm.executeQuery();

			return BuildObjectByQueryData.BuildPricingModelRates(res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets the company active pricing rate.
	 *
	 * @param companyName the company name
	 * @param modelNumber the model number
	 * @return the company active pricing rate
	 */
	public static PricingModule getCompanyActivePricingRate(String companyName,int modelNumber) {

		PreparedStatement stm;
		ResultSet res;

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.pricingmodule where company = ? and modelNumber = ? and status = ?");
			stm.setString(1, companyName);
			stm.setInt(2, modelNumber);
			stm.setString(3, RatesStatus.active.toString());
			res = stm.executeQuery();

			ArrayList<PricingModule> result=BuildObjectByQueryData.BuildPricingModelRates(res);
			if(result.isEmpty())return null;
			else return result.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Save pricing model.
	 *
	 * @param pricingModel the pricing model
	 * @return true, if successful
	 */
	public static boolean savePricingModel(PricingModule pricingModel) {
		String query = "insert into pricingmodule (modelNumber,salePercent,company,status) " + "values (?,?,?,?)";
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setInt(1, pricingModel.getModelNumber());
			stm.setString(2, pricingModel.getSalePercent());
			stm.setString(3, pricingModel.getCompanyName());
			stm.setString(4, pricingModel.getStatus().toString());
			
			stm.executeUpdate();
			stm.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Update sale.
	 *
	 * @param sale the sale
	 * @return true, if successful
	 */
	public static boolean updateSale(Sale sale) {

		PreparedStatement stm;

		try {
			stm = ConnectionToDB.conn.prepareStatement(
					"UPDATE myfueldb.sale set status=?,companyName=?,fueltype=?,salePercent=?,startTime=?"
							+ ",endTime=?,startDate=?,endDate=?,days=?" + " WHERE saleID = ?");
			stm.setString(1, sale.getStatus().toString());
			stm.setString(2, sale.getCompanyName());
			stm.setString(3, sale.getFuelType());
			stm.setFloat(4, sale.getSalePercent());
			stm.setString(5, sale.getStartTime());
			stm.setString(6, sale.getEndTime());
			stm.setString(7, sale.getStartDate());
			stm.setString(8, sale.getEndDate());
			stm.setString (9, sale.getSaleDays());
			stm.setInt(10, sale.getSaleID());

			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Adds the new sale.
	 *
	 * @param sale the sale
	 * @return the object
	 */
	public static Object addNewSale(Sale sale) {

		PreparedStatement stm;

		try {

			stm = ConnectionToDB.conn.prepareStatement(
					"INSERT INTO myfueldb.rates (saleID,status,companyName,fueltype,purchaseModule,salePercent,startTime,"
							+ "endTime,startDate,endDate,days)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			stm.setInt(1, sale.getSaleID());
			stm.setString(2, sale.getStatus().toString());
			stm.setString(3, sale.getCompanyName());
			stm.setString(4, sale.getFuelType());
			stm.setFloat(5, sale.getSalePercent());
			stm.setString(6, sale.getStartTime());
			stm.setString(7, sale.getEndTime());
			stm.setString(8, sale.getStartDate());
			stm.setString(9, sale.getEndDate());
			stm.setString(10, sale.getSaleDays());

			stm.executeUpdate();

			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Delete sale.
	 *
	 * @param sale the sale
	 * @return the object
	 */
	public static Object deleteSale(Sale sale) {

		PreparedStatement stm;

		try {
			stm = ConnectionToDB.conn.prepareStatement("DELETE FROM myfueldb.sale WHERE where saleID = ?");
			stm.setInt(1, sale.getSaleID());
			stm.executeQuery();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}


	/**
	 * Gets the all company sales by status.
	 *
	 * @param companyName the company name
	 * @param status the status
	 * @return the all company sales by status
	 */
	public static ArrayList<Sale> getAllCompanySalesByStatus(String companyName, SaleStatus status) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Sale> sales = new ArrayList<Sale>();

		try {

			stm = ConnectionToDB.conn
					.prepareStatement("select * from myfueldb.sale where status = ? and companyName = ?");
			stm.setString(1, status.toString());
			stm.setString(2, companyName);
			res = stm.executeQuery();

			while (res.next() == true) {

				Sale sale = new Sale(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getFloat(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9),
						res.getString(10));
				sales.add(sale);

			}
			res.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sales;

	}

	/**
	 * a function to get the current price for the gas of the CEO companies.
	 *
	 * @param companyName is the name of the company CEO runs
	 * @return returns an arraylist of the details of the gas for the ceo's company
	 */
	public static ArrayList<CompanyFuel> getAllFuelMaxPriceDetails(String companyName) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<CompanyFuel> companyFuel = null;

		try {

			stm = ConnectionToDB.conn.prepareStatement("SELECT * FROM myfueldb.company where companyName = ?");

			stm.setString(1, companyName);
			res = stm.executeQuery();

			companyFuel = BuildObjectByQueryData.BuildCompanyFuel(res);

			res.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(companyFuel==null||companyFuel.isEmpty())return null;
		return companyFuel;

	}
	
	/**
	 * this function updates the max price of the chosen gas after update is pressed.
	 *
	 * @param list with the details
	 * @return true, if successful
	 */
	public static boolean updateAllFuelMaxPriceDetails(ArrayList<CompanyFuel> list) {
		PreparedStatement stm;

		try {
			stm = ConnectionToDB.conn.prepareStatement(
					"UPDATE myfueldb.company set maxPrice=? " + " WHERE companyName = ? and fuelType = ?");
			for (CompanyFuel fuellist : list) {
				stm.setDouble(1, Double.valueOf(fuellist.getNewMaxPrice()));
				stm.setString(2, fuellist.getCompanyName());
				stm.setString(3, fuellist.getFuelType());
				stm.executeUpdate();
			}
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	/**
	 * this function is for the list that is selected by the ceo for those to approve.
	 *
	 * @param list the selected rates to be rejected
	 * @return returns true if change or false if not
	 */
	public static boolean checkBoxRateApproval(ArrayList<PricingModule> list) {
		PreparedStatement stm;

		try {

			stm = ConnectionToDB.conn.prepareStatement(
					"UPDATE myfueldb.PricingModule set status = ? " + " WHERE company = ? and modelNumber = ? and salePercent = ?");
			for (PricingModule fuellist : list) {

				stm.setString(1, String.valueOf(RatesStatus.confirmed.toString()));
				stm.setString(2, fuellist.getCompanyName());
				stm.setInt(3, fuellist.getModelNumber());
				stm.setString(4, fuellist.getSalePercent());
				
				stm.executeUpdate();

			}
			stm.close();
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

		return true;

	}

	/**
	 * this function is for the list that is selected by the ceo for those to reject.
	 *
	 * @param list the selected rates to be rejected
	 * @return returns true or false
	 */
	public static boolean checkBoxRateReject(ArrayList<PricingModule> list) {
		PreparedStatement stm;

		try {

			stm = ConnectionToDB.conn.prepareStatement(
					"UPDATE myfueldb.PricingModule set status = ? " + " WHERE company = ? and modelNumber = ? and salePercent = ?");
			for (PricingModule fuellist : list) {

				stm.setString(1, String.valueOf(RatesStatus.rejected.toString()));
				stm.setString(2, fuellist.getCompanyName());
				stm.setInt(3, fuellist.getModelNumber());
				stm.setString(4, fuellist.getSalePercent());
				
				stm.executeUpdate();

			}
			stm.close();
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

		return true;

	}
	
	/**
	 * this function gets the rates from the database to show the CEO
	 * the function returns an arraylist with all the details of the ceo companies
	 * the details return are those with "created" status.
	 *
	 * @param companyName the ceo company name
	 * @return an array list with the details
	 */
	public static ArrayList<PricingModule> getBuildRateApprovalDetails(String companyName) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<PricingModule> BuildRateApproval = null;

		try {

			stm = ConnectionToDB.conn
					.prepareStatement("SELECT * FROM myfueldb.pricingmodule where company = ? and status = ?");

			stm.setString(1, companyName);
			stm.setString(2, RatesStatus.created.toString());
			res = stm.executeQuery();
			BuildRateApproval = BuildObjectByQueryData.getBuildRateApprovalDetails(res);
			System.out.println(BuildRateApproval);
			res.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return BuildRateApproval;

	}
	

	/**
	 * Gets the all report by yearand station id.
	 *
	 * @param year the year
	 * @param stationID the station ID
	 * @return the all report by yearand station id
	 */
	public static ArrayList<GenericReport> getAllReportByYearandStationId(String year, int stationID) {

		ArrayList<GenericReport> reports = new ArrayList<GenericReport>();
		PreparedStatement stm;
		ResultSet res;
		GenericReport r;

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from genericreport WHERE year = ? and stationId = ?");
			stm.setString(1, year);
			stm.setInt(2, stationID);
			res = stm.executeQuery();

			while (res.next()) {
				r = new GenericReport(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getInt(6));
				System.out.println(r);
				reports.add(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return reports;
	}
	
	/**
	 * Gets the all analitic data by year and month.
	 *
	 * @param month the month
	 * @param year the year
	 * @param company the company
	 * @return the all analitic data by year and month
	 */
	public static ArrayList<AnaliticDataReport> getAllAnaliticDataByYearAndMonth(String month,String year, String company){
		ArrayList<AnaliticDataReport> reports = new ArrayList<AnaliticDataReport>();
		PreparedStatement stm;
		ResultSet res;
		
		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from analiticdata WHERE month = ? and year = ? and company = ?");
			stm.setString(1, month);
			stm.setString(2,year);
			stm.setString(3,company);
			res = stm.executeQuery();
			
			while(res.next()) {
				reports.add(new AnaliticDataReport(res.getString(1),res.getString(2),res.getString(3),res.getString(4),
						res.getString(5),res.getString(6)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reports;
	}

	
	
	/**
	 * Creates the analitic report.
	 *
	 * @param report the report
	 * @return true, if successful
	 */
	public static boolean createAnaliticReport(AnaliticDataReport report) {
		String query = "insert into analiticdata (filename,week,month,year,company,type) " + "values (?,?,?,?,?,?)";
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, report.getFileName());
			stm.setString(2, report.getWeek());
			stm.setString(3, report.getMonth());
			stm.setString(4, report.getYear());
			stm.setString(5, report.getCompany());
			stm.setString(6, report.getType());
			
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	/**
	 * Gets the analitic file.
	 *
	 * @param fileName the file name
	 * @param company the company
	 * @param type the type
	 * @return the analitic file
	 */
	public static File getAnaliticFile(String fileName,String company,String type) {
		return FileManagmentSys.getFile(FileManagmentSys.createLocation(company, FileManagmentSys.analiticData
				, type), fileName);
	}

}
