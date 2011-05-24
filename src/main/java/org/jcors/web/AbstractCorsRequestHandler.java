package org.jcors.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jcors.config.JCorsConfiguration;
import org.jcors.model.CorsHeaders;
import org.jcors.util.Constraint;

/**
 * Specialized handler for CORS Requests (aka Actual Requests)
 * 
 * @author Diego Silveira
 */
public abstract class AbstractCorsRequestHandler implements RequestHandler {

	/**
	 * @see RequestHandler.handle
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response, JCorsConfiguration config) throws IOException,
			ServletException {
		
		String originHeader = request.getHeader(CorsHeaders.ORIGIN_HEADER);
		Constraint.ensureNotNull(originHeader, "Cross-Origin requests must specify an Origin Header");

		String[] origins = originHeader.split(" ");

		for (String origin : origins) {
			Constraint.ensureTrue(config.isOriginAllowed(origin), String.format("The specified origin is not allowed: '%s'", origin));
		}
		
	}
	
}
