package Entity;

import java.io.Serializable;

import javafx.scene.control.TextField;


/**
 * The Class Fuel.
 */
public class Fuel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8904134409630964150L;
	
	/** The fuel type. */
	private String fuelType;
	
	/** The max price of gas. */
	private double maxPrice;
	
	/** The Company name. */
	private String CompanyName;
	
	/** The new max price of gas. */
	private String newMaxPrice; // for the GUI not to use

	/**
	 * Instantiates a new fuel.
	 *
	 * @param fuelType the fuel type
	 * @param maxPrice the max price
	 */
	public Fuel(String fuelType, double maxPrice) {
		this.fuelType = fuelType;
		this.maxPrice = maxPrice;
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
	 * @param fuelType the new fuel type
	 */
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	/**
	 * Gets the max price of gas.
	 * @return the max price of gas
	 */
	public double getMaxPrice() {
		return maxPrice;
	}

	/**
	 * Sets the max price of gas.
	 * @param maxPrice the new max price of gas
	 */
	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * Gets the company name.
	 * @return the company name
	 */
	public String getCompanyName() {
		return CompanyName;
	}

	/**
	 * Sets the company name.
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	/**
	 * Gets the new max price of gas.
	 *
	 * @return the new max price of gas
	 */
	public String getNewMaxPrice() {
		return newMaxPrice;
	}

	/**
	 * Sets the new max price of gas.
	 *
	 * @param newMaxPrice the new new max price of gas
	 */
	public void setNewMaxPrice(String newMaxPrice) {
		this.newMaxPrice = newMaxPrice;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Fuel [fuelType=" + fuelType + ", maxPrice=" + maxPrice + ", CompanyName=" + CompanyName
				+ ", newMaxPrice=" + newMaxPrice + "]";
	}
	



}
