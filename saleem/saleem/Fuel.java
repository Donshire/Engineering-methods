package Yosef;

public class Fuel {
	private String fuelType;
	private float maxPrice;
	private float currentPrice;
	private String customerID;

	public Fuel(String fuelType, float maxPrice, float currentPrice, GasStation station) {
		this.fuelType = fuelType;
		this.maxPrice = maxPrice;
		this.currentPrice = currentPrice;

	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public float getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}
	public float getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	@Override
	public String toString() {
		return "Fuel [fuelType=" + fuelType + ", maxPrice=" + maxPrice + ", currentPrice=" + currentPrice + "]";
	}

	
}
