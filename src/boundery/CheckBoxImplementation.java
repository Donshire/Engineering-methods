package boundery;

import java.io.Serializable;

/**
 * for the implementaion of the check box
 * @author iamme
 *
 */
public abstract class CheckBoxImplementation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3517145898777102465L;
	protected boolean check;
	
	public boolean getCheck() {
		return check;
	};

	public void setCheck(boolean check) {
		this.check=check;
	};
}
