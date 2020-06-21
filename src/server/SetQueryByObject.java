package server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entity.FuelPurchase;

/**
 * The Class SetQueryByObject.
 */
public class SetQueryByObject {

	/**
	 * recive purchase and insert the data to the statment and execute it,
	 * purchase must contain all the data needed.
	 *
	 * @param stm the stm
	 * @param purchase the purchase
	 * @return true if every thing succeded else false
	 */
	public static boolean SetFuelPurchaseTable(PreparedStatement stm,FuelPurchase purchase) {
		//purchase Id is automaticlly set By MySQL
		
		//stationId,CarNumber,fuelQuantity,
		//priceOfPurchase,time,date,saleID,currentPrice
		
		try {
			stm.setInt(1, purchase.getStationId());
			stm.setString(2, purchase.getCarNumber());
			stm.setFloat(3, purchase.getFuelQuantity());
			stm.setFloat(4, purchase.getPriceOfPurchase());
			stm.setString(5, purchase.getTime());
			stm.setString(6, purchase.getDate());
			stm.setInt(7, purchase.getSaleID());
			stm.setFloat(8, purchase.getCurrentPrice());
			stm.setInt(9, purchase.getPricingModelNumber());
			stm.setString(10, purchase.getCompanyName());
			stm.setString(11, purchase.getFuelType());
			stm.executeUpdate();
			stm.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
