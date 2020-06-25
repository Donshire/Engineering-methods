package Entity;

import enums.OrderStatus;
import java.io.Serializable;

/**
 * The Class GasOrder.
 */
public class GasOrder implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7930841922331982180L;
	
	/** The purchase ID. */
	/*
	 * delete fuelType,saleID,currentPrice,companyName
	 */
	private int purchaseID;
	
	/** The custmoer id. */
	private String custmoerId;
	
	/** The supply date. */
	//private String fuelType;
	private String supplyDate;
	
	/** The time. */
	private String time;
	
	/** The gas amount. */
	private float gasAmount;
	
	/** The date. */
	private String date;
	
	/** The price of purchase. */
	private float priceOfPurchase;
	
	/** The urgent. */
	private boolean urgent;
	
	/** The status. */
	private OrderStatus status;
//	private int saleID;
//  float currentPrice;
//	private String companyName;
	

	/**
 * Instantiates a new gas order.
 *
 * @param purchaseID      - Automatically created on DB.
 * @param custmoerId the custmoer id
 * @param supplyDate the supply date
 * @param time the time
 * @param gasAmount the gas amount
 * @param date the date
 * @param priceOfPurchase the price of purchase
 * @param urgent the urgent
 * @param processing the processing
 */
	public GasOrder(int purchaseID, String custmoerId, String supplyDate, String time, float gasAmount, String date,
			float priceOfPurchase, boolean urgent, OrderStatus processing) {
		this.purchaseID = purchaseID;
		this.custmoerId = custmoerId;
		this.supplyDate = supplyDate;
		this.time = time;
		this.gasAmount = gasAmount;
		this.date = date;
		this.priceOfPurchase = priceOfPurchase;
		this.urgent = urgent;
		this.status = processing;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Gets the purchase ID.
	 *
	 * @return the purchase ID
	 */
	public int getPurchaseID() {
		return purchaseID;
	}

	/**
	 * Sets the purchase ID.
	 *
	 * @param purchaseID the new purchase ID
	 */
	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}

	/**
	 * Gets the custmoer id.
	 *
	 * @return the custmoer id
	 */
	public String getCustmoerId() {
		return custmoerId;
	}

	/**
	 * Sets the custmoer id.
	 *
	 * @param custmoerId the new custmoer id
	 */
	public void setCustmoerId(String custmoerId) {
		this.custmoerId = custmoerId;
	}

/*
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
*/

	/**
 * Gets the supply date.
 *
 * @return the supply date
 */
public String getSupplyDate() {
		return supplyDate;
	}

	/**
	 * Sets the supply date.
	 *
	 * @param supplyDate the new supply date
	 */
	public void setSupplyDate(String supplyDate) {
		this.supplyDate = supplyDate;
	}

	/**
	 * Gets the gas amount.
	 *
	 * @return the gas amount
	 */
	public float getGasAmount() {
		return gasAmount;
	}

	/**
	 * Sets the gas amount.
	 *
	 * @param gasAmount the new gas amount
	 */
	public void setGasAmount(float gasAmount) {
		this.gasAmount = gasAmount;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the price of purchase.
	 *
	 * @return the price of purchase
	 */
	public float getPriceOfPurchase() {
		return priceOfPurchase;
	}

	/**
	 * Sets the price of purchase.
	 *
	 * @param priceOfPurchase the new price of purchase
	 */
	public void setPriceOfPurchase(float priceOfPurchase) {
		this.priceOfPurchase = priceOfPurchase;
	}

	/**
	 * Checks if is urgent.
	 *
	 * @return true, if is urgent
	 */
	public boolean isUrgent() {
		return urgent;
	}

	/**
	 * Sets the urgent.
	 *
	 * @param urgent the new urgent
	 */
	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}
	
	/**
	 * Gets the status int.
	 *
	 * @return the status int
	 */
	public int getStatusInt() {
		switch (status) {
		case processing:
			return 1;
		case onTheWay:
			return 2;
		case arrived:
			return 3;
		default:
			return 1;
		}	
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
/*
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
*/

	/**
 * To string.
 *
 * @return the string
 */
@Override
	public String toString() {
		return "GasOrder [purchaseID=" + purchaseID + ", custmoerId=" + custmoerId + ", supplyDate=" + supplyDate
				+ ", time=" + time + ", gasAmount=" + gasAmount + ", date=" + date + ", priceOfPurchase="
				+ priceOfPurchase + ", urgent=" + urgent + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custmoerId == null) ? 0 : custmoerId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + Float.floatToIntBits(gasAmount);
		result = prime * result + Float.floatToIntBits(priceOfPurchase);
		result = prime * result + purchaseID;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((supplyDate == null) ? 0 : supplyDate.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + (urgent ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GasOrder other = (GasOrder) obj;
		if (custmoerId == null) {
			if (other.custmoerId != null)
				return false;
		} else if (!custmoerId.equals(other.custmoerId))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Float.floatToIntBits(gasAmount) != Float.floatToIntBits(other.gasAmount))
			return false;
		if (Float.floatToIntBits(priceOfPurchase) != Float.floatToIntBits(other.priceOfPurchase))
			return false;
		if (purchaseID != other.purchaseID)
			return false;
		if (status != other.status)
			return false;
		if (supplyDate == null) {
			if (other.supplyDate != null)
				return false;
		} else if (!supplyDate.equals(other.supplyDate))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (urgent != other.urgent)
			return false;
		return true;
	}

}
