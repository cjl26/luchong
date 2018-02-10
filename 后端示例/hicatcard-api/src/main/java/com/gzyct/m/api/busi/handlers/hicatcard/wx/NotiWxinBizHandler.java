package com.gzyct.m.api.busi.handlers.hicatcard.wx;

import com.gzyct.m.api.busi.bean.wx.pay.WxNotifyBizReq;
import com.gzyct.m.api.busi.bean.wx.pay.WxNotifyBizResp;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.pay.TPayOrder;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import com.gzyct.m.api.busi.db.service.hicatcard.WxTokenService;
import com.gzyct.m.api.busi.db.service.hicatcard.card.CardService;
import com.gzyct.m.api.busi.db.service.hicatcard.coupon.CouponService;
import com.gzyct.m.api.busi.db.service.hicatcard.pay.PayOrderService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCardService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCouponService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.util.IdGenerator.IdGenerator;
import com.gzyct.m.api.busi.util.OrderStatus;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.busi.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

//https://mp.weixin.qq.com/debug/wxadoc/dev/api/notice.html#模版消息管理

@Component
public class NotiWxinBizHandler {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@Autowired
	PayOrderService payOrderService;

	@Autowired
	UserCardService userCardService;

	@Autowired
	CardService cardService;

	@Autowired
	CouponService couponService;

	@Autowired
	UserCouponService userCouponService;

	@Autowired
	WxTokenService wxTokenService;

	@Value("${weixin.miniapp.appid}")
	String wxMinAppId;

	@Value("${weixin.miniapp.secret}")
	String wxMinAppSecret;

	@Value("${weixin.message.send.template.pay}")
	private String messageTemplate;

