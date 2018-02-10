package com.project.m.api.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.m.api.common.intf.InterfaceContext;

public class InterfaceContextWebFilter implements Filter {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// logger.debug("interface context " + InterfaceContext.getContext());
		if (request instanceof HttpServletRequest) {
			InterfaceContext.getContext().setHttpServletRequest((HttpServletRequest) request);
			// logger.debug("set http servlet request to interface context "
			// + InterfaceContext.getContext().getHttpServletRequest());
		}
		if (response instanceof HttpServletResponse) {
			InterfaceContext.getContext().setHttpServletResponse((HttpServletResponse) response);
		}
		try {
			chain.doFilter(request, response);
		} finally {
			// logger.debug("clear interface context " +
			// InterfaceContext.getContext());
			InterfaceContext.reset();
			// logger.debug("cleared interface context ");
		}

	}

	@Override
	public void destroy() {

	}

}
