package com.gzyct.m.api.busi.handlers.hicatcard;

import com.gzyct.m.api.busi.bean.hicatcard.TransactionItem;
import com.gzyct.m.api.busi.bean.hicatcard.TransactionListReq;
import com.gzyct.m.api.busi.bean.hicatcard.TransactionListResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchant;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.service.hicatcard.merchant.MerchantService;
import com.gzyct.m.api.busi.db.service.hicatcard.transaction.TransactionService;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.CommonConvertor;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionListBizHandler extends DefaultBizParamChecker<TransactionListReq, TransactionListResp>
		implements BizHandler<TransactionListReq, TransactionListResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	TransactionService transactionService;

	@Autowired
	UserService userService;

	@Autowired
	MerchantService merchantService;

	@Override
	public TransactionListResp handle(TransactionListReq bizRequest) throws Exception {
		// TODO Auto-generated method stub

		TransactionListResp bizResp = new TransactionListResp();
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

			// 判斷用戶是否存在
			List<TUser> userList = userService.findByOpenidAndEnable(bizRequest.getOpenid(), true);
			if (userList == null || userList.size() == 0) {
				bizResp.setResult_code(BusiError.ERR_CODE_USER_NONE);
				bizResp.setError_message(BusiError.ERR_MSG_USER_NONE);
				return bizResp;
			}
			TUser user = userList.get(0);

			String searchText = CommonConvertor.convertStringAvoidNull(bizRequest.getSearch_text());
			String beginDate = CommonConvertor.convertStringAvoidNull(bizRequest.getBegin_date());
			String endDate = CommonConvertor.convertStringAvoidNull(bizRequest.getEnd_date());
			String userCardId = CommonConvertor.convertStringAvoidNull(bizRequest.getUser_card_id());
			if (!StringUtils.isEmpty(beginDate) && !StringUtils.isEmpty(endDate)) {
				try {
					beginDate = TimeUtil.transferDateFormat(beginDate, "yyyy-MM-dd", "yyyyMMdd") + "000000";
					endDate = TimeUtil.transferDateFormat(endDate, "yyyy-MM-dd", "yyyyMMdd") + "235959";
				} catch (Exception e) {
					beginDate = "";
					endDate = "";
				}
			}

			// 根据 source/ status 来取数据
			Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
			Sort sort = new Sort(order);
			Pageable pageable = new PageRequest(bizRequest.getPage() - 1, bizRequest.getPagesize(), sort);

			// 取数据
			List<TransactionItem> transactionItemList = transactionService.findTransactionItemByUserIdAndMerchantId(
					user.getUserId(), merchantId, searchText, beginDate, endDate, userCardId, pageable.getPageNumber(),
					pageable.getPageSize(), pageable);

			for (TransactionItem transaction : transactionItemList) {
				transaction.setCreate_time(TimeUtil.transferDateFormat(transaction.getCreate_time(),
						TimeUtil.DATE_PATTERN_NOSEPARTOR, "yyyy/MM/dd HH:mm"));
				transaction.setPhone(user.getPhone());
			}

			bizResp.setTransactions(transactionItemList);
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
	public ValidateRet checkParam(TransactionListReq req, TransactionListResp resp) {
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
