package Entity;

public class Sale {
	private int saleID;
	private boolean status;
	private String companyName;
	private String fuelType;
	private String purchaseModule;
	private float salePercent;
	private String startTime;
	private String endTime;
	private String startDate;
	private String endDate;
	private String saleDays;
	private boolean select;

	public Sale(int saleID, boolean status, String companyName, String fuelType, String purchaseModule,
			float salePercent, String startTime, String endTime, String startDate, String endDate, String saleDays) {
		this.saleID = saleID;
		this.status = status;
		this.companyName = companyName;
		this.fuelType = fuelType;
		this.purchaseModule = purchaseModule;
		this.salePercent = salePercent;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.saleDays = saleDays;
		select=false;
	}
	
	public boolean getSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}


	public int getSaleID() {
		return saleID;
	}

	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public String getPurchaseModule() {
		return purchaseModule;
	}

	public void setPurchaseModule(String purchaseModule) {
		this.purchaseModule = purchaseModule;
	}

	public float getSalePercent() {
		return salePercent;
	}

	public void setSalePercent(float salePercent) {
		this.salePercent = salePercent;
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

	@Override
	public String toString() {
		return "Sale [saleID=" + saleID + ", status=" + status + ", companyName=" + companyName + ", fuelType="
				+ fuelType + ", purchaseModule=" + purchaseModule + ", salePercent=" + salePercent + ", startTime="
				+ startTime + ", endTime=" + endTime + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", saleDays=" + saleDays + "]";
	}

}
