package client;

import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

import Entity.CompanyFuel;
import Entity.GasStationOrder;
import Entity.GenericReport;
import Entity.Message;
import Entity.Rates;
import Entity.Sale;
import Entity.StationFuel;
import enums.Commands;
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

	public static void createSaleResponseResport(int id) {
		ClientUI.client.accept(new Message(id, Commands.getSaleResponseReport));
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
}
