package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//Date might be changed to String

public class CustomerModule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5536017888440251636L;
	private String id;
	private int modelNumber;

	private Set<String> companyNamesSubscribed = new HashSet<String>();
	
	/**
	 * 
	 * */
	public CustomerModule(String id, int modelNumber, Set<String> companyNamesSubscribed) {
		this.id = id;
		this.modelNumber = modelNumber;
		this.companyNamesSubscribed = companyNamesSubscribed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	public Set<String> getCompanyNamesSubscribed() {
		return companyNamesSubscribed;
	}

	public void setCompanyNamesSubscribed(Set<String> companyNamesSubscribed) {
		this.companyNamesSubscribed = companyNamesSubscribed;
	}

	@Override
	public String toString() {
		return "CustomerModule [id=" + id + ", modelNumber=" + modelNumber + ", companyNamesSubscribed="
				+ companyNamesSubscribed ;
	}

}
