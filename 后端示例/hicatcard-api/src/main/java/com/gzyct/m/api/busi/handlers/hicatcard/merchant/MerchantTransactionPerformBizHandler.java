package com.gzyct.m.api.busi.handlers.hicatcard.merchant;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionPerformReq;
import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionPerformResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchant;
import com.gzyct.m.api.busi.db.entity.hicatcard.transaction.TTransaction;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCarLicence;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import com.gzyct.m.api.busi.db.service.hicatcard.WxTokenService;
import com.gzyct.m.api.busi.db.service.hicatcard.merchant.MerchantService;
import com.gzyct.m.api.busi.db.service.hicatcard.pay.PayOrderService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCarLicenceService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserCardService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.MerchantBizParamChecker;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.busi.util.IdGenerator.IdGenerator;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

//https://mp.weixin.qq.com/debug/wxadoc/dev/api/notice.html#模版消息管理

@Component
public class MerchantTransactionPerformBizHandler
		extends MerchantBizParamChecker<MerchantTransactionPerformReq, MerchantTransactionPerformResp>
		implements BizHandler<MerchantTransactionPerformReq, MerchantTransactionPerformResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	WxTokenService wxTokenService;

	@Autowired
	PayOrderService payOrderService;

	@Autowired
	UserService userService;

	@Autowired
	MerchantService merchantService;

	@Autowired
	UserCardService userCardService;

	@Autowired
	UserCarLicenceService userCarLicenceService;

	@Value("${weixin.miniapp.appid}")
	String wxMinAppId;

	@Value("${weixin.miniapp.secret}")
	String wxMinAppSecret;

	@Value("${weixin.message.send.template.transaction}")
	private String messageTemplate;

	@Override
	public MerchantTransactionPerformResp handle(MerchantTransactionPerformReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		MerchantTransactionPerformResp bizResp = new MerchantTransactionPerformResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {
			Long merchant_id = Long.parseLong(bizRequest.getMerchant_id());
			// 检查merchant
			TMerchant merchant = merchantService.findByMerchantId(merchant_id, true);
			if (merchant == null) {
				bizResp.setResult_code(BusiError.ERR_CODE_MERCHANT_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_MERCHANT_NOT_EXIST);
				return bizResp;
			}

			// 找user_card
			List<TUserCard> userCardList = userCardService
					.findByUserCardNumberAndEnable(bizRequest.getUser_card_number(), true);
			if (userCardList == null || userCardList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_NOT_EXIST);
				return bizResp;
			}

			TUserCard userCard = userCardList.get(0);
			if (!userCard.getStatus().equals(TUserCard.STATUS_ACTIVE)) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_CARD_NOT_ACTIVATED);
				bizResp.setError_message(BusiError.ERR_MSG_USER_CARD_NOT_ACTIVATED);
				return bizResp;
			}

			// 找用户车牌号
			List<TUserCarLicence> userCarLicenceList = userCarLicenceService.findByUserIdAndEnable(userCard.getUserId(),
					true);
			// if (userCarLicenceList == null || userCarLicenceList.size() == 0) {
			// bizResp.setResult_code(BusiError.ERR_CODE_CAR_LICENCE_NOT_EXIST);
			// bizResp.setError_message(BusiError.ERR_MSG_CAR_LICENCE_NOT_EXIST);
			// return bizResp;
			// }

			TTransaction transaction = new TTransaction();
			transaction.setTransactionOrder(
					IdGenerator.generateOrderId(IdGenerator.ORDERID_PREFIX_TRANSACTION, "00", "0000"));
			transaction.setMerchantId(merchant.getMerchantId());
			transaction.setUserId(userCard.getUserId());
			transaction.setUserCardId(userCard.getUserCardId());
			transaction.setStatus(TTransaction.STATUS_NOT_SETTLE);
			if (userCarLicenceList == null || userCarLicenceList.size() == 0) {
				transaction.setCarLicence("");
			} else {
				transaction.setCarLicence(userCarLicenceList.get(0).getCarLicenceNumber());
			}
			transaction.setCreateTime(TimeUtil.getCurrTime());
			transaction.setUpdateTime(TimeUtil.getCurrTime());
			transaction.setEnable(true);

			// logger.info("services = " + bizRequest.getServices());
			logger.info("services = " + JSON.toJSONString(bizRequest.getServices()));

			// Map<String, Object> resultMap =
			userCardService.saveCardTransaction(userCard, transaction, bizRequest.getServices());

			// //發送推送
			// try {
			// //先獲取token
			// List<TWxToken> wxTokenList = wxTokenService.findByEnable(true);
			// TWxToken wxToken = null;
			// boolean whetherToGetToken = false;
			// //判斷是否過期
			// if (wxTokenList == null || wxTokenList.size() == 0) {
			// whetherToGetToken = true;
			// } else {
			// wxToken = wxTokenList.get(0);
			// if (Long.valueOf(TimeUtil.getCurrTime()).longValue() >
			// Long.valueOf(wxToken.getExpiresIn()).longValue() +
			// Long.valueOf(wxToken.getUpdateTime()).longValue()) {
			// whetherToGetToken = true;
			// }
			// }
			//
			// //過期取微信拿token 并保存
			// if (whetherToGetToken) {
			// String getTokenUrl =
			// "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			// + wxMinAppId + "&secret=" + wxMinAppSecret;
			// String jsonResp = HttpRequestUtil.getRequest(getTokenUrl);
			// logger.info("jsonResp = " + jsonResp);
			// WxTokenResp wxTokenResp = JSON.parseObject(jsonResp, WxTokenResp.class);
			//
			// if (jsonResp == null || jsonResp.isEmpty()) {
			// throw new Exception();
			// }
			//
			// if (wxToken == null) {
			// wxToken = new TWxToken();
			// wxToken.setCreateTime(TimeUtil.getCurrTime());
			// wxToken.setEnable(true);
			// }
			// wxToken.setUpdateTime(TimeUtil.getCurrTime());
			// wxToken.setToken(wxTokenResp.getAccess_token());
			// wxToken.setExpiresIn(wxTokenResp.getExpires_in());
			// wxTokenService.saveWxToken(wxToken);
			// }
			//
			//
			// TUser user = userService.findByUserId(userCard.getUserId());
			// if (user == null) {
			// throw new Exception();
			// }
			//
			// List<TPayOrder> payOrderList =
			// payOrderService.findByUserCardId(userCard.getUserCardId());
			// if (payOrderList == null && payOrderList.size() == 0) {
			// throw new Exception();
			// }
			//
			// TPayOrder payOrder = payOrderList.get(0);
			// if (StringUtils.isEmpty(payOrder.getPrepayId())) {
			// throw new Exception();
			// }
			//
			// //https://mp.weixin.qq.com/debug/wxadoc/dev/api/notice.html#%E5%8F%91%E9%80%81%E6%A8%A1%E6%9D%BF%E6%B6%88%E6%81%AF
			// Map<String, Object> sendMap = new HashMap<String, Object>();
			// sendMap.put("touser", user.getOpenid());
			// sendMap.put("template_id", messageTemplate);
			// sendMap.put("form_id", payOrder.getPrepayId());
			// Map<String, Object> dataMap = new HashMap<String, Object>();
			//
			// //订单编号
			// Map<String, Object> keyword1Map = new HashMap<String, Object>();
			// keyword1Map.put("value", transaction.getTransactionOrder());
			// dataMap.put("keyword1", keyword1Map);
			//
			// //服务门店
			// Map<String, Object> keyword2Map = new HashMap<String, Object>();
			// keyword2Map.put("value", merchant.getName());
			// dataMap.put("keyword2", keyword2Map);
			//
			//
			// //服务项目
			// Map<String, Object> keyword3Map = new HashMap<String, Object>();
			// keyword3Map.put("value", resultMap.get("serviceContent"));
			// dataMap.put("keyword3", keyword3Map);
			//
			// //备注
			// Map<String, Object> keyword4Map = new HashMap<String, Object>();
			// keyword4Map.put("value", "");
			// dataMap.put("keyword4", keyword4Map);
			//
			// //洗车时间
			// Map<String, Object> keyword5Map = new HashMap<String, Object>();
			// keyword5Map.put("value", TimeUtil
			// .getFormatTime(TimeUtil.strToDate(transaction.getCreateTime(),
			// "yyyyMMddHHmmss"), "yyyy/MM/dd HH:mm:ss"));
			// dataMap.put("keyword5", keyword5Map);
			//
			// sendMap.put("data", dataMap);
			//
			// String sendContent = JSON.toJSONString(sendMap);
			// logger.info("sendContent = " + sendContent);
			// String pushUrl =
			// "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="
			// + wxToken.getToken();
			// String response = HttpRequestUtil.httpsRequest(pushUrl, "POST", sendContent);
			// logger.info("notify finish response = " + response);
			//
			// //WxaTemplateApi wxaTemplateApi = Duang.duang(WxaTemplateApi.class);
			// //ApiResult apiResult = wxaTemplateApi.send(sendContent);
			// } catch (Exception e) {
			// logger.error("wxaTemplateApi.send", e);
			// }

			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			return bizResp;

		} catch (Exception e) {
			logger.error("MerchantCardQueryBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}
	}

	@Override
	public ValidateRet checkParam(MerchantTransactionPerformReq req, MerchantTransactionPerformResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("merchant_id", ValidateType.NOTBLANK, req.getMerchant_id()));
		paramList.add(new ValidateParam("user_card_number", ValidateType.NOTBLANK, req.getUser_card_number()));

		vRet = ValidateUtil.validate(paramList);
		if (vRet == null) {
			vRet = new ValidateRet(true, "");
		}

		if (!vRet.isValid()) {
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}

		return vRet;
	}
}
