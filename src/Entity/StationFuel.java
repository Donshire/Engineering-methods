package Entity;

public class StationFuel {

	private Fuel fuel;
	private GasStation station;
	private float amount;
	private float minQuantity;

	public StationFuel(Fuel fuel, GasStation station, float amount, float minQuantity) {
		this.fuel = fuel;
		this.station = station;
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
		return "StationFuel [fuel=" + fuel + ", station=" + station + ", amount=" + amount + ", minQuantity="
				+ minQuantity + "]";
	}

}
