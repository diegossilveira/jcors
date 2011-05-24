package org.jcors.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jcors.config.JCorsConfiguration;
import org.jcors.model.CorsHeaders;

/**
 * Specialized handler for Simple Cross-Origin Requests (aka Actual Requests)
 * 
 * @author Diego Silveira
 */
public class ActualRequestHandler extends AbstractCorsRequestHandler {

	/**
	 * @see RequestHandler.handle
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response, JCorsConfiguration config) throws IOException,
			ServletException {

		super.handle(request, response, config);

		if(config.isCredentialsSupported()) {
			response.setHeader(CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		}
		
		if(config.hasNotSimpleResponseHeadersExposed()) {
			response.setHeader(CorsHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, config.getExposedHeaders());
		}

		response.setHeader(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, request.getHeader(CorsHeaders.ORIGIN_HEADER));
	}

}
