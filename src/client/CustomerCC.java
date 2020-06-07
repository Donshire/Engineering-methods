package client;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.Name;

import Entity.GasOrder;
import Entity.Message;
import enums.Commands;
import sun.font.CreatedFontTracker;

public class CustomerCC {

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
	
	/**
	 * Request from the server for a maximum price for a product unitõ
	 * @param str - Name of product
	 * @return - price max.
	 */
	public static Object getMaxPrice(String str) {
		ClientUI.client.accept(new Message(str, Commands.GetMaxPrice));
		return (float)MyFuelClient.ServerRetObj;
	}

	/**
	 * Request from the server to create new purchase of gas to home.
	 * @param order - order to create.
	 * @return true - if the purchase was successful.
	 */
	public static boolean createNewOrder(GasOrder order) {
		ClientUI.client.accept(new Message(order, Commands.CreateNewOrder));
		return (Boolean)MyFuelClient.ServerRetObj;
	}

}
