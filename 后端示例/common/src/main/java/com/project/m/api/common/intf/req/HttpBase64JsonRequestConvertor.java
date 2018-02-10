package com.project.m.api.common.intf.req;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.project.m.api.common.util.JacksonObjectMapper;
import com.project.m.api.common.util.JacksonUtils;

public class HttpBase64JsonRequestConvertor implements RequestConvertor<HttpServletRequest> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public InterfaceRequest convert(HttpServletRequest request) throws Exception {

		ServletInputStream is = request.getInputStream();
		byte[] bytes = IOUtils.toByteArray(is);

		if (bytes == null || bytes.length == 0) {
			InterfaceRequest ir = new InterfaceRequest();
			ir.setProtocolType(ProtocolType.HTTP_JSON);
			ir.setCharset("UTF-8");
			return ir;
		}

		bytes = Base64.decodeBase64(bytes);

		String inputCharset = getCharset(bytes);

		String requestBody = new String(bytes, inputCharset);
		logger.info("requestBody : " + requestBody);

		ObjectMapper mapper = JacksonObjectMapper.getDefault();
		JsonNode root = mapper.readTree(requestBody.getBytes("UTF-8"));

		InterfaceRequest ir = new InterfaceRequest();
		
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

		ir.setRequestBody(requestBody);
		ir.setProtocolType(ProtocolType.HTTP_JSON);

		for (Iterator<Entry<String, JsonNode>> it = root.fields(); it.hasNext();) {
			Entry<String, JsonNode> entry = it.next();
			JsonNode node = entry.getValue();
			ir.getRequestParam().put(entry.getKey(), JacksonUtils.getNodeStringValue(node));
		}

		ir.getBizParam().putAll(ir.getRequestParam());

		for (String key : InterfaceRequest.NON_BIZ_KEYS) {
			ir.getBizParam().remove(key);
		}

		return ir;
	}

	private String getCharset(byte[] bytes) throws UnsupportedEncodingException {
		JsonObject obj = (JsonObject) new JsonParser().parse(new String(bytes, "UTF-8"));
		return obj.get(InterfaceRequest.CHARSET).getAsString();
	}

	private String getNodeValue(JsonNode node, String field) {
		JsonNode fieldNode = node.findValue(field);
		return fieldNode == null ? null : fieldNode.asText();
	}

}
