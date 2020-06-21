
package Entity;

import java.io.Serializable;

/**
 * The Class Employee.
 */
public class Employee extends User implements Serializable {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3457828437098587272L;
	
	/** The role. */
	private String role;
	
	/** The department. */
	private String department;
	
	/** The worker ID. */
	private Integer workerID;
	
	/** The company name. */
	private String companyName;
	
	/**
	 * Instantiates a new employee.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param mail the mail
	 * @param id the id
	 * @param phoneNumber the phone number
	 * @param department the department
	 * @param role the role
	 * @param online the online
	 * @param workerID the worker ID
	 * @param companyName the company name
	 */
	public Employee(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber,String department,String role,int online,Integer workerID,String companyName) {
		super(userName, password, firstName, lastName, mail, id, phoneNumber,online);
		this.workerID = workerID;
		this.role = role;
		this.department = department;
		this.companyName=companyName;
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
	 * Gets the worker ID.
	 *
	 * @return the worker ID
	 */
	public Integer getWorkerID() {
		return workerID;
	}

	/**
	 * Sets the worker ID.
	 *
	 * @param workerID the new worker ID
	 */
	public void setWorkerID(Integer workerID) {
		this.workerID = workerID;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Sets the department.
	 *
	 * @param department the new department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}



	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return super.toString()+" Employee [role=" + role + ", department=" + department + ", workerID=" + workerID + ", companyName="
				+ companyName + "]";
	}

	

}

