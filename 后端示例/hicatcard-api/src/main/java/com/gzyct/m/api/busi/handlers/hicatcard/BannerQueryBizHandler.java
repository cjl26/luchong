package com.gzyct.m.api.busi.handlers.hicatcard;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.ext.AdvertisementQueryReq;
import com.gzyct.m.api.busi.bean.ext.AdvertisementQueryResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.TBanner;
import com.gzyct.m.api.busi.db.service.hicatcard.BannerService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

/**
 * 滚动广告栏信息获取
 *
 */
@Component
public class BannerQueryBizHandler extends DefaultBizParamChecker<AdvertisementQueryReq, AdvertisementQueryResp>
		implements BizHandler<AdvertisementQueryReq, AdvertisementQueryResp> {

	protected final org.slf4j.Logger logger = LoggerFactory.getLogger(BannerQueryBizHandler.class);

	@Autowired
	BannerService bannerService;


	@Value("${image.upload.url}")
	private String imageUploadUrl;

	@Override
	public AdvertisementQueryResp handle(AdvertisementQueryReq req) throws Exception {
		AdvertisementQueryResp bizResp = new AdvertisementQueryResp();
		ValidateRet vRet = checkParam(req, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {

			List<TBanner> bannerList = bannerService.findByStatusAndEnableAndPlace(TBanner.STATUS_IN_USE, true,
					"" + req.getPlace());
			for(TBanner x:bannerList){
				if (!x.getPictureUrl().startsWith(imageUploadUrl)) {
					x.setPictureUrl(imageUploadUrl + x.getPictureUrl());
				}
			}
			bizResp.setAds(bannerList);
			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			return bizResp;

		} catch (Exception e) {
			logger.error("内部出错", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_CONF);
			bizResp.setError_message(BusiError.ERR_MSG_DB_CONF);
			return bizResp;
		}

		// if( req.getPlace() == 1 || req.getPlace() == 0){//主页banner
		// Advertisement advertisement = new Advertisement();
		// advertisement.setPic_url("http://hicatcitycardimage.6so2o.com/wxappimage/banner01.png");
		// List<Advertisement> list = new ArrayList<>();
		// list.add(advertisement);
		// bizResp.setAds(list);
		// }
		// if( req.getPlace() == 2){//核销banner
		// Advertisement advertisement = new Advertisement();
		// advertisement.setPic_url("http://hicatcitycardimage.6so2o.com/wxappimage/qr_banner.jpg");
		// List<Advertisement> list = new ArrayList<>();
		// list.add(advertisement);
		// bizResp.setAds(list);
		// }
		// bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		// return bizResp;
	}

	@Override
	public ValidateRet checkParam(AdvertisementQueryReq req, AdvertisementQueryResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;

		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		vRet = ValidateUtil.validate(paramList);

		if (vRet == null)
			vRet = new ValidateRet(true, "");
		if (!vRet.isValid()) {
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}
		return vRet;
	}
}
