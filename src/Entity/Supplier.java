package Entity;

import java.io.Serializable;

/**
 * The Class Supplier.
 */
public class Supplier extends User implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2883443962425161281L;
	
	/** The fuel type. */
	private String fuelType;

	/**
	 * Instantiates a new supplier.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param mail the mail
	 * @param id the id
	 * @param phoneNumber the phone number
	 * @param online the online
	 * @param fuelType the fuel type
	 */
	public Supplier(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber,int online, String fuelType) {
		super(userName, password, firstName, lastName, mail, id, phoneNumber,online);
		this.fuelType = fuelType;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Supplier [fuelType=" + fuelType + "]";
	}
	
}
