package Entity;

import java.io.Serializable;

/**
 * The Class GenericReport.
 */
public class GenericReport implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5679960090887730645L;
	
	/** The year. */
	private String year;
	
	/** The quarter. */
	private String quarter;
	
	/** The file name. */
	private String fileName;
	
	/** The report type. */
	private String reportType;
	
	/** The company name. */
	private String companyName;
	
	/** The station id. */
	private int stationId;

	/**
	 * Instantiates a new generic report.
	 *
	 * @param year the year
	 * @param quarter the quarter
	 * @param fileName the file name
	 * @param reportType the report type
	 * @param companyName the company name
	 * @param stationId the station id
	 */
	public GenericReport(String year, String quarter, String fileName, String reportType, String companyName,
			int stationId) {
		this.year = year;
		this.quarter = quarter;
		this.fileName = fileName;
		this.reportType = reportType;
		this.companyName = companyName;
		this.stationId = stationId;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Gets the quarter.
	 *
	 * @return the quarter
	 */
	public String getQuarter() {
		return quarter;
	}

	/**
	 * Sets the quarter.
	 *
	 * @param quarter the new quarter
	 */
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the report type.
	 *
	 * @return the report type
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * Sets the report type.
	 *
	 * @param reportType the new report type
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
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
	 * Gets the station id.
	 *
	 * @return the station id
	 */
	public int getStationId() {
		return stationId;
	}

	/**
	 * Sets the station id.
	 *
	 * @param stationId the new station id
	 */
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "GenericReport [year=" + year + ", quarter=" + quarter + ", fileName=" + fileName + ", reportType="
				+ reportType + ", companyName=" + companyName + ", stationId=" + stationId + "]";
	}

}