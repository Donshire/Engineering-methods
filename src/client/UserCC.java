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
	
	public static void logOut(String userID,String className) {
		ArrayList<String> array = new ArrayList<String>();
		array.add(userID);
		array.add(className);
		ClientUI.client.accept(new Message(array, Commands.logOut));
	}

	public static Object fastFuelingLogIn(String catNumber) {
		ClientUI.client.accept(new Message(catNumber, Commands.fastFuelingLogIn));
		return MyFuelClient.ServerRetObj;
	}
}
