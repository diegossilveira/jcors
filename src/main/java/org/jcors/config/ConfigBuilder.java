package org.jcors.config;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class responsible for building configuration from XML file
 * 
 * @author Diego Silveira
 */
@XmlRootElement(name = "configuration", namespace = "http://jcors.org/config")
@XmlAccessorType(XmlAccessType.FIELD)
class ConfigBuilder {

	@XmlElement(namespace = "http://jcors.org/config")
	private Boolean enableNonCorsRequests;

	@XmlElement(namespace = "http://jcors.org/config")
	private Boolean resourcesSupportCredentials;

	@XmlElement(namespace = "http://jcors.org/config")
	private Integer preflightResultCacheMaxAge;

	@XmlElementWrapper(namespace = "http://jcors.org/config")
	@XmlElement(namespace = "http://jcors.org/config", name = "origin")
	private Set<String> allowedOrigins;

	@XmlElementWrapper(namespace = "http://jcors.org/config")
	@XmlElement(namespace = "http://jcors.org/config", name = "header")
	private Set<String> exposedHeaders;

	@XmlElementWrapper(namespace = "http://jcors.org/config")
	@XmlElement(namespace = "http://jcors.org/config", name = "header")
	private Set<String> allowedHeaders;

	@XmlElementWrapper(namespace = "http://jcors.org/config")
	@XmlElement(namespace = "http://jcors.org/config", name = "method")
	private Set<String> allowedMethods;

	/**
	 * Build a new configuration
	 * 
	 * @return
	 */
	JCorsConfig buildConfig() {

		JCorsConfig config = new JCorsConfig();

		if (enableNonCorsRequests != null) {
			config.setEnableNonCorsRequests(enableNonCorsRequests);
		}

		if (resourcesSupportCredentials != null) {
			config.setResourcesSupportCredentials(resourcesSupportCredentials);
		}

		if (preflightResultCacheMaxAge != null) {
			config.setPreflightResultCacheMaxAge(preflightResultCacheMaxAge);
		}

		setAllowedOrigins(config);
		setAllowedHeaders(config);
		setAllowedMethods(config);
		setExposedHeaders(config);

		return config;
	}

	private void setAllowedOrigins(JCorsConfig config) {
		
		if (allowedOrigins != null) {
			for (String origin : allowedOrigins) {
				config.addAllowedOrigin(origin);
			}
		}
	}
	
	private void setExposedHeaders(JCorsConfig config) {
		
		if (exposedHeaders != null) {
			for (String header : exposedHeaders) {
				config.addExposedHeader(header);
			}
		}
	}
	
	private void setAllowedHeaders(JCorsConfig config) {
		
		if (allowedHeaders != null) {
			for (String header : allowedHeaders) {
				config.addAllowedHeader(header);
			}
		}
	}
	
	private void setAllowedMethods(JCorsConfig config) {
		
		if (allowedMethods != null) {
			for (String method : allowedMethods) {
				config.addAllowedMethod(method);
			}
		}
	}

}
