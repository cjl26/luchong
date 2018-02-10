package com.gzyct.m.api.busi.handlers.hicatcard.merchant;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzyct.m.api.busi.bean.merchant.MerchantListQueryItem;
import com.gzyct.m.api.busi.bean.merchant.MerchantListQueryReq;
import com.gzyct.m.api.busi.bean.merchant.MerchantListQueryResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchant;
import com.gzyct.m.api.busi.db.service.hicatcard.merchant.MerchantService;
import com.gzyct.m.api.busi.handlers.MerchantBizParamChecker;
import com.project.m.api.common.biz.BizHandler;

@Component
public class MerchantListQueryBizHandler extends MerchantBizParamChecker<MerchantListQueryReq, MerchantListQueryResp>
		implements BizHandler<MerchantListQueryReq, MerchantListQueryResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MerchantService merchantService;

	@Override
	public MerchantListQueryResp handle(MerchantListQueryReq bizRequest) throws Exception {
		// TODO Auto-generated method stub
		MerchantListQueryResp bizResp = new MerchantListQueryResp();
		try {
			List<TMerchant> merchantList = merchantService.findAllMerchant();
			List<MerchantListQueryItem> merchantListQueryItemList = new ArrayList<MerchantListQueryItem>();
			for (TMerchant merchant : merchantList) {
				MerchantListQueryItem merchantListQueryItem = new MerchantListQueryItem();
				merchantListQueryItem.setMerchantId(merchant.getMerchantId());
				merchantListQueryItem.setName(merchant.getName());
				//merchantListQueryItem.setPhone(merchant.getPhone());
				merchantListQueryItem.setAddress(merchant.getAddress());
				merchantListQueryItemList.add(merchantListQueryItem);
			}

			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
			bizResp.setError_message("");
			bizResp.setMerchant_list(merchantListQueryItemList);
			return bizResp;
		} catch (Exception e) {
			logger.error("MerchantListQueryBizHandler", e);
			bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
			bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
			return bizResp;
		}
	}

}
