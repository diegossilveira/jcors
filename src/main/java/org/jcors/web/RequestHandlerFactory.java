package org.jcors.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jcors.model.CorsHeaders;

/**
 * Class that produces the correct instance for CORS requests handling
 * 
 * @author Diego Silveira
 */
public final class RequestHandlerFactory {

	private static final Logger log = Logger.getLogger(RequestHandlerFactory.class);

	private static final String PREFLIGHT_REQUEST_TYPE = "preflight";
	private static final String ACTUAL_REQUEST_TYPE = "actual";
	private static final String NON_CORS_REQUEST_TYPE = "non-CORS";

	public static RequestHandler getRequestHandler(HttpServletRequest request) {

		// CORS Request
		if (!isEmptyHeader(request, CorsHeaders.ORIGIN_HEADER)) {

			if (!isEmptyHeader(request, CorsHeaders.ACCESS_CONTROL_REQUEST_METHOD_HEADER)) {
				logRequest(PREFLIGHT_REQUEST_TYPE, request);
				return new PreflightRequestHandler();
			}

			logRequest(ACTUAL_REQUEST_TYPE, request);
			return new ActualRequestHandler();

		}

		logRequest(NON_CORS_REQUEST_TYPE, request);
		return new SimpleRequestHandler();
	}

	private static boolean isEmptyHeader(HttpServletRequest request, String header) {

		String headerValue = request.getHeader(header);
		return headerValue == null || "".equals(headerValue.trim());
	}

	private static void logRequest(String requestType, HttpServletRequest request) {
		
		String message = String.format("Handling %s request: %s (%s)", requestType, request.getRequestURI(), request.getMethod());
		log.debug(message);
	}

}
