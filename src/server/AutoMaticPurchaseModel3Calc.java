package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * a thread that run at all the customers to proced payment on PricingModel 3
 * @author iamme
 *
 */
public class AutoMaticPurchaseModel3Calc implements Runnable {

	public boolean continueThread = true;
	
	private LocalDate before;
	private LocalDate after;
	
	@Override
	public void run() {
		while(continueThread) {
			 do{
				before=getFirstOfMonth();
				after =getLastOfMonth();
				System.out.println("before "+before+" after "+after);
				
				//
				takeMoneyFromUsers();
				
			}while(threadSleep());
		}
	}

	private boolean threadSleep() {
		boolean flag=false;//didn't finish
		while(continueThread&&!flag)
			try {
			    TimeUnit.SECONDS.sleep(calculateTimeToSleep());
			    flag=true;
			    return true;
			} catch (InterruptedException ie) {
			    Thread.currentThread().interrupt();
			}
		return false;
	}
	
	
	public static void takeMoneyFromUsers() {
		//run at all users
		Statement stm;
		ResultSet res;
		String currentCustomer,currentCar;
		
		String query="select id,carNumber\r\n" + 
				"from myfueldb.car,myfueldb.customer\r\n" + 
				"where pricingModel='3' and car.CustomerID=customer.id \r\n" + 
				"order by id";
		try {
			stm = ConnectionToDB.conn.createStatement();
			res=stm.executeQuery(query);
			
			while(res.next()) {
				currentCustomer=res.getString(1);
				currentCar=res.getString(2);
			}
			
			res.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	private long calculateTimeToSleep() {
		Calendar calendar = Calendar.getInstance(); 
		Date currMonth = calendar.getTime();
		
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date nextMonthFirstDay = calendar.getTime();
		
		long duration = nextMonthFirstDay.getTime()-currMonth.getTime();
		long diff = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS);
		
		System.out.println("the wait time "+diff);
		//return diff;
		//for testing
		return 100;
	}
	
	private LocalDate getFirstOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date nextMonthFirstDay = calendar.getTime();
		return nextMonthFirstDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	private LocalDate getLastOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date nextMonthFirstDay = calendar.getTime();
		return nextMonthFirstDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
}
