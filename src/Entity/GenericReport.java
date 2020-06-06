package Entity;

import java.io.Serializable;

public class GenericReport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5679960090887730645L;
	private Integer reportId;
	private String date;
	private String time;
	private String fileName;
	private String reportType;

	public GenericReport(Integer reportId, String date, String time, String fileName, String reportType) {
		this.reportId = reportId;
		this.date = date;
		this.time = time;
		this.fileName = fileName;
		this.reportType = reportType;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
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
		return "GenericReport [reportId=" + reportId + ", date=" + date + ", time=" + time + ", fileName=" + fileName
				+ ", reportType=" + reportType + "]";
	}

}
