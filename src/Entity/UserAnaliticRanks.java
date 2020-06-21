package Entity;

import java.io.Serializable;

/**
 * The Class UserAnaliticRanks.
 */
public class UserAnaliticRanks implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2877089321072069716L;
	
	/** The user id. */
	private String userId;
	
	/** The customer type analetic rank. */
	private int customerTypeAnaleticRank;
	
	/** The fueling hour analetic rank. */
	private int fuelingHourAnaleticRank;
	
	/** The fuel type analetic rank. */
	private int fuelTypeAnaleticRank;

	/**
	 * Instantiates a new user analitic ranks.
	 *
	 * @param userId the user id
	 * @param customerTypeAnaleticRank the customer type analetic rank
	 * @param fuelingHourAnaleticRank the fueling hour analetic rank
	 * @param fuelTypeAnaleticRank the fuel type analetic rank
	 */
	public UserAnaliticRanks(String userId, int customerTypeAnaleticRank, int fuelingHourAnaleticRank,
			int fuelTypeAnaleticRank) {
		this.userId = userId;
		this.customerTypeAnaleticRank = customerTypeAnaleticRank;
		this.fuelingHourAnaleticRank = fuelingHourAnaleticRank;
		this.fuelTypeAnaleticRank = fuelTypeAnaleticRank;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the customer type analetic rank.
	 *
	 * @return the customer type analetic rank
	 */
	public int getCustomerTypeAnaleticRank() {
		return customerTypeAnaleticRank;
	}

	/**
	 * Sets the customer type analetic rank.
	 *
	 * @param customerTypeAnaleticRank the new customer type analetic rank
	 */
	public void setCustomerTypeAnaleticRank(int customerTypeAnaleticRank) {
		this.customerTypeAnaleticRank = customerTypeAnaleticRank;
	}

	/**
	 * Gets the fueling hour analetic rank.
	 *
	 * @return the fueling hour analetic rank
	 */
	public int getFuelingHourAnaleticRank() {
		return fuelingHourAnaleticRank;
	}

	/**
	 * Sets the fueling hour analetic rank.
	 *
	 * @param fuelingHourAnaleticRank the new fueling hour analetic rank
	 */
	public void setFuelingHourAnaleticRank(int fuelingHourAnaleticRank) {
		this.fuelingHourAnaleticRank = fuelingHourAnaleticRank;
	}

	/**
	 * Gets the fuel type analetic rank.
	 *
	 * @return the fuel type analetic rank
	 */
	public int getFuelTypeAnaleticRank() {
		return fuelTypeAnaleticRank;
	}

	/**
	 * Sets the fuel type analetic rank.
	 *
	 * @param fuelTypeAnaleticRank the new fuel type analetic rank
	 */
	public void setFuelTypeAnaleticRank(int fuelTypeAnaleticRank) {
		this.fuelTypeAnaleticRank = fuelTypeAnaleticRank;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "UserAnaliticRanks [userId=" + userId + ", customerTypeAnaleticRank=" + customerTypeAnaleticRank
				+ ", fuelingHourAnaleticRank=" + fuelingHourAnaleticRank + ", fuelTypeAnaleticRank="
				+ fuelTypeAnaleticRank + "]";
	}

}
