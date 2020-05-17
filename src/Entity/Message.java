package Entity;

import java.io.Serializable;

import enums.Commands;

public class Message implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2104589928804663372L;
	private Object obj;
	private Commands cmd;

	public Message(Object obj, Commands cmd) {
		this.obj = obj;
		this.cmd = cmd;
	}

	public Object getObj() {
		return obj;
	}

	public Commands getCmd() {
		return cmd;
	}

	@Override
	public String toString() {
		return "" + cmd + obj;
	}

}
