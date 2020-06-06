package Entity;

import java.io.Serializable;

public class GenericReport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5679960090887730645L;
	private String date;
	private String time;
	private String fileName;
	private String reportType;

	/**
	 * 
	 * @param date String
	 * @param time String
	 * @param fileName String
	 * @param reportType String
	 */
	public GenericReport(String date, String time, String fileName, String reportType) {
		this.date = date;
		this.time = time;
		this.fileName = fileName;
		this.reportType = reportType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	@Override
	public String toString() {
		return "GenericReport [fileName=" + fileName+", reportType=" + reportType+", date=" + date + 
				", time=" + time + "]";
	}

}
