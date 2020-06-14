package Entity;

import java.io.Serializable;

import javafx.scene.control.TextField;

public class Fuel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8904134409630964150L;
	private String fuelType;
	private double maxPrice;
	private String CompanyName;
	private String newMaxPrice; // for the GUI not to use

	public Fuel() {
	}

	public Fuel(String fuelType, double maxPrice, String CompanyName) {
		this.fuelType = fuelType;
		this.maxPrice = maxPrice;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getNewMaxPrice() {
		return newMaxPrice;
	}

	public void setNewMaxPrice(String newMaxPrice) {
		this.newMaxPrice = newMaxPrice;
	}

	@Override
	public String toString() {
		return "Fuel [fuelType=" + fuelType + ", maxPrice=" + maxPrice + ", CompanyName=" + CompanyName
				+ ", newMaxPrice=" + newMaxPrice + "]";
	}
	



}
