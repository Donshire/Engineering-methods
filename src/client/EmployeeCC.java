package client;

import java.io.File;
import java.util.ArrayList;

import Entity.Car;
import Entity.CompanyFuel;
import Entity.Customer;
import Entity.GasStationOrder;
import Entity.GenericReport;
import Entity.Message;
import Entity.PricingModule;
import Entity.Rates;
import Entity.Sale;
import Entity.StationFuel;
import enums.Commands;
import enums.StationManagerReportsTypes;
import enums.CustomerRateTypes;

public class EmployeeCC {

	public static Message updatePricingModel(ArrayList<PricingModule> rates) { 
		ClientUI.client.accept(new Message(rates, Commands.updatePricingModel));
		return (Message) MyFuelClient.ServerRetObj;
	}

	public static ArrayList<PricingModule> getAllCompanyRatesByStatus(PricingModule ratedata) {
		ClientUI.client.accept(new Message(ratedata, Commands.getAllCompanyRatesByStatus));
		return (ArrayList<PricingModule>)MyFuelClient.ServerRetObj;
	}

	public static Message updateSale(ArrayList<Sale> sales) {
		ClientUI.client.accept(new Message(sales, Commands.updateSale));
		return (Message) MyFuelClient.ServerRetObj;
	}

	public static ArrayList<Sale> getCompanySalesByStatus(Sale sale) {
		ClientUI.client.accept(new Message(sale, Commands.getAllCompanySalesByStatus));

		return (ArrayList<Sale>)MyFuelClient.ServerRetObj;
	}

	public static Message craeteNewPricingModel(PricingModule pricingModel) {
		ClientUI.client.accept(new Message(pricingModel, Commands.savePricingModel));
		return (Message) MyFuelClient.ServerRetObj;
	}
	
	public static Object createSaleResponseResport(String id,String companyName) {
		ArrayList<String> str = new ArrayList<String>();
		str.add(id);
		str.add(companyName);
		
		ClientUI.client.accept(new Message(str, Commands.getSaleResponseReport));
		//return the sale details
		return MyFuelClient.ServerRetObj;
	}
	
	public static Object createPeriodicResport(String companyName,String startDate,String endDate) {
		ArrayList<String> periodicReportdetails =new ArrayList<String>();
		periodicReportdetails.add(companyName);
		periodicReportdetails.add(startDate);
		periodicReportdetails.add(endDate);
		ClientUI.client.accept(new Message(periodicReportdetails, Commands.getPeriodicReport));
		//return the Periodic Resport details
		return MyFuelClient.ServerRetObj;
	}
	
	public static ArrayList<String> getAllCompanyFuelTypes(String CompanyName){
		ClientUI.client.accept(new Message(CompanyName, Commands.getAllCompanyFuel));
		return (ArrayList<String>) MyFuelClient.ServerRetObj;
	}
	
	//this isn't used anyMore by rate
	public static CompanyFuel getCompanyFuel(String CompanyName,String fuelType){
		ArrayList<String> str =new ArrayList<String>(); 
		str.add(CompanyName);
		str.add(fuelType);
		ClientUI.client.accept(new Message(str, Commands.getCompanyFuel));
		return (CompanyFuel) MyFuelClient.ServerRetObj;
	}
	
	public static PricingModule getCompanyActiveRateAccordingPriceModel(PricingModule ratedata){
		ClientUI.client.accept(new Message(ratedata, Commands.getCompanyPricingRate));
		return (PricingModule) MyFuelClient.ServerRetObj;
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

	public static Boolean createFuelStationReports(int stationId, StationManagerReportsTypes reportType) {

		switch (reportType) {
		case income:
			ClientUI.client.accept(new Message(stationId, Commands.createFuelStationIncmomeReport));
			break;
		case purchases:
			ClientUI.client.accept(new Message(stationId, Commands.createFuelStationPurchasesReport));
			break;
		case inventory:
			ClientUI.client.accept(new Message(stationId, Commands.createFuelStationInventoryReport));
			break;
		}
		
		return (Boolean) MyFuelClient.ServerRetObj;
	}
	
	public static Customer getCustomerDetails(String id) {
		ClientUI.client.accept(new Message(id,Commands.getCustomerDetails));
		return (Customer)MyFuelClient.ServerRetObj;
	}
	
	public static boolean updateCustomerDetails(ArrayList<String> upCutomer) {
		ClientUI.client.accept(new Message(upCutomer,Commands.updateCustomerDetails));
		return (boolean)MyFuelClient.ServerRetObj;
	}
	
	public static ArrayList<Sale> getAllSales(){
		ClientUI.client.accept(new Message(null, Commands.getAllSales));
		return(ArrayList<Sale>)MyFuelClient.ServerRetObj;
	}
	
	public static boolean deleteSales(ArrayList<Sale> sales) {
		ClientUI.client.accept(new Message(sales, Commands.deleteSales));
		return (boolean)MyFuelClient.ServerRetObj;
	}
	
	public static boolean addNewSaleTemp(Sale sale) {
		ClientUI.client.accept(new Message(sale,Commands.addNewSaleTemp));
		return (boolean)MyFuelClient.ServerRetObj;
	}
	
	public static ArrayList<String> getFuelTypesByCompany(String companyName){
		ClientUI.client.accept(new Message(companyName, Commands.getFuelTypesByCompany));
		return(ArrayList<String>)MyFuelClient.ServerRetObj;
	}
	
	public static boolean checkIfUserNameExist(String userName) {
		ClientUI.client.accept(new Message(userName, Commands.checkIfUserNameExist));
		return(boolean)MyFuelClient.ServerRetObj;
	}
}
