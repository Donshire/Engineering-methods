package Entity;

public class StationManager extends Employee {

	private int stationID;

	/**
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
	

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	@Override
	public String toString() {
		return "StationManager [stationID=" + stationID + "]";
	}

}
