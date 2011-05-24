package org.jcors.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
public class PreflightRequestHandler implements RequestHandler {

	/**
	 * @see RequestHandler.handle
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response, JCorsConfiguration config) throws IOException,
			ServletException {

		// Security Checks
		String origin = checkOriginHeader(request, config);
		String requestMethod = checkRequestMethod(request, config);
		List<String> requestHeaders = checkRequestHeaders(request, config);

		if (config.isCredentialsSupported()) {
			response.setHeader(CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER, "true");
		}

		if (config.isPreflightResultCacheEnabled()) {
			response.setHeader(CorsHeaders.ACCESS_CONTROL_MAX_AGE_HEADER, String.valueOf(config.getPreflightResultCacheMaxAge()));
		}
		
		for(String method : config.getAllowedMethods(requestMethod)) {
			response.addHeader(CorsHeaders.ACCESS_CONTROL_ALLOW_METHODS_HEADER, method);
		}
		
		for(String header : config.getAllowedHeaders(requestHeaders)) {
			response.addHeader(CorsHeaders.ACCESS_CONTROL_ALLOW_HEADERS_HEADER, header);
		}

		response.setHeader(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, origin);
	}

	/**
	 * Checks if the origin is allowed
	 * 
	 * @param request
	 * @param config
	 */
	private String checkOriginHeader(HttpServletRequest request, JCorsConfiguration config) {

		String originHeader = request.getHeader(CorsHeaders.ORIGIN_HEADER);
		Constraint.ensureNotEmpty(originHeader, "Cross-Origin requests must specify an Origin Header");

		String[] origins = originHeader.split(" ");

		for (String origin : origins) {
			Constraint.ensureTrue(config.isOriginAllowed(origin), String.format("The specified origin is not allowed: '%s'", origin));
		}
		
		return originHeader;
	}

	/**
	 * Checks if the requested method is allowed
	 * 
	 * @param request
	 * @param config
	 */
	//TODO: parse and validate the method
	private String checkRequestMethod(HttpServletRequest request, JCorsConfiguration config) {

		String requestMethod = request.getHeader(CorsHeaders.ACCESS_CONTROL_REQUEST_METHOD_HEADER);
		Constraint.ensureNotEmpty(requestMethod, "Request Method Header must be supplied");

		Constraint.ensureTrue(config.isMethodAllowed(requestMethod),
				String.format("The specified method is not allowed: '%s'", requestMethod));
		
		return requestMethod;
	}

	/**
	 * Checks if the requested headers are allowed
	 * 
	 * @param request
	 * @param config
	 */
	//TODO: parse and validate the headers
	private List<String> checkRequestHeaders(HttpServletRequest request, JCorsConfiguration config) {

		@SuppressWarnings("unchecked")
		Enumeration<String> requestHeadersHeaders = request.getHeaders(CorsHeaders.ACCESS_CONTROL_REQUEST_HEADERS_HEADER);

		List<String> requestHeaders = new ArrayList<String>();
		
		while (requestHeadersHeaders.hasMoreElements()) {
			String requestHeader = (String) requestHeadersHeaders.nextElement();
			Constraint.ensureTrue(config.isHeaderAllowed(requestHeader),
					String.format("The specified header is not allowed: '%s'", requestHeader));
			requestHeaders.add(requestHeader);
		}
		
		return requestHeaders;
	}

}
