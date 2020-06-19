package Entity;

import java.io.Serializable;

public class AnaliticDataReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1745646423234331234L;
	private String fileName;
	private String week;
	private String month;
	private String year;

	public AnaliticDataReport(String fileName, String week, String month, String year) {
		this.fileName = fileName;
		this.week = week;
		this.month = month;
		this.year = year;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "AnaliticDataReport [fileName=" + fileName + ", week=" + week + ", month=" + month + ", year=" + year
				+ "]";
	}

}
