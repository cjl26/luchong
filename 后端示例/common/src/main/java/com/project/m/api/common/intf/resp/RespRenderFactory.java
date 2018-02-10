package com.project.m.api.common.intf.resp;

import java.util.HashMap;
import java.util.Map;

import com.project.m.api.common.intf.req.ProtocolType;

public class RespRenderFactory {

	private static Map<ProtocolType, RespRender<?>> map = new HashMap<ProtocolType, RespRender<?>>();

	static {
		map.put(ProtocolType.HTTP_JSON, new HttpJsonRespRender());
		map.put(ProtocolType.HTTP_BASE64_JSON, new HttpBase64JsonRespRender());
	}

	public static RespRender<?> getRespConvertor(ProtocolType protocolType) {
		RespRender<?> render = map.get(protocolType);
		if (render == null) {
			throw new RuntimeException("No response render found for type " + protocolType);
		} else {
			return render;
		}
	}

}
