package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Customer;
import Entity.Employee;
import Entity.Message;
import Entity.MyFile;
import Entity.Rates;
import Entity.Sale;
import Entity.Supplier;
import boundery.ServerController;
import client.EmployeeCC;
import enums.Commands;
import ocsf.server.*;

public class MyFuelServer extends AbstractServer {

	public static ArrayList<UserOnline> usersOnline = new ArrayList<UserOnline>();
	
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
				Object resltObject=LogINController.LogIn(arr.get(0), arr.get(1));
				//founded a user
				if(resltObject!=null) {
					if(resltObject instanceof Supplier) {
						Supplier supplier= (Supplier)resltObject;
						ServerController.onlineUserTableCont(supplier.getId(),"supplier");
						client.sendToClient(new Message(supplier, Commands.defaultRes));
					}
					if(resltObject instanceof Customer) {
						Customer customer= (Customer)resltObject;
						ServerController.onlineUserTableCont(customer.getId(),"customer");
						client.sendToClient(new Message(customer, Commands.defaultRes));
					}
					if(resltObject instanceof Employee) {
						Employee employee= (Employee)resltObject;
						ServerController.onlineUserTableCont(employee.getId(),employee.getRole());
						client.sendToClient(new Message(employee, Commands.defaultRes));
					}
				}
				client.sendToClient(new Message(resltObject, Commands.defaultRes));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case logOut:
			try {
				ArrayList<String> arr = (ArrayList<String>) (message.getObj());
				String tableName=arr.get(1).toLowerCase().substring(13, arr.get(1).length());
				LogINController.updateUserOnlineStatus(tableName,arr.get(0), 0);
				ServerController.onlineUserTableCont(arr.get(0),"");
				try {
					client.sendToClient(new Message(null, Commands.defaultRes));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case updateFuelRate:
			ArrayList<Rates> rates = (ArrayList<Rates>) message.getObj();
			try {
				boolean flag = true;
				for (Rates rate : rates) {
					if (!CompanyFuelControllerServer.updateRateStatus(rate))
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

		case getSaleResponseReport:
			int salID = (int) message.getObj();
			try {
				client.sendToClient(new Message(CompanyFuelControllerServer.doh(salID), Commands.defaultRes));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case getAllCompanyFuel:
			String companyName = (String) message.getObj();
			try {
				client.sendToClient(new Message(CompanyFuelControllerServer.getAllCompanyFuelTypes(companyName),Commands.defaultRes));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case getCompanyFuel:
			ArrayList<String> compName = (ArrayList<String>) message.getObj();
			try {
				client.sendToClient(new Message(CompanyFuelControllerServer.getCompanyFuel(compName.get(0), compName.get(1)),Commands.defaultRes));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default:
			System.out.println("default");
		}

	}

	/**
	 * convert File To Seializable
	 * @param fileName
	 * @param filePath
	 * @return
	 */
	
	private MyFile convertFileToSeializable(String fileName,String filePath) {
		MyFile msg = new MyFile(fileName);
		String LocalfilePath = filePath+"\\"+fileName;
		try {
			File newFile = new File(LocalfilePath);

			byte[] mybytearray = new byte[(int) newFile.length()];
			FileInputStream fis = new FileInputStream(newFile);
			BufferedInputStream bis = new BufferedInputStream(fis);

			msg.initArray(mybytearray.length);
			msg.setSize(mybytearray.length);

			bis.read(msg.getMybytearray(), 0, mybytearray.length);
		} catch (Exception e) {
			System.out.println("Error send (Files)msg) to Server");
		}
		return msg;
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
		//log out all the users
		/*
		for(UserOnline user : usersOnline) {
				try {
					if(user.userType.compareTo("customer")==0)
					LogINController.updateUserOnlineStatus("customer",user.getUserID(), 0);
					
					if(user.userType.compareTo("supplier")==0)
					LogINController.updateUserOnlineStatus("customer",user.getUserID(), 0);
					
					else 
					LogINController.updateUserOnlineStatus("customer",user.getUserID(), 0);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			ServerController.onlineUserTableCont(user.getUserID(),"");
		}
		*/
	}
	
	//just to show in the GUI
	/**
	 * String UserID ,String userType
	 * @author iamme
	 *
	 */
		public static class UserOnline{
			String UserID;
			String userType;
			/**
			 * 
			 * @param userID
			 * @param userType
			 */
			public UserOnline(String userID, String userType) {
				UserID = userID;
				this.userType = userType;
			}
			public String getUserID() {
				return UserID;
			}
			public void setUserID(String userID) {
				UserID = userID;
			}
			public String getUserType() {
				return userType;
			}
			public void setUserType(String userType) {
				this.userType = userType;
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				UserOnline other = (UserOnline) obj;
				if (UserID == null) {
					if (other.UserID != null)
						return false;
				} else if (!UserID.equals(other.UserID))
					return false;
				return true;
			}
			@Override
			public String toString() {
				return "UserOnline [UserID=" + UserID + ", userType=" + userType + "]";
			}
			
		}
}