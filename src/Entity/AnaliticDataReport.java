package Entity;

import java.io.Serializable;

/**
 * The Class AnaliticDataReport.
 */
public class AnaliticDataReport implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1745646423234331234L;
	
	/** The file name. */
	private String fileName;
	
	/** The week. */
	private String week;
	
	/** The month. */
	private String month;
	
	/** The year. */
	private String year;
	
	/** The company. */
	private String company;
	
	/** The type. */
	private String type;
	
	/**
	 * Instantiates a new analitic data report.
	 *
	 * @param fileName the file name
	 * @param week the week
	 * @param month the month
	 * @param year the year
	 * @param company the company
	 * @param type the type
	 */
	public AnaliticDataReport(String fileName, String week, String month, String year, String company, String type) {
		this.fileName = fileName;
		this.week = week;
		this.month = month;
		this.year = year;
		this.company = company;
		this.type = type;
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
	 * Gets the week.
	 *
	 * @return the week
	 */
	public String getWeek() {
		return week;
	}

	/**
	 * Sets the week.
	 *
	 * @param week the new week
	 */
	public void setWeek(String week) {
		this.week = week;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Sets the month.
	 *
	 * @param month the new month
	 */
	public void setMonth(String month) {
		this.month = month;
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
	 * Gets the company.
	 *
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
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
		return "AnaliticDataReport [fileName=" + fileName + ", week=" + week + ", month=" + month + ", year=" + year
				+ ", company=" + company + ", type=" + type + "]";
	}

}
