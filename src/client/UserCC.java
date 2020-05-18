package client;

import java.util.ArrayList;

import Entity.Message;
import enums.Commands;

public class UserCC {
	
	public static Object login(String username,String password) {
		ArrayList<String> data = new ArrayList<String>();
		data.add(username);
		data.add(password);
		ClientUI.client.accept(new Message(data, Commands.Login));
		return MyFuelClient.ServerRetObj;
	}

}
