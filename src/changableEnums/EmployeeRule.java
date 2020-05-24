package changableEnums;

import java.util.ArrayList;

public class EmployeeRule {

	private static ArrayList<String> rules=new ArrayList<String>();
	
	public static boolean isEmpety() throws Exception {
		if(rules.isEmpty())
			throw new Exception("the employees rules are empety");
		return false;
	}
	
	public static boolean isRuleExist(String rule) throws Exception {
		isEmpety();
		if(rule!=null) {
			if(rules.contains(rule))
				return true;
		}
		return false;
	}
	
	public static void addRule(String rule) {
		if(rule!=null) {
			if(!rules.contains(rule))
				rules.add(rule);
		}
	}
	
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
