//package com.gzyct.m.api.busi.handlers.hicatcard.pay;
//
//import com.alibaba.fastjson.JSON;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.JsonObject;
//import com.gzyct.m.api.common.biz.BizHandler;
//import com.gzyct.m.api.common.intf.InterfaceContext;
//import com.gzyct.m.api.common.sign.DefaultSignKeyStore;
//import com.gzyct.m.api.common.sign.SignType;
//import com.gzyct.m.api.common.util.JacksonObjectMapper;
//import com.gzyct.m.api.paycenter.bean.PaySdkApplyBizRequest;
//import com.gzyct.m.api.paycenter.bean.PaySdkApplyBizResp;
//import com.gzyct.m.api.paycenter.bean.orderquery.OrderInfo;
//import com.gzyct.m.api.paycenter.bean.orderquery.OrderQueryReq;
//import com.gzyct.m.api.paycenter.bean.orderquery.OrderQueryResp;
//import com.gzyct.m.api.paycenter.bean.wxin.WxUnifiedOrderRequest;
//import com.gzyct.m.api.paycenter.bean.wxin.WxUnifiedOrderResp;
//import com.gzyct.m.api.paycenter.bean.yctpay.YctpayOrderCreateReq;
//import com.gzyct.m.api.paycenter.bean.yctpay.YctpayOrderCreateResp;
//import com.gzyct.m.api.paycenter.config.BusiError;
//import com.gzyct.m.api.paycenter.config.PayCenterError;
//import com.gzyct.m.api.paycenter.config.PayChannelCode;
//import com.gzyct.m.api.paycenter.config.PayChannelConfig;
//import com.gzyct.m.api.paycenter.db.entity.TPayDeliPartner;
//import com.gzyct.m.api.paycenter.db.entity.TPayOrderInfo;
//import com.gzyct.m.api.paycenter.db.entity.TPayThirdInfo;
//import com.gzyct.m.api.paycenter.db.entity.TPayWxPayOrder;
//import com.gzyct.m.api.paycenter.db.service.PayDbService;
//import com.gzyct.m.api.paycenter.util.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
//@Component
//public class PaySdkApplyBizHandler implements BizHandler<PaySdkApplyBizRequest, PaySdkApplyBizResp> {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    PayDbService payDbservice;
//    @Autowired
//    DefaultSignKeyStore defaultSignKeyStore;
//    @Autowired
//    Environment env;
//    @Value("${yct.paycenter.sdkappy.checkOrderOn:true}")
//	private boolean checkOrderOn = true;
//
//    @Override
//    public PaySdkApplyBizResp handle(PaySdkApplyBizRequest bizRequest) throws Exception {
//        PaySdkApplyBizResp bizResp = new PaySdkApplyBizResp();
//        String order_id = bizRequest.getOrder_id();
//
//        if(checkOrderOn){
//        	OrderQueryReq orderQueryReq = formPaySdkApplyBizReq(bizRequest);
//            OrderQueryResp orderQueryResp = internalPost(orderQueryReq, bizRequest.getOrder_source());
//            if (orderQueryResp.getOrder().size()<=0) {
//                logger.error("order not exist!");
//                bizResp.setErr_msg("订单不存在");
//                bizResp.setResult_code("-1");
//                return bizResp;
//            } else {
//            	String orderStatus = orderQueryResp.getOrder().get(0).getOrderstatus();
//            	if (orderStatus!=null&& !orderStatus.equalsIgnoreCase(OrderInfo.ORDER_INFO_STATUS_NOT_PAID)){
//            		logger.error("order_id = " + bizRequest.getOrder_id() + " | status = " + orderStatus);
//                    bizResp.setErr_msg("订单状态异常：" + orderStatus);
//                    bizResp.setResult_code("-1");
//                    return bizResp;
//            	}
//            }
//        }
//
//        TPayOrderInfo poi = new TPayOrderInfo();
//        // 请求json转换TPayOrderInfo形式,在数据库中保存
//        convertBizReqToPoi(bizRequest, poi);
//        // 封装第三方支付接口下单参数并下下单,保存返回的数据到poi和第三方支付订单数据库(T_WX_PAY_ORDER),返回BizRespJson.
//        createThirdPayOrder(bizRequest, bizResp);
//        return bizResp;
//    }
//
//    // 创建订单初始数据并保存到数据库
//    private void convertBizReqToPoi(PaySdkApplyBizRequest request, TPayOrderInfo poi) {
//        poi.setPayChannelCode(request.getPay_channel());
//        poi.setPayChannelType(request.getPay_channel_type());
//        poi.setChannelCode(request.getChannel_code());
//        poi.setPayFee(request.getPay_fee());
//        poi.setFeeType(request.getFee_type());
//        // poi.setxxx(request.getSource());//终端来源暂无字段存储
//        // poi.setxxx(request.getBody());//商品描述无字段存储
//        // poi.setxxx(request.getDetail());//商品详情暂无字段存储
//        poi.setProductSource(request.getOrder_source());
//        poi.setOrderId(request.getOrder_id());
//        poi.setTotalFee(request.getPay_fee());
//        poi.setOrderStatus(OrderStatus.STATUS_1_NOT_PAY);
//        poi.setCreateTime(new Date());
//        poi.setUpdateTime(poi.getCreateTime());
//        payDbservice.saveOrderInfo(poi);// 保存TPayOrderInfo到数据库
//    }
//
//    private void createThirdPayOrder(PaySdkApplyBizRequest bizRequest, PaySdkApplyBizResp bizResp) {
//
//        TPayThirdInfo payThirdInfo = payDbservice.findPayThirdInfo(bizRequest.getPay_channel(), bizRequest.getPay_channel_type());
//        if (bizRequest.getPay_channel().equals(PayChannelCode.WXIN)) {// WXIN支付
//            TPayWxPayOrder wxPayOrder = new TPayWxPayOrder();
//            wxUnifiedOrderCreate(bizRequest, wxPayOrder, payThirdInfo);
//            // 判断返回结果和预支付订单
//            if (wxPayOrder.getResultCode() != null && wxPayOrder.getResultCode().equalsIgnoreCase("SUCCESS")) {
//                if (wxPayOrder.getPrepayId() != null && wxPayOrder.getPrepayId().length() > 0) {
//                    // 封装返回的json
//                    bizResp.setResult_code("0");
//                    bizResp.setChannel_code(wxPayOrder.getChannel());
//                    bizResp.setOrder_id(wxPayOrder.getOutTradeNo());
//
//                    if(bizRequest.getSource().equals("JSAPI")){
//                        SortedMap<String, String> signParams = new TreeMap<String, String>();
//                        signParams.put("appId", payThirdInfo.getAppId());
//                        signParams.put("timeStamp", wxPayOrder.getTimeStart());
//                        signParams.put("nonceStr", wxPayOrder.getNonceStr());
//                        signParams.put("package", "prepay_id="+wxPayOrder.getPrepayId());
//                        signParams.put("signType", "MD5");
//                        String sign = WxPayUtil.genWxPaySign(signParams, payThirdInfo.getAppKey(), "utf-8");
//
//                        JsonObject json = new JsonObject();
//                        json.addProperty("appId", payThirdInfo.getAppId());
//                        json.addProperty("timeStamp", wxPayOrder.getTimeStart());
//                        json.addProperty("nonceStr", wxPayOrder.getNonceStr());
//                        json.addProperty("package", "prepay_id="+wxPayOrder.getPrepayId());
//                        json.addProperty("signType", "MD5");
//                        json.addProperty("paySign", sign);
//                        bizResp.setPay_info(json.toString());
//                    }else {
//                        SortedMap<String, String> signParams = new TreeMap<String, String>();
//                        signParams.put("appid", payThirdInfo.getAppId());
//                        signParams.put("partnerid", payThirdInfo.getYctPartner());
//                        signParams.put("prepayid", wxPayOrder.getPrepayId());
//                        signParams.put("package", "Sign=WXPay");
//                        signParams.put("noncestr", wxPayOrder.getNonceStr());
//                        signParams.put("timestamp", wxPayOrder.getTimeStart());
//                        String sign = WxPayUtil.genWxPaySign(signParams, payThirdInfo.getAppKey(), "utf-8");
//
//                        JsonObject json = new JsonObject();
//                        json.addProperty("appid", payThirdInfo.getAppId());
//                        json.addProperty("partnerid", payThirdInfo.getYctPartner());
//                        json.addProperty("prepayid", wxPayOrder.getPrepayId());
//                        if(wxPayOrder.getCodeUrl()!=null)json.addProperty("codeurl", wxPayOrder.getCodeUrl());
//                        json.addProperty("package", "Sign=WXPay");
//                        json.addProperty("noncestr", wxPayOrder.getNonceStr());
//                        json.addProperty("timestamp", wxPayOrder.getTimeStart());
//                        json.addProperty("sign", sign);
//                        bizResp.setPay_info(json.toString());
//                    }
//                }
//            } else {
//                bizResp.setResult_code("1");
//                bizResp.setPay_info(null);
//                bizResp.setErr_msg(wxPayOrder.getErrCodeDes());
//            }
//        }else if (bizRequest.getPay_channel().equals(PayChannelCode.YPAY)) {// 羊城通宝支付
//            YctpayOrderCreateReq req = new YctpayOrderCreateReq();
//            req.setMch_acct(Long.parseLong(payThirdInfo.getYctPartner()));
//            req.setAppid(payThirdInfo.getAppId());
//            req.setBody(bizRequest.getBody());
//            req.setMch_order_id(bizRequest.getOrder_id());
//            req.setFee_type("CNY");
//            req.setTotal_fee(bizRequest.getPay_fee());
//            req.setNotify_url("http://www.gzyct.com");
//            req.setTrade_type("APP");
//            req.setAttach("attach");
//            YctpayOrderCreateResp resp = new YctpayOrderCreateResp();
//            String jsonResp = (new InternalPostUtil<YctpayOrderCreateReq, YctpayOrderCreateResp>()).call(req, resp, env.getProperty("yctapi_yctpay_create_order_url"),
//                    "yctpay.acct.order.create",env.getProperty("yctapi_yctpay_channel"),env.getProperty("yctapi_yctpay_channel_key"),bizRequest.getUser_id());
//            if(jsonResp == null){
//                bizResp.setResult_code("201201");
//                bizResp.setErr_msg("yctpay.acct.order.create返回为空");
//            }else {
//                resp = JSON.parseObject(jsonResp,YctpayOrderCreateResp.class);
//                if(jsonResp == null ||  resp.getResult_code() == null  ){
//                    bizResp.setResult_code("201201");
//                    bizResp.setErr_msg("内部网络请求错误");
//                }else if( !resp.getResult_code().equals(BusiError.ERR_CODE_SUCCESS)){
//                    bizResp.setResult_code(resp.getResult_code());
//                    bizResp.setErr_msg(resp.getErr_msg());
//                }else {//返回成功
//                    bizResp.setResult_code("0");
//                    bizResp.setChannel_code(bizRequest.getChannel_code());
//                    bizResp.setOrder_id(resp.getOrder_id());
//                    SortedMap<String, String> signParams = new TreeMap<String, String>();
//                    signParams.put("appid", payThirdInfo.getAppId());//商户编码(appid)
//                    signParams.put("mchAcct", payThirdInfo.getYctPartner());//商户账户编号(mchid)
//                    signParams.put("timeStamp", resp.getTimestamp());
//                    String nonceStr = WxPayUtil.getNonceStr();
//                    signParams.put("nonceStr", nonceStr);
//                    signParams.put("orderId", resp.getOrder_id());
//                    signParams.put("signType", "MD5");
//                    String sign = WxPayUtil.genWxPaySign(signParams, payThirdInfo.getAppKey(), "utf-8");
//                    JsonObject json = new JsonObject();
//                    json.addProperty("appid", payThirdInfo.getAppId());
//                    json.addProperty("mchAcct", payThirdInfo.getYctPartner());
//                    json.addProperty("timeStamp", resp.getTimestamp());
//                    json.addProperty("nonceStr", nonceStr);
//                    json.addProperty("orderId", resp.getOrder_id());
//                    json.addProperty("signType", "MD5");
//                    json.addProperty("paySign", sign);
//                    bizResp.setPay_info(json.toString());
//                }
//            }
//
//        } else if (bizRequest.getPay_channel().equals(PayChannelCode.APAY)) {// sdk支付宝
//        }
//    }
//
//    // 连接微信服务器创建订单
//    private void wxUnifiedOrderCreate(PaySdkApplyBizRequest bizRequest, TPayWxPayOrder wxPayOrder,
//                                      TPayThirdInfo payThirdInfo) {
//        if (bizRequest == null)
//            return;
//
//        try {
//            //String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//            String payChannel = bizRequest.getPay_channel().trim().toUpperCase();
//            String payChannelType = bizRequest.getPay_channel_type().trim().toUpperCase();
//            String url = PayChannelConfig.getOrderCreateUrl(payChannel, payChannelType);
//
//            WxUnifiedOrderRequest wxOrderReq = new WxUnifiedOrderRequest();
//            convertReqToWxOrderReq(bizRequest, wxOrderReq, payThirdInfo);
//            String xmlReq = XmlUtil.toXML(wxOrderReq, WxUnifiedOrderRequest.class);
//            // xmlReq = new String(xmlReq.getBytes(this.CHARSET_UTF8));
//            logger.info("=======wx bill create request: " + xmlReq);
//            //// String xmlRes = YCTCommonUtil.sendPost(url, xmlReq);
//            String xmlRes = HttpRequestUtil.httpsRequest(url, "POST", xmlReq);
//            // xmlRes = new String(xmlRes.getBytes(this.CHARSET_UTF8));
//            logger.info("=======wx bill create response: " + xmlRes);
//            WxUnifiedOrderResp wxOrderResp = new WxUnifiedOrderResp();
//            wxOrderResp = (WxUnifiedOrderResp) XmlUtil.fromXml(xmlRes, WxUnifiedOrderResp.class);
//            savePayWxPayOrder(bizRequest, wxOrderReq, wxOrderResp, wxPayOrder);
//        } catch (Exception ex) {
//            logger.error("=======unifiedOrderCreate error: " + ex.getMessage());
//        }
//    }
//
//    private void convertReqToWxOrderReq(PaySdkApplyBizRequest request, WxUnifiedOrderRequest order,
//                                        TPayThirdInfo payThirdInfo) {
//        SortedMap<String, String> signParams = new TreeMap<String, String>();
//        order.setAppid(payThirdInfo.getAppId());
//        signParams.put("appid", order.getAppid());
//        order.setMch_id(payThirdInfo.getYctPartner());
//        signParams.put("mch_id", order.getMch_id());
//        order.setDevice_info("web");// 终端设备号(门店号或收银设备ID)，默认请传"WEB"
//        signParams.put("device_info", order.getDevice_info());
//        order.setNonce_str(WxPayUtil.getNonceStr());
//        signParams.put("nonce_str", order.getNonce_str());
//        if (request.getBody() != null && !request.getBody().equals("")) {
//            order.setBody(request.getBody());
//            signParams.put("body", order.getBody());
//        }
//        if (request.getDetail() != null && !request.getDetail().equals("")) {
//            order.setDetail(request.getDetail());
//            signParams.put("detail", order.getDetail());
//        }
//        if (request.getYct_extend() != null && !request.getYct_extend().equals("")) {
//            order.setAttach(request.getYct_extend());
//            signParams.put("attach", order.getAttach());
//        }
//        if (request.getOrder_id() != null && !request.getOrder_id().equals("")) {
//            order.setOut_trade_no(request.getOrder_id());
//            signParams.put("out_trade_no", order.getOut_trade_no());
//        }
//        if (request.getFee_type() != null && !request.getFee_type().equals("")) {
//            order.setFee_type(request.getFee_type());
//            signParams.put("fee_type", order.getFee_type());
//        }
//        if (request.getPay_fee() != 0) {
//            order.setTotal_fee(request.getPay_fee());
//            signParams.put("total_fee", request.getPay_fee() + "");
//        }
//        HttpServletRequest httpRequest = InterfaceContext.getContext().getHttpServletRequest();
//        logger.info("ip = " + httpRequest.getRemoteAddr());
//
//        if (httpRequest != null && !httpRequest.getRemoteAddr().equals("")) {
//            order.setSpbill_create_ip(httpRequest.getRemoteAddr());
//            signParams.put("spbill_create_ip", order.getSpbill_create_ip());
//        }
//        order.setTime_start(WxPayUtil.getCurrentTimestamp());
//        signParams.put("time_start", order.getTime_start());
//        // if (poi.getTimeExpire() != null && !poi.getTimeExpire().equals("")) {
//        // order.setTime_expire(poi.getTimeExpire());
//        // signParams.put("time_expire", order.getTime_expire());
//        // }交易结束时间留空
//        // if (poi.getGoodsTag() != null && !poi.getGoodsTag().equals("")) {
//        // order.setGoods_tag(poi.getGoodsTag());
//        // signParams.put("goods_tag", order.getGoods_tag());
//        // }商品标记留空
//        order.setNotify_url(payThirdInfo.getNotifyUrl());
//        signParams.put("notify_url", order.getNotify_url());
//        if(request.getSource()!=null){
//        	String source = request.getSource().toUpperCase();
//        	if(source.equals("NATIVE"))order.setTrade_type("NATIVE");
//        	else if(source.equals("JSAPI")){
//        		order.setTrade_type("JSAPI");
//        		order.setOpenid(request.getUser_id());
//        		signParams.put("openid", order.getOpenid());
//        	}
//        	else order.setTrade_type("APP");
//        }
//        else order.setTrade_type("APP");
//        signParams.put("trade_type", order.getTrade_type());
//
//        if(order.getTrade_type().equals("NATIVE")){
//        	order.setProduct_id(request.getOrder_id());
//        	signParams.put("product_id", order.getProduct_id());
//        }
//        // if (poi.getProductId() != null && !poi.getProductId().equals("")) {
//        // order.setProduct_id(poi.getProductId());
//        // signParams.put("product_id", order.getProduct_id());
//        // }
//
//        // if (poi.getChannel() != null &&
//        // poi.getChannel().startsWith(PLATFORM_PREFIX_WX)) {
//        // if (poi.getUserId() != null && !poi.getUserId().equals("")) {
//        // order.setOpenid(poi.getUserId());
//        // signParams.put("openid", order.getOpenid());
//        // }
//        // }
//
//        String sign = WxPayUtil.genWxPaySign(signParams, payThirdInfo.getAppKey(), "utf-8");
//        order.setSign(sign);
//
//    }
//
//    private void savePayWxPayOrder(PaySdkApplyBizRequest bizRequest, WxUnifiedOrderRequest wxOrderReq,
//                                   WxUnifiedOrderResp wxOrderResp, TPayWxPayOrder wxPayOrder) {
//        wxPayOrder.setChannel(bizRequest.getChannel_code());
//        wxPayOrder.setDeviceInfo(wxOrderReq.getDevice_info());
//        wxPayOrder.setNonceStr(wxOrderReq.getNonce_str());
//        wxPayOrder.setBody(wxOrderReq.getBody());
//        wxPayOrder.setDetail(wxOrderReq.getDetail());
//        if (wxOrderReq.getAttach() != null && wxOrderReq.getAttach().length() >= 128) {
//            wxOrderReq.setAttach((wxOrderReq.getAttach().substring(0, 128)));
//        }
//        wxPayOrder.setAttach(wxOrderReq.getAttach());
//        wxPayOrder.setOutTradeNo(wxOrderReq.getOut_trade_no());
//        wxPayOrder.setFeeType(wxOrderReq.getFee_type());
//        wxPayOrder.setTotalFee(wxOrderReq.getTotal_fee());
//        wxPayOrder.setSpBillCreateIp(wxOrderReq.getSpbill_create_ip());
//        wxPayOrder.setTimeStart(wxOrderReq.getTime_start());
//        wxPayOrder.setTradeType(wxOrderReq.getTrade_type());
//        wxPayOrder.setResultCode(wxOrderResp.getResult_code());
//        wxPayOrder.setErrCode(wxOrderResp.getReturn_code());
//        wxPayOrder.setErrCodeDes(wxOrderResp.getErr_code_des());
//        wxPayOrder.setPrepayId(wxOrderResp.getPrepay_id());
//        wxPayOrder.setCodeUrl(wxOrderResp.getCode_url());
//        wxPayOrder.setCreateTime(new Date());
//        payDbservice.saveWxPayOrder(wxPayOrder);
//    }
//
//    private OrderQueryResp internalPost(OrderQueryReq req, String orderSource) {
//
//        OrderQueryResp resp = new OrderQueryResp();
//        String qryUrl = null;
//		TPayDeliPartner dpi = payDbservice.findDeliPartnerByProductSource(orderSource);
//		if(dpi!=null){
//			qryUrl = dpi.getOrderqueryUrl();
//		}
//        String url = qryUrl;
//        if(url==null)url=env.getProperty("yctapi_czj_url");
//
//        if (url == null || url.trim().equals("")) {
//            logger.error("internalPost url is null or empty");
//            resp.setResult_code(PayCenterError.ERR_CODE_INNER_DELIURL);
//            resp.setErr_msg(PayCenterError.ERR_MSG_INNER_DELIURL);
//            return resp;
//        }
//        logger.error("defaultSignKeyStore = " + defaultSignKeyStore);
//        String paramSignKey = "";
//        try{
//        	paramSignKey = defaultSignKeyStore.getSignKey(req.getChannel_code(), SignType.valueOf(req.getSign_type().toUpperCase()));
//        }catch(Exception e){
//        	logger.error("sign generation error");
//            resp.setResult_code(PayCenterError.ERR_CODE_INNER_GEN_SIGN);
//            resp.setErr_msg(PayCenterError.ERR_MSG_INNER_GEN_SIGN);
//            return resp;
//        }
//        String sign = YctApiMD5.encryptObjectMD5(req, paramSignKey);
//        if (sign.equals("")) {
//            logger.error("sign generation error");
//            resp.setResult_code(PayCenterError.ERR_CODE_INNER_GEN_SIGN);
//            resp.setErr_msg(PayCenterError.ERR_MSG_INNER_GEN_SIGN);
//            return resp;
//        }
//        req.setSign(sign);
//
//        try {
//            ObjectMapper objectMapper = JacksonObjectMapper.getDefault();
//            String jsonReq = "";
//            jsonReq = objectMapper.writeValueAsString(req);
//            logger.info("sending orderqry request: " + jsonReq);
//            //String jsonResp = HttpRequestUtil.httpRequest(url, "POST", jsonReq);
//            String jsonResp = HttpRequestUtil.postRequeset(url, jsonReq);
//            logger.info("received orderqry resp: " + jsonResp);
//            if (jsonResp == null || jsonResp.equals("")) {
//                logger.error("post to good_delivery error: no return");
//                resp.setResult_code(PayCenterError.ERR_CODE_NET_INNER_POST);
//                resp.setErr_msg(PayCenterError.ERR_MSG_NET_INNER_POST);
//                return resp;
//            }
//            resp = objectMapper.readValue(jsonResp, OrderQueryResp.class);
//        } catch (Exception ex) {
//            logger.error("exception: " + ex.getMessage());
//            resp.setResult_code(PayCenterError.ERR_CODE_INNER_TRANSFER);
//            resp.setErr_msg(PayCenterError.ERR_MSG_INNER_TRANSFER);
//            return resp;
//        }
//        return resp;
//
//    }
//
//    private OrderQueryReq formPaySdkApplyBizReq(PaySdkApplyBizRequest paySdkApplyBizRequest) {
//        OrderQueryReq req = new OrderQueryReq();
//        req.setVersion(paySdkApplyBizRequest.getVersion());
//        req.setChannel_code(paySdkApplyBizRequest.getChannel_code());
//        req.setUser_id(paySdkApplyBizRequest.getUser_id());
//        req.setTimestamp(paySdkApplyBizRequest.getTimestamp());
//        req.setCharset(paySdkApplyBizRequest.getCharset());
//        req.setSign_type("MD5");
//        req.setService("yct.product.czj.order.query");
//        List<String> orderList = new ArrayList<String>();
//        orderList.add(paySdkApplyBizRequest.getOrder_id());
//        req.setYct_order(orderList);
//        req.setPage(1);
//        req.setPagesize(1);
//        req.setCondition("");
//        req.setYct_extend("");
//        req.setBiz_extend("");
//        return req;
//    }
//
//}