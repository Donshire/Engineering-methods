package Entity;

import java.io.Serializable;

public abstract class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1526248330527204838L;
	private String userName;
	private String password;	
	private String id;
	private String firstName;
	private String lastName;
	private String mail;
	private String phoneNumber;
	private int online;
	
	
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



	public int getOnline() {
		return online;
	}



	public void setOnline(int online) {
		this.online = online;
	}



	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	
}
