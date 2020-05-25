<<<<<<< HEAD
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

	public Customer(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber, int online, String adress, int pricingModel, int customerTypeAnaleticRank,
			int purchaseModule, int fuelingHourAnaleticRank, String visaNumber) {
		super(userName, password, firstName, lastName, mail, id, phoneNumber, online);
		this.adress = adress;
		this.pricingModel = pricingModel;
		this.purchaseModule = purchaseModule;
		this.customerTypeAnaleticRank = customerTypeAnaleticRank;
		this.fuelingHourAnaleticRank = fuelingHourAnaleticRank;
		this.visaNumber = visaNumber;
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

	public int getCustomerTypeAnaleticRank() {
		return customerTypeAnaleticRank;
	}

	public void setCustomerTypeAnaleticRank(int customerTypeAnaleticRank) {
		this.customerTypeAnaleticRank = customerTypeAnaleticRank;
	}

	public int getFuelingHourAnaleticRank() {
		return fuelingHourAnaleticRank;
	}

	public void setFuelingHourAnaleticRank(int fuelingHourAnaleticRank) {
		this.fuelingHourAnaleticRank = fuelingHourAnaleticRank;
	}

	@Override
	public String toString() {
		return super.toString() + " Customer [visaNumber=" + visaNumber + ", adress=" + adress +"\n"+ ", pricingModel="
				+ pricingModel + ", purchaseModule=" +"\n"+ purchaseModule + ", customerTypeAnaleticRank="
				+ customerTypeAnaleticRank + "\n"+ ", fuelingHourAnaleticRank=" + fuelingHourAnaleticRank + "]";
	}

}
=======
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

	public Customer(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber, int online, String adress, int pricingModel, int customerTypeAnaleticRank,
			int purchaseModule, int fuelingHourAnaleticRank, String visaNumber) {
		super(userName, password, firstName, lastName, mail, id, phoneNumber, online);
		this.adress = adress;
		this.pricingModel = pricingModel;
		this.purchaseModule = purchaseModule;
		this.customerTypeAnaleticRank = customerTypeAnaleticRank;
		this.fuelingHourAnaleticRank = fuelingHourAnaleticRank;
		this.visaNumber = visaNumber;
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

	public int getCustomerTypeAnaleticRank() {
		return customerTypeAnaleticRank;
	}

	public void setCustomerTypeAnaleticRank(int customerTypeAnaleticRank) {
		this.customerTypeAnaleticRank = customerTypeAnaleticRank;
	}

	public int getFuelingHourAnaleticRank() {
		return fuelingHourAnaleticRank;
	}

	public void setFuelingHourAnaleticRank(int fuelingHourAnaleticRank) {
		this.fuelingHourAnaleticRank = fuelingHourAnaleticRank;
	}

	@Override
	public String toString() {
		return super.toString() + " Customer [visaNumber=" + visaNumber + ", adress=" + adress + ", pricingModel="
				+ pricingModel + ", purchaseModule=" + purchaseModule + ", customerTypeAnaleticRank="
				+ customerTypeAnaleticRank + ", fuelingHourAnaleticRank=" + fuelingHourAnaleticRank + "]";
	}

}
>>>>>>> refs/heads/MyOrderBoundary
