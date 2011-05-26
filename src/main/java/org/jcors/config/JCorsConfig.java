package org.jcors.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jcors.model.HttpMethod;
import org.jcors.util.Constraint;

/**
 * Class that represents the filter configuration
 * 
 * @author Diego Silveira
 */
public final class JCorsConfig {

	private boolean enableNonCorsRequests;

	private boolean resourcesSupportsCredentials;

	private int preflightResultCacheMaxAge;

	private Set<String> allowedOrigins;

	private Set<String> exposedHeaders;

	private Set<String> allowedHeaders;

	private Set<String> allowedMethods;

	public JCorsConfig() {

		enableNonCorsRequests = true;
		resourcesSupportsCredentials = true;
		preflightResultCacheMaxAge = 0;
		allowedOrigins = new HashSet<String>();
		exposedHeaders = new HashSet<String>();
		allowedHeaders = new HashSet<String>();
		allowedMethods = new HashSet<String>();
	}

	/**
	 * Check if conventional requests are enabled
	 * 
	 * @return
	 */
	public boolean isNonCorsRequestsEnabled() {

		return enableNonCorsRequests;
	}

	/**
	 * Check if the resource supports credentials
	 * 
	 * @return
	 */
	public boolean isCredentialsSupported() {

		return resourcesSupportsCredentials;
	}

	/**
	 * Check if the preflight result cache is enabled
	 * 
	 * @return
	 */
	public boolean isPreflightResultCacheEnabled() {

		return preflightResultCacheMaxAge > 0;
	}

	/**
	 * Get the delta-seconds that indicates how long the results of a preflight request can be cached in a preflight result cache
	 * 
	 * @return
	 */
	public int getPreflightResultCacheMaxAge() {
		return preflightResultCacheMaxAge;
	}

	/**
	 * Return all origins allowed by the resource
	 * 
	 * @return
	 */
	public Collection<String> getAllowedOrigins() {

		return allowedOrigins;
	}

	/**
	 * Check if the origin is allowed by the resource
	 * 
	 * @param origin
	 * @return
	 */
	public boolean isOriginAllowed(String origin) {

		return allowedOrigins.isEmpty() || allowedOrigins.contains(origin);
	}

	/**
	 * Add an origin allowed by the resource
	 * 
	 * @param origin
	 */
	public void addAllowedOrigin(String origin) {

		Constraint.ensureNotEmpty(origin, String.format("Invalid origin '%s'", origin));
		allowedOrigins.add(origin);
	}

	/**
	 * Check if there are any non-simple resource header exposed
	 * 
	 * @return
	 */
	public boolean hasNotSimpleResponseHeadersExposed() {

		return !exposedHeaders.isEmpty();
	}

	/**
	 * Add a non-simple resource header exposed by the resource. A simple resource header is one of these: Cache-Control, Content-Language,
	 * Content-Type, Expires, Last-Modified, Pragma
	 * 
	 * @param header
	 */
	public void addExposedHeader(String header) {

		Constraint.ensureNotEmpty(header, String.format("Invalid header '%s'", header));
		exposedHeaders.add(header);
	}

	/**
	 * Return all non-simple resource headers exposed by the resource
	 * 
	 * @return
	 */
	public Collection<String> getExposedHeaders() {

		return exposedHeaders;
	}

	/**
	 * Check if the header is allowed by the resource. This check is case-insensitive
	 * 
	 * @param header
	 * @return
	 */
	public boolean isHeaderAllowed(String header) {

		return allowedHeaders.isEmpty() || allowedHeaders.contains(header.toLowerCase());
	}

	/**
	 * Add a header allowed by the resource
	 * 
	 * @param header
	 */
	public void addAllowedHeader(String header) {

		Constraint.ensureNotEmpty(header, String.format("Invalid header '%s'", header));
		allowedHeaders.add(header.toLowerCase());
	}

	/**
	 * Return all headers allowed by the resource, including the preflight request headers
	 * 
	 * @param requestHeaders
	 * @return
	 */
	public Collection<String> getAllowedHeaders(Collection<String> requestHeaders) {

		Constraint.ensureNotNull(requestHeaders, "Null headers");
		Set<String> rtAllowedHeaders = new HashSet<String>(allowedHeaders);
		rtAllowedHeaders.addAll(requestHeaders);

		return rtAllowedHeaders;
	}

	/**
	 * Check if the method is allowed by the resource This check is case-sensitive
	 * 
	 * @param method
	 * @return
	 */
	public boolean isMethodAllowed(String method) {

		return allowedMethods.isEmpty() || allowedMethods.contains(method);
	}

	/**
	 * Add a method allowed by the resource
	 * 
	 * @param method
	 */
	public void addAllowedMethod(String method) {

		Constraint.ensureNotEmpty(method, String.format("Invalid method '%s'", method));
		Constraint.ensureTrue(HttpMethod.isValidMethod(method), String.format("Invalid method '%s'", method));
		allowedMethods.add(method.toUpperCase());
	}

	/**
	 * Return all methods allowed by the resource, including the preflight request method
	 * 
	 * @param requestMethod
	 * @return
	 */
	public Collection<String> getAllowedMethods(String requestMethod) {

		Constraint.ensureNotEmpty(requestMethod, String.format("Invalid method '%s'", requestMethod));
		Set<String> rtAllowedMethods = new HashSet<String>(allowedMethods);
		rtAllowedMethods.add(requestMethod.toUpperCase());

		return rtAllowedMethods;
	}

	/**
	 * Simple setter
	 * 
	 * @param enableNonCorsRequests
	 */
	public void setEnableNonCorsRequests(boolean enableNonCorsRequests) {

		this.enableNonCorsRequests = enableNonCorsRequests;
	}

	/**
	 * Simple setter
	 * 
	 * @param resourcesSupportsCredentials
	 */
	public void setResourcesSupportsCredentials(boolean resourcesSupportsCredentials) {

		this.resourcesSupportsCredentials = resourcesSupportsCredentials;
	}

	/**
	 * Simple setter
	 * 
	 * @param preflightResultCacheMaxAge
	 */
	public void setPreflightResultCacheMaxAge(int preflightResultCacheMaxAge) {

		this.preflightResultCacheMaxAge = preflightResultCacheMaxAge;
	}

	public String toString() {

		String newLine = System.getProperty("line.separator");

		StringBuilder builder = new StringBuilder();
		builder.append("JCors Configuration: ");
		builder.append(newLine);
		builder.append("Enabling non-CORS requests: ").append(enableNonCorsRequests);
		builder.append(newLine);
		builder.append("Credentials Supported: ").append(resourcesSupportsCredentials);
		builder.append(newLine);
		builder.append("Preflight Requests max age: ").append(
				isPreflightResultCacheEnabled() ? preflightResultCacheMaxAge : "cache disabled");
		builder.append(newLine);
		builder.append("Allowed Origins: ").append(allowedOrigins.isEmpty() ? "*" : allowedOrigins);
		builder.append(newLine);
		builder.append("Allowed Headers: ").append(allowedHeaders.isEmpty() ? "*" : allowedHeaders);
		builder.append(newLine);
		builder.append("Allowed Methods: ").append(allowedMethods.isEmpty() ? "*" : allowedMethods);
		builder.append(newLine);
		builder.append("Exposed Headers: ").append(exposedHeaders);

		return builder.toString();
	}
}
