package Entity;

public class StationFuel {

	private Fuel fuel;
	private String fuelType; 
	private GasStation station;
	private int stationID;
	private float amount;
	private float minQuantity;
	private int tankSize;

	public StationFuel(Fuel fuel, GasStation station, float amount, float minQuantity, int tankSize) {
		this.fuel = fuel;
		this.station = station;
		this.amount = amount;
		this.minQuantity = minQuantity;
		this.tankSize = tankSize;
		
		fuelType=fuel.getFuelType();
		stationID=station.getStationID();
	}
	
	public StationFuel(String fuelType, int stationID, float amount, float minQuantity, int tankSize) {
		this.fuelType = fuelType;
		this.stationID = stationID;
		this.amount = amount;
		this.minQuantity = minQuantity;
		this.tankSize = tankSize;
		
		station=null;
		fuel=null;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
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

	public int getTankSize() {
		return tankSize;
	}

	public void setTankSize(int tankSize) {
		this.tankSize = tankSize;
	}

	@Override
	public String toString() {
		return "StationFuel [fuel=" + fuel + ", station=" + station + ", amount=" + amount + ", minQuantity="
				+ minQuantity + ", tankSize=" + tankSize + "]";
	}

}
