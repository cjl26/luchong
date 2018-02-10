package com.project.m.api.common.intf;

import org.apache.commons.lang3.StringUtils;

import com.project.m.api.common.biz.resp.BizResp;
import com.project.m.api.common.intf.req.InterfaceRequest;

public abstract class AbstractInterfaceHandler implements InterfaceHandler {

	//private final Logger logger = LoggerFactory.getLogger(getClass());

	protected BizResp checkInterfaceRequest(InterfaceRequest ir) throws Exception {
		if (ir == null) {
			BizResp bizResp = new BizResp();
			bizResp.error(ErrCodes.EMPTY_INTERFACE_REQUEST);
			return bizResp;
		}
//		if (StringUtils.isBlank(ir.getVersion())) {
//			return errorBizResp(InterfaceRequest.VERSION);
//		}
		if (StringUtils.isBlank(ir.getService())) {
			return errorBizResp(InterfaceRequest.SERVICE);
		}
//		if (StringUtils.isBlank(ir.getOpenid())) {
//			return errorBizResp(InterfaceRequest.OPENID);
//		}
//		if (StringUtils.isBlank(ir.getChannel_code())) {
//			return errorBizResp(InterfaceRequest.CHANNEL_CODE);
//		}
//		if (!checkPermission(ir.getChannel_code(), ir.getService())) {
//			logger.error("invalid channelCode {} and service {}", ir.getChannel_code(), ir.getService());
//			BizResp bizResp = new BizResp();
//			bizResp.error(ErrCodes.INVALID_SERVICE, new Object[] { "channelCode:" + ir.getChannel_code()
//					+ " service:" + ir.getService() });
//			return bizResp;
//		}
		// if (StringUtils.isBlank(ir.getUser_id())) {
		// return errorBizResp(InterfaceRequest.USER_ID);
		// }
		if (StringUtils.isBlank(ir.getCharset())) {
			return errorBizResp(InterfaceRequest.CHARSET);
		}
		if (StringUtils.isBlank(ir.getTimestamp())) {
			return errorBizResp(InterfaceRequest.TIMESTAMP);
		}
		return null;
	}

//	private boolean checkPermission(String channelCode, String service) throws Exception {
//		return PartnerService.permit(channelCode, service);
//	}

	private BizResp errorBizResp(String paramName) {
		BizResp bizResp = new BizResp();
		bizResp.error(ErrCodes.INVALID_INTERFACE_PARAM, new Object[] { paramName });
		return bizResp;
	}
	
	
}
