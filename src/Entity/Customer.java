package Entity;

import java.io.Serializable;

/**
 * The Class Customer.
 */
public class Customer extends User implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1862468645901009080L;
	
	/** The adress. */
	private String adress;
	
	/** The pricing model. */
	private int pricingModel;
	
	/** The purchase module. */
	private int purchaseModule;
	
	/** The visa number. */
	private String visaNumber;
	
	/** The exp date. */
	private String expDate;
	
	/** The cvv. */
	private String CVV;
	
	/** The customer type. */
	private String customerType;
	
	/** The company name. */
	private String companyName;

	/**
	 * Instantiates a new customer.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param mail the mail
	 * @param id the id
	 * @param phoneNumber the phone number
	 * @param online the online
	 * @param adress the adress
	 * @param pricingModel the pricing model
	 * @param purchaseModule the purchase module
	 * @param visaNumber the visa number
	 * @param expDate the exp date
	 * @param cVV the c VV
	 * @param customerType the customer type
	 * @param companyName the company name
	 */
	public Customer(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber, int online, String adress, int pricingModel, int purchaseModule, String visaNumber,
			String expDate, String cVV, String customerType, String companyName) {
		super(userName, password, firstName, lastName, mail, id, phoneNumber, online);
		this.adress = adress;
		this.pricingModel = pricingModel;
		this.purchaseModule = purchaseModule;
		this.visaNumber = visaNumber;
		this.expDate = expDate;
		CVV = cVV;
		this.customerType = customerType;
		this.companyName = companyName;
	}

	/**
	 * Gets the customer type.
	 *
	 * @return the customer type
	 */
	public String getCustomerType() {
		return customerType;
	}

	/**
	 * Sets the customer type.
	 *
	 * @param customerType the new customer type
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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
	 * Gets the visa number.
	 *
	 * @return the visa number
	 */
	public String getVisaNumber() {
		return visaNumber;
	}

	/**
	 * Sets the visa number.
	 *
	 * @param visaNumber the new visa number
	 */
	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	/**
	 * Gets the adress.
	 *
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * Sets the adress.
	 *
	 * @param adress the new adress
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * Gets the pricing model.
	 *
	 * @return the pricing model
	 */
	public int getPricingModel() {
		return pricingModel;
	}

	/**
	 * Sets the pricing model.
	 *
	 * @param pricingModel the new pricing model
	 */
	public void setPricingModel(int pricingModel) {
		this.pricingModel = pricingModel;
	}

	/**
	 * Gets the purchase module.
	 *
	 * @return the purchase module
	 */
	public int getPurchaseModule() {
		return purchaseModule;
	}

	/**
	 * Sets the purchase module.
	 *
	 * @param purchaseModule the new purchase module
	 */
	public void setPurchaseModule(int purchaseModule) {
		this.purchaseModule = purchaseModule;
	}

	/**
	 * Gets the exp date.
	 *
	 * @return the exp date
	 */
	public String getExpDate() {
		return expDate;
	}

	/**
	 * Sets the exp date.
	 *
	 * @param expDate the new exp date
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	/**
	 * Gets the cvv.
	 *
	 * @return the cvv
	 */
	public String getCVV() {
		return CVV;
	}

	/**
	 * Sets the cvv.
	 *
	 * @param cVV the new cvv
	 */
	public void setCVV(String cVV) {
		CVV = cVV;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Customer [adress=" + adress + ", pricingModel=" + pricingModel + ", purchaseModule=" + purchaseModule
				+ ", visaNumber=" + visaNumber + ", expDate=" + expDate + ", CVV=" + CVV + ", customerType="
				+ customerType + ", companyName=" + companyName + "]";
	}

}
