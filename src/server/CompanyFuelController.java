package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompanyFuelController {

	public static Double getMaxPrice(String type) {
		PreparedStatement stm;
		ResultSet res;
		Double maxPrice = null;

		try {
			stm = ConnectionToDB.conn.prepareStatement("select maxPrice from fuel where fuelType = ?");
			stm.setString(1, type);
			res = stm.executeQuery();
			maxPrice = res.getDouble(1);
					
			stm.close();
			res.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maxPrice;
	}

}
