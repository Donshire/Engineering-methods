package stubs;

import java.util.ArrayList;

import Entity.GasOrder;

public class FakeCustomerCC implements CustomerCCI{

	@Override
	public ArrayList<GasOrder> GasOrderList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getMaxPrice(String str) {
		// TODO Auto-generated method stub
		return 5f;
	}

	@Override
	public boolean createNewOrder(GasOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
