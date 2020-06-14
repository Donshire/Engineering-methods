package Entity;

import java.io.Serializable;

public class GenericReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5679960090887730645L;
	private String year;
	private String quarter;
	private String fileName;
	private String reportType;
	private String companyName;
	private int stationId;

	public GenericReport(String year, String quarter, String fileName, String reportType, String companyName,
			int stationId) {
		this.year = year;
		this.quarter = quarter;
		this.fileName = fileName;
		this.reportType = reportType;
		this.companyName = companyName;
		this.stationId = stationId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "GenericReport [year=" + year + ", quarter=" + quarter + ", fileName=" + fileName + ", reportType="
				+ reportType + ", companyName=" + companyName + ", stationId=" + stationId + "]";
	}

}
