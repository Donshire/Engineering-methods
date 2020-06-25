package homeGasTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boundery.CustomerGuiController;
import stubs.FakeCustomerCC;

class HomeGasClientTest {

	int below600=550,mid600_800=700,plus800=900;
	
	@BeforeEach
	void setUp() throws Exception {
		//the stub will return always the price as 5
		CustomerGuiController.setContoller(new FakeCustomerCC());
	}

	@Test
	void calculatePriceForImediate() {
		//ugret = true
		float expected = (float) (below600*(1+0.02))*5;
		float actual = CustomerGuiController.calculatePrice(CustomerGuiController.getHomeGasPrice()
				, below600, true);
		assertEquals(expected, actual);
	}
	
	@Test
	void calculatePriceForNonImediatebelow600() {
		//ugret = false
		float expected = below600*5;
		float actual = CustomerGuiController.calculatePrice(CustomerGuiController.getHomeGasPrice()
				, below600, false);
		assertEquals(expected, actual);
	}
	
	@Test
	void calculatePriceForNonImediatemid600_800() {
		//ugret = false
		float expected = (float) (mid600_800*(1-0.03))*5;
		float actual = CustomerGuiController.calculatePrice(CustomerGuiController.getHomeGasPrice()
				, mid600_800, false);
		assertEquals(expected, actual);
	}
	
	@Test
	void calculatePriceForNonImediateplus800() {
		//ugret = false
		float expected = (float) (plus800*(1-0.04))*5;
		float actual = CustomerGuiController.calculatePrice(CustomerGuiController.getHomeGasPrice()
				, plus800, false);
		assertEquals(expected, actual);
	}

}
