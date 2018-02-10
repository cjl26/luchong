package com.project.m.api.common.intf.req;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpParamRequestConvertor implements RequestConvertor<HttpServletRequest> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public InterfaceRequest convert(HttpServletRequest request) throws Exception {
		logger.info("http request param map : " + request.getParameterMap());

		String charset = request.getParameter(InterfaceRequest.CHARSET);
		logger.info("input charset : " + charset);
		if (charset != null) {
			request.setCharacterEncoding(charset);
		}
		/*
		ir.setOpenid(getNodeValue(root, InterfaceRequest.OPENID));
		ir.setApi_version(getNodeValue(root, InterfaceRequest.API_VERSION));
		ir.setCharset(getNodeValue(root, InterfaceRequest.CHARSET));
		ir.setTimestamp(getNodeValue(root, InterfaceRequest.TIMESTAMP));
		ir.setService(getNodeValue(root, InterfaceRequest.SERVICE));
		ir.setBrand(getNodeValue(root, InterfaceRequest.BRAND));
		ir.setModel(getNodeValue(root, InterfaceRequest.MODEL));
		ir.setLanguage(getNodeValue(root, InterfaceRequest.LANGUAGE));
		ir.setVersion(getNodeValue(root, InterfaceRequest.VERSION));
		ir.setSystem(getNodeValue(root, InterfaceRequest.SYSTEM));
		ir.setPlatform(getNodeValue(root, InterfaceRequest.PLATFORM));
		 * */
		String openId = request.getParameter(InterfaceRequest.OPENID);
		String apiVersion = request.getParameter(InterfaceRequest.API_VERSION);
		String timestamp = request.getParameter(InterfaceRequest.TIMESTAMP);
		String service = request.getParameter(InterfaceRequest.SERVICE);
		String brand = request.getParameter(InterfaceRequest.BRAND);
		String model = request.getParameter(InterfaceRequest.MODEL);
		String language = request.getParameter(InterfaceRequest.LANGUAGE);
		String version = request.getParameter(InterfaceRequest.VERSION);
		String system = request.getParameter(InterfaceRequest.SYSTEM);
		String platform = request.getParameter(InterfaceRequest.PLATFORM);
		
		InterfaceRequest ir = new InterfaceRequest();
		
		ir.setOpenid(openId);
		ir.setApi_version(apiVersion);
		ir.setTimestamp(timestamp);
		ir.setService(service);
		ir.setBrand(brand);
		ir.setModel(model);
		ir.setLanguage(language);
		ir.setVersion(version);
		ir.setSystem(system);
		ir.setPlatform(platform);
		ir.setCharset(charset);
		
		ir.setRequestBody(null);
		ir.setProtocolType(ProtocolType.HTTP_PARAM);

		for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			String[] values = entry.getValue();
			String value = null;
			if (values != null) {
				value = values[0];
			}
			ir.getRequestParam().put(entry.getKey(), value);
		}

		ir.getBizParam().putAll(ir.getRequestParam());

		for (String key : InterfaceRequest.NON_BIZ_KEYS) {
			ir.getBizParam().remove(key);
		}

		return ir;
	}
}
