package Entity;

import java.io.Serializable;


/**
 * The Class FuelPurchase.
 */
public class FuelPurchase implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2106310498999584856L;
	
	/** The purchase ID. */
	private Integer purchaseID;
	
	/** The station id. */
	private int stationId;
	
	/** The Car number. */
	private String CarNumber;
	
	/** The fuel quantity. */
	private float fuelQuantity;
	
	/** The price of purchase. */
	private float priceOfPurchase;
	
	/** The time. */
	private String time;
	
	/** The date. */
	private String date;
	
	/** The sale ID. */
	private int saleID;
	
	/** The current price. */
	private float currentPrice;
	
	/** The customer ID. */
	private String customerID;
	
	/** The pricing model number. */
	private int pricingModelNumber;
	
	/** The company name. */
	private String companyName;
	
	/** The fuel type. */
	private String fuelType;


	public FuelPurchase(Integer purchaseID, int stationId, String carNumber, float fuelQuantity, float priceOfPurchase,
			String time, String date, int saleID, float currentPrice, String customerID, int pricingModelNumber,
			String companyName,String fuelType) {
		this.purchaseID = purchaseID;
		this.stationId = stationId;
		CarNumber = carNumber;
		this.fuelQuantity = fuelQuantity;
		this.priceOfPurchase = priceOfPurchase;
		this.time = time;
		this.date = date;
		this.saleID = saleID;
		this.currentPrice = currentPrice;
		this.customerID = customerID;
		this.pricingModelNumber = pricingModelNumber;
		this.companyName=companyName;
		this.fuelType=fuelType;
	}
	
	
	/**
	 * Gets the fuel type.
	 * @return the fuel type
	 */
	public String getFuelType() {
		return fuelType;
	}

	/**
	 * Sets the fuel type.
	 * @param fuelType the new fuel type
	 */
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	/**
	 * Gets the company name.
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/**
	 * Sets the company name.
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * Gets the pricing model number.
	 * @return the pricing model number
	 */
	public int getPricingModelNumber() {
		return pricingModelNumber;
	}
	
	/**
	 * Sets the pricing model number.
	 * @param pricingModelNumber the new pricing model number
	 */
	public void setPricingModelNumber(int pricingModelNumber) {
		this.pricingModelNumber = pricingModelNumber;
	}

	/**
	 * Gets the customer ID.
	 * @return the customer ID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * Sets the customer ID.
	 * @param customerID the new customer ID
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	} 

	/**
	 * Sets the purchase ID.
	 * @param purchaseID the new purchase ID
	 */
	public void setPurchaseID(Integer purchaseID) {
		this.purchaseID = purchaseID;
	}

	/**
	 * Gets the purchase ID.
	 * @return the purchase ID
	 */
	public int getPurchaseID() {
		return purchaseID;
	}

	/**
	 * Sets the purchase ID.
	 * @param purchaseID the new purchase ID
	 */
	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}

	/**
	 * Gets the station id.
	 * @return the station id
	 */
	public int getStationId() {
		return stationId;
	}

	/**
	 * Sets the station id.
	 * @param stationId the new station id
	 */
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	/**
	 * Gets the car number.
	 * @return the car number
	 */
	public String getCarNumber() {
		return CarNumber;
	}

	/**
	 * Sets the car number.
	 * @param carNumber the new car number
	 */
	public void setCarNumber(String carNumber) {
		CarNumber = carNumber;
	}

	/**
	 * Gets the fuel quantity.
	 * @return the fuel quantity
	 */
	public float getFuelQuantity() {
		return fuelQuantity;
	}

	/**
	 * Sets the fuel quantity.
	 * @param fuelQuantity the new fuel quantity
	 */
	public void setFuelQuantity(float fuelQuantity) {
		this.fuelQuantity = fuelQuantity;
	}

	/**
	 * Gets the price of purchase.
	 * @return the price of purchase
	 */
	public float getPriceOfPurchase() {
		return priceOfPurchase;
	}

	/**
	 * Sets the price of purchase.
	 * @param priceOfPurchase the new price of purchase
	 */
	public void setPriceOfPurchase(float priceOfPurchase) {
		this.priceOfPurchase = priceOfPurchase;
	}

	/**
	 * Gets the time.
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 * @param time the new time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Gets the date.
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the sale ID.
	 * @return the sale ID
	 */
	public int getSaleID() {
		return saleID;
	}

	/**
	 * Sets the sale ID.
	 * @param saleID the new sale ID
	 */
	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}

	/**
	 * Gets the current price.
	 * @return the current price
	 */
	public float getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * Sets the current price.
	 * @param currentPrice the new current price
	 */
	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}


	/**
	 * To string.
	 * @return the string
	 */
	@Override
	public String toString() {
		return "FuelPurchase [purchaseID=" + purchaseID + ", stationId=" + stationId + ", CarNumber=" + CarNumber
				+ ", fuelQuantity=" + fuelQuantity + ", priceOfPurchase=" + priceOfPurchase + ", time=" + time
				+ ", date=" + date + ", saleID=" + saleID + ", currentPrice=" + currentPrice + ", customerID="
				+ customerID + ", pricingModelNumber=" + pricingModelNumber + ", companyName=" + companyName
				+ ", fuelType=" + fuelType + "]";
	}
	
}

