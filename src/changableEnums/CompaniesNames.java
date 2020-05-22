package changableEnums;

import java.util.ArrayList;

//load this class with details



public class CompaniesNames {
	private static ArrayList<String> companiesNames = new ArrayList<String>();
	//"PAZ","SONOL","YELLOW"
	public static boolean isEmpety() throws Exception {
		if (companiesNames.isEmpty())
			throw new Exception("the employees rules are empety");
		return false;
	}

	public static boolean isComapnyNameExist(String rule) throws Exception {
		isEmpety();
		if (rule != null) {
			if (companiesNames.contains(rule))
				return true;
		}
		return false;
	}

	public static void addComapnyName(String rule) {
		if (rule != null) {
			if (!companiesNames.contains(rule))
				companiesNames.add(rule);
		}
	}

	public static boolean removeComapnyName(String rule) throws Exception {
		if (isEmpety()) {
			System.out.println("the rule arraylist is empety");
			return false;
		}

		if (rule != null) {
			if (companiesNames.contains(rule)) {
				companiesNames.remove(rule);
				return true;
			}
		}
		return false;
	}
}
