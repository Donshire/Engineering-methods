package Entity;

import enums.CustomerRateTypes;
import enums.RatesStatus;
import helpinigStructForGUI.CheckBoxImplementation;
import java.io.Serializable;

public class PricingModule extends CheckBoxImplementation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3160490784670591070L;

	private int modelNumber;
	private String salePercent;
	private String companyName;
	private RatesStatus status;
	// just for tables
	private String modelname;

	public PricingModule(int modelNumber, String salePercent) {
		this.modelNumber = modelNumber;
		this.salePercent = salePercent;
	}

	public PricingModule(int modelNumber, String salePercent, String companyName, RatesStatus status) {
		this.modelNumber = modelNumber;
		this.salePercent = salePercent;
		this.companyName = companyName;
		this.status = status;
		this.modelname = CustomerRateTypes.values()[modelNumber].toString();
	}

	public RatesStatus getStatus() {
		return status;
	}

	public void setStatus(RatesStatus status) {
		this.status = status;
	}

	public String getModelname() {
		return modelname;
	}

	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
		this.modelname = CustomerRateTypes.values()[modelNumber].toString();
	}

	public String getSalePercent() {
		return salePercent;
	}

	public void setSalePercent(String salePercent) {
		this.salePercent = salePercent;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "PricingModule [modelNumber=" + modelNumber + ", salePercent=" + salePercent + ", CompanyName="
				+ companyName + "]";
	}

}
