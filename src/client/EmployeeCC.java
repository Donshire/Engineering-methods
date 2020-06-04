package client;

import java.io.File;
import java.util.ArrayList;

import Entity.CompanyFuel;
import Entity.Message;
import Entity.PricingModule;
import Entity.Rates;
import Entity.Sale;
import enums.Commands;
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
	
}
