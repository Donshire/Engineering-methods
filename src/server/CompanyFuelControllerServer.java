package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import Entity.CompanyFuel;
import Entity.Fuel;
import Entity.Rates;
import Entity.Sale;
import enums.RatesStatus;
import enums.SaleStatus;

public class CompanyFuelControllerServer {

	public static Object doh(int saleid) {
		PreparedStatement stm;
		ResultSet res1, res2, res3;

		try {

			// price count
			stm = ConnectionToDB.conn
					.prepareStatement("SELECT SUM(priceOfPurchase)" + " FROM fuelpurchase " + "where saleID = ?");
			stm.setInt(1, saleid);
			res1 = stm.executeQuery();
			System.out.println("SUM(priceOfPurchase)");
			while (res1.next()) {
				System.out.println(res1.getString(1));
			}

			// number of customers
			System.out.println("number of customers");
			stm = ConnectionToDB.conn
					.prepareStatement("SELECT COUNT(DISTINCT customerID)" + " FROM fuelpurchase " + "where saleID = ?");
			stm.setInt(1, saleid);
			res2 = stm.executeQuery();

			while (res2.next()) {
				System.out.println(res2.getString(1));
			}

			// for each customer total purchases
			String query3 ="SELECT customerID,SUM(priceOfPurchase) " + 
					"FROM myfueldb.fuelpurchase " + " WHERE saleID = ?" + 
					" GROUP BY customerID" ;
					stm = ConnectionToDB.conn.prepareStatement(query3);
			stm.setInt(1, saleid);
			res3=stm.executeQuery();
			
			System.out.println("for each customer total purchases");
			while(res3.next())
				System.out.println(res3.getString(1)+":"+res3.getString(2));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Object getCompanyFuel(String companyName,String fuelType) {

		PreparedStatement stm;
		ResultSet res, res2;
		CompanyFuel companyFuel=null;

		try {

			stm = ConnectionToDB.conn
					.prepareStatement("select currentPrice from company where companyName = ? "+
			"And fuelType = ? ");
			stm.setString(1, companyName);
			stm.setString(2, fuelType);
			res = stm.executeQuery();

			res.next();
			stm = ConnectionToDB.conn
					.prepareStatement("select maxPrice from fuel where fuelType = ? ");
			stm.setString(1, fuelType);
			res2 = stm.executeQuery();
			
			res2.next();
			Fuel f = new Fuel(fuelType, res2.getFloat(1));
			
			companyFuel= new CompanyFuel(companyName, f, res.getFloat(1));

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
			stm = ConnectionToDB.conn
					.prepareStatement("select fuelType from company where companyName = ? ");
			stm.setString(1, companyName);
			res = stm.executeQuery();
			
			while(res.next()) {
				str.add(res.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return str;
	}
	
	public static boolean updateRateStatus(Rates rate) {

		PreparedStatement stm;

		switch (rate.getStatus()) {

		// ceo chage rate status to confirmed
		case confirmed:

			try {
				stm = ConnectionToDB.conn.prepareStatement("update myfueldb.rates set status = ? where rateID = ?");
				stm.setString(1,rate.getStatus().toString());
				stm.setInt(2, rate.getRateId());
				stm.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		// marketing emp update after confirmd
		case active:
			try {
				//delete the old rate
				stm = ConnectionToDB.conn.prepareStatement("DELETE FROM myfueldb.rates WHERE status = ? AND company = ?");
				stm.setString(1,RatesStatus.active.toString());
				stm.setString(2,rate.getCompanyName());
				stm.executeUpdate();
				
				//update the new rate status
				stm = ConnectionToDB.conn.prepareStatement(
						"update myfueldb.rates set status=? where rateID = ?");
				stm.setString(1, rate.getStatus().toString());
				stm.setInt(2, rate.getRateId());
				stm.executeUpdate();
				
				//update the current price in the company
				stm = ConnectionToDB.conn.prepareStatement(
						"update myfueldb.company set currentPrice=? where companyName = ? AND fuelType = ?");
				stm.setString(1,String.valueOf(rate.getFuel().getMaxPrice()+rate.getRateValue()));
				stm.setString(2, rate.getCompanyName());
				stm.setString(3, rate.getFuelType());
				stm.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

			break;
		}

		return true;

	}

	public static ArrayList<Rates> getAllCompanyRatesByStatus(String companyName, RatesStatus status) {

		PreparedStatement stm;
		ResultSet res, res2;
		ArrayList<Rates> rates = new ArrayList<Rates>();

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.rates where status = ? and company = ?");
			stm.setString(1, status.toString());
			stm.setString(2, companyName);
			res = stm.executeQuery();

			while (res.next()) {

				stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.fuel where fuelType = ?");
				stm.setString(1, res.getString(3));
				res2 = stm.executeQuery();
				res2.next();
				Fuel f = new Fuel(res2.getString(1), res.getFloat(2));

				Rates rate = new Rates(res.getInt(1), res.getFloat(2), f, RatesStatus.valueOf(res.getString(4)),
						res.getString(5), res.getString(6));

				rates.add(rate);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
		return rates;
	}

	public static boolean saveRate(Rates rate) {
		String query = "insert into rates (rateValue,fuelType,status,date,company) " + "values (?,?,?,?,?)";
		PreparedStatement stm;
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setFloat(1, rate.getRateValue());
			stm.setString(2, rate.getFuelType());
			stm.setString(3, rate.getStatus().toString());
			stm.setString(4, rate.getDate());
			stm.setString(5, rate.getCompanyName());

			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
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
