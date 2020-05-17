package Yosef;

public class GenericReport {
	private String reportId;
	private String date;
	private String time; 
	private String data;


	private String reportType;

	public GenericReport(String reportID, String date, String time, String data, String reportType) {
		this.reportId = reportID;
		this.date = date;
		this.time = time;
		this.data = data;
		this.reportType = reportType;
	}

	public String getReportID() {
		return reportId;
	}

	public void setReportID(String reportID) {
		this.reportId = reportID;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	@Override
	public String toString() {
		return "GenericReport [reportID=" + reportId + ", date=" + date + ", time=" + time + ", data=" + data
				+ ", reportType=" + reportType + ", getReportID()=" + getReportID() + ", getDate()=" + getDate()
				+ ", getTime()=" + getTime() + ", getData()=" + getData() + ", getReportType()=" + getReportType()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
