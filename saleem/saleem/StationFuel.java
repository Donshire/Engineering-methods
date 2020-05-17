package Yosef;

public class StationFuel {
	private String stationID;
	private String fuelType;
	private String gasStationID;
	private double amount;
	private double minQuantity;
	public StationFuel(String stationID, String fuelType, String gasStationID, double amount, double minQuantity) {
		super();
		this.stationID = stationID;
		this.fuelType = fuelType;
		this.gasStationID = gasStationID;
		this.amount = amount;
		this.minQuantity = minQuantity;
	}
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getGasStationID() {
		return gasStationID;
	}
	public void setGasStationID(String gasStationID) {
		this.gasStationID = gasStationID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(double minQuantity) {
		this.minQuantity = minQuantity;
	}
	@Override
	public String toString() {
		return "StationFuel [stationID=" + stationID + ", fuelType=" + fuelType + ", gasStationID=" + gasStationID
				+ ", amount=" + amount + ", minQuantity=" + minQuantity + "]";
	}
	

}
