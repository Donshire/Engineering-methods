package client;

import java.util.ArrayList;

import Entity.GasOrder;
import Entity.Message;
import enums.Commands;
import Entity.CustomerModule;
import Entity.FuelPurchase;

public class CustomerCC {

	public static ArrayList<Object> fastFuelingLogIn(String carNumber) {
		ClientUI.client.accept(new Message(carNumber, Commands.fastFuelingLogIn));
		return (ArrayList<Object>) MyFuelClient.ServerRetObj;
	}
	
	public static ArrayList<String> getAllCompanies() {
		ClientUI.client.accept(new Message("nothing", Commands.getAllCompanies));
		return (ArrayList<String>) MyFuelClient.ServerRetObj;
	}
	
	public static ArrayList<Integer> getAllCompanyFuelStationID(String companyName) {
		ClientUI.client.accept(new Message(companyName, Commands.getAllCompanyFuelStationID));
		return (ArrayList<Integer>) MyFuelClient.ServerRetObj;
	}
	
	public static ArrayList<Float> getPurchasePriceDetails(String companyName,CustomerModule model,int prcingModelNumber) {
		ArrayList<Object> str = new ArrayList<Object>();
		str.add(companyName);
		str.add(model);
		str.add(prcingModelNumber);
		ClientUI.client.accept(new Message(str, Commands.getPurchasePriceDetails));
		return (ArrayList<Float>) MyFuelClient.ServerRetObj;
	}
	
	public static boolean commitFuelPurchase(String customerId,String paymentOption,FuelPurchase purchase) {
		ArrayList<Object> str = new ArrayList<Object>();
		str.add(customerId);
		str.add(paymentOption);
		str.add(purchase);
		ClientUI.client.accept(new Message(str, Commands.commitFuelPurchase));
		return (boolean) MyFuelClient.ServerRetObj;
	}
	

	public static ArrayList<GasOrder> GasOrderList(String userId) {
		ClientUI.client.accept(new Message(userId, Commands.CustomerOrderList));
		try {
			ArrayList<GasOrder> array = (ArrayList<GasOrder>) MyFuelClient.ServerRetObj;
			System.out.println(array);
			return array;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
