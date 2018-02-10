package com.gzyct.m.api.busi.handlers.hicatcard.merchant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.merchant.MerchantSettleQueryItem;
import com.gzyct.m.api.busi.bean.merchant.MerchantSettleQueryReq;
import com.gzyct.m.api.busi.bean.merchant.MerchantSettleQueryResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchant;
import com.gzyct.m.api.busi.db.service.hicatcard.merchant.MerchantService;
import com.gzyct.m.api.busi.db.service.hicatcard.merchant.MerchantSettleService;
import com.gzyct.m.api.busi.handlers.MerchantBizParamChecker;
import com.gzyct.m.api.busi.util.CommonConvertor;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

@Component
public class MerchantSettleQueryBizHandler
		extends MerchantBizParamChecker<MerchantSettleQueryReq, MerchantSettleQueryResp>
		implements BizHandler<MerchantSettleQueryReq, MerchantSettleQueryResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MerchantSettleService merchantSettleService;

	@Autowired
	MerchantService merchantService;

	@Override
	public MerchantSettleQueryResp handle(MerchantSettleQueryReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		MerchantSettleQueryResp bizResp = new MerchantSettleQueryResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {
			Long merchantId = null;
			TMerchant merchant = null;
			if (bizRequest.getMerchant_id() != null && !bizRequest.getMerchant_id().isEmpty()) {
				merchantId = Long.parseLong(bizRequest.getMerchant_id());
				merchant = merchantService.findByMerchantId(merchantId, true);
				if (merchant == null) {
					bizResp.setResult_code(BusiError.ERR_CODE_MERCHANT_NOT_EXIST);
					bizResp.setError_message(BusiError.ERR_MSG_MERCHANT_NOT_EXIST);
					return bizResp;
				}
			}

			String searchText = CommonConvertor.convertStringAvoidNull(bizRequest.getSearch_service_name());
			String beginDate = CommonConvertor.convertStringAvoidNull(bizRequest.getBegin_date());
			String endDate = CommonConvertor.convertStringAvoidNull(bizRequest.getEnd_date());
			if (!StringUtils.isEmpty(beginDate) && !StringUtils.isEmpty(endDate)) {
				try {
					beginDate = TimeUtil.transferDateFormat(beginDate, "yyyy-MM-dd", "yyyyMMdd") + "000000";
					endDate = TimeUtil.transferDateFormat(endDate, "yyyy-MM-dd", "yyyyMMdd") + "235959";
				} catch (Exception e) {
					beginDate = "";
					endDate = "";
				}
			}

			List<MerchantSettleQueryItem> merchantSettleQueryItemList = merchantSettleService.findByMerchantId(
					merchantId, beginDate, endDate, searchText, bizRequest.getPage() - 1, bizRequest.getPagesize());

			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			bizResp.setMerchantSettleQueryItemList(merchantSettleQueryItemList);
			return bizResp;
		} catch (Exception e) {
			logger.error("内部出错", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_CONF);
			bizResp.setError_message(BusiError.ERR_MSG_DB_CONF);
			return bizResp;
		}

	}

	@Override
	public ValidateRet checkParam(MerchantSettleQueryReq req, MerchantSettleQueryResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;

		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("page", ValidateType.NOTBLANK, req.getPage()));
		paramList.add(new ValidateParam("pagesize", ValidateType.NOTBLANK, req.getPagesize()));

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
