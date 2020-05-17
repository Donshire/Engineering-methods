package server;

import java.io.IOException;
import java.util.ArrayList;

import Entity.Customer;
import Entity.Message;
import enums.Commands;
import ocsf.server.*;

public class MyFuelServer extends AbstractServer {

	public MyFuelServer(int port) {
		super(port);
	}

	// this.sendToAllClients(students[i].toString());

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Message message = (Message) msg;

		System.out.println(
				"Message received: cmd " + message.getCmd() + " the object " + message.getObj() + " from " + client);

		switch (message.getCmd()) {
		case Login:
			try {
				ArrayList<String> arr = (ArrayList<String>) (message.getObj());
				client.sendToClient(new Message(LogINController.LogIn(arr.get(0), arr.get(1)), Commands.LoginRes));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("default");
		}

	}

	protected void serverStarted() {
		ConnectionToDB.connectToDB("myfueldb", "Aa123456");
		System.out.println("Server listening for connections on port " + getPort());
	}

	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}
}