package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileManagmentSys {

	private static File mainFoler;
	private static final String curWorkingDir = "C:\\MyFuel_Server";

	public static final String responseReport = "responseReport";
	public static final String periodicReport = "periodicReport";
	public static final String incomeReport = "incomeReport";
	public static final String purchasesReport = "purchasesReport";
	public static final String inventoryReport = "inventoryReport";
	
	public static final String customerAnaliticData = "customerAnaliticData";
	public static final String statisticData = "statisticData";

	public static final String marketingManagerReports = "marketingManagerReports";
	public static final String stationManagerReports = "stationManagerReports";

	public static final String analiticData = "analiticData";

	public static void createSystemWorkSpace() {
		createSystemFolders();
	}

	private static void createSystemFolders() {
		// Create the main System directory(Folder)
		mainFoler = createSingleFolder(curWorkingDir);
		ArrayList<String> companies = AnalticData.getAllCompanies();
		for (String val : companies) {
			createCompanyFolderSystem(val);
			}
		//
	}

	public static boolean createCompanyFolderSystem(String CompanyName) {
		String curr = curWorkingDir + "\\" + CompanyName + "_Fuel_Company";
		String temp;
		createSingleFolder(curr);
		temp = curr;
		curr += "\\" + marketingManagerReports;
		createSingleFolder(curr);
		createSingleFolder(curr + "\\" + responseReport);
		createSingleFolder(curr + "\\" + periodicReport);
		//
		curr = temp;
		curr += "\\" + stationManagerReports;
		createSingleFolder(curr);
		createSingleFolder(curr + "\\" + incomeReport);
		createSingleFolder(curr + "\\" + purchasesReport);
		createSingleFolder(curr + "\\" + inventoryReport);
		//
		curr = temp;
		curr += "\\" + analiticData;
		createSingleFolder(curr);
		createSingleFolder(curr + "\\" + customerAnaliticData);
		createSingleFolder(curr + "\\" + statisticData);

		return true;
	}

	public static File createSingleFolder(String workingDir) {
		File file = new File(workingDir);
		if (!Files.isDirectory(Paths.get(workingDir))) {
			file.mkdir();
		}
		return file;
	}

	/**
	 * There is three types of Files All of them require "fileType" : One for
	 * Marketing manager reports witch requires (), Second for station manager
	 * reports witch requires (stationID), Third for the analytic data witch
	 * requires ()
	 * 
	 * @param loc       String the file path
	 * @param fileType  String must be one of the class static types
	 * @param stationID int
	 * @return the created File
	 */
	public static File createFile(String loc, String fileType, int stationID, String quarter, String year) {

		String fileName = createFileName(fileType, stationID, quarter, year);
		File file;

		boolean flage = false;

		file = new File(loc + "\\" + fileName + ".txt");
		try {
			if (!file.createNewFile()) {
				if (!(fileType.compareTo(FileManagmentSys.periodicReport) == 0
						|| fileType.compareTo(FileManagmentSys.responseReport) == 0))
					file = null;
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("System clould not create the file");
			e.printStackTrace();
		}

		return file;

	}

	public static String createFileName(String fileType, int stationID, String quarter, String year) {
		String fileName = "";

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();

		switch (fileType) {
		case (responseReport):
		case (periodicReport):
			// ReportType-dateTime
			fileName = String.format("%s", fileType);
			break;
		case (incomeReport):
		case (purchasesReport):
		case (inventoryReport):
			// ReportType-stationID
			fileName = String.format("st_id-%d-%s-%s", stationID, year, quarter);
			break;
		case (analiticData):
			// analiticData-Date
			fileName = String.format("%s-%s", fileType, dtf.format(now));
			break;
		}

		return fileName;
	}

	/**
	 * return the File accoring to the givven data,return null if the file doesn't
	 * exist
	 * 
	 * @param loc         String
	 * @param fileType    String
	 * @param companyName String
	 * @param stationID   int
	 * @return File object String
	 */

	public static File getFile(String loc, String fileName) {

		File[] listOfFiles = new File(loc).listFiles();
		File returnFile = null;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile())
				if (listOfFiles[i].getName().compareTo(fileName) == 0)
					return returnFile = listOfFiles[i];
		}

		return returnFile;
	}

	/**
	 * return the file path according to the data sent ,it doesn't end with \\
	 * 
	 * @param ComapnayName String
	 * @param Owner        String as markiting manager or analitic data
	 * @param fileType     String
	 * @return file path String
	 */
	public static String createLocation(String ComapnayName, String Owner, String fileType) {
		if (ComapnayName.isEmpty())
			return null;
		if (Owner.isEmpty())
			return curWorkingDir + "\\" + ComapnayName + "_Fuel_Company";
		if (fileType.isEmpty())
			return curWorkingDir + "\\" + ComapnayName + "_Fuel_Company" + "\\" + Owner;
		else
			return curWorkingDir + "\\" + ComapnayName + "_Fuel_Company" + "\\" + Owner + "\\" + fileType;

	}

	public static boolean writeToQuarterReport(File file, String data) {
		FileWriter myWriter;
		BufferedWriter br;

		try {
			myWriter = new FileWriter(file, true);
			br = new BufferedWriter(myWriter);

			br.write(String.format("%s", data));

			br.close();
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * this func will return line of file data to be returned each line of data is
	 * Quarter report result cloud be changed to lines for all year Quarters or more
	 * but for simlification QuarterData must be sorted
	 * 
	 * @param file
	 * @param QuarterData
	 * @return
	 */
	public static String readQuarterReport(File file) {
		StringBuilder s = new StringBuilder();
		FileReader fr;

		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
			// StringBuffer sb=new StringBuffer(); //constructs a string buffer with no
			// characters
			String line;
			while ((line = br.readLine()) != null) {
				s.append(line + "\n"); // appends line to string buffer
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} // reads the file
		catch (IOException e) {
			e.printStackTrace();
		}
		return s.toString();
	}

	/**
	 * responseReport doesn't requier companies periodicReport doesn't requier
	 * numberOfCustomers,totalPurchases
	 * 
	 * @param file
	 * @param data
	 * @param reportType
	 * @param numberOfCustomers
	 * @param totalPurchases
	 * @param companies
	 * @return
	 */

	public static boolean writeToMarkitingManagerReport(File file, String data, String reportType,
			int numberOfCustomers, float totalPurchases, ArrayList<String> companies) {
		FileWriter myWriter;
		// String[] lines = data.split(System.getProperty("line.separator"));
		try {
			myWriter = new FileWriter(file);

			if (reportType.compareTo(periodicReport) == 0) {
				int i = 0;
				String str = String.format("%-12s", "CutomerID");
				for (String strfor : companies)
					str += String.format("%-15s", strfor + " Rank(%)");

				myWriter.write(str + String.format("%15s\n", "total_Purchases"));
			} else if (reportType.compareTo(responseReport) == 0) {
				myWriter.write(
						String.format("Number of cutomers in the SALE %s\n", Integer.toString(numberOfCustomers)));
				myWriter.write(
						String.format("Total purchases of cutomers in the SALE %s\n", Float.toString(totalPurchases)));
				myWriter.write(String.format("\n%-12s %s\n", "CutomerID", "totalePurchases"));
			}
			myWriter.write(data);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * the ArrayList contains first Number of customers, Second Total purchases
	 * ,third all the customers
	 * 
	 * @param file
	 * @return
	 */
	public static ArrayList<String> readMarkitingManagerReport(File file, String reportType) {
		FileReader fr;
		ArrayList<String> resArray = new ArrayList<String>();
		StringBuilder strBuilder = new StringBuilder();
		String rest = null;
		try {
			fr = new FileReader(file);
			// creates a buffering character input stream
			BufferedReader br = new BufferedReader(fr);
			if (reportType.compareTo(FileManagmentSys.responseReport) == 0) {
				// response Report
				String line1 = null, line2 = null;
				if ((line1 = br.readLine()) != null)
					if ((line2 = br.readLine()) != null)
						if ((rest = br.readLine()) != null)
							while ((rest = br.readLine()) != null)
								strBuilder.append(rest + "\n");

				fr.close();
				resArray.add(line1);
				resArray.add(line2);
				rest = strBuilder.toString();
				System.out.println(rest.length());
				resArray.add(rest.substring(29, rest.length()));
			} else if (reportType.compareTo(FileManagmentSys.periodicReport) == 0) {
				while ((rest = br.readLine()) != null)
					strBuilder.append(rest + "\n");
				resArray.add(strBuilder.toString());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} // reads the file
		catch (IOException e) {
			e.printStackTrace();
		}
		return resArray;
	}

	/**
	 * Writes data to analict data file
	 * 
	 * @param file
	 * @param data
	 * @return
	 */
	public static boolean writeToAnaliticData(File file, String data) {

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file, true)); // Set true for append mode
			writer.newLine(); // Add new line
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static String readAnaliticData(File file) {
		FileReader fr;
		String str;
		StringBuilder strBuilder = new StringBuilder();
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
			while ((str = br.readLine()) != null) {
				strBuilder.append(str + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} // reads the file
		catch (IOException e) {
			e.printStackTrace();
		}
		return strBuilder.toString();
	}

	public static String analiticFileFormate(String CutomerID, String CutomerType, String purchaseHoures,
			String FuelTypePuchased) {
		return String.format("%-12s %-15s %-15s %s", CutomerID, CutomerType, purchaseHoures, FuelTypePuchased) + "\n";
	}

	/**
	 * build string according to the file formate
	 * 
	 * @param CutomerID
	 * @param CustomerRank
	 * @param numOfComapnies
	 * @return the string containing /n
	 */
	public static String periodicReportFileFormate(String CutomerID, float[] CustomerRank, int numOfComapnies,
			float totalPurchases) {
		String str = String.format("%-12s", CutomerID);
		StringBuilder strb = new StringBuilder(str);
		for (int i = 0; i < numOfComapnies; i++) {
			strb.append(String.format("%-15.2f", CustomerRank[i]));
		}
		return strb.toString() + String.format("%-15.2f\n", totalPurchases);
	}

	/**
	 * build string according to the file formate
	 * 
	 * @param CutomerID
	 * @param totalePurchases
	 * @return the string containing /n
	 */
	public static String responseReportFileFormate(String CutomerID, String totalePurchases) {
		return String.format("%-12s %s", CutomerID, totalePurchases) + "\n";
	}

	
	
	public static String buildHeader(String year, String stationId, String reportType, String quarter) {

		return String.format("%s %s %s Quarter station_id : %-4s\n\n", reportType, year, quarter, stationId);
	}

	
	
	public static String incomeReportFormat(ResultSet res, String stationId, String year, String quarter)
			throws SQLException {
		String format = buildHeader(year, stationId, incomeReport, quarter);
		return String.format("%sthe income is = %.2f\n_____________________________________________\n", format,
				res.getFloat(1));
	}

	
	
	public static String purchaseReportFormat(ResultSet res, String stationId, String year, String quarter)
			throws SQLException {
		String format = buildHeader(year, stationId, purchasesReport, quarter);

		format += String.format("%-20s%-15s\n", "Fuel Type", "total purchases");

		do {
			format += String.format("%-24s%-15.2f\n", res.getString(1), res.getFloat(2));
		} while (res.next());

		return format;
	}

	public static String inventoryReportFormat(ResultSet res, String stationId, String year, String quarter)
			throws SQLException {
		String format = buildHeader(year, stationId, inventoryReport, quarter);

		format += String.format("%-20s%-15s\n", "Fuel Type", "Quantity");

		do {
			format += String.format("%-24s%-15.2f\n", res.getString(1), res.getFloat(2));
		} while (res.next());

		return format;
	}

//	public static String analiticDataFormat(String week,String month,String year,ArrayList<String> customersID,
//			ArrayList<Float> fuelTypeRanks,ArrayList<Float> fuelingHourRanks,ArrayList<Integer> customerTypeRank) {
//
//		StringBuilder s = new StringBuilder();
//		s.append(String.format("week : %s   month : %s  year %s\n%-15s%-15s%-15s%-15s",week,month,year,"Id","fuelTypeRanks","fuelingHourRanks","customerTypeRank"));
//		
//		for(String v : customersID) {
//			s.append(String.format("Id : ", args))
//		}
//		
//		
//		return s.toString();
//	}

}