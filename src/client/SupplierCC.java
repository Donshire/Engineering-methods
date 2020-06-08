package client;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.NEW;

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
	
	public static Message updateGasOrdersStatus(ArrayList<GasStationOrder> orders) {
		ArrayList<Object> objs = new ArrayList<Object>();

		ClientUI.client.accept(new Message(orders, Commands.updateGasOrdersStatus));
		return (Message) MyFuelClient.ServerRetObj;
	}
	
}
