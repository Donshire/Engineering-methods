package Entity;

import java.io.Serializable;

/**
 * The Class CompanyFuel.
 */
public class CompanyFuel implements Serializable,Comparable<CompanyFuel>{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6279808662776147250L;
	
	/** The company name. */
	private String companyName;
	
	/** The fuel. */
	private Fuel fuel;
	
	/** The fuel type. */
	private String fuelType;
	
	/** The company price. */
	private float companyPrice;
	
	/** The new max price. */
	private String newMaxPrice; // for the GUI not to use
	
	/**
	 * with refrence.
	 *
	 * @param companyName the company name
	 * @param fuel the fuel
	 * @param companyPrice the company price
	 */
	public CompanyFuel(String companyName, Fuel fuel, float companyPrice) {
		this.companyName = companyName;
		this.fuel = fuel;
		this.companyPrice = companyPrice;
		fuelType=fuel.getFuelType();
	}
	
	/**
	 * without refrence.
	 *
	 * @param companyName the company name
	 * @param fuelType the fuel type
	 * @param companyPrice the company price
	 */
	public CompanyFuel(String companyName, String fuelType, float companyPrice) {
		this.companyName = companyName;
		this.fuelType = fuelType;
		this.companyPrice = companyPrice;
		fuel=null;
	}

	/**
	 * Gets the new max price.
	 *
	 * @return the new max price
	 */
	public String getNewMaxPrice() {
		return newMaxPrice;
	}

	/**
	 * Sets the new max price.
	 *
	 * @param newMaxPrice the new new max price
	 */
	public void setNewMaxPrice(String newMaxPrice) {
		this.newMaxPrice = newMaxPrice;
	}
	
	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the fuel.
	 *
	 * @return the fuel
	 */
	public Fuel getFuel() {
		return fuel;
	}

	/**
	 * Sets the fuel.
	 *
	 * @param fuel the new fuel
	 */
	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	/**
	 * Gets the company price.
	 *
	 * @return the company price
	 */
	public float getCompanyPrice() {
		return companyPrice;
	}

	/**
	 * Sets the company price.
	 *
	 * @param companyPrice the new company price
	 */
	public void setCompanyPrice(float companyPrice) {
		this.companyPrice = companyPrice;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "CompanyFuel [companyName=" + companyName + ", fuel=" + fuel + ", fuelType=" + fuelType
				+ ", companyPrice=" + companyPrice + "]";
	}

	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(CompanyFuel o) {
		if(this.companyPrice>o.companyPrice)return 1;
		if(this.companyPrice<o.companyPrice)return -1;
		return 0;
	}

}
