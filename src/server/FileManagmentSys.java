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
	private static ArrayList<File> CompaniesFolers = new ArrayList<File>();
	private static final String curWorkingDir = "C:\\MyFuel_Server";

	public static final String ResponseReport = "ResponseReport";
	public static final String PeriodicReport = "PeriodicReport";
	public static final String IncomeReport = "IncomeReport";
	public static final String PurchasesReport = "PurchasesReport";
	public static final String InventoryReport = "InventoryReport";

	public static final String MarkyingManagerReports = "MarkyingManagerReports";
	public static final String StationManagerReports = "StationManagerReports";

	public static void createSystemWorkSpace() {
		createSystemFolders();
	}

	private static void createSystemFolders() {
		// Create the main System directory(Folder)
		mainFoler = new File(curWorkingDir);
		if (!Files.isDirectory(Paths.get(curWorkingDir))) {
			mainFoler.mkdir();
		}

		// Create the PazCompany directory(Folder)
		createCompanyFolderSystem("Paz");

		// Create the Markiting Manager Reports directory(Folder)

		//

		//
	}

	public static boolean createCompanyFolderSystem(String CompanyName) {
		String curr = curWorkingDir + "\\" + CompanyName + "_Fuel_Company";
		String temp;
		CompaniesFolers.add(createSingleFolder(curr));
		temp = curr;
		curr += "\\" + MarkyingManagerReports;
		createSingleFolder(curr);
		createSingleFolder(curr + "\\" + ResponseReport);
		createSingleFolder(curr + "\\" + PeriodicReport);
		//
		curr = temp;
		curr += "\\" + StationManagerReports;
		createSingleFolder(curr);
		createSingleFolder(curr + "\\" + IncomeReport);
		createSingleFolder(curr + "\\" + PurchasesReport);
		createSingleFolder(curr + "\\" + InventoryReport);
		return true;
	}

	private static File createSingleFolder(String workingDir) {
		File file = new File(workingDir);
		if (!Files.isDirectory(Paths.get(workingDir))) {
			file.mkdir();
		}
		return file;
	}

	public static File createFile(String loc, String fileType, int fileID, int stationID) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String fileName = "";
		File file;

		switch (fileType) {
		case (ResponseReport):
		case (PeriodicReport):
			// ReportType<fileID><dateTime>
			fileName = String.format("%s<%d><%s>", fileType, fileID, dtf.format(now));
			break;
		case (IncomeReport):
		case (PurchasesReport):
		case (InventoryReport):
			// ReportType<stationID><fileID>
			fileName = String.format("%s<%d><%d><%s>", fileType, stationID, fileID);
			break;

		}

		file = new File(loc + "\\" + fileName + ".txt");

		try {
			if (!file.createNewFile()) {
				file = null;
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	 * @param fileID      int
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
	 * return sub string of file name containing the details of the file for example
	 * <?><?> numOfSets will tell tell wich one of < to take
	 * 
	 * @param filename
	 * @param numOfSets
	 * @return
	 */
	private static String getByNumer(String filename, int numOfSets) {
		int count = 0;
		for (int i = 0; i < filename.length(); i++) {
			if (filename.charAt(i) == '<' || filename.charAt(i) == '>') {
				count++;
				if (count == numOfSets * 2)
					return filename.substring(0, i);
			}
		}
		return filename;
	}

	/**
	 * return the file path according to the data sent ,it doesn't end with \\
	 * 
	 * @param ComapnayName String
	 * @param fileType     String
	 * @return file path String
	 */
	public static String createLocation(String ComapnayName, String fileType) {
		return curWorkingDir + "\\" + ComapnayName + "_Fuel_Company" + "\\" + fileType;
	}

	public static boolean writeToQuarterReport(File file, String data, String QuarterData) {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(file);
			myWriter.write(String.format("%-20s>%s\n", QuarterData, data));
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
				return line;
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
	 * Saleid must be in the data 
	 * @param file
	 * @param data
	 * @param reportType
	 * @param numberOfCustomers
	 * @param totalPurchases
	 * @return
	 */
	
	public static boolean writeToMarkitingManagerReport(File file,String data,String reportType,int numberOfCustomers,float totalPurchases) {
		FileWriter myWriter;
		//String[] lines = data.split(System.getProperty("line.separator"));
		try {
			myWriter = new FileWriter(file);
			
			if(reportType.compareTo(PeriodicReport)==0) {
				myWriter.write(String.format("%-12s %s","CutomerID","Cutomer Rank"));
				myWriter.write(data);
			}
			else if(reportType.compareTo(ResponseReport)==0) {
				myWriter.write(String.format("Number of cutomers in the SALE %s",Integer.toString(numberOfCustomers)));
				myWriter.write(String.format("Total purchases of cutomers in the SALE %s",Float.toString(totalPurchases)));
				myWriter.write(data);
			}
			
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
	 * @param file
	 * @return
	 */
	public static ArrayList<String> readMarkitingManagerReport(File file) {

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
				return line;
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

}
