package Entity;

import java.io.Serializable;

/**
 * The Class Car.
 */
public class Car implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3697082777987571143L;
	
	/** The car number. */
	private String carNumber;
	
	/** The fuel type. */
	private String fuelType;
	
	/** The Customer ID. */
	private String CustomerID;
	
	/**
	 * Instantiates a new car.
	 *
	 * @param carNumber the car number
	 * @param fuelType the fuel type
	 * @param CustomerID the customer ID
	 */
	public Car(String carNumber, String fuelType, String CustomerID) {
		this.carNumber = carNumber;
		this.fuelType = fuelType;
		this.CustomerID = CustomerID;
	}

	/**
	 * Gets the car number.
	 *
	 * @return the car number
	 */
	public String getCarNumber() {
		return carNumber;
	}

	/**
	 * Sets the car number.
	 *
	 * @param carNumber the new car number
	 */
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	/**
	 * Gets the fuel type.
	 *
	 * @return the fuel type
	 */
	public String getFuelType() {
		return fuelType;
	}

	/**
	 * Sets the fuel type.
	 *
	 * @param fuelType the new fuel type
	 */
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	/**
	 * Gets the car customer id.
	 *
	 * @return the car customer id
	 */
	public String getCarCustomerId() {
		return CustomerID;
	}

	/**
	 * Sets the car customer id.
	 *
	 * @param CustomerID the new car customer id
	 */
	public void setCarCustomerId(String CustomerID) {
		this.CustomerID = CustomerID;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Car [carNumber=" + carNumber + ", fuelType=" + fuelType + ", Customer ID=" + CustomerID + "]";
	}
	
}
