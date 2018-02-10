package com.gzyct.m.api.busi.handlers.hicatcard.card;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardOrderReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardOrderResp;
import com.gzyct.m.api.busi.bean.wx.pay.WxUnifiedOrderRequest;
import com.gzyct.m.api.busi.bean.wx.pay.WxUnifiedOrderResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.coupon.TCoupon;
import com.gzyct.m.api.busi.db.entity.hicatcard.pay.TPayOrder;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCoupon;
import com.gzyct.m.api.busi.db.service.hicatcard.card.CardService;
import com.gzyct.m.api.busi.db.service.hicatcard.card.CardServiceService;
import com.gzyct.m.api.busi.db.service.hicatcard.coupon.CouponService;
import com.gzyct.m.api.busi.db.service.hicatcard.pay.PayOrderService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCardService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCouponService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.HttpRequestUtil;
import com.gzyct.m.api.busi.util.OrderStatus;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.busi.util.WxPayUtil;
import com.gzyct.m.api.busi.util.XmlUtil;
import com.gzyct.m.api.busi.util.IdGenerator.IdGenerator;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;
import com.project.m.api.common.intf.InterfaceContext;

@Component
public class CardOrderBizHandler extends DefaultBizParamChecker<CardOrderReq, CardOrderResp>
		implements BizHandler<CardOrderReq, CardOrderResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	CardService cardService;

	@Autowired
	CardServiceService cardServiceService;

	@Autowired
	UserCardService userCardService;

	@Autowired
	PayOrderService payOrderService;

	@Autowired
	UserCouponService userCouponService;

	@Autowired
	CouponService couponService;

	@Value("${weixin.pay.appid}")
	String wxPayAppId;

	@Value("${weixin.pay.mchid}")
	String wxPayMchId;

	@Value("${weixin.pay.signkey}")
	String wxPaySignKey;

	@Value("${weixin.pay.notify.url}")
	String wxPayNotifyUrl;

	@Value("${weixin.pay.unifiedorder.url}")
	String wxPayUnifiedOrderUrl;

	@Override
	public CardOrderResp handle(CardOrderReq bizRequest) throws Exception {
		CardOrderResp bizResp = new CardOrderResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {
			Long cardId = Long.parseLong(bizRequest.getCard_id());
			Long userCouponId = null;
			TUserCoupon userCoupon = null;
			TCoupon coupon = null;

			// 先查卡片是否存在
			TCard card = cardService.findByCardIdAndEnable(cardId, true);
			if (card == null) {
				bizResp.setResult_code(BusiError.ERR_CODE_CARD_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_CARD_NOT_EXIST);
				return bizResp;
			}

			// 用户是否存在
			List<TUser> userList = userService.findByOpenidAndEnable(bizRequest.getOpenid(), true);
			if (userList == null || userList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_NONE);
				bizResp.setError_message(BusiError.ERR_MSG_USER_NONE);
				return bizResp;
			}

			// 判断手机号码
			if (StringUtils.isBlank(userList.get(0).getPhone())) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_PHONE_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_USER_PHONE_NOT_EXIST);
				return bizResp;
			}

			// 优惠券使用
			if (bizRequest.getUser_coupon_id() != null && bizRequest.getUser_coupon_id().length() > 0) {
				userCouponId = Long.parseLong(bizRequest.getUser_coupon_id());
				userCoupon = userCouponService.findByUserCouponIdAndEnable(userCouponId, true);
				if (userCoupon.getStatus().equals(TUserCoupon.STATUS_USED)) {
					bizResp.setResult_code(BusiError.ERR_CODE_CARD_NOT_EXIST);
					bizResp.setError_message(BusiError.ERR_MSG_CARD_NOT_EXIST);
					return bizResp;
				}

				coupon = couponService.findByCouponId(userCoupon.getCouponId(), true);
				if (coupon.getFee().intValue() != bizRequest.getCoupon_fee().intValue()) {
					bizResp.setResult_code(BusiError.ERR_CODE_COUPONE_FEE_ERROR);
					bizResp.setError_message(BusiError.ERR_MSG_COUPONE_FEE_ERROR);
					return bizResp;
				}
			}

			// 微信落單 - PaySdkApplyBizHandler
			String orderId = IdGenerator.generateOrderId(IdGenerator.OrderID_Prefix_PAY, "00", "0000");
			WxUnifiedOrderRequest wxOrderReq = buildWxUnifiedOrderRequest(bizRequest, card, orderId);
			String wxOrderXmlReq = XmlUtil.toXML(wxOrderReq, WxUnifiedOrderRequest.class);
			logger.info("=======wx bill create request: " + wxOrderXmlReq);
			String url = wxPayUnifiedOrderUrl;
			logger.info("url = " + url);
			String wxOrderXmlResp = HttpRequestUtil.httpsRequest(url, "POST", wxOrderXmlReq);
			logger.info("=======wx bill create response: " + wxOrderXmlResp);
			WxUnifiedOrderResp wxOrderResp = (WxUnifiedOrderResp) XmlUtil.fromXml(wxOrderXmlResp,
					WxUnifiedOrderResp.class);

			// // 测试
			// TPayOrder payOrder = buildPayOrder(orderId, userList.get(0), cardId,
			// userCouponId, bizRequest);
			// payOrderService.savePayOrder(payOrder);
			// continueNotify(payOrder, bizRequest, card);
			// bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			// bizResp.setError_message("");
			// return bizResp;

			if (wxOrderResp.getReturn_code() != null && wxOrderResp.getReturn_code().equalsIgnoreCase("SUCCESS")) {
				// 保存pay order
				TPayOrder payOrder = buildPayOrder(orderId, userList.get(0), cardId, userCouponId, bizRequest,
						wxOrderResp.getPrepay_id());
				payOrderService.savePayOrder(payOrder);
				String timeStamp = "" + System.currentTimeMillis();
				timeStamp = timeStamp.substring(0, 10);
				SortedMap<String, String> payMap = new TreeMap<String, String>();
				payMap.put("appId", wxOrderResp.getAppid());
				payMap.put("timeStamp", timeStamp);
				payMap.put("nonceStr", wxOrderResp.getNonce_str() + wxOrderResp.getNonce_str());
				payMap.put("package", "prepay_id=" + wxOrderResp.getPrepay_id());
				payMap.put("signType", "MD5");

				String paySign = WxPayUtil.genWxPaySign(payMap, wxPaySignKey, "utf-8");
				// continueNotify(payOrder, bizRequest, cardList.get(0));
				String payInfoString = JSON.toJSONString(payMap);
				logger.info("payInfoString = " + payInfoString);
				logger.info("paySign = " + paySign);
				bizResp.setTimeStamp(timeStamp);
				bizResp.setNonceStr(wxOrderResp.getNonce_str() + wxOrderResp.getNonce_str());
				bizResp.setPkg("prepay_id=" + wxOrderResp.getPrepay_id());
				bizResp.setSignType("MD5");
				bizResp.setPaySign(paySign);
				bizResp.setAppId(wxPayAppId);

				bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
				// bizResp.setPay_info(payInfoString);
				bizResp.setError_message("");

				return bizResp;
			} else {
				bizResp.setResult_code(BusiError.ERR_CODE_CARD_UNIFIED_ORDER_ERROR);
				bizResp.setError_message(BusiError.ERR_MSG_CARD_UNIFIED_ORDER_ERROR + wxOrderResp.getReturn_msg());
				return bizResp;
			}

		} catch (Exception e) {
			logger.error("CardOrderBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}

	}

	@Override
	public ValidateRet checkParam(CardOrderReq req, CardOrderResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("card_id", ValidateType.NOTBLANK, req.getCard_id()));
		paramList.add(new ValidateParam("total_fee", ValidateType.NOTBLANK, req.getTotal_fee()));
		paramList.add(new ValidateParam("pay_fee", ValidateType.NOTBLANK, req.getPay_fee()));
		vRet = ValidateUtil.validate(paramList);
		if (vRet == null) {
			vRet = new ValidateRet(true, "");
		}
		if (!vRet.isValid()) {
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}

		// 金额检查
		if (req.getCoupon_fee() == null) {
			req.setCoupon_fee(0);
		}

		if (req.getTotal_fee() != req.getPay_fee() + req.getCoupon_fee()) {
			resp.setResult_code(BusiError.ERR_CODE_FEE_CALCULATION_ERROR);
			resp.setError_message(BusiError.ERR_MSG_FEE_CALCULATION_ERROR);
			vRet.setValid(false);
		}
		return vRet;
	}

	/**
	 * 组统一下单接口
	 * 
	 * @param cardOrderReq
	 * @param card
	 * @return
	 */
	private WxUnifiedOrderRequest buildWxUnifiedOrderRequest(CardOrderReq cardOrderReq, TCard card, String orderId)
			throws Exception {
		WxUnifiedOrderRequest wxOrderReq = new WxUnifiedOrderRequest();
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		wxOrderReq.setAppid(wxPayAppId);
		signParams.put("appid", wxOrderReq.getAppid());

		wxOrderReq.setMch_id(wxPayMchId);
		signParams.put("mch_id", wxOrderReq.getMch_id());

		// wxOrderReq.setDevice_info("");
		// signParams.put("device_info", "");

		wxOrderReq.setNonce_str(WxPayUtil.getNonceStr());
		signParams.put("nonce_str", wxOrderReq.getNonce_str());

		wxOrderReq.setSign_type("MD5");
		signParams.put("sign_type", wxOrderReq.getSign_type());

		wxOrderReq.setBody("购买" + card.getCardName());
		signParams.put("body", wxOrderReq.getBody());

		wxOrderReq.setDetail("");
		signParams.put("detail", wxOrderReq.getDetail());

		wxOrderReq.setAttach("");
		signParams.put("attach", wxOrderReq.getAttach());

		wxOrderReq.setOut_trade_no(orderId);
		signParams.put("out_trade_no", wxOrderReq.getOut_trade_no());

		wxOrderReq.setFee_type("CNY");
		signParams.put("fee_type", wxOrderReq.getFee_type());

		// wxOrderReq.setTotal_fee(Long.valueOf(card.getFee()));
		wxOrderReq.setTotal_fee(Long.valueOf(cardOrderReq.getPay_fee()));
		signParams.put("total_fee", "" + wxOrderReq.getTotal_fee());

		HttpServletRequest httpRequest = InterfaceContext.getContext().getHttpServletRequest();
		wxOrderReq.setSpbill_create_ip(httpRequest.getRemoteAddr());
		//TODO 调试用
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString(); // 获取本机ip
		wxOrderReq.setSpbill_create_ip(ip);
		signParams.put("spbill_create_ip", wxOrderReq.getSpbill_create_ip()); // 

		// wxOrderReq.setTime_start(WxPayUtil.getCurrentTimestamp());
		// signParams.put("time_start", wxOrderReq.getTime_start());

		// wxOrderReq.setTime_expire("");
		// signParams.put("time_expire", wxOrderReq.getTime_expire());

		// wxOrderReq.setGoods_tag("");
		// signParams.put("goods_tag", wxOrderReq.getGoods_tag());

		wxOrderReq.setNotify_url(wxPayNotifyUrl);
		signParams.put("notify_url", wxOrderReq.getNotify_url());

		wxOrderReq.setTrade_type("JSAPI");
		signParams.put("trade_type", wxOrderReq.getTrade_type());

		// wxOrderReq.setLimit_pay("");
		// signParams.put("limit_pay", wxOrderReq.getLimit_pay());

		wxOrderReq.setOpenid(cardOrderReq.getOpenid());
		signParams.put("openid", wxOrderReq.getOpenid());

		String sign = WxPayUtil.genWxPaySign(signParams, wxPaySignKey, "utf-8");
		wxOrderReq.setSign(sign);
		// wxOrderReq.setSign("D534DDA00BF0C78D52E92439C1FA2044");
		return wxOrderReq;
	}

	/**
	 * 组payorder
	 * 
	 * @param orderId
	 * @param user
	 * @param cardId
	 * @param userCouponId
	 * @param bizRequest
	 * @return
	 */
	private TPayOrder buildPayOrder(String orderId, TUser user, Long cardId, Long userCouponId, CardOrderReq bizRequest,
			String prepayId) {
		TPayOrder payOrder = new TPayOrder();
		payOrder.setPayOrderNumber(orderId);
		payOrder.setUserId(user.getUserId());
		payOrder.setCardId(cardId);
		payOrder.setWxOrderNumber("");
		payOrder.setUserCardId(null);
		payOrder.setUserCouponId(userCouponId);
		payOrder.setTotalFee(bizRequest.getTotal_fee());
		payOrder.setCouponFee(bizRequest.getCoupon_fee());
		payOrder.setPayFee(bizRequest.getPay_fee());
		payOrder.setRemark("");
		payOrder.setPrepayId(prepayId);
		payOrder.setStatus(OrderStatus.STATUS_1_NOT_PAY);
		payOrder.setCreateTime(TimeUtil.getCurrTime());
		payOrder.setUpdateTime(TimeUtil.getCurrTime());
		return payOrder;
	}

	/**
	 * 假装有通知
	 */
	private void continueNotify(TPayOrder payOrder, CardOrderReq cardOrderReq, TCard card) {

		// 把tpayorder 设置为已经发货

		TUserCard userCard = new TUserCard();
		userCard.setUserId(payOrder.getUserId());
		userCard.setCardId(card.getCardId());
		userCard.setUserCardNumber(IdGenerator.generateOrderId(IdGenerator.ORDERID_PREFIX_USER_CARD, "00", "0000"));
		userCard.setSource(TUserCard.SOURCE_PURCHASE);
		userCard.setSourceUserId(null);
		userCard.setStatus(TUserCard.STATUS_NOT_ACTIVE);
		userCard.setCreateTime(TimeUtil.getCurrTime());
		userCard.setUpdateTime(TimeUtil.getCurrTime());
		userCard.setEnable(true);

		userCardService.saveUserCardByNotify(userCard, payOrder);
	}
}
