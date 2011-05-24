package org.jcors.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jcors.config.JCorsConfiguration;
import org.jcors.model.CorsHeaders;
import org.jcors.util.Constraint;

/**
 * Specialized handler for Preflight Requests
 * 
 * @author Diego Silveira
 */
public class PreflightRequestHandler extends AbstractCorsRequestHandler {

	/**
	 * @see RequestHandler.handle
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response, JCorsConfiguration config) throws IOException,
			ServletException {
		
		
		super.handle(request, response, config);
		
		String requestMethod = request.getHeader(CorsHeaders.ACCESS_CONTROL_REQUEST_METHOD);
		Constraint.ensureNotEmpty(requestMethod, "Request Method Header must be supplied"); //TODO: parse
		
		String headerFieldNames = request.getHeader(CorsHeaders.ACCESS_CONTROL_REQUEST_HEADERS); //TODO: parse
		
		
	}

	
	
}
