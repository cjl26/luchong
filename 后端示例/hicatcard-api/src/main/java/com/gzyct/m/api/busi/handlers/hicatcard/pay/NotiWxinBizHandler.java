//package com.gzyct.m.api.busi.handlers.hicatcard.pay;
//
//import com.gzyct.m.api.common.biz.BizHandler;
//import com.gzyct.m.api.common.biz.BizHandlerConfigItem;
//import com.gzyct.m.api.common.biz.BizHanlderConfig;
//import com.gzyct.m.api.common.biz.req.BizRequest;
//import com.gzyct.m.api.common.biz.resp.BizResp;
//import com.gzyct.m.api.paycenter.bean.PayResultNotifyBizReq;
//import com.gzyct.m.api.paycenter.bean.PayResultNotifyBizResp;
//import com.gzyct.m.api.paycenter.bean.wxin.NotiWxinBizReq;
//import com.gzyct.m.api.paycenter.bean.wxin.NotiWxinBizResp;
//import com.gzyct.m.api.paycenter.util.XmlUtil;
//import com.gzyct.m.api.paycenter.util.builder.PayResultNotiReqBuilder;
//import com.gzyct.m.api.paycenter.util.builder.PayResultNotiRespBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//public class NotiWxinBizHandler {
//	protected final Logger logger = LoggerFactory.getLogger(getClass());
//
//	@Resource(name = "paycenterBizHandlerConfig")
//	private BizHanlderConfig bizHanlderConfig;
//
//	public String handle(NotiWxinBizReq notiWxinBizReq,String requestBody) throws Exception {
//
////		ValidateRet vRet = checkParam(notiWxinBizReq);
////		if(!vRet.isValid()){
////			NotiQQPayBizResp resp = new NotiQQPayBizResp();
//////			BaseRespConverter.fromRequest(notiWxinBizReq, resp);
////			resp.setResult_code(PayCenterError.ERR_CODE_PARAM_BAD_REQ);
////			resp.setErr_msg(vRet.getErrMsg());
////			return resp;
////		}
//		logger.info("notify xml = "+requestBody);
//		// TODO: 2016/4/21  签名校对
//		if(!notiWxinBizReq.getReturn_code().equalsIgnoreCase("SUCCESS")){
//			// TODO微信通知返回不成功
//		}
//		PayResultNotifyBizReq payResultNotifyBizRequest = PayResultNotiReqBuilder.fromNotiWxinReq(notiWxinBizReq);
//		if(payResultNotifyBizRequest.getProduct_source()==null || payResultNotifyBizRequest.getProduct_source().equals("")){
//			logger.error("product source is null or empty, reset it to default:1001, CZJ recharge");
//			payResultNotifyBizRequest.setProduct_source("1001");
//		}
//
//		//TODO should use http request?
//
//		BizHandlerConfigItem<BizRequest, BizResp> itemConfig = bizHanlderConfig.getItemConfig(payResultNotifyBizRequest.getService());
//		BizHandler<BizRequest, BizResp> bizHanlder = itemConfig.getBizHanlder();
//		logger.info("bizHanlder " + bizHanlder);
//
//		BizResp bizResp = bizHanlder.handle(payResultNotifyBizRequest);
//		logger.info("bizResp " + bizResp);
//
//		NotiWxinBizResp resp = PayResultNotiRespBuilder.toNotiWxinResp((PayResultNotifyBizResp)bizResp);
//		String xmlReq = XmlUtil.toXML(resp, NotiWxinBizResp.class);
//		return xmlReq;
//	}
//
//	public BizHanlderConfig getBizHanlderConfig() {
//		return bizHanlderConfig;
//	}
//
//	public void setBizHanlderConfig(BizHanlderConfig bizHanlderConfig) {
//		this.bizHanlderConfig = bizHanlderConfig;
//	}
//
////	public ValidateRet checkParam(NotiQQPayBizReq req) {
////		ValidateRet vRet = super.checkParam(req);
////		if(!vRet.isValid())return vRet;
////
////		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
////		paramList.add(new ValidateParam("transaction_id", ValidateType.NOTBLANK, req.getTransaction_id()));
////		paramList.add(new ValidateParam("card_face_no", ValidateType.NOTBLANK, req.getCard_face_no()));
////		paramList.add(new ValidateParam("pay_fee", ValidateType.NOTBLANK, req.getPay_fee()));
////		paramList.add(new ValidateParam("attach", ValidateType.NOTBLANK, req.getAttach()));
////		vRet = ValidateUtil.validate(paramList);
////		if(vRet!=null && !vRet.isValid())return vRet;
////
////		//checking attach
////		String attach = req.getAttach();
////		if(!attach.contains(NotiQQPayBizReq.ATTACH_KEY_PRODSRC)
////				|| !attach.contains(NotiQQPayBizReq.ATTACH_KEY_PAYTIME)
////				|| !attach.contains(NotiQQPayBizReq.ATTACH_KEY_PAYID)
////				|| !attach.contains(NotiQQPayBizReq.ATTACH_KEY_PAYERID)){
////			vRet = new ValidateRet(false, "attach 数据格式不正确");
////		}
////		if(vRet==null)vRet = new ValidateRet(true, "");
////		return vRet;
////	}
//}
