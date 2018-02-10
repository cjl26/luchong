package com.project.m.api.common.intf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.m.api.common.biz.BizHandler;
import com.project.m.api.common.biz.BizHandlerConfigItem;
import com.project.m.api.common.biz.BizHanlderConfig;
import com.project.m.api.common.biz.req.BizRequest;
import com.project.m.api.common.biz.req.BizRequestParser;
import com.project.m.api.common.biz.resp.BizResp;
import com.project.m.api.common.intf.req.InterfaceRequest;
import com.project.m.api.common.intf.resp.InterfaceResp;
import com.project.m.api.common.intf.resp.RespConvertor;
import com.project.m.api.common.intf.resp.RespConvertorFactory;

public class DefaultInterfaceHandler extends AbstractInterfaceHandler implements InterfaceHandler {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private BizHanlderConfig bizHanlderConfig;

//	@Value("${yctapi.common.verifySignOn:true}")
//	private boolean verifySignOn = true;

	@Override
	public InterfaceResp handle(InterfaceRequest ir) throws Exception {

		logger.info("interface request : {}", ir);

		BizResp bizResp = null;
		InterfaceResp interfaceResp = null;
		try {
			bizResp = checkInterfaceRequest(ir);
			if (bizResp == null) {// interface request is ok
				bizResp = doHandle(ir);
			}
		} catch (Exception e) {
			// TODO create correlation id for system exception?
			logger.error("system exception when handling interface request {}", ir, e);
			bizResp = new BizResp();
			bizResp.error(ErrCodes.INNER_ERROR);
		}

		RespConvertor respConvertor;
		try {
			respConvertor = RespConvertorFactory.getRespConvertor(ir.getProtocolType());
			interfaceResp = respConvertor.convert(ir, bizResp);
		} catch (Exception e1) {
			throw new Exception();
		}

//		if (StringUtils.isNotBlank(ir.getChannel_code())) {
//			try {
//				interfaceResp = SignWorker.sign(interfaceResp);
//			} catch (Exception e) {
//				// TODO create correlation id for system exception?
//				logger.error("system exception when sign interface resp {} for interface request {}",
//						interfaceResp, ir, e);
//				bizResp = new BizResp();
//				bizResp.error(ErrCodes.INNER_ERROR);
//				try {
//					interfaceResp = respConvertor.convert(ir, bizResp);
//				} catch (Exception e1) {
//					throw new Exception();
//				}
//			}
//		}
		logger.info("interfaceResp : {} ===== for interface request {}", interfaceResp, ir);

		return interfaceResp;
	}

	private BizResp doHandle(InterfaceRequest ir) throws Exception {
		BizResp bizResp = null;
//		boolean isSignValid = true;
//		logger.info("is verify sign on : {}", verifySignOn);
//		if (verifySignOn) {
//			isSignValid = SignWorker.verifySign(ir);
//		}

		//if (isSignValid) {
			BizHandlerConfigItem<BizRequest, BizResp> itemConfig = bizHanlderConfig.getItemConfig(ir
					.getService());

			if (itemConfig == null) {
				logger.error("no bizHanlder mapped for service {} ", ir.getService());
				bizResp = new BizResp();
				bizResp.error(ErrCodes.INVALID_SERVICE, new Object[] { ir.getService() });
			} else {

				BizRequestParser<BizRequest> bizRequestParser = itemConfig.getBizRequestParser();
				BizRequest bizRequest = null;
				try {
					bizRequest = bizRequestParser.parse(ir);
				} catch (Exception e) {
					logger.error("error when parsing interface request {}", ir, e);
					bizResp = new BizResp();
					bizResp.error(ErrCodes.REQUEST_PARSE_ERROR);
					return bizResp;
				}

				logger.info("bizRequest : {}", bizRequest);

				BizHandler<BizRequest, BizResp> bizHanlder = itemConfig.getBizHanlder();

				bizResp = bizHanlder.handle(bizRequest);
				logger.info("bizResp : {} ===== for bizRequest {} ===== by {}", bizResp, bizRequest,
						bizHanlder);
			}

//		} else {
//			bizResp = new BizResp();
//			bizResp.error(ErrCodes.INVALID_SIGN);
//		}
		return bizResp;
	}

	public BizHanlderConfig getBizHanlderConfig() {
		return bizHanlderConfig;
	}

	public void setBizHanlderConfig(BizHanlderConfig bizHanlderConfig) {
		this.bizHanlderConfig = bizHanlderConfig;
	}


}
