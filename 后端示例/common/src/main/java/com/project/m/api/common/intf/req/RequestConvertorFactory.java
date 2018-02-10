package com.project.m.api.common.intf.req;

import java.util.HashMap;
import java.util.Map;

public class RequestConvertorFactory {

	private static Map<ProtocolType, RequestConvertor<?>> map = new HashMap<ProtocolType, RequestConvertor<?>>();

	static {
		map.put(ProtocolType.HTTP_PARAM, new HttpParamRequestConvertor());
		map.put(ProtocolType.HTTP_JSON, new HttpJsonRequestConvertor());
		map.put(ProtocolType.HTTP_BASE64_JSON, new HttpBase64JsonRequestConvertor());
	}

	public static RequestConvertor<?> getRequestConvertor(ProtocolType protocolType) {
		RequestConvertor<?> convertor = map.get(protocolType);
		if (convertor == null) {
			throw new RuntimeException("No request convertor found for type " + protocolType);
		} else {
			return convertor;
		}
	}
}
