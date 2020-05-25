package client;

import java.util.ArrayList;

import Entity.GasOrder;
import Entity.Message;
import enums.Commands;

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

}
