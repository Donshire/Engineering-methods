package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import boundery.ServerController;

public class ConnectionToDB {

	public static Connection conn;

	public static void connectToDB(String schema, String passWord) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			//ServerController.writeToServerConsole("Driver definition succeed");
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {
			/* handle the error */
			//ServerController.writeToServerConsole("Driver definition failed");
			System.out.println("Driver definition failed");
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + schema + "?serverTimezone=IST", "root",
					passWord);

			//ServerController.writeToServerConsole("SQL connection succeed");
			System.out.println("SQL connection succeed");
		} catch (SQLException e) {/* handle any errors */
			e.printStackTrace();
		}

	}

}
