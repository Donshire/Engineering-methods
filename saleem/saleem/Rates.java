package Yosef;

public class Rates {
	private String rateId;
	private float rateValue;
	private Fuel fuelInfo;
	private String date;
	private String companyName;
	private String status;
	public Rates(String rateId, float rateValue, Fuel fuelInfo, String date, String companyName, String status) {
		super();
		this.rateId = rateId;
		this.rateValue = rateValue;
		this.fuelInfo = fuelInfo;
		this.date = date;
		this.companyName = companyName;
		this.status = status;
	}
	public String getRateId() {
		return rateId;
	}
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}
	public float getRateValue() {
		return rateValue;
	}
	public void setRateValue(float rateValue) {
		this.rateValue = rateValue;
	}
	public Fuel getFuelInfo() {
		return fuelInfo;
	}
	public void setFuelInfo(Fuel fuelInfo) {
		this.fuelInfo = fuelInfo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Rates [rateId=" + rateId + ", rateValue=" + rateValue + ", fuelInfo=" + fuelInfo + ", date=" + date
				+ ", companyName=" + companyName + ", status=" + status + "]";
	}
	
	
}
