package com.project.m.api.common.util;


public class JsonUtils {

	public static String toJsonString(Object obj) throws Exception {
		return JacksonObjectMapper.getDefault().writeValueAsString(obj);
	}

	public static <T> T toObject(String json, Class<T> toClass) throws Exception {
		return JacksonObjectMapper.getOkForUnknownFieldOM().readValue(json, toClass);
	}
}
