package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * a thread that run at all the customers to proced payment on PricingModel 3.
 *
 * @author iamme
 */
public class AutoMaticPurchaseModel3Calc implements Runnable {

	/** The continue thread. */
	public boolean continueThread = true;
	
	/** The old date. */
	private LocalDate before;
	
	/** The late date. */
	private LocalDate after;
	
	/**
	 * Run threads function.
	 */
	@Override
	public void run() {
		while(continueThread) {
			 do{
				before=getLastOfMonth();
				after =getFirstOfMonth();
				System.out.println("before "+before+" after "+after);
				
				//
				takeMoneyFromUsers();
				
			}while(threadSleep());
		}
	}

	/**
	 * Thread sleep.
	 *
	 * @return true, if successful
	 */
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
	
	
	/**
	 * Take money from users.
	 */
	public void takeMoneyFromUsers() {
		//run at all users
		PreparedStatement stm;
		ResultSet res;
		String currentCustomer,currentCar;
		
		String query="select par.id,sum(fp2.priceOfPurchase)\r\n" + 
				"from \r\n" + 
				"(SELECT fp.CarNumber,fp.priceOfPurchase\r\n" + 
				"FROM  myfueldb.fuelpurchase as fp\r\n" + 
				"where fp.date<=? and fp.date >= ? and fp.pricingModel='3') as fp2\r\n" + 
				"Left join \r\n" + 
				"(SELECT id,carNumber FROM myfueldb.car,myfueldb.customer\r\n" + 
				"where car.CustomerID=id) as par\r\n" + 
				"on par.carNumber=fp2.CarNumber\r\n" + 
				"group by par.id";
		try {
			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, before.toString());
			stm.setString(2, after.toString());
			
			res=stm.executeQuery();
			//get the current date
			String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/yyyy"));
			
			while(res.next()) {
				FastFuelController.payment(res.getString(1), "", res.getFloat(2), formattedDate);
			}
			
			res.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Calculate time to sleep.
	 *
	 * @return the long
	 */
	private long calculateTimeToSleep() {
		Calendar calendar = Calendar.getInstance(); 
		Date currMonth = calendar.getTime();
		
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date nextMonthFirstDay = calendar.getTime();
		
		long duration = nextMonthFirstDay.getTime()-currMonth.getTime();
		long diff = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS);
		
		System.out.println("the wait time "+diff);
		return diff;
		//for testing
		//return 100;
	}
	
	/**
	 * Gets the first of month.
	 *
	 * @return the first of month
	 */
	private LocalDate getFirstOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date nextMonthFirstDay = calendar.getTime();
		return nextMonthFirstDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	/**
	 * Gets the last of month.
	 *
	 * @return the last of month
	 */
	private LocalDate getLastOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date nextMonthFirstDay = calendar.getTime();
		return nextMonthFirstDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
}
