package Entity;

import java.io.Serializable;

public class StationFuel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9210850340007841186L;
	private Fuel fuel;
	private GasStation station;

	private int stationId;
	private String fueltype;
	private float amount;
	private float minQuantity;

	boolean selectMinQuantity;

	public StationFuel(Fuel fuel, GasStation station, float amount, float minQuantity) {
		this.fuel = fuel;
		this.station = station;
		this.amount = amount;
		this.minQuantity = minQuantity;
	}

	public StationFuel(int stationId, String fueltype, float amount, float minQuantity) {
		this.stationId = stationId;
		this.fueltype = fueltype;
		this.amount = amount;
		this.minQuantity = minQuantity;
	}

	public Fuel getFuel() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	public GasStation getStation() {
		return station;
	}

	public void setStation(GasStation station) {
		this.station = station;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(float minQuantity) {
		this.minQuantity = minQuantity;
	}

	@Override
	public String toString() {
		return "StationFuel [stationId=" + stationId + ", fueltype=" + fueltype + ", amount=" + amount
				+ ", minQuantity=" + minQuantity + "]";
	}

	public String getFueltype() {
		return fueltype;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}

	public boolean isSelectMinQuantity() {
		return selectMinQuantity;
	}

	public void setSelectMinQuantity(boolean selectMinQuantity) {
		this.selectMinQuantity = selectMinQuantity;
	}

}
