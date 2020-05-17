package Entity;

import java.io.Serializable;

public class Employee extends User implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3457828437098587272L;
	private String role;
	private String department;
	private String workerID;

	public Employee(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber,String department,String role,int online,String workerID) {
		super(userName, password, firstName, lastName, mail, id, phoneNumber,online);
		this.workerID = workerID;
		this.role = role;
		this.department = department;
	}



	public String getWorkerID() {
		return workerID;
	}



	public void setWorkerID(String workerID) {
		this.workerID = workerID;
	}



	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [workerID=" + workerID + ", role=" + role + ", department=" + department + "]";
	}

}
