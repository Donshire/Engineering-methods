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
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Entity.CompanyFuel;
import Entity.GasStationOrder;
import enums.SupplierOrderStatus;

public class AnalticData extends Thread {
	
	private ScheduledExecutorService ses;
	private int index =0;
	
	private LocalDate before;
	private LocalDate after;
	
	private static ArrayList<String> currentCustomers;
	
	public void run() 
    { 
		if(index>10) {
			ses.shutdown();
			return;
		}
		threadSleep();
		currentCustomers=getAllCustomersID();
		
		calculatefuelTypeAnaleticRank();
		calculateCustomerTypeAnaleticRank();
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
	 * as fuel price is higher rank is higher
	 * @return
	 */
	private ArrayList<Integer> calculatefuelTypeAnaleticRank() {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Integer> countOfGasStationFuelsPurchased= new ArrayList<Integer>();
		int index=0;
		//get all system fuels
		ArrayList<String> companies = getAllCompanies();
		ArrayList<CompanyFuel> fuels = new ArrayList<CompanyFuel>();
		for(String company : companies)
			fuels.addAll(CompanyFuelControllerServer.getAllCompanyFuelTypes(company));
		//sort the fuels prices
		 Collections.sort(fuels);
		 ArrayList<FuelplusPrice> rankedFuels = new ArrayList<FuelplusPrice>();
		 
		 for(int i=0;i<fuels.size();i++) {
			 if(rankedFuels.contains(fuels.get(i).getFuelType())) {
				 rankedFuels.add(new FuelplusPrice(fuels.get(i).getFuelType(),i));
			 }
			 index=rankedFuels.indexOf(fuels.get(i).getFuelType());
			 rankedFuels.set(index, new FuelplusPrice(rankedFuels.get(index).getFuelsName()
					 ,rankedFuels.get(index).getRankArray()+i));
		 }
		 //sort the result
		 Collections.sort(rankedFuels);
		 //give for each fuel rank
		 //exp. 5 => sumto 10 => 5/15 4/15 3/15 2/15 1/15
		 int lower=sumto(rankedFuels.size()),uper=rankedFuels.size();
		 
		 for(FuelplusPrice current:rankedFuels) {
			 current.rankArray=(int)(10*(float)(uper/lower));
			 uper--;
		 }
		 
		try {
			//gasStationFuels
			stm = ConnectionToDB.conn.prepareStatement("Select id,car.fuelType " + 
					"from myfueldb.customer as cus " + 
					"left join myfueldb.car on cus.id=car.CustomerID " + 
					"left join myfueldb.fuelpurchase as pur on car.carNumber = pur.CarNumber " + 
					"where pur.date>= ? and pur.date<= ? group by car.carNumber order by cus.id");
			
			stm.setString(1,after.toString());
			stm.setString(2,before.toString());
			res = stm.executeQuery();
			
			index=0;
			int customersIndex=0;
			String currentCustomer="";
			
			if(res.next()) {
				currentCustomer=res.getString(1);
				while(currentCustomer.compareTo(currentCustomers.get(customersIndex++))!=0 && customersIndex<currentCustomers.size()) {
					countOfGasStationFuelsPurchased.add(1);
				}
				//found the first non zero fuel purchase
				countOfGasStationFuelsPurchased.add(rankedFuels.get(rankedFuels.indexOf(res.getString(2))).rankArray);
			}
			while(res.next()) {
				while(currentCustomer.compareTo(currentCustomers.get(customersIndex++))!=0 && customersIndex<currentCustomers.size()) {
					countOfGasStationFuelsPurchased.add(1);
				}
				if(currentCustomer.compareTo(res.getString(1))==0)
					countOfGasStationFuelsPurchased.set(index,countOfGasStationFuelsPurchased.get(index)+
							rankedFuels.get(rankedFuels.indexOf(res.getString(2))).rankArray);
				else {
					countOfGasStationFuelsPurchased.add(rankedFuels.get(rankedFuels.indexOf(res.getString(2))).rankArray);
					index++;
				}
			}
			
			//homeGasFuel 1/0
			stm = ConnectionToDB.conn.prepareStatement("Select id " + 
					"from myfueldb.customer as cus  " + 
					"left join myfueldb.gasorder as ord on cus.id = ord.customerID " + 
					"where ord.date>= ? and ord.date<= ? GROUP BY cus.id order by cus.id");
			
			stm.setString(1,after.toString());
			stm.setString(2,before.toString());
			res = stm.executeQuery();
			int homeGasRank=rankedFuels.get(rankedFuels.indexOf("HOME GAS")).rankArray;
			
			//
			index=0;
			while(res.next()) {
				currentCustomer=res.getString(1);
				while(currentCustomer.compareTo(currentCustomers.get(customersIndex++))!=0 && customersIndex<currentCustomers.size()) {}
				if(currentCustomer.compareTo(res.getString(1))==0)
					countOfGasStationFuelsPurchased.set(index,countOfGasStationFuelsPurchased.get(index)+
							homeGasRank);
				else {
					countOfGasStationFuelsPurchased.add(rankedFuels.get(rankedFuels.indexOf(res.getString(2))).rankArray);
					index++;
				}
			}
			
			res.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return countOfGasStationFuelsPurchased;
	}
	
	private static int sumto(int num) {
		return (int)((num+1)*(num/2f));
	}

	//for sorting fuels prices
	private static class FuelplusPrice implements Comparable<FuelplusPrice>{
		 String fuelsName;
		 int rankArray;
		public FuelplusPrice(String fuelsName, int rankArray) {
			this.fuelsName = fuelsName;
			this.rankArray = rankArray;
		}
		public String getFuelsName() {
			return fuelsName;
		}
		public void setFuelsName(String fuelsName) {
			this.fuelsName = fuelsName;
		}
		public int getRankArray() {
			return rankArray;
		}
		public void setRankArray(int rankArray) {
			this.rankArray = rankArray;
		}
		@Override
		public int compareTo(FuelplusPrice o) {
			if(this.rankArray>o.rankArray)return 1;
			if(this.rankArray<o.rankArray)return 1;
			return 0;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FuelplusPrice other = (FuelplusPrice) obj;
			if (fuelsName == null) {
				if (other.fuelsName != null)
					return false;
			} else if (!fuelsName.equals(other.fuelsName))
				return false;
			return true;
		}
		 
	}
	
	public static ArrayList<String> getAllCompanies(){
		ArrayList<String> companies = new ArrayList<String>();
		Statement stm;
		ResultSet res;
		
		try {
			//gasStationFuels
			stm = ConnectionToDB.conn.createStatement();

			res = stm.executeQuery("Select distinct(companyName) from myfueldb.company");
			
			while(res.next())companies.add(res.getString(1));
			
			res.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return companies;
	}
	
	/**
	 * 1 the is according to how many cars customer have <br>
	 * Like: 1-3   cars 3  points <br>
	 *       4-7   cars 5  points <br>
	 * 		 7-12  cars 7  points <br>
	 * 		 12-20 cars 9  points <br>
	 * 		 21+   cars 10 points<br>
	 * and 2 according total purchase<br>
	 * Like: 1000<=   shekel   3  points<br>
	 *       10000<=  shekel   6  points<br>
	 *       100000<= shekel   8  points<br>
	 *       100000+  shekel   10  points<br>
	 *       and the calculation is 1,2 divided by 2
	 * @return
	 */
	private ArrayList<Integer> calculateCustomerTypeAnaleticRank() {
		
		//CompanyFuelControllerServer.customersTotalPurchases(start, end)
		
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
