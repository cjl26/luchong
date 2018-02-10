package com.project.m.api.common.intf.resp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.m.api.common.biz.resp.BaseResp;
import com.project.m.api.common.intf.InterfaceContext;
import com.project.m.api.common.intf.req.InterfaceRequest;
import com.project.m.api.common.intf.req.ProtocolType;
import com.project.m.api.common.util.JacksonObjectMapper;
import com.project.m.api.common.util.JacksonUtils;

public class HttpJsonRespConvertor implements RespConvertor {

	@Override
	public InterfaceResp convert(InterfaceRequest request, BaseResp bizResp) throws Exception {

		InterfaceResp ir = new InterfaceResp();
		buildBaseResp(request, bizResp, ir);
		// BeanUtils.copyProperties(bizResp, ir);

		ObjectMapper objectMapper = JacksonObjectMapper.getNotNullOM();
		if (InterfaceContext.getContext().get(InterfaceContext.VO_RESP) != null) {
			objectMapper = JacksonObjectMapper.getVoOM();
		}
		String jsonString = objectMapper.writeValueAsString(bizResp);

		ir.setProtocolType(ProtocolType.HTTP_JSON);
		ir.setResponseBody(jsonString);

		JsonNode root = objectMapper.readTree(jsonString);

		for (Iterator<Entry<String, JsonNode>> it = root.fields(); it.hasNext();) {
			Entry<String, JsonNode> entry = it.next();
			JsonNode node = entry.getValue();
			ir.getResponseParam().put(entry.getKey(), JacksonUtils.getNodeStringValue(node));
		}

		ir.getBizParam().putAll(ir.getResponseParam());

		for (String key : InterfaceResp.NON_BIZ_KEYS) {
			ir.getBizParam().remove(key);
		}

		return ir;
	}

	private void buildBaseResp(InterfaceRequest ir, BaseResp bizResp, InterfaceResp iresp) {

		bizResp.setOpenid(ir.getOpenid());
		bizResp.setApi_version(ir.getApi_version());
		bizResp.setCharset(ir.getCharset());
		bizResp.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		bizResp.setService(ir.getService());
		bizResp.setBrand(ir.getBrand());
		bizResp.setModel(ir.getModel());
		bizResp.setLanguage(ir.getLanguage());
		bizResp.setVersion(ir.getVersion());
		bizResp.setSystem(ir.getSystem());
		bizResp.setPlatform(ir.getPlatform());

		iresp.setOpenid(ir.getOpenid());
		iresp.setApi_version(ir.getApi_version());
		iresp.setCharset(ir.getCharset());
		iresp.setTimestamp(bizResp.getTimestamp());
		iresp.setService(ir.getService());
		iresp.setBrand(ir.getBrand());
		iresp.setModel(ir.getModel());
		iresp.setLanguage(ir.getLanguage());
		iresp.setVersion(ir.getVersion());
		iresp.setSystem(ir.getSystem());
		iresp.setPlatform(ir.getPlatform());

	}

}
