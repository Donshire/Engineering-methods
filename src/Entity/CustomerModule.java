package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//Date might be changed to String

/**
 * The Class CustomerModule.
 */
public class CustomerModule implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5536017888440251636L;
	
	/** The id. */
	private String id;
	
	/** The model number. */
	private int modelNumber;

	/** The company names subscribed. */
	private Set<String> companyNamesSubscribed = new HashSet<String>();
	
	/**
	 * Instantiates a new customer module.
	 *
	 * @param id the id
	 * @param modelNumber the model number
	 * @param companyNamesSubscribed the company names subscribed
	 */
	public CustomerModule(String id, int modelNumber, Set<String> companyNamesSubscribed) {
		this.id = id;
		this.modelNumber = modelNumber;
		this.companyNamesSubscribed = companyNamesSubscribed;
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
	 * Gets the model number.
	 *
	 * @return the model number
	 */
	public int getModelNumber() {
		return modelNumber;
	}

	/**
	 * Sets the model number.
	 *
	 * @param modelNumber the new model number
	 */
	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	/**
	 * Gets the company names subscribed.
	 *
	 * @return the company names subscribed
	 */
	public Set<String> getCompanyNamesSubscribed() {
		return companyNamesSubscribed;
	}

	/**
	 * Sets the company names subscribed.
	 *
	 * @param companyNamesSubscribed the new company names subscribed
	 */
	public void setCompanyNamesSubscribed(Set<String> companyNamesSubscribed) {
		this.companyNamesSubscribed = companyNamesSubscribed;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "CustomerModule [id=" + id + ", modelNumber=" + modelNumber + ", companyNamesSubscribed="
				+ companyNamesSubscribed ;
	}

}
