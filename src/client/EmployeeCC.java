package client;

import java.io.File;
import java.util.ArrayList;

import Entity.CompanyFuel;
import Entity.Fuel;
import Entity.GenericReport;
import Entity.Message;
import Entity.PricingModule;
import Entity.Rates;
import Entity.Sale;
import enums.Commands;

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

	public static Object createSaleResponseResport(String id, String companyName) {
		ArrayList<String> str = new ArrayList<String>();
		str.add(id);
		str.add(companyName);

		ClientUI.client.accept(new Message(str, Commands.getSaleResponseReport));
		// return the sale details
		return MyFuelClient.ServerRetObj;
	}

	public static void createPeriodicResport(String companyName) {
		ClientUI.client.accept(new Message(companyName, Commands.getPeriodicReport));
		// return the Periodic Resport details
	}

	public static ArrayList<String> getAllCompanyFuelTypes(String CompanyName) {
		ClientUI.client.accept(new Message(CompanyName, Commands.getAllCompanyFuel));
		return (ArrayList<String>) MyFuelClient.ServerRetObj;
	}

	public static CompanyFuel getCompanyFuel(String CompanyName, String fuelType) {
		ArrayList<String> str = new ArrayList<String>();
		str.add(CompanyName);
		str.add(fuelType);
		ClientUI.client.accept(new Message(str, Commands.getCompanyFuel));
		return (CompanyFuel) MyFuelClient.ServerRetObj;
	}

	public static ArrayList<Fuel> getFuelMaxPriceDetails(String companyName) {
		ClientUI.client.accept(new Message(companyName, Commands.getFuelMaxPriceDetails));

		return (ArrayList<Fuel>) MyFuelClient.ServerRetObj;
	}

	public static boolean updateFuelMaxPriceDetails(ArrayList<Fuel> list) {
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

}
