package org.jcors.web.filter;

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
import org.jcors.config.JCorsConfiguration;
import org.jcors.web.RequestHandler;
import org.jcors.web.RequestHandlerFactory;

/**
 * Main application filter, responsible for enabling CORS Requests handling
 * 
 * @author Diego Silveira
 */
public class CorsEnablingFilter implements Filter {

	private final Logger log = Logger.getLogger(CorsEnablingFilter.class);
	
	private JCorsConfiguration config;

	public void init(FilterConfig config) throws ServletException {
		//TODO: parse XML configuration file, if existing in classpath
		this.config = new JCorsConfiguration();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request; 
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		try {
			
			RequestHandler requestHandler = RequestHandlerFactory.getRequestHandler(httpRequest);
			requestHandler.handle(httpRequest, httpResponse, config);
			
		} catch(Exception ex) {
			
			log.error("Error while handling a request");
			throw new ServletException(ex.getMessage(), ex);
		}
		
		chain.doFilter(request, response);

	}

	public void destroy() {
	}

}
