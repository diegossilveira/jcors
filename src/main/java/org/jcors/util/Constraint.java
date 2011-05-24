package org.jcors.util;

/**
 * Auxiliary methods that ensures constraints are not violated
 * 
 * @author Diego Silveira
 */
public final class Constraint {

	private Constraint() {
	}
	
	/**
	 * Ensures that an object is not null
	 * 
	 * @param object
	 * @param errorMessage
	 */
	public static void ensureNotNull(Object object, String errorMessage) {
		
		ensure(object != null, errorMessage);
	}
	
	/**
	 * Ensures that a String is not empty
	 * 
	 * @param string
	 * @param errorMessage
	 */
	public static void ensureNotEmpty(String string, String errorMessage) {
		
		ensureNotNull(string, errorMessage);
		ensure(string.trim().length() > 0, errorMessage);
	}
	
	/**
	 * Ensures that a condition is true
	 * 
	 * @param condition
	 * @param errorMessage
	 */
	public static void ensureTrue(boolean condition, String errorMessage) {
		
		ensure(condition, errorMessage);
	}
	
	private static void ensure(boolean condition, String errorMessage) {
		
		if(!condition) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
}
