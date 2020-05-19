package boundery;

import Entity.Message;
import client.ClientUI;
import enums.Commands;

public class ClientCC {

	public static double getMaxPrice(String string) {
		// TODO Auto-generated method stub
		
		ClientUI.client.accept(new Message(string, Commands.GetMaxPrice));
		/*
		public static Object login(String username,String password) {
			ArrayList<String> data = new ArrayList<String>();
			data.add(username);
			data.add(password);
			ClientUI.client.accept(new Message(data, Commands.Login));
			return MyFuelClient.ServerRetObj;
		 */
		return 0;
	}

}
