
package Entity;

import java.io.Serializable;

import javafx.scene.control.CheckBox;

public class GasStationOrder  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8646758799916999583L;
	private Integer orderID;
	private String supplierId;
	private Integer stationID;
	private String status;
	private String date;
	private String orderPrice;
	private String fuelType;
	private Integer quantity;
	private Boolean select;

	

	public GasStationOrder(Integer orderID, String supplierId, Integer stationID, String status, String date,
			String orderPrice, String fuelType, Integer quantity) {
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


	public Boolean getSelect() {
		return select;
	}


	public void setSelect(Boolean select) {
		this.select = select;
	}


	public Integer getOrderID() {
		return orderID;
	}



	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}



	public String getSupplierId() {
		return supplierId;
	}



	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}



	public Integer getStationID() {
		return stationID;
	}



	public void setStationID(Integer stationID) {
		this.stationID = stationID;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getOrderPrice() {
		return orderPrice;
	}



	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}



	public String getFuelType() {
		return fuelType;
	}



	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	

}

