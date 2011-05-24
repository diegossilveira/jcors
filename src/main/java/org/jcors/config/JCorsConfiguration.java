package org.jcors.config;

import java.util.HashSet;
import java.util.Set;

import org.jcors.util.Constraint;

/**
 * Class that represents the filter configuration
 * 
 * @author Diego Silveira
 */
public final class JCorsConfiguration {

	private boolean enableNonCorsRequests = true;
	private boolean resourcesSupportsCredentials = true;
	private Set<String> allowedOrigins = new HashSet<String>();
	private Set<String> exposedHeaders = new HashSet<String>();
	private Set<String> allowedHeaders = new HashSet<String>();

	public boolean isNonCorsRequestsEnabled() {

		return enableNonCorsRequests;
	}

	public boolean isCredentialsSupported() {

		return resourcesSupportsCredentials;
	}

	public boolean isAllOriginsAllowed() {

		return allowedOrigins.isEmpty() || allowedOrigins.contains("*");
	}

	public boolean isOriginAllowed(String origin) {

		return isAllOriginsAllowed() || allowedOrigins.contains(origin);
	}

	public void addAllowedOrigin(String allowedOrigin) {

		Constraint.ensureNotEmpty(allowedOrigin, String.format("Invalid allowed origin '%s'", allowedOrigin));
		allowedOrigins.add(allowedOrigin);
	}

	public boolean hasNotSimpleResponseHeadersExposed() {

		return !exposedHeaders.isEmpty();
	}

	public String getExposedHeaders() {

		StringBuffer buffer = new StringBuffer();
		for (String header : exposedHeaders) {
			buffer.append(header).append(" ");
		}
		return buffer.length() > 0 ? buffer.substring(0, buffer.length() - 1) : buffer.toString();
	}

}
