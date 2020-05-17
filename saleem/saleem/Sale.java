package Yosef;

public class Sale {
	private String saleID;
	private String startTime;
	private String endTime;
	private String startDate;
	private String endDate;
	private String saleDays;
	private String companyName;
	private String purchaseModule;
	private String fuelType;
	private boolean status;
	private int salePercent;
	
	public Sale(String saleID, String startTime, String endTime, String startDate, String endDate, String saleDays,
			String companyName, String fuelType, boolean status, int salePercent) {

		this.saleID = saleID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.saleDays = saleDays;
		this.companyName = companyName;
		this.fuelType = fuelType;
		this.status = status;
		this.salePercent = salePercent;
	}

	@Override
	public String toString() {
		return "Sale [saleID=" + saleID + ", startTime=" + startTime + ", endTime=" + endTime + ", startDate="
				+ startDate + ", endDate=" + endDate + ", saleDays=" + saleDays + ", companyName=" + companyName
				+ ", fuelType=" + fuelType + ", status=" + status + ", salePercent=" + salePercent + "]";
	}

	public String getSaleID() {
		return saleID;
	}

	public void setSaleID(String saleID) {
		this.saleID = saleID;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSaleDays() {
		return saleDays;
	}

	public void setSaleDays(String saleDays) {
		this.saleDays = saleDays;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getSalePercent() {
		return salePercent;
	}

	public void setSalePercent(int salePercent) {
		this.salePercent = salePercent;
	}
	
	
}
