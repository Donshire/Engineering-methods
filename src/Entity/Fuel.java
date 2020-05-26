package Entity;

import java.io.Serializable;

public class Fuel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8904134409630964150L;
	private String fuelType;
	private double maxPrice;

	public Fuel() {
	}
	
	public Fuel(String fuelType, double maxPrice) {
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

	@Override
	public String toString() {
		return "Fuel [fuelType=" + fuelType + ", maxPrice=" + maxPrice + "]";
	}

}

