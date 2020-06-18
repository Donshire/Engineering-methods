package Entity;

import java.io.Serializable;


public class FuelPurchase implements Serializable {
	private static final long serialVersionUID = -2106310498999584856L;
	private Integer purchaseID;
	private int stationId;
	private String CarNumber;
	
	private float fuelQuantity;
	private float priceOfPurchase;
	private String time;
	private String date;
	private int saleID;
	private float currentPrice;
	private String customerID;
	private int pricingModelNumber;

	public FuelPurchase(Integer purchaseID, int stationId, String carNumber, float fuelQuantity, float priceOfPurchase,
			String time, String date, int saleID, float currentPrice, String customerID, int pricingModelNumber) {
		super();
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
	}
	
	public int getPricingModelNumber() {
		return pricingModelNumber;
	}
	
	public void setPricingModelNumber(int pricingModelNumber) {
		this.pricingModelNumber = pricingModelNumber;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	} 

	public void setPurchaseID(Integer purchaseID) {
		this.purchaseID = purchaseID;
	}

	public int getPurchaseID() {
		return purchaseID;
	}

	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getCarNumber() {
		return CarNumber;
	}

	public void setCarNumber(String carNumber) {
		CarNumber = carNumber;
	}

	public float getFuelQuantity() {
		return fuelQuantity;
	}

	public void setFuelQuantity(float fuelQuantity) {
		this.fuelQuantity = fuelQuantity;
	}

	public float getPriceOfPurchase() {
		return priceOfPurchase;
	}

	public void setPriceOfPurchase(float priceOfPurchase) {
		this.priceOfPurchase = priceOfPurchase;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSaleID() {
		return saleID;
	}

	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}

	public float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "FuelPurchase [purchaseID=" + purchaseID + ", stationId=" + stationId + ", CarNumber=" + CarNumber
				+ ", fuelQuantity=" + fuelQuantity + ", priceOfPurchase=" + priceOfPurchase + ", time=" + time
				+ ", date=" + date + ", saleID=" + saleID + ", currentPrice=" + currentPrice + "]";
	}
	
}

