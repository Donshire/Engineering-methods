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
	private String companyName;
	
	public Employee(String userName, String password, String firstName, String lastName, String mail, String id,
			String phoneNumber,String department,String role,int online,String workerID,String companyName) {
		super(userName, password, firstName, lastName, mail, id, phoneNumber,online);
		this.workerID = workerID;
		this.role = role;
		this.department = department;
		this.companyName=companyName;
	}



	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
		return super.toString()+" Employee [role=" + role + ", department=" + department + ", workerID=" + workerID + ", companyName="
				+ companyName + "]";
	}

	

}
