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

import Entity.CompanyFuel;
import Entity.Fuel;
import Entity.GenericReport;
import Entity.PricingModule;
import Entity.Rates;
import Entity.Sale;
import enums.RatesStatus;
import enums.SaleStatus;

public class CompanyFuelControllerServer {

	
	/**
	 * create periodicReport and save it in a file
	 * @param companyName String
	 * @param date String
	 * @param time String
	 * @return
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
				FileManagmentSys.periodicReport,0);
		
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
		createGenericReport(new GenericReport(date, time, file.getAbsolutePath(), FileManagmentSys.periodicReport));
		return file;
	}

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
	 * companies
	 * 
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

	private static class rankedcustomer implements Comparable<rankedcustomer> {
		customerTotalPurchase purchas;
		customerCompaniesDiversion companies;

		int customerTotalPurchaseRank, customerCompaniesDiversionRank,realRank;
		/**
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

		@Override
		public int compareTo(rankedcustomer o) {
			if (this.realRank > o.realRank)
				return 1;
			if (this.realRank < o.realRank)
				return -1;
			return 0;
		}

	}

	private static class customerTotalPurchase implements Comparable<customerTotalPurchase> {
		String customerId;
		float totalPurchase;

		public customerTotalPurchase(String customerId, float totalPurchase) {
			this.customerId = customerId;
			this.totalPurchase = totalPurchase;
		}

		@Override
		public int compareTo(customerTotalPurchase o) {
			if (this.totalPurchase > o.totalPurchase)
				return -1;
			if (this.totalPurchase < o.totalPurchase)
				return 1;
			return 0;
		}

		@Override
		public String toString() {
			return "customerTotalPurchase [customerId=" + customerId + ", totalPurchase=" + totalPurchase + "]";
		}

	}

	private static class customerCompaniesDiversion implements Comparable<customerCompaniesDiversion> {
		String customerId;
		int totalNumOfPurchaese;
		int numOfPurchaeseByCompanies[];
		
		public customerCompaniesDiversion(String customerId, int totalNumOfPurchaese, int[] numOfPurchaeseByCompanies) {
			this.customerId = customerId;
			this.totalNumOfPurchaese = totalNumOfPurchaese;
			this.numOfPurchaeseByCompanies = numOfPurchaeseByCompanies;
		}

		public static float[] getPurchasePercent(int []purchases,int numOfPurchaese) {
			float[] res = new float[purchases.length];
			for(int i=0;i<purchases.length;i++)
				res[i]=(purchases[i]/(float)numOfPurchaese)*100;
			return res;
		}
		
		// The ALG
		private static int calculateRank(customerCompaniesDiversion o) {
			int numOfCompanies = o.numOfPurchaeseByCompanies.length;
			float average = o.totalNumOfPurchaese / numOfCompanies;
			int rank = 0;
			for (int i = 0; i < o.numOfPurchaeseByCompanies.length; i++)
				rank += Math.abs((average - o.numOfPurchaeseByCompanies[i]) * numOfCompanies);
			return rank;
		}

		@Override
		public int compareTo(customerCompaniesDiversion o) {
			if (calculateRank(this) > calculateRank(o))
				return 1;
			if (calculateRank(this) < calculateRank(o))
				return -1;
			return 0;
		}

		@Override
		public String toString() {
			return "customerCompaniesDiversion [customerId=" + customerId + ", totalNumOfPurchaese="
					+ totalNumOfPurchaese + ", numOfPurchaeseByCompanies=" + Arrays.toString(numOfPurchaeseByCompanies)
					+ "]";
		}
	}

	/**
	 * 
	 * @param saleid      int
	 * @param companyName String
	 * @param date        String
	 * @param time        String
	 * @return
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
					FileManagmentSys.responseReport, 0);
			// fill the file with the details
			FileManagmentSys.writeToMarkitingManagerReport(file, strBRes3.toString(), FileManagmentSys.responseReport,
					Integer.valueOf(strRes1), Float.valueOf(strRes2), null);

			// connecting to the db
			createGenericReport(new GenericReport(date, time, file.getAbsolutePath(), FileManagmentSys.responseReport));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;

	}

	public static boolean createGenericReport(GenericReport report) {
		String query = "insert into genericreport (date,time,Filename,reportType) " + "values (?,?,?,?)";
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, report.getDate());
			stm.setString(2, report.getTime());
			stm.setString(3, report.getFileName());
			stm.setString(4, report.getReportType());

			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

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

	public static ArrayList<String> getAllCompanyFuelTypes(String companyName) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<String> str = new ArrayList<String>();
		try {
			stm = ConnectionToDB.conn.prepareStatement("select fuelType from company where companyName = ? ");
			stm.setString(1, companyName);
			res = stm.executeQuery();

			while (res.next()) {
				str.add(res.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return str;
	}

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
				stm.setFloat(4, pricingModule.getSalePercent());
				
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
				stm.setFloat(4, pricingModule.getSalePercent());
				
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

	public static boolean savePricingModel(PricingModule pricingModel) {
		String query = "insert into pricingmodule (modelNumber,salePercent,company,status) " + "values (?,?,?,?)";
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setInt(1, pricingModel.getModelNumber());
			stm.setFloat(2, pricingModel.getSalePercent());
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

	public static boolean updateSale(Sale sale) {

		PreparedStatement stm;

		try {

			stm = ConnectionToDB.conn.prepareStatement(
					"UPDATE myfueldb.sale set status=?,companyName=?,fueltype=?,purchaseModule=?,salePercent=?,startTime=?"
							+ ",endTime=?,startDate=?,endDate=?,days=?" + " WHERE saleID = ?");
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
			stm.setInt(11, sale.getSaleID());

			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

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
			stm.setString(5, sale.getPurchaseModule());
			stm.setFloat(6, sale.getSalePercent());
			stm.setString(7, sale.getStartTime());
			stm.setString(8, sale.getEndTime());
			stm.setString(9, sale.getStartDate());
			stm.setString(10, sale.getEndDate());
			stm.setString(11, sale.getSaleDays());

			stm.executeUpdate();

			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

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
						res.getString(5), res.getFloat(6), res.getString(7), res.getString(8), res.getString(9),
						res.getString(10), res.getString(11));

				sales.add(sale);

			}
			res.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sales;

	}

}
