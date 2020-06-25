package homeGasTests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entity.GasOrder;
import enums.OrderStatus;
import server.ConnectionToDB;
import server.CustomerGasOrderController;

class HomeGasServerTest {

	String customerID;
	GasOrder gasOrder;
	int purchaseID;
	
	@BeforeEach
	void setUp() throws Exception {
		//
		ConnectionToDB.connectToDB("myfueldb", "Aa123456");
		
		customerID="1";
		purchaseID=1;
		
		//
		//the db is empity in the begining so the index of the first purchase is 1
		//(because it is reset by each time)
		gasOrder = new GasOrder(purchaseID, customerID, "2020-02-02", "12:00", 200,
			"2020-03-02", 1200, false, OrderStatus.processing);
		//clear the table
		Statement stm = ConnectionToDB.conn.createStatement();
		
		stm.execute("delete FROM gasorder;");
		stm.execute("ALTER TABLE myfueldb.gasorder AUTO_INCREMENT  = 1;");
		
	}

	@Test
	void createNewValidOrderTest() {
		CustomerGasOrderController.createNewOrder(gasOrder);
		ArrayList<GasOrder> expected = new ArrayList<GasOrder>();
		expected.add(gasOrder);
		ArrayList<GasOrder> actual = CustomerGasOrderController.getOrders(customerID);
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void createNewNullOrderTest() {
		CustomerGasOrderController.createNewOrder(null);
		//the test will fail with null pointer expetion here
		ArrayList<GasOrder> expected = new ArrayList<GasOrder>();
		expected.add(gasOrder);
		ArrayList<GasOrder> actual = CustomerGasOrderController.getOrders(customerID);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void deleteValidOrderTest() {
		//there must be one order in the db
		CustomerGasOrderController.deleteOrder(gasOrder);
		ArrayList<GasOrder> actual = CustomerGasOrderController.getOrders(customerID);
		
		assertTrue(actual.isEmpty());
	}
	
	@Test
	void deleteUnexistingOrderEmpetyDBTest() {
		GasOrder secGasOrder = new GasOrder(purchaseID, customerID, "2020-02-02", "13:00", 200,
				"2020-03-02", 1200, false, OrderStatus.processing);
		CustomerGasOrderController.deleteOrder(secGasOrder);
		ArrayList<GasOrder> actual = CustomerGasOrderController.getOrders(customerID);
		
		assertTrue(actual.isEmpty());
	}
	
	@Test
	void deleteUnexistingOrderFilledDBTest() {
		CustomerGasOrderController.createNewOrder(gasOrder);
		ArrayList<GasOrder> expected = new ArrayList<GasOrder>();
		expected.add(gasOrder);
		
		GasOrder secGasOrder = new GasOrder(purchaseID, customerID, "2020-02-02", "13:00", 200,
				"2020-03-02", 1200, false, OrderStatus.processing);
		
		CustomerGasOrderController.deleteOrder(gasOrder);
		ArrayList<GasOrder> actual = CustomerGasOrderController.getOrders(customerID);
		
		assertEquals(expected, actual);
	}

}
