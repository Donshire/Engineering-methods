
package Entity;

import java.io.Serializable;
import java.sql.Date;

import enums.SaleStatus;
import helpinigStructForGUI.CheckBoxImplementation;

/**
 * The Class Sale.
 */
public class Sale extends CheckBoxImplementation implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4112663596694462413L;
	
	/** The sale ID. */
	private Integer saleID;
	
	/** The status. */
	private SaleStatus status;
	
	/** The company name. */
	private String companyName;
	
	/** The fuel type. */
	private String fuelType;
	
	/** The sale percent. */
	private float salePercent;
	
	/** The start time. */
	private String startTime;
	
	/** The end time. */
	private String endTime;
	
	/** The start date. */
	private String startDate;
	
	/** The end date. */
	private String endDate;
	
	/** The sale days. */
	private String saleDays;

/** The select. */
private Boolean select;
	
	/**
	 * Instantiates a new sale.
	 *
	 * @param saleID the sale ID
	 * @param status the status
	 * @param companyName the company name
	 * @param fuelType the fuel type
	 * @param salePercent the sale percent
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param saleDays the sale days
	 */
	public Sale(Integer saleID, String status, String companyName, String fuelType,
			float salePercent, String startTime, String endTime, String startDate, String endDate, String saleDays) {
		this.saleID = saleID;
		this.status = SaleStatus.valueOf(status);
		this.companyName = companyName;
		this.fuelType = fuelType;
		this.salePercent = salePercent;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.saleDays = saleDays;
		this.select = false;
	}


	/**
	 * Gets the sale ID.
	 *
	 * @return the sale ID
	 */
	public int getSaleID() {
		return saleID;
	}

	/**
	 * Sets the sale ID.
	 *
	 * @param saleID the new sale ID
	 */
	public void setSaleID(Integer saleID) {
		this.saleID = saleID;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public SaleStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(SaleStatus status) {
		this.status = status;
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
	 * Gets the fuel type.
	 *
	 * @return the fuel type
	 */
	public String getFuelType() {
		return fuelType;
	}

	/**
	 * Sets the fuel type.
	 *
	 * @param fuelType the new fuel type
	 */
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}


	/**
	 * Gets the sale percent.
	 *
	 * @return the sale percent
	 */
	public float getSalePercent() {
		return salePercent;
	}

	/**
	 * Sets the sale percent.
	 *
	 * @param salePercent the new sale percent
	 */
	public void setSalePercent(float salePercent) {
		this.salePercent = salePercent;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the sale days.
	 *
	 * @return the sale days
	 */
	public String getSaleDays() {
		return saleDays;
	}

	/**
	 * Sets the sale days.
	 *
	 * @param saleDays the new sale days
	 */
	public void setSaleDays(String saleDays) {
		this.saleDays = saleDays;
	}


	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Sale [saleID=" + saleID + ", status=" + status + ", companyName=" + companyName + ", fuelType="
				+ fuelType + ", salePercent=" + salePercent + ", startTime="
				+ startTime + ", endTime=" + endTime + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", saleDays=" + saleDays + ", select=" + select + "]";
	}


	/**
	 * Gets the select.
	 *
	 * @return the select
	 */
	public Boolean getSelect() {
		return select;
	}


	/**
	 * Sets the select.
	 *
	 * @param select the new select
	 */
	public void setSelect(Boolean select) {
		this.select = select;
	}




}

