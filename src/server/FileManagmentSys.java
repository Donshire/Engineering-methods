package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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

	public static final String marketingManagerReports = "marketingManagerReports";
	public static final String stationManagerReports = "stationManagerReports";

	public static final String analiticData = "analiticData";

	public static void createSystemWorkSpace() {
		createSystemFolders();
	}

	private static void createSystemFolders() {
		// Create the main System directory(Folder)
		mainFoler = createSingleFolder(curWorkingDir);
		//
		createCompanyFolderSystem("PAZ");
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
	public static File createFile(String loc, String fileType, int stationID) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String fileName = "";
		File file;

		switch (fileType) {
		case (responseReport):
		case (periodicReport):
			// ReportType-dateTime
			fileName = String.format("%s-%s", fileType, dtf.format(now));
			break;
		case (incomeReport):
		case (purchasesReport):
		case (inventoryReport):
			// ReportType-stationID
			fileName = String.format("%s-%d", fileType, stationID);
			break;
		case (analiticData):
			// analiticData-Date
			fileName = String.format("%s-%s", fileType, dtf.format(now));
			break;

		}

		file = new File(loc + "\\" + fileName + ".txt");
		try {
			if (!file.createNewFile()) {
				file = null;
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("System clould not create the file");
			e.printStackTrace();
		}

		return file;

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
	 * @param Owner String as markiting manager or analitic data
	 * @param fileType String
	 * @return file path String
	 */
	public static String createLocation(String ComapnayName,String Owner,String fileType) {
		if(ComapnayName.isEmpty())return null;
		if(Owner.isEmpty())
			return curWorkingDir + "\\" + ComapnayName + "_Fuel_Company";
		if(fileType.isEmpty())
			return curWorkingDir + "\\" + ComapnayName + "_Fuel_Company" + "\\" + Owner;
		else 
			return curWorkingDir + "\\" + ComapnayName + "_Fuel_Company" + "\\" + Owner + "\\" + fileType;
		
	}

	public static boolean writeToQuarterReport(File file, String data, String QuarterData) {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(file);
			myWriter.write(String.format("%-20s_%s\n", QuarterData, data));
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
	public static String readQuarterReport(File file, String QuarterData) {

		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
			// StringBuffer sb=new StringBuffer(); //constructs a string buffer with no
			// characters
			String line;
			while ((line = br.readLine()) != null) {
				// sb.append(line); //appends line to string buffer
				// sb.append("\n"); //line feed
				line.substring(0, 20).replaceAll("\\s+", "").compareTo(QuarterData);
				return line.substring(21, line.length());
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} // reads the file
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * responseReport doesn't requier companies
	 * periodicReport doesn't requier numberOfCustomers,totalPurchases
	 * @param file
	 * @param data
	 * @param reportType
	 * @param numberOfCustomers
	 * @param totalPurchases
	 * @param companies
	 * @return
	 */

	public static boolean writeToMarkitingManagerReport(File file, String data, String reportType,
			int numberOfCustomers, float totalPurchases,ArrayList<String> companies) {
		FileWriter myWriter;
		// String[] lines = data.split(System.getProperty("line.separator"));
		try {
			myWriter = new FileWriter(file);

			if (reportType.compareTo(periodicReport) == 0) {
				int i=0;
				String str=String.format("%-12s ", "CutomerID");
				for(String strfor:companies)
					str+=String.format("%-20s",strfor+" Rank");
		
				myWriter.write(str+"\n");
			} else if (reportType.compareTo(responseReport) == 0) {
				myWriter.write(String.format("Number of cutomers in the SALE %s\n", Integer.toString(numberOfCustomers)));
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
	public static ArrayList<String> readMarkitingManagerReport(File file) {
		FileReader fr;
		ArrayList<String> resArray = new ArrayList<String>();
		StringBuilder strBuilder = new StringBuilder();
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
			// StringBuffer sb=new StringBuffer(); //constructs a string buffer with no
			// characters
			String line1 = null, line2 = null, rest = null;
			if ((line1 = br.readLine()) != null)
				if ((line2 = br.readLine()) != null)
					if((rest = br.readLine()) != null)
					while ((rest = br.readLine()) != null)
						strBuilder.append(rest + "\n");
			
			fr.close();
			resArray.add(line1);
			resArray.add(line2);
			rest=strBuilder.toString();
			System.out.println(rest.length());
			resArray.add(rest.substring(29,rest.length()));
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
	 * @param file
	 * @param data
	 * @return
	 */
	public static boolean writeToAnaliticData(File file, String data) {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(file);

			myWriter.write(String.format("%-12s %-15s %-15s %s\n", "Cutomer_ID", "Cutomer_type", "purchase_houres",
					"FuelType Puchased"));
			myWriter.write(data);

			myWriter.close();
		} catch (IOException e) {
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
	
	public static String analiticFileFormate(String CutomerID,String CutomerType,String purchaseHoures,String FuelTypePuchased) {
		return String.format("%-12s %-15s %-15s %s", CutomerID,CutomerType,purchaseHoures,
				FuelTypePuchased)+"\n";
	}
	
	/**
	 * build string according to the file formate
	 * @param CutomerID
	 * @param CustomerRank
	 * @param numOfComapnies
	 * @return the string containing /n
	 */
	public static String periodicReportFileFormate(String CutomerID,float[] CustomerRank,int numOfComapnies) {
		String str=String.format("%-12s ", CutomerID);
		StringBuilder strb = new StringBuilder(str); 
		for(int i=0;i<numOfComapnies;i++) {
			strb.append(String.format("%-20.2f", CustomerRank[i]));
		}
		return strb.toString()+"\n";
	}
	
	/**
	 * build string according to the file formate
	 * @param CutomerID
	 * @param totalePurchases
	 * @return the string containing /n
	 */
	public static String responseReportFileFormate(String CutomerID,String totalePurchases) {
		return String.format("%-12s %s", CutomerID, totalePurchases)+"\n";
	}
	
	/**
	 * build string according to the file formate
	 * @param QuarterData
	 * @param data
	 * @return the string containing /n
	 */
	public static String quatrerFileFormate(String QuarterData,String data) {
		return String.format("%-20s_%s\n", QuarterData, data);
	}

}
