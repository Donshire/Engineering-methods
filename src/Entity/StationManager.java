package Entity;

public class StationManager extends Employee {

	private int stationID;

	public StationManager(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber, String department, String role, int online, String workerID, int stationID) {
		super(userName, password, firstName, lastName, mail, id, phoneNumber, department, role, online, workerID);
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
