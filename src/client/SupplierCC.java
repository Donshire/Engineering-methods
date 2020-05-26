package client;

import java.util.ArrayList;

import Entity.GasStationOrder;
import Entity.Message;
import enums.Commands;
public class SupplierCC {

	public static ArrayList<GasStationOrder> getAllOrdersByStatus(GasStationOrder order){
		ClientUI.client.accept(new Message(order, Commands.getAllOrdersByStatus));
		return(ArrayList<GasStationOrder>)MyFuelClient.ServerRetObj;
	}
	
}
