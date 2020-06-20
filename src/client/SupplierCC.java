package client;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Entity.GasStationOrder;
import Entity.Message;
import enums.Commands;
public class SupplierCC {


/**
 * The function create an object array	and create a new message to the server which contains the object arrayList
 * The function return GasStationOrder arrayList
 * @param supplierId
 * @param status
 * @return ArrayList<GasStationOrder>
 */
	
	public static ArrayList<GasStationOrder> getAllOrdersByStatus(String supplierId, String status){
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.add(supplierId);
		objs.add(status);
		ClientUI.client.accept(new Message(objs, Commands.getAllOrdersByStatus));
		return(ArrayList<GasStationOrder>)MyFuelClient.ServerRetObj;
	}
	
	
	/**
	 * The function create a new message to the server which contains the orders arrayList
	 * The function return boolean value
	 * @param orders
	 * @return boolean
	 */
	
	public static boolean updateGasOrdersStatus(ArrayList<GasStationOrder> orders) {
		ArrayList<Object> objs = new ArrayList<Object>();

		ClientUI.client.accept(new Message(orders, Commands.updateGasOrdersStatus));
		return (boolean) MyFuelClient.ServerRetObj;
	}
	
}
