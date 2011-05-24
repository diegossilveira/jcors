package org.jcors.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jcors.config.JCorsConfiguration;

/**
 * Specialized handler for Preflight Requests
 * 
 * @author Diego Silveira
 */
public class SimpleRequestHandler implements RequestHandler {

	/**
	 * @see RequestHandler.handle
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response, JCorsConfiguration config) throws IOException,
			ServletException {
		
		// nothing to do
	}

	
	
}
