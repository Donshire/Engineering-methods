package Entity;

import java.io.Serializable;

public class CompanyFuel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6279808662776147250L;
	private String companyName;
	private Fuel fuel;
	private float companyPrice;

	public CompanyFuel(String companyName, Fuel fuel, float companyPrice) {
		this.companyName = companyName;
		this.fuel = fuel;
		this.companyPrice = companyPrice;
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

	@Override
	public String toString() {
		return "CompanyFuel [companyName=" + companyName + ", fuel=" + fuel + ", companyPrice=" + companyPrice + "]";
	}

}
