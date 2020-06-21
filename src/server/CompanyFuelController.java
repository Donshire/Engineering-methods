package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The Class CompanyFuelController.
 */
public class CompanyFuelController {


	/**
	 * Gets the max price.
	 * @param type the type
	 * @return the max price
	 */
	public static Object getMaxPrice(String type) {
		float maxPrice = -1;

		try {
			PreparedStatement stm;
			ResultSet res;

			stm = ConnectionToDB.conn.prepareStatement("SELECT * FROM fuel where fuelType= ? ;");
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
