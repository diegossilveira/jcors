package org.jcors.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.jcors.config.JCorsConfiguration;

/**
 * Main application filter, responsible for enabling CORS Requests handling
 * 
 * @author Diego Silveira
 */
public class CorsEnablingFilter implements Filter {

	private final Logger log = Logger.getLogger(CorsEnablingFilter.class);
	
	private JCorsConfiguration config;

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
