package com.project.m.api.common.intf.resp;

import java.util.HashMap;
import java.util.Map;

import com.project.m.api.common.intf.req.ProtocolType;

public class RespConvertorFactory {

	private static Map<ProtocolType, RespConvertor> map = new HashMap<ProtocolType, RespConvertor>();

	static {
		map.put(ProtocolType.HTTP_JSON, new HttpJsonRespConvertor());
		map.put(ProtocolType.HTTP_BASE64_JSON, new HttpJsonRespConvertor());
	}

	public static RespConvertor getRespConvertor(ProtocolType protocolType) {
		RespConvertor convertor = map.get(protocolType);
		if (convertor == null) {
			throw new RuntimeException("No response convertor found for type " + protocolType);
		} else {
			return convertor;
		}
	}
}
