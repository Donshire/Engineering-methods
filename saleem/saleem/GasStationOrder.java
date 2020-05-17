package Yosef;

public class GasStationOrder {
	private String supplierID;
	private String gasStationID;
	private String status;
	private String date;
	private String orderID;
	private String orderPrice;
	private String  fuelType;
	public GasStationOrder(String supplierID, String gasStationID, String status, String date, String orderID,
			String orderPrice, String fuelType) {
		super();
		this.supplierID = supplierID;
		this.gasStationID = gasStationID;
		this.status = status;
		this.date = date;
		this.orderID = orderID;
		this.orderPrice = orderPrice;
		this.fuelType = fuelType;
	}
	public String getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	public String getGasStationID() {
		return gasStationID;
	}
	public void setGasStationID(String gasStationID) {
		this.gasStationID = gasStationID;
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
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
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
	@Override
	public String toString() {
		return "GasStationOrder [supplierID=" + supplierID + ", gasStationID=" + gasStationID + ", status=" + status
				+ ", date=" + date + ", orderID=" + orderID + ", orderPrice=" + orderPrice + ", fuelType=" + fuelType
				+ "]";
	}
	
	
}
