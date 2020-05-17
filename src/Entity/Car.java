package Entity;

public class Car {

	private String carNumber;
	private String fuelType;
	private String CustomerID;
	
	public Car(String carNumber, String fuelType, String CustomerID) {
		this.carNumber = carNumber;
		this.fuelType = fuelType;
		this.CustomerID = CustomerID;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getCarCustomerId() {
		return CustomerID;
	}

	public void setCarCustomerId(String CustomerID) {
		this.CustomerID = CustomerID;
	}

	@Override
	public String toString() {
		return "Car [carNumber=" + carNumber + ", fuelType=" + fuelType + ", Customer ID=" + CustomerID + "]";
	}
	
}
