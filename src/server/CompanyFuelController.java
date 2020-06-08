package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompanyFuelController {


	public static Object getMaxPrice(String type) {
		float maxPrice = -1;

		try {
			PreparedStatement stm;
			ResultSet res;

			stm = ConnectionToDB.conn.prepareStatement("SELECT * FROM myfueldb.fuel where fuelType= ? ;");
			stm.setString(1, type);
			res = stm.executeQuery();
			
			res.next();
			maxPrice = res.getFloat(2);

			res.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maxPrice;
		
	}

}
