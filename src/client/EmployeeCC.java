package client;

import java.io.File;
import java.util.ArrayList;

import Entity.AnaliticDataReport;
import Entity.Car;
import Entity.CompanyFuel;
import Entity.Customer;
import Entity.GasStationOrder;
import Entity.GenericReport;
import Entity.Fuel;
import Entity.GenericReport;
import Entity.Message;
import Entity.PricingModule;
import Entity.Rates;
import Entity.Sale;
import Entity.StationFuel;
import enums.Commands;
import enums.StationManagerReportsTypes;
import sun.security.krb5.internal.PAData;
import enums.CustomerRateTypes;
import enums.Quarter;

public class EmployeeCC {

	public static boolean updatePricingModel(ArrayList<PricingModule> rates) { 
		ClientUI.client.accept(new Message(rates, Commands.updatePricingModel));
		return (boolean) MyFuelClient.ServerRetObj;
	}

	public static ArrayList<PricingModule> getAllCompanyRatesByStatus(PricingModule ratedata) {
		ClientUI.client.accept(new Message(ratedata, Commands.getAllCompanyRatesByStatus));
		return (ArrayList<PricingModule>)MyFuelClient.ServerRetObj;
	}

	public static boolean updateSale(ArrayList<Sale> sales) {
		ClientUI.client.accept(new Message(sales, Commands.updateSale));
		return (boolean) MyFuelClient.ServerRetObj;
	}

	public static ArrayList<Sale> getCompanySalesByStatus(Sale sale) {
		ClientUI.client.accept(new Message(sale, Commands.getAllCompanySalesByStatus));

		return (ArrayList<Sale>)MyFuelClient.ServerRetObj;
	}

