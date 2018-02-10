package com.project.m.api.common.intf.resp;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpJsonRespRender implements RespRender<HttpServletResponse> {

	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void render(HttpServletResponse output, InterfaceResp ir) throws Exception {
		output.setContentType("application/json;charset=" + ir.getCharset());
		// logger.info("response charset " + ir.getCharset());
		// logger.info("response body " + ir.getResponseBody());
		output.setCharacterEncoding(ir.getCharset());
		output.getOutputStream().write(ir.getResponseBody().getBytes(ir.getCharset()));
		output.getOutputStream().flush();
	}
}
