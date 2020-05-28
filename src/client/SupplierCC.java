package client;

import java.util.ArrayList;

import Entity.GasStationOrder;
import Entity.Message;
import enums.Commands;
public class SupplierCC {

	public static ArrayList<GasStationOrder> getAllOrdersByStatus(String supplierId, String status){
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.add(supplierId);
		objs.add(status);
		ClientUI.client.accept(new Message(objs, Commands.getAllOrdersByStatus));
		return(ArrayList<GasStationOrder>)MyFuelClient.ServerRetObj;
	}
	
}
