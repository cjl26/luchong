package com.gzyct.m.api.busi.config;

import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.ext.AdvertisementQueryReq;
import com.gzyct.m.api.busi.bean.ext.AdvertisementQueryResp;
import com.gzyct.m.api.busi.bean.ext.AuthCodeToOpenIdReq;
import com.gzyct.m.api.busi.bean.ext.AuthCodeToOpenIdResp;
import com.gzyct.m.api.busi.bean.ext.DecryptDataReq;
import com.gzyct.m.api.busi.bean.ext.DecryptDataResp;
import com.gzyct.m.api.busi.bean.hicatcard.MerchantListReq;
import com.gzyct.m.api.busi.bean.hicatcard.MerchantListResp;
import com.gzyct.m.api.busi.bean.hicatcard.SmsCodeReq;
import com.gzyct.m.api.busi.bean.hicatcard.SmsCodeResp;
import com.gzyct.m.api.busi.bean.hicatcard.TransactionListReq;
import com.gzyct.m.api.busi.bean.hicatcard.TransactionListResp;
import com.gzyct.m.api.busi.bean.hicatcard.UserInfoReq;
import com.gzyct.m.api.busi.bean.hicatcard.UserInfoResp;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardActivateReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardActivateResp;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardCollectReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardCollectResp;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardDetailQueryReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardDetailQueryResp;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardListReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardListResp;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardOrderReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardOrderResp;
import com.gzyct.m.api.busi.bean.hicatcard.card.SystemCardCollectReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.SystemCardCollectResp;
import com.gzyct.m.api.busi.bean.hicatcard.carlicence.CarLicenceQueryReq;
import com.gzyct.m.api.busi.bean.hicatcard.carlicence.CarLicenceQueryResp;
import com.gzyct.m.api.busi.bean.hicatcard.carlicence.CarLicenceUpdateReq;
import com.gzyct.m.api.busi.bean.hicatcard.carlicence.CarLicenceUpdateResp;
import com.gzyct.m.api.busi.bean.hicatcard.coupon.UserCouponListReq;
import com.gzyct.m.api.busi.bean.hicatcard.coupon.UserCouponListResp;
import com.gzyct.m.api.busi.bean.hicatcard.user.UserCardListReq;
import com.gzyct.m.api.busi.bean.hicatcard.user.UserCardListResp;
import com.gzyct.m.api.busi.bean.hicatcard.weixin.QRCodeGenerateReq;
import com.gzyct.m.api.busi.bean.hicatcard.weixin.QRCodeGenerateResp;
import com.gzyct.m.api.busi.bean.merchant.MerchantCardQueryReq;
import com.gzyct.m.api.busi.bean.merchant.MerchantCardQueryResp;
import com.gzyct.m.api.busi.bean.merchant.MerchantListQueryReq;
import com.gzyct.m.api.busi.bean.merchant.MerchantListQueryResp;
import com.gzyct.m.api.busi.bean.merchant.MerchantSettleQueryReq;
import com.gzyct.m.api.busi.bean.merchant.MerchantSettleQueryResp;
import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionPerformReq;
import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionPerformResp;
import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionQueryReq;
import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionQueryResp;
import com.gzyct.m.api.busi.handlers.hicatcard.BannerQueryBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.MerchantListBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.QRCodeGenerateBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.SmsCodeHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.TransactionListBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.card.CardActivateBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.card.CardCollectBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.card.CardDetailQueryBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.card.CardListBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.card.CardOrderBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.card.SystemCardCollectBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.carlicence.CarLicenceQueryBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.carlicence.CarLicenceUpdateBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.merchant.MerchantCardQueryBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.merchant.MerchantListQueryBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.merchant.MerchantSettleQueryBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.merchant.MerchantTransactionPerformBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.merchant.MerchantTransactionQueryBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.user.UserCardListBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.user.UserCouponListBizHandler;
//import com.gzyct.m.api.busi.handlers.hicatcard.user.UserInfoBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.wx.AuthCodeToOpenIdBizHandler;
import com.gzyct.m.api.busi.handlers.hicatcard.wx.DecryptedDataBizHandler;
import com.project.m.api.common.biz.AbstractBizHandlerMapConfig;
import com.project.m.api.common.biz.BizHandlerConfigItem;
import com.project.m.api.common.biz.req.JsonBizRequestParser;
import com.project.m.api.common.util.SpringUtils;

/**
 * 羊城通宝支付系统接口
 */
@Component
public class BusiBizHandlerConfig extends AbstractBizHandlerMapConfig {

