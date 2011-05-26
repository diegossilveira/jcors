package org.jcors.model;

/**
 * Valid HTTP Methods
 * 
 * @author Diego Silveira
 */
public enum HttpMethod {

	CONNECT, DELETE, GET, HEAD, OPTIONS, POST, PUT, TRACE;

	public static boolean isValidMethod(String method) {

		try {

			HttpMethod.valueOf(method);
			return true;

		} catch (Exception ex) {
			return false;
		}
	}

}
