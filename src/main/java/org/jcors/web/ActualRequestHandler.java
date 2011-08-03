package org.jcors.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jcors.config.JCorsConfig;
import org.jcors.model.CorsHeaders;
import org.jcors.util.Constraint;

/**
 * Specialized handler for Simple Cross-Origin Requests (aka Actual Requests)
 * 
 * @author Diego Silveira
 */
public class ActualRequestHandler implements RequestHandler {

	/**
	 * @see RequestHandler.handle
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain chain, JCorsConfig config) throws IOException, ServletException {

		String origin = checkOriginHeader(request, config);

		if (config.areCredentialsSupported()) {
			response.setHeader(CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER, "true");
		}

		if (config.hasNotSimpleResponseHeadersExposed()) {
			for (String exposedHeader : config.getExposedHeaders()) {
				response.addHeader(CorsHeaders.ACCESS_CONTROL_EXPOSE_HEADERS_HEADER, exposedHeader);
			}
		}

		response.setHeader(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, origin);
		
		chain.doFilter(request, response);
	}

	/**
	 * Checks if the origin is allowed
	 * 
	 * @param request
	 * @param config
	 */
	private String checkOriginHeader(HttpServletRequest request, JCorsConfig config) {

		String originHeader = request.getHeader(CorsHeaders.ORIGIN_HEADER);
		Constraint.ensureNotEmpty(originHeader, "Cross-Origin requests must specify an Origin Header");

		String[] origins = originHeader.split(" ");

		for (String origin : origins) {
			Constraint.ensureTrue(config.isOriginAllowed(origin), String.format("The specified origin is not allowed: '%s'", origin));
		}

		return originHeader;
	}

}
