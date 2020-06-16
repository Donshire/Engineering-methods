package Entity;

import java.io.Serializable;

public class Customer extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1862468645901009080L;
	private String adress;
	private int pricingModel;
	private int purchaseModule;
	private int customerTypeAnaleticRank;
	private int fuelingHourAnaleticRank;
	private String visaNumber;
	private String expDate;
	private String CVV;
	private String customerType;
	private String companyName;

	public Customer(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber, int online, String adress, int pricingModel, int customerTypeAnaleticRank,
			int purchaseModule, int fuelingHourAnaleticRank, String visaNumber, String expDate, String CVV,String customerType, String companyName) {
		super(userName, password, firstName, lastName, mail, id, phoneNumber, online);
		this.adress = adress;
		this.pricingModel = pricingModel;
		this.purchaseModule = purchaseModule;
		this.customerTypeAnaleticRank = customerTypeAnaleticRank;
		this.fuelingHourAnaleticRank = fuelingHourAnaleticRank;
		this.visaNumber = visaNumber;
		this.expDate = expDate;
		this.CVV = CVV;
		this.customerType = customerType;
		this.companyName = companyName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getPricingModel() {
		return pricingModel;
	}

	public void setPricingModel(int pricingModel) {
		this.pricingModel = pricingModel;
	}

	public int getPurchaseModule() {
		return purchaseModule;
	}

	public void setPurchaseModule(int purchaseModule) {
		this.purchaseModule = purchaseModule;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String cVV) {
		CVV = cVV;
	}

	public int getCustomerTypeAnaleticRank() {
		return customerTypeAnaleticRank;
	}

	public void setCustomerTypeAnaleticRank(int customerTypeAnaleticRank) {
		this.customerTypeAnaleticRank = customerTypeAnaleticRank;
	}



	@Override
	public String toString() {
		return "Customer [adress=" + adress + ", pricingModel=" + pricingModel + ", purchaseModule=" + purchaseModule
				+ ", customerTypeAnaleticRank=" + customerTypeAnaleticRank + ", fuelingHourAnaleticRank="
				+ fuelingHourAnaleticRank + ", visaNumber=" + visaNumber + ", expDate=" + expDate + ", CVV=" + CVV
				+ ", customerType=" + customerType + ", companyName=" + companyName + "]";
	}

	public int getFuelingHourAnaleticRank() {
		return fuelingHourAnaleticRank;
	}

	public void setFuelingHourAnaleticRank(int fuelingHourAnaleticRank) {
		this.fuelingHourAnaleticRank = fuelingHourAnaleticRank;
	}


}
