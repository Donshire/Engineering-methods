package server;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

import Entity.AnaliticDataReport;
import Entity.CompanyFuel;
import Entity.GasStationOrder;
import enums.SupplierOrderStatus;

public class AnalticData implements Runnable {

	private ScheduledExecutorService ses;

	public boolean continueThread = true;

	private LocalDate before;
	private LocalDate after;

	private static ArrayList<String> currentCustomers;
	private static ArrayList<Float> fuelingHourRanks;
	private static ArrayList<Integer> customerTypeRanks;
	private static ArrayList<Float> fuelTypeRanks;

	public void run() {
		while (continueThread) {
			do {
				before = LocalDate.now();
				after = LocalDate.now().minusDays(7);
				System.out.println("before " + before + " after " + after);
				
				//create files for all companies
				ArrayList<String> companies = getAllCompanies();
				File [] filesRank = new File[companies.size()];
				File [] filesStatistic = new File[companies.size()];
				int index = 0;
				
				//
				currentCustomers = getAllCustomersID();
				
				for (String val : companies) {
					filesRank[index] = FileManagmentSys.createFile(FileManagmentSys.createLocation
						(val, FileManagmentSys.analiticData, FileManagmentSys.customerAnaliticData),
							FileManagmentSys.analiticData, 0, "", "");
					filesStatistic[index] = FileManagmentSys.createFile(FileManagmentSys.createLocation
						(val, FileManagmentSys.analiticData, FileManagmentSys.statisticData),
							FileManagmentSys.analiticData, 0, "", "");
					
					//
					fuelingHourRanks = calculatefuelingHourAnaleticRank(filesStatistic[index],val);
					customerTypeRanks = calculateCustomerTypeAnaleticRank(filesStatistic[index],val);
					fuelTypeRanks = calculatefuelTypeAnaleticRank(filesStatistic[index],val);
					//
					System.out.println("Houers: " + fuelingHourRanks);
					System.out.println("CustomerType: " + customerTypeRanks);
					System.out.println("fuelType: " + fuelTypeRanks);
					//
					
					writeAnalitic(filesRank[index],currentCustomers, fuelTypeRanks, fuelingHourRanks, customerTypeRanks);
					
					//save file detaile in the db
					if(getWeekNumber(after.toString()).compareTo("-1")!=0) {
						CompanyFuelControllerServer.createAnaliticReport(new AnaliticDataReport(filesStatistic[index].getName(),
								getWeekNumber(after.toString()), getMonthName(after.toString()), after.toString().substring(0, 4), val, FileManagmentSys.statisticData));
						//
						CompanyFuelControllerServer.createAnaliticReport(new AnaliticDataReport(filesRank[index].getName(),
								getWeekNumber(after.toString()), getMonthName(after.toString()), after.toString().substring(0, 4), val, FileManagmentSys.customerAnaliticData));
					
					}
					
					index++;
				}

				//updateCutomersAnaliticdata(currentCustomers, fuelTypeRanks, fuelingHourRanks, customerTypeRanks);

				// calculateCustomerTypeAnaleticRank();
			} while (threadSleep());
			//
		}
	}
	
	private void writeAnalitic(File filesRank,ArrayList<String> customersID, ArrayList<Float> fuelTypeRanks,
			ArrayList<Float> fuelingHourRanks, ArrayList<Integer> customerTypeRank) {
		int index =0;
		StringBuilder str = new StringBuilder();
		str.append(String.format("%-15s%-15s%-15s%-15s\n","customerID","fuel Type","fueling Hour","customerType"));
		//
		while (index < customersID.size()) {
		str.append(String.format("%-15s%-15d%-15d%-15d\n",customersID.get(index),Math.round(fuelTypeRanks.get(index)),
				Math.round(fuelingHourRanks.get(index)),customerTypeRank.get(index)));
			index++;
		}
		FileManagmentSys.writeToAnaliticData(filesRank, str.toString());
	}
	
