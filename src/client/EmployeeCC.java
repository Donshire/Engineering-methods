package client;

import java.io.File;
import java.util.ArrayList;

import Entity.Car;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import Entity.CompanyFuel;
import Entity.Customer;
import Entity.GasStationOrder;
import Entity.GenericReport;
import Entity.Message;
import Entity.MyFile;
import Entity.Rates;
import Entity.Sale;
import Entity.StationFuel;
import enums.Commands;
import enums.Quarter;
import enums.StationManagerReportsTypes;

public class EmployeeCC {

	public static boolean updateFuelRate(ArrayList<Rates> rates) {

		ClientUI.client.accept(new Message(rates, Commands.updateFuelRate));

		return (boolean) MyFuelClient.ServerRetObj;
	}

	public static ArrayList<Rates> getAllCompanyRatesByStatus(Rates rate) {
		ClientUI.client.accept(new Message(rate, Commands.getAllCompanyRatesByStatus));
		return (ArrayList<Rates>) MyFuelClient.ServerRetObj;
	}

	public static boolean updateSale(ArrayList<Sale> sales) {
		ClientUI.client.accept(new Message(sales, Commands.updateSale));
		// may Cause some problems
		return (boolean) MyFuelClient.ServerRetObj;
	}

	public static ArrayList<Sale> getCompanySalesByStatus(Sale sale) {
		ClientUI.client.accept(new Message(sale, Commands.getAllCompanySalesByStatus));

		return (ArrayList<Sale>) MyFuelClient.ServerRetObj;
	}

	public static boolean craeteNewRate(Rates rate) {
		ClientUI.client.accept(new Message(rate, Commands.saveRate));
		return (boolean) MyFuelClient.ServerRetObj;
	}
	
	public static Object createSaleResponseResport(String id,String companyName) {
		ArrayList<String> str = new ArrayList<String>();
		str.add(id);
		str.add(companyName);
		
		ClientUI.client.accept(new Message(str, Commands.getSaleResponseReport));
		//return the sale details
		return MyFuelClient.ServerRetObj;
	}
	
	public static void createPeriodicResport(String companyName) {
		ClientUI.client.accept(new Message(companyName, Commands.getPeriodicReport));
		//return the Periodic Resport details
	}
	
	public static ArrayList<String> getAllCompanyFuelTypes(String CompanyName){
		ClientUI.client.accept(new Message(CompanyName, Commands.getAllCompanyFuel));
		return (ArrayList<String>) MyFuelClient.ServerRetObj;
	}
	 
	public static CompanyFuel getCompanyFuel(String CompanyName,String fuelType){
		ArrayList<String> str =new ArrayList<String>(); 
		str.add(CompanyName);
		str.add(fuelType);
		ClientUI.client.accept(new Message(str, Commands.getCompanyFuel));
		return (CompanyFuel) MyFuelClient.ServerRetObj;
	}
	
	public static boolean AddNewCustomer(Customer customer) {
		ClientUI.client.accept(new Message(customer,Commands.addNewCustomer));
		return(boolean)MyFuelClient.ServerRetObj;
	}
	
	public static int checkIfExist(String id) {
		ClientUI.client.accept(new Message(id,Commands.checkIfExist));
		return(int)MyFuelClient.ServerRetObj;
	}
	
	public static boolean addNewCar(Car car) {
		ClientUI.client.accept(new Message(car,Commands.addNewCar));
		return (boolean)MyFuelClient.ServerRetObj;
	}
	
	public static boolean updateModels(String pricing, String purchase, String id) {
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.add(pricing);
		objs.add(purchase);
		objs.add(id);
		ClientUI.client.accept(new Message(objs,Commands.updateModels));
		return (boolean)MyFuelClient.ServerRetObj;
	}
	

	public static ArrayList<GasStationOrder> getAllstationOrders(int stationId, String status) {

		ArrayList<Object> o = new ArrayList<Object>();
		o.add(stationId);
		o.add(status);

		ClientUI.client.accept(new Message(o, Commands.getAllstationOrdersBystatus));
		return (ArrayList<GasStationOrder>) MyFuelClient.ServerRetObj;

	}

	public static Boolean ApproveOrders(ArrayList<GasStationOrder> orders) {

		ClientUI.client.accept(new Message(orders, Commands.approveOrders));
		return (Boolean) MyFuelClient.ServerRetObj;
	}

	public static ArrayList<StationFuel> getAllStationFuelById(int stationId) {

		ClientUI.client.accept(new Message(stationId, Commands.getAllStationFuelById));

		return (ArrayList<StationFuel>) MyFuelClient.ServerRetObj;

	}

	public static Boolean updateFuelMinQuantitybyType(int stationid, String fueltype, float minQuantity) {

		ArrayList<Object> o = new ArrayList<Object>();
		o.add(stationid);
		o.add(fueltype);
		o.add(minQuantity);

		ClientUI.client.accept(new Message(o, Commands.updateFuelMinQuantitybyType));

		return (Boolean) MyFuelClient.ServerRetObj;

	}

	public static File createFuelStationReports(int stationId,String companyName,StationManagerReportsTypes reportType,Quarter quarter,String year) {
		ArrayList<Object> params = new ArrayList<Object>();
		File f;
		params.add(stationId);
		params.add(companyName);
		params.add(quarter);
		params.add(year);
		switch (reportType) {
		case income:
			ClientUI.client.accept(new Message(params, Commands.createFuelStationIncmomeReport));
			break;
		case purchases:
			ClientUI.client.accept(new Message(params, Commands.createFuelStationPurchasesReport));
			break;
		case inventory:
			ClientUI.client.accept(new Message(params, Commands.createFuelStationInventoryReport));
			break;
		}
		
		 if(MyFuelClient.ServerRetObj == null) return null;
		
		return (File) MyFuelClient.ServerRetObj;
	}
	
	public static ArrayList<GenericReport> getAllReportByYearandStationId(String year,int stationId){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(year);
		parameters.add(stationId);
		ClientUI.client.accept(new Message(parameters, Commands.getAllReportByYearandStationId));
		return (ArrayList<GenericReport>) MyFuelClient.ServerRetObj;
	}
	
	public static File getFile(GenericReport r) {
		ClientUI.client.accept(new Message(r, Commands.getFileToclient));
		return (File) MyFuelClient.ServerRetObj;
	}
}
