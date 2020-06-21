
package Entity;

import java.io.Serializable;

import javafx.scene.control.CheckBox;

/**
 * The Class GasStationOrder.
 */
public class GasStationOrder  implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8646758799916999583L;
	
	/** The order ID. */
	private Integer orderID;
	
	/** The supplier id. */
	private String supplierId;
	
	/** The station ID. */
	private Integer stationID;
	
	/** The status. */
	private String status;
	
	/** The date. */
	private String date;
	
	/** The order price. */
	private String orderPrice;
	
	/** The fuel type. */
	private String fuelType;
	
	/** The quantity. */
	private float quantity;
	
	/** The select. */
	private Boolean select;

	/**
	 * Instantiates a new gas station order.
	 *
	 * @param orderID the order ID
	 * @param supplierId the supplier id
	 * @param stationID the station ID
	 * @param status the status
	 * @param date the date
	 * @param orderPrice the order price
	 * @param fuelType the fuel type
	 * @param quantity the quantity
	 */
	public GasStationOrder(Integer orderID, String supplierId, Integer stationID, String status, String date,
			String orderPrice, String fuelType, float quantity) {
		this.orderID = orderID;
		this.supplierId = supplierId;
		this.stationID = stationID;
		this.status = status;
		this.date = date;
		this.orderPrice = orderPrice;
		this.fuelType = fuelType;
		this.quantity = quantity;
		this.select = false;
	}


	/**
	 * Gets the select.
	 *
	 * @return the select
	 */
	public Boolean getSelect() {
		return select;
	}


	/**
	 * Sets the select.
	 *
	 * @param select the new select
	 */
	public void setSelect(Boolean select) {
		this.select = select;
	}


	/**
	 * Gets the order ID.
	 *
	 * @return the order ID
	 */
	public Integer getOrderID() {
		return orderID;
	}



	/**
	 * Sets the order ID.
	 *
	 * @param orderID the new order ID
	 */
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}



	/**
	 * Gets the supplier id.
	 *
	 * @return the supplier id
	 */
	public String getSupplierId() {
		return supplierId;
	}



	/**
	 * Sets the supplier id.
	 *
	 * @param supplierId the new supplier id
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}



	/**
	 * Gets the station ID.
	 *
	 * @return the station ID
	 */
	public Integer getStationID() {
		return stationID;
	}



	/**
	 * Sets the station ID.
	 *
	 * @param stationID the new station ID
	 */
	public void setStationID(Integer stationID) {
		this.stationID = stationID;
	}



	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * Gets the order price.
	 *
	 * @return the order price
	 */
	public String getOrderPrice() {
		return orderPrice;
	}



	/**
	 * Sets the order price.
	 *
	 * @param orderPrice the new order price
	 */
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}



	/**
	 * Gets the fuel type.
	 *
	 * @return the fuel type
	 */
	public String getFuelType() {
		return fuelType;
	}



	/**
	 * Sets the fuel type.
	 *
	 * @param fuelType the new fuel type
	 */
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}



	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public float getQuantity() {
		return quantity;
	}



	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

}

