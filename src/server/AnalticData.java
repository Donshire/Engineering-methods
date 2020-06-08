package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Entity.GasStationOrder;
import enums.SupplierOrderStatus;

public class AnalticData extends Thread {
	
	private ScheduledExecutorService ses;
	private int index =0;
	
	private LocalDate before;
	private LocalDate after;
	
	public void run() 
    { 
		if(index>10) {
			ses.shutdown();
			return;
		}
		threadSleep();
		//calculatefuelTypeAnaleticRank();
		//calculateCustomerTypeAnaleticRank();
		System.out.println(index++);
		
		//
		before=LocalDate.now();
		after =LocalDate.now().minusDays(7);
		System.out.println("before "+before+" after "+after);
		
		
    } 
	
	private void threadSleep() {
		ses = Executors.newScheduledThreadPool(1);
		//run this task after X seconds
        ses.schedule(this, calculateTimeToSleep(), TimeUnit.MILLISECONDS);
        
        ses.shutdown();
	}
	
	public void shutDownThread() {
		 ses.shutdown();
	}
	
	public static ArrayList<String> getAllCustomersID(){
		Statement stm;
		ResultSet res;
		ArrayList<String> customersID= new ArrayList<String>();

		try {
			stm = ConnectionToDB.conn.createStatement();
			res = stm.executeQuery("select id from myfueldb.gasstationorder order by cus.id");

			while(res.next())customersID.add(res.getString(1));

			res.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return customersID;
	}
	
	/**
	 * This func will count for each customer how many fuel type he puchased<br>
	 * 0 -> 1<br>
	 * 1 -> 3<br>
	 * 2 -> 5<br>
	 * 3 -> 7<br>
	 * 4 -> 10<br>
	 * @return
	 */
	private ArrayList<Integer> calculatefuelTypeAnaleticRank() {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Integer> countOfGasStationFuelsPurchased= new ArrayList<Integer>();
		int index=0;
		
		try {
			//gasStationFuels
			stm = ConnectionToDB.conn.prepareStatement("Select count(distinct(fuelType)) from myfueldb.customer as cus " + 
					"left join myfueldb.car on cus.id=car.CustomerID " + 
					"left join myfueldb.fuelpurchase as pur on car.carNumber = pur.CarNumber " + 
					"where pur.date>= ? and pur.date<= ? GROUP BY cus.id order by cus.id");
			
			stm.setString(1,after.toString());
			stm.setString(2,before.toString());
			res = stm.executeQuery();
			
			while(res.next())countOfGasStationFuelsPurchased.add(res.getInt(1));
			
			//homeGasFuel 1/0
			stm = ConnectionToDB.conn.prepareStatement("Select count(distinct(customerID)) from myfueldb.customer as cus " + 
					"left join myfueldb.gasorder as ord on cus.id = ord.customerID " + 
					"where pur.date>= ? and pur.date<= ? GROUP BY cus.id order by cus.id");
			
			stm.setString(1,after.toString());
			stm.setString(2,before.toString());
			res = stm.executeQuery();
			
			while(res.next()) {
				countOfGasStationFuelsPurchased.set(index,
				countOfGasStationFuelsPurchased.get(index)+res.getInt(1));
				index++;
			}
			
			res.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return countOfGasStationFuelsPurchased;
	}

	/**
	 * the is according to how many cars customer have <br>
	 * Like: 1-3   cars 3  points <br>
	 * 		 4-7   cars 5  points <br>
	 * 		 7-12  cars 7  points <br>
	 * 		 12-20 cars 9  points <br>
	 * 		 21+   cars 10 points
	 * @return
	 */
	private ArrayList<Integer> calculateCustomerTypeAnaleticRank() {
		
		
		
		return null;
	}

	/**
	 * for each customer a rank will be given by number of different hours purchased<br>
	 * 
	 * @return
	 */
	private static int calculatefuelingHourAnaleticRank() {
		
		return 0;
	}
	
	private static boolean updateCutomersAnaliticdata(ArrayList<String> customersID,
			ArrayList<Integer> fuelTypeRank,ArrayList<Integer> houresRank,
			ArrayList<Integer> customerTypeRank) {
		PreparedStatement stm;
		int index=0;
		try {
			stm= ConnectionToDB.conn.prepareStatement("update myfueldb.customer "
					+ "set customerTypeAnaleticRank = ?, fuelingHourAnaleticRank = ? "
					+ ", fuelTypeAnaleticRank = ? where id= ?");
			
			while(index<customersID.size()) {
				stm.setInt(1, customerTypeRank.get(index));
				stm.setInt(2, houresRank.get(index));
				stm.setInt(3, fuelTypeRank.get(index));
				stm.setString(4, customersID.get(index));
				stm.addBatch();
			}
			
			stm.executeBatch();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private long calculateTimeToSleep() {
		LocalDate nextFriday=LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		Duration duration = Duration.between(LocalDateTime.now(),
				LocalDateTime.of(nextFriday.getYear(), nextFriday.getMonth(),
						nextFriday.getDayOfMonth(), 18, 0));
		//return duration.getSeconds();
		//for testing
		return 10;
	}
	
}
