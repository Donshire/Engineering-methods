package server;

import java.io.File;
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

	public static File createFile(String loc, String fileType,int fileID, int stationID) {

		// ReportType<stationID><fileID><dateTime>
		//fileID is the generic report id
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String fileName = String.format("%s<%d><%s><%s>", fileType, stationID, fileID, dtf.format(now));

		File file = new File(loc + "\\" + fileName + ".txt");

		try {
			if (!file.createNewFile()) {
				file=null;
				System.out.println("File already exists.");
			} else {

				switch (fileType) {
				case (ResponseReport):

					break;
				case (PeriodicReport):

					break;
				case (IncomeReport):

					break;
				case (PurchasesReport):

					break;
				case (InventoryReport):

					break;

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;

	}
	
	/**
	 * return the File accoring to the givven data,return null if the file doesn't exist
	 * 
	 * @param loc String
	 * @param fileType String
	 * @param companyName String
	 * @param stationID int
	 * @param fileID int
	 * @return File object String
	 */
	
	public static File getFile(String loc, String fileType, String companyName, int stationID,int fileID) {
		
		File folder = new File(loc);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
		
		String fileName = String.format("%s<%d><%s><%d>", fileType, stationID, dtf.format(now),fileID);
		File file = new File(loc + "\\" + fileName + ".txt");
	}
	
	/**
	 * return the file path according to the data sent 
	 * ,it doesn't end with \\
	 * @param ComapnayName String
	 * @param fileType String
	 * @return file path String
	 */
	public static String createLocation(String ComapnayName,String fileType) {
		return curWorkingDir+"\\"+ComapnayName+"_Fuel_Company"+"\\"+fileType;
	}

}
