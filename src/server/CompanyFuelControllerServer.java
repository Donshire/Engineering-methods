package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import Entity.Fuel;
import Entity.Rates;
import Entity.Sale;
import enums.RatesStatus;
import enums.SaleStatus;

public class CompanyFuelControllerServer {

	public static boolean updateRateStatus(int rateId, RatesStatus status) {

		PreparedStatement stm;
		ResultSet res, res2;

		switch (status) {

		// ceo chage rate status to confirmed
		case confirmed:

			try {
				stm = ConnectionToDB.conn.prepareStatement("update myfueldb.rates set status = ? where rateID = ?");
				stm.setInt(1, rateId);
				stm.setString(2, status.toString());
				stm.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		// marketing emp update after confirmd
		case active:
			try {
				stm = ConnectionToDB.conn
						.prepareStatement("select * from myfueldb.rates where status = ? where rateID = ?");
				stm.setInt(1, rateId);
				stm.setString(2, status.toString());
				res = stm.executeQuery();

				// create fuel intance - find max price for rate constarctor

				stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.fuel where fuelType = ?");
				stm.setString(1, res.getString(3));
				res2 = stm.executeQuery();

				Fuel f = new Fuel(res2.getString(1), res.getFloat(2));

				// create new instance of rate
				Rates rate = new Rates(res.getInt(1), res.getFloat(2), f, RatesStatus.active,
						res.getString(5), res.getString(6));

				// delete the row that was confirmed

				stm = ConnectionToDB.conn.prepareStatement("DELETE FROM myfueldb.rates WHERE where rateID = ?");
				stm.executeQuery();

				// udate the row with active status with the new rate
				stm = ConnectionToDB.conn.prepareStatement(
						"update myfueldb.rates set rateID = ?,rateValue = ?,fuelType = ?,status=?,date=?,company=? where status = ?");
				stm.setInt(1, rate.getRateId());
				stm.setFloat(2, rate.getRateValue());
				stm.setString(3, rate.getFuel().getFuelType());
				stm.setString(4, rate.getStatus().toString());
				stm.setString(5, rate.getDate());
				stm.setString(6, rate.getCompanyName());
				stm.executeQuery();

			} catch (SQLException e) {
				return false;
			}

			break;
		}

		return true;

	}

	public static Object getAllCompanyRatesByStatusRes(String companyName, RatesStatus status) {

		PreparedStatement stm;
		ResultSet res, res2;
		ArrayList<Rates> rates = new ArrayList<Rates>();

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.rates where status = ? and company = ?");
			stm.setString(1, status.toString());
			stm.setString(2, companyName);
			res = stm.executeQuery();

			while (res.next() == true) {

				stm = ConnectionToDB.conn.prepareStatement("select * from myfueldb.fuel where fuelType = ?");
				stm.setString(1, res.getString(3));
				res2 = stm.executeQuery();
				Fuel f = new Fuel(res2.getString(1), res.getFloat(2));

				Rates rate = new Rates(res.getInt(1), res.getFloat(2), f,RatesStatus.valueOf(res.getString(4)), res.getString(5),
						res.getString(6));

				rates.add(rate);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rates;
	}

	public static Object saveRate(Rates rate) {

		PreparedStatement stm;
		ResultSet res;

		try {
			stm = ConnectionToDB.conn
					.prepareStatement("INSERT INTO myfueldb.rates (rateID,rateValue,fuelType,status,date,company)"
							+ "VALUES (?,?,?,?,?,?)");
			stm.setInt(1, rate.getRateId());
			stm.setFloat(2, rate.getRateValue());
			stm.setString(3, rate.getFuel().getFuelType());
			stm.setString(4, rate.getStatus().toString());
			stm.setString(5, rate.getDate());
			stm.setString(6, rate.getCompanyName());
			stm.executeQuery();

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static boolean updateSale(Sale sale) {

		PreparedStatement stm;

		try {

			stm = ConnectionToDB.conn.prepareStatement(
					"INSERT INTO myfueldb.rates (saleID,status,companyName,fueltype,purchaseModule,salePercent,startTime,"
							+ "endTime,startDate,endDate,days)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?) where saleID = ?");
			stm.setInt(1, sale.getSaleID());
			stm.setBoolean(2, sale.getStatus());
			stm.setString(3, sale.getCompanyName());
			stm.setString(4, sale.getFuelType());
			stm.setString(5, sale.getPurchaseModule());
			stm.setFloat(6, sale.getSalePercent());
			stm.setString(7, sale.getStartTime());
			stm.setString(8, sale.getEndTime());
			stm.setString(9, sale.getStartDate());
			stm.setString(10, sale.getEndDate());
			stm.setString(11, sale.getSaleDays());
			stm.setInt(12, sale.getSaleID());

			stm.executeQuery();

		} catch (Exception e) {
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
			stm.setBoolean(2, sale.getStatus());
			stm.setString(3, sale.getCompanyName());
			stm.setString(4, sale.getFuelType());
			stm.setString(5, sale.getPurchaseModule());
			stm.setFloat(6, sale.getSalePercent());
			stm.setString(7, sale.getStartTime());
			stm.setString(8, sale.getEndTime());
			stm.setString(9, sale.getStartDate());
			stm.setString(10, sale.getEndDate());
			stm.setString(11, sale.getSaleDays());

			stm.executeQuery();

		} catch (Exception e) {
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
		} catch (Exception e) {
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

				Sale sale = new Sale(res.getInt(1), res.getBoolean(2), res.getString(3), res.getString(4),
						res.getString(5), res.getFloat(6), res.getString(7), res.getString(8), res.getString(9),
						res.getString(10), res.getString(11));

				sales.add(sale);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sales;

	}

}
