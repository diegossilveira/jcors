package org.jcors.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jcors.config.ConfigLoader;
import org.jcors.config.JCorsConfig;

/**
 * Main application filter, responsible for enabling CORS Requests handling
 * 
 * @author Diego Silveira
 */
public class CorsEnablingFilter implements Filter {

	private final Logger log = Logger.getLogger(CorsEnablingFilter.class);
	
	private JCorsConfig config;

	public void init(FilterConfig config) throws ServletException {
		
		this.config = ConfigLoader.load();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request; 
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		try {
			
			RequestHandler requestHandler = RequestHandlerFactory.getRequestHandler(httpRequest);
			requestHandler.handle(httpRequest, httpResponse, chain, config);
			
		} catch(Exception ex) {
			
			log.error("Error while handling a request", ex);
			httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
			return;
		}
	}

	public void destroy() {
	}

}
