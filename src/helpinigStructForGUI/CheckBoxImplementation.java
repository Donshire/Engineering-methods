package helpinigStructForGUI;

import java.io.Serializable;

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