	public static boolean craeteNewPricingModel(PricingModule pricingModel) {
		ClientUI.client.accept(new Message(pricingModel, Commands.savePricingModel));
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
	
	/**
	 * The function create a new message to the server which contains id
	 * @param id
	 * @return customer object
	 */
	
	public static Customer getCustomerDetails(String id) {
		ClientUI.client.accept(new Message(id,Commands.getCustomerDetails));
		return (Customer)MyFuelClient.ServerRetObj;
	}
	
	/**
	 * The function create a new message to the server which contains ArrayList of string that represent customers
	 * @param upCutomer
	 * @return true or false
	 */
	
	public static boolean updateCustomerDetails(ArrayList<String> upCutomer) {
		ClientUI.client.accept(new Message(upCutomer,Commands.updateCustomerDetails));
		return (boolean)MyFuelClient.ServerRetObj;
	}
	
	/**
	 * The function create a new message to the server
	 * @return ArrayList<Sale>
	 */
	
	public static ArrayList<Sale> getAllSales(){
		ClientUI.client.accept(new Message(null, Commands.getAllSales));
		return(ArrayList<Sale>)MyFuelClient.ServerRetObj;
	}
	
	/**
	 * The function create a new message to the server which contains ArrayList of sales
	 * @param sales
	 * @return true or false
	 */
	
	public static boolean deleteSales(ArrayList<Sale> sales) {
		ClientUI.client.accept(new Message(sales, Commands.deleteSales));
		return (boolean)MyFuelClient.ServerRetObj;
	}
	
	/**
	 * The function create a new message to the server which contains sale object
	 * @param sale
	 * @return true or false
	 */
	
	public static boolean addNewSaleTemp(Sale sale) {
		ClientUI.client.accept(new Message(sale,Commands.addNewSaleTemp));
		return (boolean)MyFuelClient.ServerRetObj;
	}

	
	
	public static ArrayList<CompanyFuel> getFuelMaxPriceDetails(String companyName) {
		ClientUI.client.accept(new Message(companyName, Commands.getFuelMaxPriceDetails));

		return (ArrayList<CompanyFuel>) MyFuelClient.ServerRetObj;
	}

	public static boolean updateFuelMaxPriceDetails(ArrayList<CompanyFuel> list) {
		ClientUI.client.accept(new Message(list, Commands.updateFuelMaxPriceDetails));

		return (boolean) MyFuelClient.ServerRetObj;
	}

	public static ArrayList<PricingModule> getBuildRateApprovalDetails(String companyName) {
		ClientUI.client.accept(new Message(companyName, Commands.getBuildRateApprovalDetails));

		return (ArrayList<PricingModule>) MyFuelClient.ServerRetObj;
	}

	public static boolean confirmBuildRateApprovalDetails(ArrayList<PricingModule> list) {
		ClientUI.client.accept(new Message(list, Commands.confirmFuelMaxPriceDetails));
		return (boolean) MyFuelClient.ServerRetObj;
	}

	public static boolean rejectBuildRateApprovalDetails(ArrayList<PricingModule> list) {
		ClientUI.client.accept(new Message(list, Commands.rejectFuelMaxPriceDetails));
		return (boolean) MyFuelClient.ServerRetObj;
	}

	public static ArrayList<GenericReport> getAllReportByYearandStationId(String year, int stationId) {
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

	/**
	 * The function create a new message to the server which contains company name
	 * @param companyName
	 * @return ArrayList<String>
	 */
	
	public static ArrayList<String> getFuelTypesByCompany(String companyName){
		ClientUI.client.accept(new Message(companyName, Commands.getFuelTypesByCompany));
		return(ArrayList<String>)MyFuelClient.ServerRetObj;
	}
	
	/**
	 * The function create a new message to the server which contains user name
	 * @param userName
	 * @return boolean
	 */
	
	public static boolean checkIfUserNameExist(String userName) {
		ClientUI.client.accept(new Message(userName, Commands.checkIfUserNameExist));
		return(boolean)MyFuelClient.ServerRetObj;
	}
	
	/**
	 * The function create a new message to the server 
	 * @return ArrayList<String>
	 */

	public static ArrayList<String> getCompanyNames() {
		ClientUI.client.accept(new Message(null, Commands.getCompanyNames));
		return(ArrayList<String>)MyFuelClient.ServerRetObj;
		
	}
	
	/**
	 * The function create a new message to the server which contains arrayList<object> - id, purchase model, company name
	 * @param id
	 * @param purchaseM
	 * @param companyNames
	 * @return boolean
	 */
	
	public static boolean addModule(String id,String purchaseM,String companyNames) {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(id);
		parameters.add(purchaseM);
		parameters.add(companyNames);
		ClientUI.client.accept(new Message(parameters, Commands.addModule));
		return(boolean)MyFuelClient.ServerRetObj;
	}
	
	/**
	 * The function create a new message to the server which contains arrayList<object> - car and old car
	 * @param car
	 * @param oldCar
	 * @return boolean
	 */
	
	public static boolean updateCar(Car car,String oldCar) {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(car);
		parameters.add(oldCar);
		ClientUI.client.accept(new Message(parameters, Commands.updateCar));
		return(boolean)MyFuelClient.ServerRetObj;
	}
	
	/**
	 * The function create a new message to the server which contains id number
	 * @param id
	 * @return int value
	 */
	
	public static int getCarCount(String id) {
		ClientUI.client.accept(new Message(id,Commands.getCarCount));
		return (int)MyFuelClient.ServerRetObj;
	}
	
	public static ArrayList<AnaliticDataReport> getAllAnaliticDataByYearAndMonth(String month,String year,String company){
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(month);
		params.add(year);
		params.add(company);
		ClientUI.client.accept(new Message(params,Commands.getAllAnaliticDataByYearAndMonth));
		return (ArrayList<AnaliticDataReport>) MyFuelClient.ServerRetObj;
	}
	
	public static File getAllAnaliticFileByYearAndMonth(String fileName,String company,String type){
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(fileName);
		params.add(company);
		params.add(type);
		
		ClientUI.client.accept(new Message(params,Commands.getAnaliticFile));
		return (File) MyFuelClient.ServerRetObj;
	}
}
