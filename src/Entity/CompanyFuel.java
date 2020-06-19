package Entity;

import java.io.Serializable;

public class CompanyFuel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6279808662776147250L;
	private String companyName;
	private Fuel fuel;
	private String fuelType;
	private float companyPrice;
	String fuelType;
	private String newMaxPrice; // for the GUI not to use
	
	/**
	 * with refrence
	 * 
	 * @param companyName
	 * @param fuel
	 * @param companyPrice
	 */
	public CompanyFuel(String companyName, Fuel fuel, float companyPrice) {
		this.companyName = companyName;
		this.fuel = fuel;
		this.companyPrice = companyPrice;
		fuelType=fuel.getFuelType();
	}
	
	/**
	 * without refrence
	 * @param companyName
	 * @param fuelType
	 * @param companyPrice
	 */
	public CompanyFuel(String companyName, String fuelType, float companyPrice) {
		this.companyName = companyName;
		this.fuelType = fuelType;
		this.companyPrice = companyPrice;
		fuel=null;
	}
	
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	public CompanyFuel(String companyName, String fuelType, float companyPrice) {
		this.companyName = companyName;
		this.fuelType = fuelType;
		this.companyPrice = companyPrice;
	}

	public String getNewMaxPrice() {
		return newMaxPrice;
	}

	public void setNewMaxPrice(String newMaxPrice) {
		this.newMaxPrice = newMaxPrice;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Fuel getFuel() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	public float getCompanyPrice() {
		return companyPrice;
	}

	public void setCompanyPrice(float companyPrice) {
		this.companyPrice = companyPrice;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public String toString() {
		return "CompanyFuel [companyName=" + companyName + ", fuel=" + fuel + ", fuelType=" + fuelType
				+ ", companyPrice=" + companyPrice + "]";
	}

	@Override
	public int compareTo(CompanyFuel o) {
		if(this.companyPrice>o.companyPrice)return 1;
		if(this.companyPrice<o.companyPrice)return -1;
		return 0;
	}

}