	public String handle(WxNotifyBizReq notiWxinBizReq, String requestBody) throws Exception {
		logger.info("notify xml = " + requestBody);
		WxNotifyBizResp resp = new WxNotifyBizResp();
		if (!notiWxinBizReq.getReturn_code().equalsIgnoreCase("SUCCESS")) {
			// TODO微信通知返回不成功
			resp.setReturn_code("FAIL");
			String xmlReq = XmlUtil.toXML(resp, WxNotifyBizResp.class);
			return xmlReq;
		}

		try {
			List<TPayOrder> payOrderList = payOrderService.findByPayOrderNumber(notiWxinBizReq.getOut_trade_no());
			if (payOrderList == null || payOrderList.size() == 0) {
				resp.setReturn_code("FAIL");
				resp.setReturn_msg("pay order list null or empty");
				String xmlReq = XmlUtil.toXML(resp, WxNotifyBizResp.class);
				return xmlReq;
			}

			TPayOrder payOrder = payOrderList.get(0);

			// 已经支付
			if (payOrder.getStatus().equalsIgnoreCase(OrderStatus.STATUS_4_DELIVERED)) {
				resp.setReturn_code("SUCCESS");
				String xmlReq = XmlUtil.toXML(resp, WxNotifyBizResp.class);
				return xmlReq;
			}

			// 卡片信息
			TCard card = cardService.findByCardId(payOrder.getCardId());
			if (card == null) {
				resp.setReturn_code("FAIL");
				resp.setReturn_msg("card is not existed");
				String xmlReq = XmlUtil.toXML(resp, WxNotifyBizResp.class);
				return xmlReq;
			}

			TUserCard userCard = new TUserCard();
			userCard.setUserId(payOrder.getUserId());
			userCard.setCardId(payOrder.getCardId());
			userCard.setUserCardNumber(IdGenerator.generateOrderId(IdGenerator.ORDERID_PREFIX_USER_CARD, "00", "0000"));
			userCard.setSource(TUserCard.SOURCE_PURCHASE);
			userCard.setSourceUserId(null);
			userCard.setStatus(TUserCard.STATUS_NOT_ACTIVE);
            userCard.setEndTime("");
//			if (card.getEffectiveDay() != null) {
//				SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//				userCard.setEndTime(outFormat.format(TimeUtil.addDateNDay(new Date(), card.getEffectiveDay())));
//			} else {
//				SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//				userCard.setEndTime(outFormat.format(TimeUtil.addDateNDay(new Date(), 365)));
//			}
			userCard.setCreateTime(TimeUtil.getCurrTime());
			userCard.setUpdateTime(TimeUtil.getCurrTime());
			userCard.setEnable(true);

//			if (payOrder.getUserCouponId() != null) {
//				// 用户优惠券作废
//				TUserCoupon userCoupon = userCouponService.findByUserCouponIdAndEnable(payOrder.getUserCouponId(),
//						true);
//				if (userCoupon != null) {
//					userCoupon.setEnable(false);
//					userCoupon.setUpdateTime(TimeUtil.getCurrTime());
//					userCoupon.setUseTime(TimeUtil.getCurrTime());
//					userCouponService.saveUserCoupon(userCoupon);
//				}
//			}
//
//			// 送coupon
//			if (card.getCouponId() != null) {
//				TCoupon coupon = couponService.findByCouponId(card.getCouponId(), true);
//				if (coupon != null) {
//					TUserCoupon userCoupon = new TUserCoupon();
//					userCoupon.setUserId(userCard.getUserId());
//					userCoupon.setCouponId(coupon.getCouponId());
//					userCoupon.setUserCouponNumber(
//							IdGenerator.generateOrderId(IdGenerator.ORDERID_PREFIX_COUPON, "0000", "00"));
//					userCoupon.setStatus("1");
//					userCoupon.setCreateTime(TimeUtil.getCurrTime());
//					userCoupon.setUpdateTime(TimeUtil.getCurrTime());
//					userCoupon.setEnable(true);
//					userCouponService.saveUserCoupon(userCoupon);
//				}
//			}

			userCardService.saveUserCardByNotify(userCard, payOrder);

//			//TODO 发送推送
//			if (!StringUtils.isEmpty(payOrder.getPrepayId())) {
//				try {
//
//					//先獲取token
//					List<TWxToken> wxTokenList = wxTokenService.findByEnable(true);
//					TWxToken wxToken = null;
//					boolean whetherToGetToken = false;
//					//判斷是否過期
//					if (wxTokenList == null || wxTokenList.size() == 0) {
//						whetherToGetToken = true;
//					} else {
//						wxToken = wxTokenList.get(0);
//						if (Long.valueOf(TimeUtil.getCurrTime()).longValue() > Long.valueOf(wxToken.getExpiresIn()).longValue() + Long.valueOf(wxToken.getUpdateTime()).longValue()) {
//							whetherToGetToken = true;
//						}
//					}
//
//					//過期取微信拿token 并保存
//					if (whetherToGetToken) {
//						String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxMinAppId + "&secret=" + wxMinAppSecret;
//						String jsonResp = HttpRequestUtil.getRequest(getTokenUrl);
//						logger.info("jsonResp = " + jsonResp);
//						WxTokenResp wxTokenResp = JSON.parseObject(jsonResp, WxTokenResp.class);
//
//						if (jsonResp == null || jsonResp.isEmpty()) {
//							throw new Exception();
//						}
//
//						if (wxToken == null) {
//							wxToken = new TWxToken();
//							wxToken.setCreateTime(TimeUtil.getCurrTime());
//							wxToken.setEnable(true);
//						}
//						wxToken.setUpdateTime(TimeUtil.getCurrTime());
//						wxToken.setToken(wxTokenResp.getAccess_token());
//						wxToken.setExpiresIn(wxTokenResp.getExpires_in());
//						wxTokenService.saveWxToken(wxToken);
//					}
//
//					TUser user = userService.findByUserId(userCard.getUserId());
//					if (user == null) {
//						throw new Exception();
//					}
//
//					Map<String, Object> sendMap = new HashMap<String, Object>();
//					sendMap.put("touser", user.getOpenid());
//					sendMap.put("template_id", messageTemplate);
//					sendMap.put("form_id", payOrder.getPrepayId());
//					Map<String, Object> dataMap = new HashMap<String, Object>();
//
//					//购买时间
//					Map<String, Object> keyword1Map = new HashMap<String, Object>();
//					keyword1Map.put("value", TimeUtil
//							.getFormatTime(TimeUtil.strToDate(payOrder.getUpdateTime(), "yyyyMMddHHmmss"), "yyyy/MM/dd HH:mm:ss"));
//					dataMap.put("keyword1", keyword1Map);
//					//物品名称
//					Map<String, Object> keyword2Map = new HashMap<String, Object>();
//					keyword2Map.put("value", userCard.getUserCardNumber());
//					dataMap.put("keyword2", keyword2Map);
//					//交易金额
//					Map<String, Object> keyword3Map = new HashMap<String, Object>();
//					keyword3Map.put("value", "" + payOrder.getPayFee());
//					dataMap.put("keyword3", keyword3Map);
//					//备注
//					Map<String, Object> keyword4Map = new HashMap<String, Object>();
//					keyword4Map.put("value", "");
//					dataMap.put("keyword4", keyword4Map);
//					sendMap.put("data", dataMap);
//
//					String sendContent = JSON.toJSONString(sendMap);
//					logger.info("sendContent = " + sendContent);
//					String pushUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + wxToken.getToken();
//					String response = HttpRequestUtil.httpsRequest(pushUrl, "POST", sendContent);
//					logger.info("notify finish response = " + response);
//
//					//WxaTemplateApi wxaTemplateApi = Duang.duang(WxaTemplateApi.class);
//					//ApiResult apiResult = wxaTemplateApi.send(sendContent);
//				} catch (Exception e) {
//					logger.info("notify error");
//					e.printStackTrace();
//				}
//			}

			resp.setReturn_code("SUCCESS");
			String xmlReq = XmlUtil.toXML(resp, WxNotifyBizResp.class);
			return xmlReq;
		} catch (Exception e) {
			resp.setReturn_code("FAIL");
			resp.setReturn_msg("inner error");
			String xmlReq = XmlUtil.toXML(resp, WxNotifyBizResp.class);
			return xmlReq;
		}

	}

}
