package com.project.m.api.common.intf;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterfaceContext {

	public static final String HTTP_SERVLET_REQUEST = "HTTP_SERVLET_REQUEST";
	public static final String HTTP_SERVLET_RESPONSE = "HTTP_SERVLET_RESPONSE";
	public static final String VO_RESP = "VO_RESP";

	static ThreadLocal<InterfaceContext> interfaceContext = new ThreadLocal<InterfaceContext>() {
		@Override
		protected InterfaceContext initialValue() {
			return new InterfaceContext();
		}
	};

	private Map<String, Object> context = new HashMap<String, Object>();

	public InterfaceContext() {

	}

	public InterfaceContext(Map<String, Object> context) {
		this.context = context;
	}

	public static void reset() {
		getContext().context.clear();
	}

	public static void setContext(InterfaceContext context) {
		interfaceContext.set(context);
	}

	public static InterfaceContext getContext() {
		return interfaceContext.get();
	}

	public void put(String key, Object value) {
		context.put(key, value);
	}

	public <T> void put(String key, Object value, Class<T> clazz) {
		context.put(key, value);
	}

	public Object get(String key) {
		return context.get(key);
	}

	public <T> T get(String key, Class<T> clazz) {
		return (T) context.get(key);
	}

	public void setHttpServletRequest(HttpServletRequest request) {
		put(HTTP_SERVLET_REQUEST, request);
	}

	public HttpServletRequest getHttpServletRequest() {
		return get(HTTP_SERVLET_REQUEST, HttpServletRequest.class);
	}

	public void setHttpServletResponse(HttpServletResponse response) {
		put(HTTP_SERVLET_RESPONSE, response);
	}

	public HttpServletResponse getHttpServletResponse() {
		return get(HTTP_SERVLET_RESPONSE, HttpServletResponse.class);
	}

}