	@Override
	protected void init() {

		map.put("xiche.sms.code", new BizHandlerConfigItem<SmsCodeReq, SmsCodeResp>(
				new JsonBizRequestParser(SmsCodeReq.class), SpringUtils.getBean(SmsCodeHandler.class)));
		map.put("xiche.banner.query",
				new BizHandlerConfigItem<AdvertisementQueryReq, AdvertisementQueryResp>(
						new JsonBizRequestParser(AdvertisementQueryReq.class),
						SpringUtils.getBean(BannerQueryBizHandler.class)));
		map.put("xiche.wx.auth",
				new BizHandlerConfigItem<AuthCodeToOpenIdReq, AuthCodeToOpenIdResp>(
						new JsonBizRequestParser(AuthCodeToOpenIdReq.class),
						SpringUtils.getBean(AuthCodeToOpenIdBizHandler.class)));
		map.put("xiche.wx.decrypt", new BizHandlerConfigItem<DecryptDataReq, DecryptDataResp>(
				new JsonBizRequestParser(DecryptDataReq.class), SpringUtils.getBean(DecryptedDataBizHandler.class)));
		map.put("xiche.merchant.list", new BizHandlerConfigItem<MerchantListReq, MerchantListResp>(
				new JsonBizRequestParser(MerchantListReq.class), SpringUtils.getBean(MerchantListBizHandler.class)));
//		map.put("xiche.user.info", new BizHandlerConfigItem<UserInfoReq, UserInfoResp>(
//				new JsonBizRequestParser(UserInfoReq.class), SpringUtils.getBean(UserInfoBizHandler.class)));
		map.put("xiche.card.list", new BizHandlerConfigItem<CardListReq, CardListResp>(
				new JsonBizRequestParser(CardListReq.class), SpringUtils.getBean(CardListBizHandler.class)));
		map.put("xiche.card.order", new BizHandlerConfigItem<CardOrderReq, CardOrderResp>(
				new JsonBizRequestParser(CardOrderReq.class), SpringUtils.getBean(CardOrderBizHandler.class)));
		map.put("xiche.card.detail.query",
				new BizHandlerConfigItem<CardDetailQueryReq, CardDetailQueryResp>(
						new JsonBizRequestParser(CardDetailQueryReq.class),
						SpringUtils.getBean(CardDetailQueryBizHandler.class)));
		map.put("xiche.user.coupon.list",
				new BizHandlerConfigItem<UserCouponListReq, UserCouponListResp>(
						new JsonBizRequestParser(UserCouponListReq.class),
						SpringUtils.getBean(UserCouponListBizHandler.class)));
		map.put("xiche.card.activate", new BizHandlerConfigItem<CardActivateReq, CardActivateResp>(
				new JsonBizRequestParser(CardActivateReq.class), SpringUtils.getBean(CardActivateBizHandler.class)));
		map.put("xiche.user.card.list", new BizHandlerConfigItem<UserCardListReq, UserCardListResp>(
				new JsonBizRequestParser(UserCardListReq.class), SpringUtils.getBean(UserCardListBizHandler.class)));
		map.put("xiche.car.licence.query",
				new BizHandlerConfigItem<CarLicenceQueryReq, CarLicenceQueryResp>(
						new JsonBizRequestParser(CarLicenceQueryReq.class),
						SpringUtils.getBean(CarLicenceQueryBizHandler.class)));
		map.put("xiche.car.licence.update",
				new BizHandlerConfigItem<CarLicenceUpdateReq, CarLicenceUpdateResp>(
						new JsonBizRequestParser(CarLicenceUpdateReq.class),
						SpringUtils.getBean(CarLicenceUpdateBizHandler.class)));
		map.put("xiche.card.collect", new BizHandlerConfigItem<CardCollectReq, CardCollectResp>(
				new JsonBizRequestParser(CardCollectReq.class), SpringUtils.getBean(CardCollectBizHandler.class)));
		map.put("xiche.transaction.list",
				new BizHandlerConfigItem<TransactionListReq, TransactionListResp>(
						new JsonBizRequestParser(TransactionListReq.class),
						SpringUtils.getBean(TransactionListBizHandler.class)));
		map.put("xiche.system.card.collect",
				new BizHandlerConfigItem<SystemCardCollectReq, SystemCardCollectResp>(
						new JsonBizRequestParser(SystemCardCollectReq.class),
						SpringUtils.getBean(SystemCardCollectBizHandler.class)));
		map.put("xiche.system.qrcode.generate",
				new BizHandlerConfigItem<QRCodeGenerateReq, QRCodeGenerateResp>(
						new JsonBizRequestParser(QRCodeGenerateReq.class),
						SpringUtils.getBean(QRCodeGenerateBizHandler.class)));

		/*---------下面是商户api*/
		map.put("xiche.merchant.card.query",
				new BizHandlerConfigItem<MerchantCardQueryReq, MerchantCardQueryResp>(
						new JsonBizRequestParser(MerchantCardQueryReq.class),
						SpringUtils.getBean(MerchantCardQueryBizHandler.class)));
		map.put("xiche.merchant.transaction.perform",
				new BizHandlerConfigItem<MerchantTransactionPerformReq, MerchantTransactionPerformResp>(
						new JsonBizRequestParser(MerchantTransactionPerformReq.class),
						SpringUtils.getBean(MerchantTransactionPerformBizHandler.class)));
		map.put("xiche.merchant.list.query",
				new BizHandlerConfigItem<MerchantListQueryReq, MerchantListQueryResp>(
						new JsonBizRequestParser(MerchantListQueryReq.class),
						SpringUtils.getBean(MerchantListQueryBizHandler.class)));
		map.put("xiche.merchant.transaction.query",
				new BizHandlerConfigItem<MerchantTransactionQueryReq, MerchantTransactionQueryResp>(
						new JsonBizRequestParser(MerchantTransactionQueryReq.class),
						SpringUtils.getBean(MerchantTransactionQueryBizHandler.class)));
		map.put("xiche.merchant.settle.query",
				new BizHandlerConfigItem<MerchantSettleQueryReq, MerchantSettleQueryResp>(
						new JsonBizRequestParser(MerchantSettleQueryReq.class),
						SpringUtils.getBean(MerchantSettleQueryBizHandler.class)));
	}
}
