package Entity;

import java.io.Serializable;

import boundery.CheckBoxImplementation;
import enums.RatesStatus;

public class PricingModule extends CheckBoxImplementation implements Serializable {

	private int modelNumber;
	private float salePercent;
	private RatesStatus status;
	private String CompanyName;
 

	public PricingModule(int modelNumber, float salePercent, String companyName, RatesStatus status) {
		this.modelNumber = modelNumber;
		this.salePercent = salePercent;
		this.status = status;
		CompanyName = companyName;
	}

	public PricingModule(int modelNumber, float salePercent) {
		this.modelNumber = modelNumber;
		this.salePercent = salePercent;
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

	public RatesStatus getStatus() {
		return status;
	}

	public void setStatus(RatesStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PricingModule [modelNumber=" + modelNumber + ", salePercent=" + salePercent + ", status=" + status
				+ ", CompanyName=" + CompanyName + "]";
	}



}
