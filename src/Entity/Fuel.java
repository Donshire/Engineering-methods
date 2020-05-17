package Entity;

public class Fuel {
	private String fuelType;
	private float maxPrice;

	public Fuel(String fuelType, float maxPrice) {
		super();
		this.fuelType = fuelType;
		this.maxPrice = maxPrice;
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

	@Override
	public String toString() {
		return "Fuel [fuelType=" + fuelType + ", maxPrice=" + maxPrice + "]";
	}

}
