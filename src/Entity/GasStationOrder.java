package Entity;

public class GasStationOrder {

	private String orderID;
	private Supplier supplier;
	private GasStation station;
	private String status;
	private String date;
	private String orderPrice;
	private String fuelType;

	public GasStationOrder(String orderID, Supplier supplier, GasStation station, String status, String date,
			String orderPrice, String fuelType) {
		this.orderID = orderID;
		this.supplier = supplier;
		this.station = station;
		this.status = status;
		this.date = date;
		this.orderPrice = orderPrice;
		this.fuelType = fuelType;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public GasStation getStation() {
		return station;
	}

	public void setStation(GasStation station) {
		this.station = station;
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

	@Override
	public String toString() {
		return "GasStationOrder [orderID=" + orderID + ", supplier=" + supplier + ", station=" + station + ", status="
				+ status + ", date=" + date + ", orderPrice=" + orderPrice + ", fuelType=" + fuelType + "]";
	}
	
	

}
