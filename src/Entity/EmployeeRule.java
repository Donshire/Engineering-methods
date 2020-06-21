package Entity;

import java.util.ArrayList;

/**
 * The Class EmployeeRule.
 */
public class EmployeeRule {

	/** The rules. */
	private static ArrayList<String> rules=new ArrayList<String>();
	
	/**
	 * Checks if is empety.
	 *
	 * @return true, if is empety
	 * @throws Exception the exception
	 */
	public static boolean isEmpety() throws Exception {
		if(rules.isEmpty())
			throw new Exception("the employees rules are empety");
		return false;
	}
	
	/**
	 * Checks if is rule exist.
	 *
	 * @param rule the rule
	 * @return true, if is rule exist
	 * @throws Exception the exception
	 */
	public static boolean isRuleExist(String rule) throws Exception {
		isEmpety();
		if(rule!=null) {
			if(rules.contains(rule))
				return true;
		}
		return false;
	}
	
	/**
	 * Adds the rule.
	 *
	 * @param rule the rule
	 */
	public static void addRule(String rule) {
		if(rule!=null) {
			if(!rules.contains(rule))
				rules.add(rule);
		}
	}
	
	/**
	 * Removes the rule.
	 *
	 * @param rule the rule
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public static boolean removeRule(String rule) throws Exception {
		if(isEmpety()) {
			System.out.println("the rule arraylist is empety");
			return false;
		}
		
		if(rule!=null) {
			if(rules.contains(rule)) {
				rules.remove(rule);
				return true;
			}
		}
		return false;
	}
}
