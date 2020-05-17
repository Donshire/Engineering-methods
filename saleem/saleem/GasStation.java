package Yosef;

public class GasStation {
	private String companyName;
	private String stationID;
	private String cityName;
	private  String areaName;
	private String carID;
	


	public GasStation(String companyName, String stationID, String cityName, String areaName) {
		super();
		this.companyName = companyName;
		this.stationID = stationID;
		this.cityName = cityName;
		this.areaName = areaName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getCarID() {
		return carID;
	}

	public void setCarID(String carID) {
		this.carID = carID;
	}
}
