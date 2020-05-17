package Entity;

public class CompanyFuel {

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
