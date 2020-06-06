package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Entity.Car;
import Entity.Customer;
import Entity.CustomerModule;
import Entity.Employee;
import Entity.GasStationOrder;
import Entity.FuelPurchase;
import Entity.Message;
import Entity.MyFile;
import Entity.Rates;
import Entity.Sale;
import Entity.StationFuel;
import Entity.Supplier;
import boundery.ServerController;
import client.EmployeeCC;
import enums.Commands;
import enums.StationManagerReportsTypes;
import ocsf.server.*;
import server.GasStationControllerServer;

public class MyFuelServer extends AbstractServer {

	public static ArrayList<UserOnline> usersOnline = new ArrayList<UserOnline>();
	public static String schemaName, dbPassword;
	
	ArrayList<GasStationOrder> orders;
	String status;
	
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
				Object resltObject = LogINController.LogIn(arr.get(0), arr.get(1));
				// founded a user
				if (resltObject != null) {
					if (resltObject instanceof Supplier) {
						Supplier supplier = (Supplier) resltObject;
						ServerController.onlineUserTableCont(supplier.getId(), "supplier");
						client.sendToClient(new Message(supplier, Commands.defaultRes));
					}
					if (resltObject instanceof Customer) {
						Customer customer = (Customer) resltObject;
						ServerController.onlineUserTableCont(customer.getId(), "customer");
						client.sendToClient(new Message(customer, Commands.defaultRes));
					}
					if (resltObject instanceof Employee) {
						Employee employee = (Employee) resltObject;
						ServerController.onlineUserTableCont(employee.getId(), employee.getRole());
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
		case fastFuelingLogIn:
			String carNumber=(String)message.getObj();
			try {
				//
				client.sendToClient(new Message(FastFuelController.fastFuelingLogIn(carNumber), Commands.defaultRes));
			} catch (IOException e) {
				e.printStackTrace();
			}
		case getAllCompanyFuelStationID:
			String companyName=(String)message.getObj();
			try {
				client.sendToClient(new Message(FastFuelController.getAllCompanyFuelStationID(companyName), Commands.defaultRes));
			} catch (IOException e) {
				e.printStackTrace();
			}
            break;
		case getPurchasePriceDetails:
			ArrayList<Object> str =(ArrayList<Object>)message.getObj();
			try {
				client.sendToClient(new Message(FastFuelController.priceCalculationAndPricingModel
						((String)str.get(0),(CustomerModule)str.get(1),(int)str.get(2)), Commands.defaultRes));
			} catch (IOException e) {
				e.printStackTrace();
			}
            break;
		case commitFuelPurchase:
			ArrayList<Object> str1 =(ArrayList<Object>)message.getObj();
			try {
				client.sendToClient(new Message(FastFuelController.commitFuelPurchase((String)str1.get(0),(String)str1.get(1),(FuelPurchase)str1.get(2)), Commands.defaultRes));
			} catch (IOException e) {
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
			
		case updateGasOrdersStatus:
			ArrayList<GasStationOrder> orders = (ArrayList<GasStationOrder>) message.getObj();
			try {
				boolean flag = true;
				for (GasStationOrder order : orders) {
					if(!GasStationControllerServer.updateOrderStatus(order))
						flag=false;
				}
				client.sendToClient(new Message(flag, Commands.defaultRes));
			} catch(IOException e) {
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
			ArrayList<String> array = (ArrayList<String>) message.getObj();
			//salID,CompanyName 
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				Date date = new Date();

				File file=CompanyFuelControllerServer.responseReport(Integer.valueOf(array.get(0)), array.get(1), dateFormat.format(date), timeFormat.format(date));
				if(file!=null)
				client.sendToClient(new Message(convertFileToSeializable(file), Commands.reciveFile));
				else client.sendToClient(new Message("", Commands.ThereIsNoSale));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case getPeriodicReport:
			String companyName_getPeriodicReport = (String) message.getObj();
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				Date date = new Date();

				File file=CompanyFuelControllerServer.periodicReport(companyName_getPeriodicReport,dateFormat.format(date), timeFormat.format(date));
				System.out.println("created the file");
				client.sendToClient(new Message(convertFileToSeializable(file), Commands.reciveFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case getAllCompanyFuel:
			String companyName1 = (String) message.getObj();
			try {
				client.sendToClient(new Message(CompanyFuelControllerServer.getAllCompanyFuelTypes(companyName1),Commands.defaultRes));
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
		case getAllOrdersByStatus:
			ArrayList<Object> objs = (ArrayList<Object>) message.getObj();
			String supplierId = (String)objs.get(0);
			String status =(String) objs.get(1);
			try {
				client.sendToClient(new Message(GasStationControllerServer.getAllOrdersByStatus(supplierId,status),Commands.defaultRes));
			}catch (Exception e) {
				// TODO: handle exception
			}
			break;
			
			
		case GetMaxPrice:
			try {
				client.sendToClient(new Message(CompanyFuelController.getMaxPrice((String) message.getObj()), Commands.GetMaxPriceRes));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

			
		case CustomerOrderList:
			String s = (String) (message.getObj());
			try {
				client.sendToClient(new Message(MyOrderConrtollerServer.getOrders(s),Commands.defaultRes));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case addNewCustomer:
			Customer cust = (Customer)(message.getObj());
			try {
				client.sendToClient(new Message(EmployeeController.addNewCustmer(cust),Commands.defaultRes));
			}catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case checkIfExist:
			String id = (String)(message.getObj());
			try {
				client.sendToClient(new Message(EmployeeController.checkIfExist(id),Commands.defaultRes));
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
			break;
			
		case addNewCar:
			Car car = (Car)(message.getObj());
			try {
				client.sendToClient(new Message(EmployeeController.addNewCar(car),Commands.defaultRes));
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		case updateModels:
			ArrayList<Object> objlist = (ArrayList<Object>) message.getObj();
			String pricing =(String)objlist.get(0);
			String purchase =(String)objlist.get(1);
			String cuId = (String)objlist.get(2);
			try {
				client.sendToClient(new Message(EmployeeController.updateModels(pricing, purchase, cuId),Commands.defaultRes));
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case getAllstationOrdersBystatus:
			ArrayList<Object> o = (ArrayList<Object>) message.getObj();
			int stationid = (int) o.get(0);
			status = (String) o.get(1);

			try {
				client.sendToClient(new Message(GasStationControllerServer.getAllstationOrdersByStatus(stationid, status),
						Commands.defaultRes));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case approveOrders:

			orders = (ArrayList<GasStationOrder>) message.getObj();

			try {
				client.sendToClient(new Message(GasStationControllerServer.changeOrdersStatus(orders), Commands.defaultRes));
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		case getAllStationFuelById:

			int stationId = (Integer) message.getObj();
			try {
				client.sendToClient(
						new Message(GasStationControllerServer.getAllStationFuel(stationId), Commands.defaultRes));
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		case updateFuelMinQuantitybyType:
			ArrayList<Object> k = (ArrayList<Object>) message.getObj();

			try {
				client.sendToClient(new Message(GasStationControllerServer.updateFuelMinQuantitybyType((int) k.get(0),
						(String) k.get(1), (float) k.get(2)), Commands.defaultRes));
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		case createFuelStationIncmomeReport:

			int st_id = (int) message.getObj();
			try {
				client.sendToClient(
				new Message(GasStationControllerServer.createFuelStationIncmomeReport(st_id), Commands.defaultRes));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
			
			case createFuelStationPurchasesReport:
				
				int st_id1 = (int) message.getObj();
				
				try {
					client.sendToClient(
					new Message(GasStationControllerServer.createFuelStationPurchasesReport(st_id1), Commands.defaultRes));
				} catch (Exception e) {
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
	
	private MyFile convertFileToSeializable(File file) {
		MyFile msg = new MyFile(file.getName());
		try {
			byte[] mybytearray = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);

			msg.initArray(mybytearray.length);
			msg.setSize(mybytearray.length);

			bis.read(msg.getMybytearray(), 0, mybytearray.length);
		} catch (Exception e) {
			System.out.println("Error send ((Files)msg) to Server");
		}
		System.out.println("file had been converted");
		return msg;
	}
	
	protected void serverStarted() {
		ConnectionToDB.connectToDB(schemaName, dbPassword);
		// ServerController.writeToServerConsole("Server listening for connections on
		// port " + getPort());
		FileManagmentSys.createSystemWorkSpace();
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