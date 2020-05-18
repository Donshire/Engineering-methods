package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entity.Customer;
import Entity.Employee;
import Entity.Supplier;
import enums.Commands;

public class LogINController {

	public static Object LogIn(String userName, String password){

		PreparedStatement stm;
		ResultSet res;

		try {
			stm = ConnectionToDB.conn.prepareStatement("select * from customer where userName = ? and passWord = ?");
			stm.setString(1, userName);
			stm.setString(2, password);
			 res = stm.executeQuery();

			if (res.next() == true) {
				
				Customer c = new Customer(res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9),
						res.getInt(10), res.getInt(11), res.getInt(12), res.getInt(13), res.getString(14));
				
				if(c.getOnline() == 1) {
					return Commands.UserAlreadyConnected;
				}
				
				
				else {
				stm = ConnectionToDB.conn.prepareStatement("update myfueldb.customer set online = ? where id = ?");
				stm.setInt(1, 1);
				stm.setString(2, c.getId());
				stm.executeUpdate();
				}
				
				return c;
			}
			
			

			stm = ConnectionToDB.conn.prepareStatement("select * from employee where userName = ? and passWord = ?");
			stm.setString(1, userName);
			stm.setString(2, password);
			 res = stm.executeQuery();
			 
			 
			 if (res.next() == true) {
					return new Employee(res.getString(1), res.getString(2), res.getString(3), 
							res.getString(4),res.getString(5), res.getString(6), 
									res.getString(7), res.getString(8),res.getString(9), 
									res.getInt(10), res.getString(11));
				}
			 
			 

				stm = ConnectionToDB.conn.prepareStatement("select * from supplier where userName = ? and passWord = ?");
				stm.setString(1, userName);
				stm.setString(2, password);
				 res = stm.executeQuery();
				 
				 
				 if (res.next() == true) {
						return new Supplier(res.getString(1), res.getString(2), res.getString(3), 
								res.getString(4), res.getString(5),res.getString(6), res.getString(7), 
								res.getInt(8), res.getString(9));
					}
			 

			stm.close();
			res.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	

}
