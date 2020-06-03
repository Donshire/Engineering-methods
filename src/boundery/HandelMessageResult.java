package boundery;

import javax.swing.JOptionPane;

import Entity.Message;

public class HandelMessageResult {
/**
 * this function will handle the result been sent by the server
 * according to the result send by the server
 * there is 3 cases : in which the apopraite string content 
 * message will print if it is not empty
 * @param <T>
 * @param message
 * @param succes
 * @param noItemFound
 * @param exprtion
 * @return the wanted object if defaultRes else null
 */
	public static <T> T handelMessage(Message message,String succes,String noItemFound,String exprtion) {
		switch(message.getCmd()) {
		case defaultRes:
			if(!succes.isEmpty())
				JOptionPane.showMessageDialog(null, succes);
			return (T)message.getObj();
		case NoElementFound:
			if(!noItemFound.isEmpty())
				JOptionPane.showMessageDialog(null, noItemFound);
			return null;
		case ExceptionHappened:
			if(!exprtion.isEmpty())
				JOptionPane.showMessageDialog(null, exprtion);
			return null;
		}
		return null;
	}
	
}
