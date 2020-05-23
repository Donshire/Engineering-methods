package client;

import java.util.ArrayList;

import Entity.Message;
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
	
	public static void createSaleResponseResport(int id) {
		ClientUI.client.accept(new Message(id, Commands.getSaleResponseReport));
	}
}
