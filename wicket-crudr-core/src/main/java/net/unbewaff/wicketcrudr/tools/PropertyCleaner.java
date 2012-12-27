package net.unbewaff.wicketcrudr.tools;


public class PropertyCleaner {

	public static String getCleanPropertyName(String property) {
		String clean = property;
		if (clean.startsWith("get")) {
		    clean = clean.substring(3);
		} else if (clean.startsWith("is")) {
		    clean = clean.substring(2);
		}
		return clean;
	}

}
