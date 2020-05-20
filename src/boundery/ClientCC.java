package boundery;

import Entity.Message;
import client.ClientUI;
import client.MyFuelClient;
import enums.Commands;

public class ClientCC {

	public static Object getMaxPrice(String string) {
		// TODO Auto-generated method stub
		
		ClientUI.client.accept(new Message(string, Commands.GetMaxPrice));
		return MyFuelClient.ServerRetObj;
	}

}
