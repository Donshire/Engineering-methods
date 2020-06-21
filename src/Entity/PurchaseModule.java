package Entity;

import java.io.Serializable;

/**
 * The Class PurchaseModule.
 */
public class PurchaseModule implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7173683030345152292L;
	
	/** The model number. */
	private int modelNumber;
	
	/** The sale percent. */
	private float salePercent;
	
	/**
	 * Instantiates a new purchase module.
	 *
	 * @param modelNumber the model number
	 * @param salePercent the sale percent
	 */
	public PurchaseModule(int modelNumber, float salePercent) {
		this.modelNumber = modelNumber;
		this.salePercent = salePercent;
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
	 * Gets the sale percent.
	 *
	 * @return the sale percent
	 */
	public float getSalePercent() {
		return salePercent;
	}

	/**
	 * Sets the sale percent.
	 *
	 * @param salePercent the new sale percent
	 */
	public void setSalePercent(float salePercent) {
		this.salePercent = salePercent;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PurchaseModule [modelNumber=" + modelNumber + ", salePercent=" + salePercent + "]";
	}
	
}
