package Entity;

public class Rates {
	private String rateId;
	private float rateValue;
	private Fuel fuelType;
	private String status;
	private String date;
	private String companyName;

	public Rates(String rateId, float rateValue, Fuel fuelType, String status, String date, String companyName) {
		this.rateId = rateId;
		this.rateValue = rateValue;
		this.fuelType = fuelType;
		this.status = status;
		this.date = date;
		this.companyName = companyName;
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

	public Fuel getFuelType() {
		return fuelType;
	}

	public void setFuelType(Fuel fuelType) {
		this.fuelType = fuelType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "Rates [rateId=" + rateId + ", rateValue=" + rateValue + ", fuelType=" + fuelType + ", status=" + status
				+ ", date=" + date + ", companyName=" + companyName + "]";
	}

}
