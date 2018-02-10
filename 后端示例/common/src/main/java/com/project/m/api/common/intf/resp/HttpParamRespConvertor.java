package com.project.m.api.common.intf.resp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.project.m.api.common.biz.resp.BaseResp;
import com.project.m.api.common.intf.req.InterfaceRequest;
import com.project.m.api.common.intf.req.ProtocolType;
import com.project.m.api.common.util.JacksonObjectMapper;

public class HttpParamRespConvertor implements RespConvertor {

	@Override
	public InterfaceResp convert(InterfaceRequest request, BaseResp bizResp) throws Exception {

		BeanUtils.copyProperties(request, bizResp);
		bizResp.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		InterfaceResp ir = new InterfaceResp();
		BeanUtils.copyProperties(bizResp, ir);
		ObjectMapper objectMapper = JacksonObjectMapper.getDefault();
		String jsonString = objectMapper.writeValueAsString(bizResp);

		ir.setProtocolType(ProtocolType.HTTP_PARAM);

		JsonNode root = objectMapper.readTree(jsonString);

		for (Iterator<Entry<String, JsonNode>> it = root.fields(); it.hasNext();) {
			Entry<String, JsonNode> entry = it.next();
			JsonNode node = entry.getValue();
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
				default:
					break;
			}
			ir.getResponseParam().put(entry.getKey(), value);
		}

		return ir;
	}
}
