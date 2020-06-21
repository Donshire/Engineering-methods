package Entity;

import java.io.Serializable;

/**
 * The Class StationFuel.
 */
public class StationFuel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9210850340007841186L;
	
	/** The fuel. */
	private Fuel fuel;
	
	/** The fuel type. */
	private String fuelType; 
	
	/** The station. */
	private GasStation station;

	/** The station ID. */
	private int stationID;
	
	/** The amount. */
	private float amount;
	
	/** The min quantity. */
	private float minQuantity;
	
	/** The tank size. */
	private int tankSize;

	/** The select min quantity. */
	boolean selectMinQuantity;

	/**
	 * Instantiates a new station fuel.
	 *
	 * @param fuel the fuel
	 * @param station the station
	 * @param amount the amount
	 * @param minQuantity the min quantity
	 * @param tankSize the tank size
	 */
	public StationFuel(Fuel fuel, GasStation station, float amount, float minQuantity, int tankSize) {
		this.fuel = fuel;
		this.station = station;
		this.amount = amount;
		this.minQuantity = minQuantity;
		this.tankSize = tankSize;
		
		fuelType=fuel.getFuelType();
		stationID=station.getStationID();
	}
	
	/**
	 * Instantiates a new station fuel.
	 *
	 * @param fuelType the fuel type
	 * @param stationID the station ID
	 * @param amount the amount
	 * @param minQuantity the min quantity
	 * @param tankSize the tank size
	 */
	public StationFuel(String fuelType, int stationID, float amount, float minQuantity, int tankSize) {
		this.fuelType = fuelType;
		this.stationID = stationID;
		this.amount = amount;
		this.minQuantity = minQuantity;
		this.tankSize = tankSize;
		
		station=null;
		fuel=null;
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
	 * Gets the station ID.
	 *
	 * @return the station ID
	 */
	public int getStationID() {
		return stationID;
	}

	/**
	 * Sets the station ID.
	 *
	 * @param stationID the new station ID
	 */
	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	/**
	 * Gets the fuel.
	 *
	 * @return the fuel
	 */
	public Fuel getFuel() {
		return fuel;
	}

	/**
	 * Sets the fuel.
	 *
	 * @param fuel the new fuel
	 */
	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	/**
	 * Gets the station.
	 *
	 * @return the station
	 */
	public GasStation getStation() {
		return station;
	}

	/**
	 * Sets the station.
	 *
	 * @param station the new station
	 */
	public void setStation(GasStation station) {
		this.station = station;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * Gets the min quantity.
	 *
	 * @return the min quantity
	 */
	public float getMinQuantity() {
		return minQuantity;
	}

	/**
	 * Sets the min quantity.
	 *
	 * @param minQuantity the new min quantity
	 */
	public void setMinQuantity(float minQuantity) {
		this.minQuantity = minQuantity;
	}

	/**
	 * Gets the tank size.
	 *
	 * @return the tank size
	 */
	public int getTankSize() {
		return tankSize;
	}

	/**
	 * Sets the tank size.
	 *
	 * @param tankSize the new tank size
	 */
	public void setTankSize(int tankSize) {
		this.tankSize = tankSize;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "StationFuel [fuel=" + fuel + ", station=" + station + ", amount=" + amount + ", minQuantity="
				+ minQuantity + ", tankSize=" + tankSize + "]";
	}

	/**
	 * Checks if is select min quantity.
	 *
	 * @return true, if is select min quantity
	 */
	public boolean isSelectMinQuantity() {
		return selectMinQuantity;
	}

	/**
	 * Sets the select min quantity.
	 *
	 * @param selectMinQuantity the new select min quantity
	 */
	public void setSelectMinQuantity(boolean selectMinQuantity) {
		this.selectMinQuantity = selectMinQuantity;
	}

}
