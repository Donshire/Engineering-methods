package Entity;

import java.io.Serializable;

public class PurchaseModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7173683030345152292L;
	private int modelNumber;
	private float salePercent;
	
	public PurchaseModule(int modelNumber, float salePercent) {
		this.modelNumber = modelNumber;
		this.salePercent = salePercent;
	}

	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	public float getSalePercent() {
		return salePercent;
	}

	public void setSalePercent(float salePercent) {
		this.salePercent = salePercent;
	}

	@Override
	public String toString() {
		return "PurchaseModule [modelNumber=" + modelNumber + ", salePercent=" + salePercent + "]";
	}
	
}
