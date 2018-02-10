package com.project.m.api.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class JacksonUtils {

	public static String getNodeStringValue(JsonNode node) {
		JsonNodeType nodeType = node.getNodeType();
		String value = null;
		switch (nodeType) {
			case OBJECT:
			case ARRAY:
				value = node.toString();
				break;
			case STRING:
				value = node.asText();
				break;
			case BOOLEAN:
				value = Boolean.toString(node.asBoolean());
				break;
			case NUMBER:
				value = node.asText();
				break;
			case NULL:
				value = null;
				break;
			default:
				node.asText();
		}
		return value;
	}

}
