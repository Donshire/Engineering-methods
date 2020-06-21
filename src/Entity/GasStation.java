package Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class GasStation.
 */
public class GasStation implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5343787255377018218L;
	
	/** The company name. */
	private String companyName;
	
	/** The station ID. */
	private Integer stationID;
	
	/** The city name. */
	private String cityName;
	
	/** The area name. */
	private String areaName;
	
	/** The manger. */
	private StationManager manger;
	
	/** The station manger worker ID. */
	private int stationMangerWorkerID;
	
	/** The station fuel. */
	private Set<StationFuel> stationFuel = new HashSet<StationFuel>();

	/**
	 * Instantiates a new gas station.
	 *
	 * @param companyName the company name
	 * @param stationID the station ID
	 * @param cityName the city name
	 * @param areaName the area name
	 * @param manger the manger
	 * @param stationFuel the station fuel
	 */
	public GasStation(String companyName, Integer stationID, String cityName, String areaName, StationManager manger,
			Set<StationFuel> stationFuel) {
		this.companyName = companyName;
		this.stationID = stationID;
		this.cityName = cityName;
		this.areaName = areaName;
		this.manger = manger;
		this.stationFuel = stationFuel;
		
		stationMangerWorkerID=0;
	}
	
	/**
	 * Instantiates a new gas station.
	 *
	 * @param companyName the company name
	 * @param stationID the station ID
	 * @param cityName the city name
	 * @param areaName the area name
	 * @param stationMangerWorkerID the station manger worker ID
	 */
	public GasStation(String companyName, Integer stationID, String cityName, String areaName, int stationMangerWorkerID) {
		this.companyName = companyName;
		this.stationID = stationID;
		this.cityName = cityName;
		this.areaName = areaName;
		this.stationMangerWorkerID=stationMangerWorkerID;
		
		this.manger = null;
		this.stationFuel = null;
	}

	
	/**
	 * Gets the station manger worker ID.
	 *
	 * @return the station manger worker ID
	 */
	public int getStationMangerWorkerID() {
		return stationMangerWorkerID;
	}

	/**
	 * Sets the station manger worker ID.
	 *
	 * @param stationMangerWorkerID the new station manger worker ID
	 */
	public void setStationMangerWorkerID(int stationMangerWorkerID) {
		this.stationMangerWorkerID = stationMangerWorkerID;
	}

	/**
	 * Sets the station ID.
	 *
	 * @param stationID the new station ID
	 */
	public void setStationID(Integer stationID) {
		this.stationID = stationID;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 * Gets the city name.
	 *
	 * @return the city name
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Sets the city name.
	 *
	 * @param cityName the new city name
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * Gets the area name.
	 *
	 * @return the area name
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Sets the area name.
	 *
	 * @param areaName the new area name
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * Gets the manger.
	 *
	 * @return the manger
	 */
	public StationManager getManger() {
		return manger;
	}

	/**
	 * Sets the manger.
	 *
	 * @param manger the new manger
	 */
	public void setManger(StationManager manger) {
		this.manger = manger;
	}

	/**
	 * Gets the station fuel.
	 *
	 * @return the station fuel
	 */
	public Set<StationFuel> getStationFuel() {
		return stationFuel;
	}

	/**
	 * Sets the station fuel.
	 *
	 * @param stationFuel the new station fuel
	 */
	public void setStationFuel(Set<StationFuel> stationFuel) {
		this.stationFuel = stationFuel;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "GasStation [companyName=" + companyName + ", stationID=" + stationID + ", cityName=" + cityName
				+ ", areaName=" + areaName + ", manger=" + manger + ", stationFuel=" + stationFuel + "]";
	}
	
}

