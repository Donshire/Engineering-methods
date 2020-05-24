package Entity;

import java.io.Serializable;

import enums.RatesStatus;

public class Rates implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7146248837119996672L;
	private Integer rateId;
	private float rateValue;
	private Fuel fuel;
	private String fuelType;
	private RatesStatus status;
	private String date;
	private String companyName;
	private boolean check;
/**
 * rateId,rateValue,fuel,status,date,companyName
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
	 * rateId,rateValue,fuel,status,date,companyName
	 * @param rateId Integer
	 * @param rateValue float
	 * @param fuelType String
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
	
	public Fuel getFuel() {
		return fuel;
	}
	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	public boolean getCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public int getRateId() {
		return rateId;
	}

	public void setRateId(int rateId) {
		this.rateId = rateId;
	}

	public float getRateValue() {
		return rateValue;
	}

	public void setRateValue(float rateValue) {
		this.rateValue = rateValue;
	}
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

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	public RatesStatus getStatus() {
		return status;
	}

	public void setStatus(RatesStatus status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "Rates [rateId=" + rateId + ", rateValue=" + rateValue + ", fuelType=" + fuelType + ", status=" + status
				+ ", date=" + date + ", companyName=" + companyName + "]";
	}

}