	private boolean threadSleep() {
		boolean flag = false;// didn't finish
		while (continueThread && !flag)
			try {
				TimeUnit.SECONDS.sleep(calculateTimeToSleep());
				flag = true;
				return true;
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		return false;
	}

	public static ArrayList<String> getAllCustomersID() {
		Statement stm;
		ResultSet res;
		ArrayList<String> customersID = new ArrayList<String>();

		try {
			stm = ConnectionToDB.conn.createStatement();
			res = stm.executeQuery("select id from myfueldb.customer order by id");

			while (res.next())
				customersID.add(res.getString(1));

			res.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return customersID;
	}

	/**
	 * for each fuel type rank is given by the price, the rank is calculated by
	 * 9*index/(sum of index-to 1) index is after sorting the fuel types by price
	 * every one has his index as the smallest number is 1 and the largest is the
	 * amount of fuel type.<br>
	 * for each customer a rank will be given according to which fuel types he
	 * purchased
	 * 
	 * @return
	 */
	private ArrayList<Float> calculatefuelTypeAnaleticRank(File filesStatistic,String company) {
		PreparedStatement stm;
		ResultSet res;
		ArrayList<Float> countOfGasStationFuelsPurchased = new ArrayList<Float>();
		int index = 0;
		//
		ArrayList<CompanyFuel> fuels = new ArrayList<CompanyFuel>();
		fuels.addAll(CompanyFuelControllerServer.getAllCompanyFuelTypes(company));
		
		int countFuelTypes[] = new int [fuels.size()];
		// sort the fuels prices
		Collections.sort(fuels);

		int count = 0, currRank,indexFuel;
		String currFuel, fuelType = "";
		
		// ------------------------------------------------------------------------------
		Set<String> fuelNames = new HashSet<String>();
		ArrayList<KeyplusRank> rankedFuels = new ArrayList<KeyplusRank>();

		while (count < fuels.size()) {
			currFuel = fuels.get(count).getFuelType();
			if (!fuelNames.contains(currFuel)) {
				fuelNames.add(currFuel);
				currRank = count;// initialize the rank
				index = 0;

				while (index < fuels.size()) {// matching fuel types
					if (currFuel.compareTo(fuels.get(index).getFuelType()) == 0)
						currRank += index + 1;
					index++;
				}
				rankedFuels.add(new KeyplusRank(currFuel, currRank));
			}
			// skip if already exist
			count++;
		}

		// sort the result
		Collections.sort(rankedFuels);
		// give for each fuel rank
		// exp. 4 => sumto 10+1 => 4/11 3/11 2/11 1/11
		int lower = sumto(rankedFuels.size()) + 1, uper = rankedFuels.size();

		for (KeyplusRank current : rankedFuels) {
			current.rank = ((float) uper) / lower;
			uper--;
		}

		// -----------------------------------------------------------------------------------
		try {
			// gasStationFuels
			stm = ConnectionToDB.conn.prepareStatement(
					"Select cus.id,par.fuelType\r\n" + 
					"from myfueldb.customer as cus\r\n" + 
					"left join \r\n" + 
					"(SELECT CustomerID,car.carNumber\r\n" + 
					"FROM  myfueldb.car) as cc\r\n" + 
					"on cus.id = cc.CustomerID\r\n" + 
					"left join \r\n" + 
					"(SELECT pur.CarNumber,pur.fuelType FROM\r\n" + 
					"myfueldb.fuelpurchase as pur where pur.date >=?  and pur.date <=?  and pur.company=?) as par\r\n" + 
					"on cc.carNumber = par.CarNumber \r\n" + 
					"group by par.CarNumber,cus.id \r\n" + 
					"order by cus.id ");

			stm.setString(1, after.toString());
			stm.setString(2, before.toString());
			stm.setString(3, company);
			res = stm.executeQuery();

			index = 0;
			float rankPercent;
			String currentCustomer = "";

			if (res.next()) {
				currentCustomer = res.getString(1);
				fuelType = res.getString(2);
				if (fuelType == null)
					rankPercent = 1f / lower;
				else {
					indexFuel = KeyplusRank.indexOf(res.getString(2), rankedFuels);
					countFuelTypes[indexFuel]++; 
					rankPercent = rankedFuels.get(indexFuel).rank;
				}
				countOfGasStationFuelsPurchased.add(rankPercent);
			}

			while (res.next()) {
				if (currentCustomer.compareTo(res.getString(1)) == 0) {
					// the same customer
					if(res.getString(2)!=null) {
						indexFuel = KeyplusRank.indexOf(res.getString(2), rankedFuels);
						countFuelTypes[indexFuel]++;
						rankPercent = rankedFuels.get(indexFuel).rank;
						countOfGasStationFuelsPurchased.set(index,
								countOfGasStationFuelsPurchased.get(index) + rankPercent);
					}
				} else {
					index++;
					currentCustomer = res.getString(1);
					fuelType = res.getString(2);
					if (fuelType == null)
						rankPercent = 1f / lower;
					else {
						indexFuel = KeyplusRank.indexOf(res.getString(2), rankedFuels);
						countFuelTypes[indexFuel]++; 
						rankPercent = rankedFuels.get(indexFuel).rank;
					}
					countOfGasStationFuelsPurchased.add(rankPercent);
				}
			}
			
			//get rank in numbers
			for(int j=0;j<countOfGasStationFuelsPurchased.size();j++)
				countOfGasStationFuelsPurchased.set(j, countOfGasStationFuelsPurchased.get(j)*10);
			
			res.close();
			stm.close();
			
			//
			StringBuilder str = new StringBuilder("count of fuel type purchases\n");
			str.append(String.format("%-15s%-10s\n","fuel type","count"));
			
			for(int j=0;j<countFuelTypes.length;j++)
				str.append(String.format("%-15s%-10d\n",rankedFuels.get(j).key, countFuelTypes[j]));
			
			FileManagmentSys.writeToAnaliticData(filesStatistic, str.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return countOfGasStationFuelsPurchased;
	}

	private static int sumto(int num) {
		return (int) ((num + 1) * (num / 2f));
	}

	// for sorting customers ranks
	private static class KeyplusRank implements Comparable<KeyplusRank> {
		String key;
		float rank;

		//
		public KeyplusRank(String key, float rank) {
			this.key = key;
			this.rank = rank;
		}

		//
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public float getRank() {
			return rank;
		}

		public void setRank(float rank) {
			this.rank = rank;
		}

		//
		@Override
		public int compareTo(KeyplusRank o) {
			if (this.rank > o.rank)
				return -1;
			if (this.rank > o.rank)
				return 1;
			return 0;
		}

		//
		public static int indexOf(String key, ArrayList<KeyplusRank> keys) {
			for (int i = 0; i < keys.size(); i++)
				if (keys.get(i).getKey().compareTo(key) == 0)
					return i;
			return -1;
		}

		public static int indexOfRang(int key, ArrayList<KeyplusRank> keys) {
			for (int i = 0; i < keys.size(); i++)
				if (Float.valueOf(keys.get(i).getKey()) - key >= 0)
					return i;
			return keys.size() - 1;
		}

		public static int indexOfHouers(String key, ArrayList<KeyplusRank> before, ArrayList<KeyplusRank> after) {
			// after<key<before
			for (int i = 0; i < before.size(); i++)
				if (before.get(i).key.compareTo(after.get(i).key) < 0) {
					// next day
					if ((before.get(i).key.compareTo(key) >= 0 && "00:00:00".compareTo(key) <= 0)
							|| (after.get(i).key.compareTo(key) <= 0 && "23:59:59".compareTo(key) >= 0))
						return i;
				} else if (before.get(i).key.compareTo(key) >= 0 && after.get(i).key.compareTo(key) <= 0)
					return i;
			return -1;
		}

		@Override
		public String toString() {
			return "KeyplusRank [key=" + key + ", rank=" + rank + "]";
		}

	}

	public static ArrayList<String> getAllCompanies() {
		ArrayList<String> companies = new ArrayList<String>();
		Statement stm;
		ResultSet res;

		try {
			// gasStationFuels
			stm = ConnectionToDB.conn.createStatement();

			res = stm.executeQuery("Select distinct(companyName) from myfueldb.company");

			while (res.next())
				companies.add(res.getString(1));

			res.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return companies;
	}

	/**
	 * 1 the is according to how many cars customer have <br>
	 * Like: 3<= cars 1 points <br>
	 * 6<= cars 2 points <br>
	 * 13<= cars 3 points <br>
	 * 17<= cars 4 points <br>
	 * 21+ cars 5 points<br>
	 * and 2 according total purchase<br>
	 * Like: 1000<= shekel 1 points<br>
	 * 5000<= shekel 2 points<br>
	 * 10000<= shekel 3 points<br>
	 * 50000<= shekel 4 points<br>
	 * 100000+ shekel 5 points<br>
	 * and the calculation is 1,2 divided by 2
	 * 
	 * @return
	 */
	private ArrayList<Integer> calculateCustomerTypeAnaleticRank(File filesStatistic,String company) {

		PreparedStatement stm;
		Statement statment;
		ResultSet res;
		ArrayList<Integer> customerTypeAnaleticRank = new ArrayList<Integer>();
		int countSumPurchase[] = new int[5];
		int countSumCar[] = new int[4];

		try {
			// Totale purchases
			String query = "Select cus.id,sum(par.priceOfPurchase) as countofpurchase\r\n"
					+ "from myfueldb.customer as cus \r\n" + "left join myfueldb.car as car\r\n"
					+ "on car.CustomerID=cus.id\r\n" + "left join\r\n"
					+ "  (SELECT pur.CarNumber,pur.priceOfPurchase\r\n" + "  FROM myfueldb.fuelpurchase as pur\r\n"
					+ "    where pur.date >= ?\r\n" + "      and pur.date <= ?\r\n" + "  and pur.company= ? ) as par \r\n"
					+ "on car.carNumber = par.CarNumber\r\n" + "group by cus.id  order by cus.id";

			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, after.toString());
			stm.setString(2, before.toString());
			stm.setString(3, company);
			
			res = stm.executeQuery();

			// ranking eatch customer
			int index = 0, rankPercent;

			// perpare the ranking array
			ArrayList<KeyplusRank> rankedFuels = new ArrayList<KeyplusRank>();
			float purchase = 1000;
			for (index = 0; index < 5; index++) {
				rankedFuels.add(new KeyplusRank(Float.toString(purchase), index + 1));
				if (index % 2 == 0)
					purchase *= 5;
				else
					purchase *= 2;
			}
			// --------------------------------------------------------------------------
			//
			Integer price;

			while (res.next()) {
				price = res.getInt(2);
				if (price == null)
					rankPercent = 1;
				else {
					rankPercent = (int) rankedFuels.get(KeyplusRank.indexOfRang(price, rankedFuels)).rank;
					countSumPurchase[rankPercent]++;
				}
				
				customerTypeAnaleticRank.add(rankPercent);
			}

			// --------------------------------------------------------------------------
			//write statistics to file
			StringBuilder str = new StringBuilder();
			purchase = 1000;
			
			str.append("car number and total purchase\n");
			str.append(String.format("%-15s%-10s","sum purchase","count"));
			
			for(int k=0;k<5;k++) {
				str.append(String.format("\n%-15.0f%-10d",purchase,countSumPurchase[k]));
				if (k % 2 == 0)
					purchase *= 5;
				else
					purchase *= 2;
			}
			
			FileManagmentSys.writeToAnaliticData(filesStatistic, str.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customerTypeAnaleticRank;
	}

	/**
	 * for each customer a rank will be given by number of different hours
	 * purchased<br>
	 * for each two houres there is a counter expet 23:00 - 03:00 and 03:00 - 07:00
	 * <h3>the rank is calculated accurding to the formela <br>
	 * index==count of houres , down = sumto(index)+1, each range of houres will get
	 * index/down<br>
	 * index will decrese in a loop min rank is 1/down and max is 1<br>
	 * <h6>the range is bettwen 10 cause rank is muliplied by 10, and 1 by
	 * caculating 10/down ~ 1
	 * 
	 * @return
	 */

	private ArrayList<Float> calculatefuelingHourAnaleticRank(File filesStatistic,String company) {
		PreparedStatement stm;
		Statement statment;
		ResultSet res;
		ArrayList<Float> customerTypeAnaleticRank = new ArrayList<Float>();
		StringBuilder builder = new StringBuilder();

		// create time stamps
		int hour = 3, i, parts = 8;
		ArrayList<KeyplusRank> fuelingHourAnaleticRank = new ArrayList<KeyplusRank>();
		//
		for (i = 0; i < parts; i++) {
			fuelingHourAnaleticRank.add(new KeyplusRank(LocalTime.of(i * hour, 0).toString(), 0));
			fuelingHourAnaleticRank.add(new KeyplusRank((LocalTime.of(((i + 1) * hour) % 24, 0).toString()), i * hour));
		}

		//
		try {
			// getPurchasesHoures
			for (i = 0; i < parts-1; i ++) {
				String query = "Select count(*)\r\n" + "from myfueldb.customer as cus \r\n"
						+ "left join myfueldb.car as car\r\n" + "on car.CustomerID=cus.id\r\n"
						+ "left join myfueldb.fuelpurchase as par\r\n" + "on car.carNumber = par.CarNumber\r\n"
						+ "where par.date >= ?\r\n" + "and par.date <= ?\r\n" + "and par.time<=? and par.time>=?\r\n"
						+ "and company= ? order by par.time  ";

				stm = ConnectionToDB.conn.prepareStatement(query);
				stm.setString(1, after.toString());
				stm.setString(2, before.toString());
				stm.setString(3, fuelingHourAnaleticRank.get(i*2+1).key);
				stm.setString(4, fuelingHourAnaleticRank.get(i*2).key);
				stm.setString(5, company);
				// fuelingHourAnaleticRank
				res = stm.executeQuery();
				res.next();

				fuelingHourAnaleticRank.get(i*2).rank = res.getInt(1);
			}
			// the cros day houres
			String query = "Select count(*)\r\n" + "from myfueldb.customer as cus \r\n"
					+ "left join myfueldb.car as car\r\n" + "on car.CustomerID=cus.id\r\n"
					+ "left join myfueldb.fuelpurchase as par\r\n" + "on car.carNumber = par.CarNumber\r\n"
					+ "where par.date >= ?\r\n" + "and par.date <= ?\r\n"
					+ "and (( par.time<='24:00' and par.time>=?) or\r\n" + "(par.time<=? and par.time>='00:00'))\r\n"
					+ "and company= ? order by par.time    ";

			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, after.toString());
			stm.setString(2, before.toString());
			stm.setString(3, fuelingHourAnaleticRank.get(parts * 2 - 2).key);
			stm.setString(4, fuelingHourAnaleticRank.get(parts * 2 - 1).key);
			stm.setString(5, company);
			// fuelingHourAnaleticRank
			res = stm.executeQuery();
			res.next();
			fuelingHourAnaleticRank.get(parts * 2 - 2).rank = res.getInt(1);
			
			
			//save for all the companies the result of the set
			
			builder.append(String.format("purchase per range of hours\n%-12s%-10s\n","range","count"));
			for(int j = 0 ; j < parts ; j++) {
			builder.append(String.format("%s-%s% -10d\n",fuelingHourAnaleticRank.get(j*2).key,fuelingHourAnaleticRank.get(j*2+1).key,(int)fuelingHourAnaleticRank.get(j*2).rank));
			}
			FileManagmentSys.writeToAnaliticData(filesStatistic, builder.toString());
			
			// ----------------------------------------------------------------
			// claculate houres rank

			// sort houres by rank
			ArrayList<KeyplusRank> startHourRank = new ArrayList<KeyplusRank>();

			for (i = 0; i < parts * 2; i += 2) {
				startHourRank.add(fuelingHourAnaleticRank.get(i));
			}
			Collections.sort(startHourRank);

			System.out.println(startHourRank);

			ArrayList<KeyplusRank> endHoursRank = new ArrayList<KeyplusRank>();
			//
			int countRank = 1;
			float down = sumto(parts + 1);
			for (KeyplusRank keyP : startHourRank) {
				keyP.rank = countRank / down;
				// find before
				for (i = 0; i < parts; i++) {
					if ((LocalTime.of((int) fuelingHourAnaleticRank.get(i * 2 + 1).rank, 0)).toString()
							.compareTo(keyP.key) == 0) {
						endHoursRank.add(fuelingHourAnaleticRank.get(i * 2 + 1));// found the before
						break;
					}
				}
				countRank++;
			}
			// free
			fuelingHourAnaleticRank = null;
			//
			System.out.println(startHourRank);
			System.out.println(endHoursRank);

			// ranks are ranged from 1/(countRank+1) to 1 so max will be 10 and min
			// countRank/(countRank+1)~1
			countRank = parts;
			// ------------------------------------
			//
			query = "Select cus.id,par.time\r\n" + "from myfueldb.customer as cus \r\n"
					+ "left join myfueldb.car as car\r\n" + "on car.CustomerID=cus.id\r\n" + "left join\r\n"
					+ "  (SELECT pur.CarNumber,pur.time\r\n" + "  FROM myfueldb.fuelpurchase as pur\r\n"
					+ "    where pur.date >= ?\r\n" + "      and pur.date <= ?\r\n" + "  ) as par \r\n"
					+ "on car.carNumber = par.CarNumber\r\n" + "order by cus.id ";

			stm = ConnectionToDB.conn.prepareStatement(query);
			stm.setString(1, after.toString());
			stm.setString(2, before.toString());
			res = stm.executeQuery();

			//
			String currentCustomer = "", purchaseTime;
			float customerRank = 0;
			int index = 0, indexOfRank = 0;
			Set<Integer> selectedHoures = new HashSet<Integer>();

			if (res.next()) {
				currentCustomer = res.getString(1);
				purchaseTime = res.getString(2);
				if (purchaseTime == null)
					customerRank = 0.1f;
				else {
					indexOfRank = KeyplusRank.indexOfHouers(res.getString(2), startHourRank, endHoursRank);
					selectedHoures.add(indexOfRank);
					customerRank = startHourRank.get(indexOfRank).rank;
				}
				customerTypeAnaleticRank.add(customerRank + 0.1f);
			}

			while (res.next()) {
				if (currentCustomer.compareTo(res.getString(1)) == 0) {
					// the same customer
					if (res.getString(2) != null) {
						indexOfRank = KeyplusRank.indexOfHouers(res.getString(2), startHourRank, endHoursRank);
						if (selectedHoures.add(indexOfRank)) {
							customerRank = startHourRank.get(indexOfRank).rank;
							customerTypeAnaleticRank.set(index, customerTypeAnaleticRank.get(index) + customerRank);

						}
					}
				} else {
					//
					customerTypeAnaleticRank.set(index, Math.max(1, customerTypeAnaleticRank.get(index) * countRank));
					selectedHoures.clear();
					//
					index++;
					currentCustomer = res.getString(1);
					purchaseTime = res.getString(2);
					if (purchaseTime == null)
						customerRank = 0.1f;
					else {
						indexOfRank = KeyplusRank.indexOfHouers(purchaseTime, startHourRank, endHoursRank);
						selectedHoures.add(indexOfRank);
						customerRank = startHourRank.get(indexOfRank).rank;
					}
					customerTypeAnaleticRank.add(customerRank + 0.1f);
				}
			}
			// the last one
			customerTypeAnaleticRank.set(index, Math.max(1, customerTypeAnaleticRank.get(index) * countRank));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return customerTypeAnaleticRank;
	}
	
	
	
	public static String getMonthName(String date) {
		int month =Integer.parseInt(date.substring(5, 7));
		String monthString;
		
		switch (month) {
        case 1:  monthString = "January";       break;
        case 2:  monthString = "February";      break;
        case 3:  monthString = "March";         break;
        case 4:  monthString = "April";         break;
        case 5:  monthString = "May";           break;
        case 6:  monthString = "June";          break;
        case 7:  monthString = "July";          break;
        case 8:  monthString = "August";        break;
        case 9:  monthString = "September";     break;
        case 10: monthString = "October";       break;
        case 11: monthString = "November";      break;
        case 12: monthString = "December";      break;
        default: monthString = "Invalid month"; break;
		}
        return monthString;
	}
	
	public static String getWeekNumber(String date) {
		Calendar cacheCalendar =  Calendar.getInstance();
		
		int month,year,day;
		year=Integer.parseInt(date.substring(0, 4));
		month=Integer.parseInt(date.substring(5, 7));
		day=Integer.parseInt(date.substring(8, 10));
		
		cacheCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    cacheCalendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
	    cacheCalendar.set(Calendar.MONTH, month);
	    cacheCalendar.set(Calendar.YEAR, year);
	    //
	    int fisrtSundayInMonth = cacheCalendar.get(Calendar.DATE);
	    //
	    int dex;
	    
	    if(fisrtSundayInMonth==1) dex=0;
	    else dex=-1;
	     
	    
	    for(int i=1-dex;i<4;i++) {
	    	if(((i-1)*7+fisrtSundayInMonth)<=day&&(i*7+fisrtSundayInMonth)>day) return i+"";
	    }
	    return "-1";
	}
	

	/**
	 * update cutomers ranks
	 * 
	 * @param customersID
	 * @param fuelTypeRanks
	 * @param fuelingHourRanks
	 * @param customerTypeRank
	 * @return
	 */
	private static boolean updateCutomersAnaliticdata(ArrayList<String> customersID, ArrayList<Float> fuelTypeRanks,
			ArrayList<Float> fuelingHourRanks, ArrayList<Integer> customerTypeRank) {
		PreparedStatement stm;
		int index = 0;
		try {
			stm = ConnectionToDB.conn.prepareStatement(
					"update myfueldb.customer " + "set customerTypeAnaleticRank = ?, fuelingHourAnaleticRank = ? "
							+ ", fuelTypeAnaleticRank = ? where id= ?");

			while (index < customersID.size()) {
				stm.setInt(1, customerTypeRank.get(index));
				stm.setInt(2, Math.round(fuelingHourRanks.get(index)));
				stm.setInt(3, Math.round(fuelTypeRanks.get(index)));
				stm.setString(4, customersID.get(index));
				stm.addBatch();
				index++;
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
		LocalDate nextFriday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		Duration duration = Duration.between(LocalDateTime.now(),
				LocalDateTime.of(nextFriday.getYear(), nextFriday.getMonth(), nextFriday.getDayOfMonth(), 0, 0));
		return duration.getSeconds();
		// for testing
		// return 10;
	}

}
