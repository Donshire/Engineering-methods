package Entity;

import enums.OrderStatus;
import java.io.Serializable;

public class GasOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7930841922331982180L;
	/*
	 * delete fuelType,saleID,currentPrice,companyName
	 */
	private int purchaseID;
	private String custmoerId;
	private String fuelType;
	private String supplyDate;
	
	private float gasAmount;
	private String date;
	private float priceOfPurchase;
	private boolean urgent;
	private OrderStatus status;
	private int saleID;
	private float currentPrice;
	private String companyName;

	/**
	 * 
	 * @param purchaseID      - Automatically created on DB.
	 * @param custmoerId
	 * @param supplyDate
	 * @param gasAmount
	 * @param date
	 * @param priceOfPurchase
	 * @param urgent
	 */
	public GasOrder(int purchaseID, String custmoerId, String supplyDate, float gasAmount, String date,
			float priceOfPurchase, boolean urgent) {
		this.purchaseID = purchaseID;
		this.custmoerId = custmoerId;
		this.supplyDate = supplyDate;
		this.gasAmount = gasAmount;
		this.date = date;
		this.priceOfPurchase = priceOfPurchase;
		this.urgent = urgent;
	}

	public int getPurchaseID() {
		return purchaseID;
	}

	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}

	public String getCustmoerId() {
		return custmoerId;
	}

	public void setCustmoerId(String custmoerId) {
		this.custmoerId = custmoerId;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(String supplyDate) {
		this.supplyDate = supplyDate;
	}

	public float getGasAmount() {
		return gasAmount;
	}

	public void setGasAmount(float gasAmount) {
		this.gasAmount = gasAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getPriceOfPurchase() {
		return priceOfPurchase;
	}

	public void setPriceOfPurchase(float priceOfPurchase) {
		this.priceOfPurchase = priceOfPurchase;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "GasOrder [purchaseID=" + purchaseID + ", custmoerId=" + custmoerId + ", fuelType=" + fuelType
				+ ", supplyDate=" + supplyDate + ", gasAmount=" + gasAmount + ", date=" + date + ", priceOfPurchase="
				+ priceOfPurchase + ", urgent=" + urgent + ", status=" + status + ", saleID=" + saleID
				+ ", currentPrice=" + currentPrice + ", companyName=" + companyName + "]";
	}

}
