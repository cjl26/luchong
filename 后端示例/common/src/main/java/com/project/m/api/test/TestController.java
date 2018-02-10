//package com.project.m.api.test;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.project.m.api.common.intf.InterfaceHandler;
//import com.project.m.api.common.intf.req.InterfaceRequest;
//import com.project.m.api.common.intf.req.ProtocolType;
//import com.project.m.api.common.intf.req.RequestConvertor;
//import com.project.m.api.common.intf.req.RequestConvertorFactory;
//import com.project.m.api.common.intf.resp.InterfaceResp;
//import com.project.m.api.common.intf.resp.RespRender;
//import com.project.m.api.common.intf.resp.RespRenderFactory;
//
//@RestController
//public class TestController {
//
//	@Resource(name = "testInterfaceHandler")
//	private InterfaceHandler interfaceHandler;
//
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
//	public void web(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		InterfaceRequest ir = ((RequestConvertor<HttpServletRequest>) RequestConvertorFactory
//				.getRequestConvertor(ProtocolType.HTTP_JSON)).convert(request);
//		InterfaceResp interfaceResp = interfaceHandler.handle(ir);
//		((RespRender<HttpServletResponse>) RespRenderFactory.getRespConvertor(ProtocolType.HTTP_JSON)).render(response,
//				interfaceResp);
//
//	}
//
//}
