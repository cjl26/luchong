package com.project.m.api.common.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonObjectMapper {

	private static final ObjectMapper OM = new ObjectMapper();
	private static final ObjectMapper NOTNULL_OM = new ObjectMapper();
	private static final ObjectMapper VO_OM = new ObjectMapper();
	private static final ObjectMapper OK_FOR_UNKNOWN_FIELD_OM = new ObjectMapper();

	static {
		OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		NOTNULL_OM.setSerializationInclusion(Include.NON_NULL);
		VO_OM.setSerializationInclusion(Include.NON_NULL);
		VO_OM.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		OK_FOR_UNKNOWN_FIELD_OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static ObjectMapper getDefault() {
		return OM;
	}

	public static ObjectMapper getNotNullOM() {
		return NOTNULL_OM;
	}

	public static ObjectMapper getVoOM() {
		return VO_OM;
	}

	public static ObjectMapper getOkForUnknownFieldOM() {
		return OK_FOR_UNKNOWN_FIELD_OM;
	}

}
