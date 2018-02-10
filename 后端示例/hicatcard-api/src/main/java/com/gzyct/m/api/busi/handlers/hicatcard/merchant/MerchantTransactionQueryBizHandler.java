package com.gzyct.m.api.busi.handlers.hicatcard.merchant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionItem;
import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionQueryReq;
import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionQueryResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchant;
import com.gzyct.m.api.busi.db.service.hicatcard.merchant.MerchantService;
import com.gzyct.m.api.busi.db.service.hicatcard.transaction.TransactionService;
import com.gzyct.m.api.busi.handlers.MerchantBizParamChecker;
import com.gzyct.m.api.busi.util.CommonConvertor;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

@Component
public class MerchantTransactionQueryBizHandler
		extends MerchantBizParamChecker<MerchantTransactionQueryReq, MerchantTransactionQueryResp>
		implements BizHandler<MerchantTransactionQueryReq, MerchantTransactionQueryResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	TransactionService transactionService;

	@Autowired
	MerchantService merchantService;

	@Override
	public MerchantTransactionQueryResp handle(MerchantTransactionQueryReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		MerchantTransactionQueryResp bizResp = new MerchantTransactionQueryResp();
		// 参数检查
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if (!vRet.isValid()) {
			return bizResp;
		}

		try {
			Long merchantId = Long.parseLong(bizRequest.getMerchant_id());
			TMerchant merchant = merchantService.findByMerchantId(merchantId, true);
			if (merchant == null) {
				bizResp.setResult_code(BusiError.ERR_CODE_MERCHANT_NOT_EXIST);
				bizResp.setError_message(BusiError.ERR_MSG_MERCHANT_NOT_EXIST);
				return bizResp;
			}

			List<MerchantTransactionItem> itemList = transactionService
					.findTransactionItemMerchantId(Long.parseLong(bizRequest.getMerchant_id()));
			bizResp.setTransactions(itemList);
			bizResp.setBegin_date("");
			bizResp.setEnd_date("");
			bizResp.setTotalAmount(0);

			for (MerchantTransactionItem x : itemList) {
				bizResp.setTotalAmount(bizResp.getTotalAmount() + x.getMerchant_fee() * x.getService_time());
				if (bizResp.getBegin_date() == null || bizResp.getBegin_date().length() == 0) {
					bizResp.setBegin_date(x.getBegin_date());
				}
				if (bizResp.getEnd_date() == null || bizResp.getEnd_date().length() == 0) {
					bizResp.setEnd_date(x.getEnd_date());
				}

				if (x.getBegin_date().compareTo(bizResp.getBegin_date()) < 0) {
					bizResp.setBegin_date(x.getBegin_date());
				}
				if (x.getEnd_date().compareTo(bizResp.getEnd_date()) > 0) {
					bizResp.setBegin_date(x.getBegin_date());
				}

			}

			// 根据 source/ status 来取数据
			// Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
			// Sort sort = new Sort(order);
			// Pageable pageable = new PageRequest(bizRequest.getPage() - 1,
			// bizRequest.getPagesize(), sort);

			// String beginDate =
			// CommonConvertor.convertStringAvoidNull(bizRequest.getBegin_date());
			// String endDate =
			// CommonConvertor.convertStringAvoidNull(bizRequest.getEnd_date());
			// if (!StringUtils.isEmpty(beginDate) && !StringUtils.isEmpty(endDate)) {
			// try {
			// beginDate = TimeUtil.transferDateFormat(beginDate, "yyyy-MM-dd", "yyyyMMdd")
			// + "000000";
			// endDate = TimeUtil.transferDateFormat(endDate, "yyyy-MM-dd", "yyyyMMdd") +
			// "235959";
			// } catch (Exception e) {
			// beginDate = "";
			// endDate = "";
			// }
			// }

			// TODO - 查找数据
			// List<MerchantTransactionItem> merchantTransactionItemList =
			// transactionService
			// .findTransactionItemMerchantId(merchantId, beginDate, endDate);
			//
			// Integer amount = 0;
			// for (MerchantTransactionItem merchantTransactionItem :
			// merchantTransactionItemList) {
			// amount = amount + merchantTransactionItem.getTotal();
			// }

			// bizResp.setTotalAmount("" + amount);
			// 取数据

			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			return bizResp;

		} catch (Exception e) {
			logger.error("内部出错", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_CONF);
			bizResp.setError_message(BusiError.ERR_MSG_DB_CONF);
			return bizResp;
		}
	}

	@Override
	public ValidateRet checkParam(MerchantTransactionQueryReq req, MerchantTransactionQueryResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if (!vRet.isValid())
			return vRet;

		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		// paramList.add(new ValidateParam("page", ValidateType.NOTBLANK,
		// req.getPage()));
		// paramList.add(new ValidateParam("pagesize", ValidateType.NOTBLANK,
		// req.getPagesize()));

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
