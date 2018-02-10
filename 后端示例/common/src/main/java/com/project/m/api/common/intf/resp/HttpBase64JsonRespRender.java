package com.project.m.api.common.intf.resp;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpBase64JsonRespRender implements RespRender<HttpServletResponse> {

	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void render(HttpServletResponse output, InterfaceResp ir) throws Exception {
		output.setContentType("application/json;charset=" + ir.getCharset());
		// logger.info("response charset " + ir.getCharset());
		// logger.info("response body " + ir.getResponseBody());
		output.setCharacterEncoding(ir.getCharset());
		output.getOutputStream().write(Base64.encodeBase64(ir.getResponseBody().getBytes(ir.getCharset())));
		output.getOutputStream().flush();
	}
}
