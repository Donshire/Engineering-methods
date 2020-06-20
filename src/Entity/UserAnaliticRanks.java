package Entity;

import java.io.Serializable;

public class UserAnaliticRanks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2877089321072069716L;
	private String userId;
	private int customerTypeAnaleticRank;
	private int fuelingHourAnaleticRank;
	private int fuelTypeAnaleticRank;

	public UserAnaliticRanks(String userId, int customerTypeAnaleticRank, int fuelingHourAnaleticRank,
			int fuelTypeAnaleticRank) {
		this.userId = userId;
		this.customerTypeAnaleticRank = customerTypeAnaleticRank;
		this.fuelingHourAnaleticRank = fuelingHourAnaleticRank;
		this.fuelTypeAnaleticRank = fuelTypeAnaleticRank;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCustomerTypeAnaleticRank() {
		return customerTypeAnaleticRank;
	}

	public void setCustomerTypeAnaleticRank(int customerTypeAnaleticRank) {
		this.customerTypeAnaleticRank = customerTypeAnaleticRank;
	}

	public int getFuelingHourAnaleticRank() {
		return fuelingHourAnaleticRank;
	}

	public void setFuelingHourAnaleticRank(int fuelingHourAnaleticRank) {
		this.fuelingHourAnaleticRank = fuelingHourAnaleticRank;
	}

	public int getFuelTypeAnaleticRank() {
		return fuelTypeAnaleticRank;
	}

	public void setFuelTypeAnaleticRank(int fuelTypeAnaleticRank) {
		this.fuelTypeAnaleticRank = fuelTypeAnaleticRank;
	}

	@Override
	public String toString() {
		return "UserAnaliticRanks [userId=" + userId + ", customerTypeAnaleticRank=" + customerTypeAnaleticRank
				+ ", fuelingHourAnaleticRank=" + fuelingHourAnaleticRank + ", fuelTypeAnaleticRank="
				+ fuelTypeAnaleticRank + "]";
	}

}
