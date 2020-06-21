package Entity;

import java.io.Serializable;

/**
 * The Class StationManager.
 */
public class StationManager extends Employee implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1210489266470434112L;
	
	/** The station ID. */
	private int stationID;

	/**
	 * Instantiates a new station manager.
	 *
	 * @param userName String
	 * @param password String
	 * @param firstName String
	 * @param lastName String
	 * @param mail String
	 * @param id String
	 * @param phoneNumber String
	 * @param department String
	 * @param role String
	 * @param online int
	 * @param workerID Integer
	 * @param companyName String
	 * @param stationID Integer
	 */
	public StationManager(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber, String department, String role, int online, Integer workerID, String companyName,
			int stationID) {
		super(userName, password, firstName, lastName, mail, id, phoneNumber, department, role, online, workerID,
				companyName);
		this.stationID = stationID;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "StationManager [stationID=" + stationID + "]";
	}

}

