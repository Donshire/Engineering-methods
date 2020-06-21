package Entity;

import enums.CustomerRateTypes;
import enums.RatesStatus;
import helpinigStructForGUI.CheckBoxImplementation;
import java.io.Serializable;

/**
 * The Class PricingModule.
 */
public class PricingModule extends CheckBoxImplementation implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3160490784670591070L;

	/** The model number. */
	private int modelNumber;
	
	/** The sale percent. */
	private String salePercent;
	
	/** The company name. */
	private String companyName;
	
	/** The status. */
	private RatesStatus status;
	
	/** The modelname. */
	// just for tables
	private String modelname;

	/**
	 * Instantiates a new pricing module.
	 *
	 * @param modelNumber the model number
	 * @param salePercent the sale percent
	 */
	public PricingModule(int modelNumber, String salePercent) {
		this.modelNumber = modelNumber;
		this.salePercent = salePercent;
	}

	/**
	 * Instantiates a new pricing module.
	 *
	 * @param modelNumber the model number
	 * @param salePercent the sale percent
	 * @param companyName the company name
	 * @param status the status
	 */
	public PricingModule(int modelNumber, String salePercent, String companyName, RatesStatus status) {
		this.modelNumber = modelNumber;
		this.salePercent = salePercent;
		this.companyName = companyName;
		this.status = status;
		this.modelname = CustomerRateTypes.values()[modelNumber].toString();
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public RatesStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(RatesStatus status) {
		this.status = status;
	}

	/**
	 * Gets the modelname.
	 *
	 * @return the modelname
	 */
	public String getModelname() {
		return modelname;
	}

	/**
	 * Gets the model number.
	 *
	 * @return the model number
	 */
	public int getModelNumber() {
		return modelNumber;
	}

	/**
	 * Sets the model number.
	 *
	 * @param modelNumber the new model number
	 */
	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
		this.modelname = CustomerRateTypes.values()[modelNumber].toString();
	}

	/**
	 * Gets the sale percent.
	 *
	 * @return the sale percent
	 */
	public String getSalePercent() {
		return salePercent;
	}

	/**
	 * Sets the sale percent.
	 *
	 * @param salePercent the new sale percent
	 */
	public void setSalePercent(String salePercent) {
		this.salePercent = salePercent;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PricingModule [modelNumber=" + modelNumber + ", salePercent=" + salePercent + ", CompanyName="
				+ companyName + "]";
	}

}
