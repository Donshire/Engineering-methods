package Entity;

import java.io.Serializable;
import enums.Commands;

/**
 * The Class Message.
 */
public class Message implements Serializable {

	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2104589928804663372L;
	
	/** The obj. */
	private Object obj;
	
	/** The cmd. */
	private Commands cmd;

	/**
	 * Instantiates a new message.
	 *
	 * @param obj the obj
	 * @param cmd the cmd
	 */
	public Message(Object obj, Commands cmd) {
		this.obj = obj;
		this.cmd = cmd;
	}

	/**
	 * Gets the obj.
	 *
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * Gets the cmd.
	 *
	 * @return the cmd
	 */
	public Commands getCmd() {
		return cmd;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "" + cmd + obj;
	}

}
