package Entity;

public class PricingModule {

	private int modelNumber;
	private float salePercent;
	private String CompanyName;
	
	public PricingModule(int modelNumber, float salePercent) {
		this.modelNumber = modelNumber;
		this.salePercent = salePercent;
	}

	public PricingModule(int modelNumber, float salePercent, String companyName) {
		super();
		this.modelNumber = modelNumber;
		this.salePercent = salePercent;
		CompanyName = companyName;
	}

	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	public float getSalePercent() {
		return salePercent;
	}

	public void setSalePercent(float salePercent) {
		this.salePercent = salePercent;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	@Override
	public String toString() {
		return "PricingModule [modelNumber=" + modelNumber + ", salePercent=" + salePercent + ", CompanyName="
				+ CompanyName + "]";
	}
	
}
