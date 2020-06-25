package stubs;

import java.util.ArrayList;

import Entity.GasOrder;
import Entity.Message;
import client.ClientUI;
import client.MyFuelClient;
import enums.Commands;

public interface CustomerCCI {
	
	public ArrayList<GasOrder> GasOrderList(String userId);
	
	public Object getMaxPrice(String str);

	public boolean createNewOrder(GasOrder order);
	
}
