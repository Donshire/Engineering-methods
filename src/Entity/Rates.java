package Entity;

import java.io.Serializable;

import enums.RatesStatus;


/*
 * this must be deleted
 * */


/**
 * The Class Rates.
 */
public class Rates implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7146248837119996672L;
	
	/** The rate id. */
	private Integer rateId;
	
	/** The rate value. */
	private float rateValue;
	
	/** The fuel. */
	private Fuel fuel;
	
	/** The fuel type. */
	private String fuelType;
	
	/** The status. */
	private RatesStatus status;
	
	/** The date. */
	private String date;
	
	/** The company name. */
	private String companyName;
	
	/** The check. */
	private boolean check;

/**
 * rateId,rateValue,fuel,status,date,companyName.
 *
 * @param rateId Integer
 * @param rateValue float
 * @param fuel Fuel
 * @param status RatesStatus
 * @param date String
 * @param companyName String
 */
	public Rates(Integer rateId, float rateValue, Fuel fuel, RatesStatus status, String date, String companyName) {
		this.rateId = rateId;
		this.rateValue = rateValue;
		this.fuel = fuel;
		this.status = status;
		this.date = date;
		this.companyName = companyName;
		this.fuelType=this.fuel.getFuelType();
		this.check=false;
	}
	
	/**
	 * rateId,rateValue,fuel,status,date,companyName.
	 *
	 * @param rateId Integer
	 * @param rateValue float
	 * @param fueltype the fueltype
	 * @param status RatesStatus
	 * @param date String
	 * @param companyName String
	 */
	public Rates(Integer rateId, float rateValue, String fueltype, RatesStatus status, String date, String companyName) {
		this.rateId = rateId;
		this.rateValue = rateValue;
		this.status = status;
		this.date = date;
		this.companyName = companyName;
		this.fuelType=fueltype;
		this.check=false;
	}
	
	/**
	 * Gets the fuel.
	 *
	 * @return the fuel
	 */
	public Fuel getFuel() {
		return fuel;
	}
	
	/**
	 * Sets the fuel.
	 *
	 * @param fuel the new fuel
	 */
	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	/**
	 * Gets the check.
	 *
	 * @return the check
	 */
	public boolean getCheck() {
		return check;
	}

	/**
	 * Sets the check.
	 *
	 * @param check the new check
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * Gets the rate id.
	 *
	 * @return the rate id
	 */
	public int getRateId() {
		return rateId;
	}

	/**
	 * Sets the rate id.
	 *
	 * @param rateId the new rate id
	 */
	public void setRateId(int rateId) {
		this.rateId = rateId;
	}

	/**
	 * Gets the rate value.
	 *
	 * @return the rate value
	 */
	public float getRateValue() {
		return rateValue;
	}

	/**
	 * Sets the rate value.
	 *
	 * @param rateValue the new rate value
	 */
	public void setRateValue(float rateValue) {
		this.rateValue = rateValue;
	}

/**
 * Gets the fuel type.
 *
 * @return the fuel type
 */
/*
	public Fuel getFuelType() {
		return fuelType;
	}

	public void setFuelType(Fuel fuelType) {
		this.fuelType = fuelType;
	}
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public RatesStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(RatesStatus status) {
		this.status = status;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Rates [rateId=" + rateId + ", rateValue=" + rateValue + ", fuelType=" + fuelType + ", status=" + status
				+ ", date=" + date + ", companyName=" + companyName + "]";
	}

}

