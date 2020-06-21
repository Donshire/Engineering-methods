
package Entity;

import java.io.Serializable;

/**
 * The Class User.
 */
public abstract class User implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1526248330527204838L;
	
	/** The user name. */
	private String userName;
	
	/** The password. */
	private String password;	
	
	/** The id. */
	private String id;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The mail. */
	private String mail;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The online. */
	private int online;
	
	
	/**
	 * Instantiates a new user.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param mail the mail
	 * @param id the id
	 * @param phoneNumber the phone number
	 * @param online the online
	 */
	//using user from tables
	public User(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber, int online) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.online = online;
	}

	/**
	 * Gets the online.
	 *
	 * @return the online
	 */
	public int getOnline() {
		return online;
	}
	
	/**
	 * Sets the online.
	 *
	 * @param online the new online
	 */
	public void setOnline(int online) {
		this.online = online;
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", id=" + id + "\n"+ ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mail=" + mail + "\n"+ ", phoneNumber=" + phoneNumber + ", online=" + online
				+ "]";
	}
	
}

