package server;

import java.io.IOException;
import java.util.ArrayList;

import Entity.Customer;
import Entity.Employee;
import Entity.Message;
import Entity.Rates;
import Entity.Sale;
import boundery.ServerController;
import client.EmployeeCC;
import enums.Commands;
import ocsf.server.*;

public class MyFuelServer extends AbstractServer {

	public MyFuelServer(int port) {
		super(port);
	}

	// this.sendToAllClients(students[i].toString());

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Message message = (Message) msg;

		ServerController.writeToServerConsole(
				"Message received: cmd " + message.getCmd() + " the object " + message.getObj() + " from " + client);

		switch (message.getCmd()) {
		case Login:
			try {
				ArrayList<String> arr = (ArrayList<String>) (message.getObj());
				client.sendToClient(new Message(LogINController.LogIn(arr.get(0), arr.get(1)), Commands.defaultRes));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case updateFuelRate:

			ArrayList<Rates> rates = (ArrayList<Rates>) message.getObj();
			try {
				boolean flag = true;
				for (Rates rate : rates) {
					if (!CompanyFuelControllerServer.updateRateStatus(rate.getRateId(), rate.getStatus()))
						flag = false;
				}
				client.sendToClient(new Message(flag, Commands.defaultRes));

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case updateSale:
			ArrayList<Sale> sales_updateSale = (ArrayList<Sale>) message.getObj();
			try {
				boolean flag = true;
				for (Sale sale : sales_updateSale) {
					if (!CompanyFuelControllerServer.updateSale(sale))
						flag = false;
				}
				client.sendToClient(new Message(flag, Commands.defaultRes));

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case getAllCompanySalesByStatus:
			Sale salesPartialData = (Sale) message.getObj();
			// partial means that not all the data are filled
			try {
				ArrayList<Sale> sales_getAllCompanySalesByStatus = CompanyFuelControllerServer
						.getAllCompanySalesByStatus(salesPartialData.getCompanyName(), salesPartialData.getStatus());
				client.sendToClient(new Message(sales_getAllCompanySalesByStatus, Commands.defaultRes));

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case getAllCompanyRatesByStatus:
			Rates rate_getAllCompanyRatesByStatus = (Rates) message.getObj();
			try {
				client.sendToClient(new Message(CompanyFuelControllerServer.getAllCompanyRatesByStatus(
						rate_getAllCompanyRatesByStatus.getCompanyName(), rate_getAllCompanyRatesByStatus.getStatus()),
						Commands.defaultRes));

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case saveRate:
			Rates rate_saveRate = (Rates) message.getObj();
			try {
				client.sendToClient(
						new Message(CompanyFuelControllerServer.saveRate(rate_saveRate), Commands.defaultRes));

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("default");
		}

	}

	protected void serverStarted() {
		ConnectionToDB.connectToDB("myfueldb", "Aa123456");
		// ServerController.writeToServerConsole("Server listening for connections on
		// port " + getPort());
		System.out.println("Server listening for connections on port " + getPort());
	}

	protected void serverStopped() {
		// ServerController.writeToServerConsole("Server has stopped listening for
		// connections.");
		System.out.println("Server has stopped listening for connections.");
	}
}