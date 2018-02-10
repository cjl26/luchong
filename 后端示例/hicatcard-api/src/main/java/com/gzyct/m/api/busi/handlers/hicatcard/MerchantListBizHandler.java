package com.gzyct.m.api.busi.handlers.hicatcard;

import com.gzyct.m.api.busi.bean.hicatcard.MerchantListItem;
import com.gzyct.m.api.busi.bean.hicatcard.MerchantListReq;
import com.gzyct.m.api.busi.bean.hicatcard.MerchantListResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.service.hicatcard.merchant.MerchantService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 獲取商戶列表，跟住距離排序
 */
@Component
public class MerchantListBizHandler extends DefaultBizParamChecker<MerchantListReq, MerchantListResp> implements BizHandler<MerchantListReq, MerchantListResp> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MerchantService merchantService;

    @Override
    public MerchantListResp handle(MerchantListReq bizRequest) throws Exception {
        MerchantListResp bizResp = new MerchantListResp();
        // 参数检查
        ValidateRet vRet = checkParam(bizRequest, bizResp);
        if (!vRet.isValid()) {
            return bizResp;
        }

        try {
            List<MerchantListItem> merchantListItemList = merchantService.findMerchantListItemByPoint(bizRequest.getLatitude(), bizRequest.getLongitude(), bizRequest.getPage(), bizRequest.getPagesize());
            bizResp.setMerchant(merchantListItemList);
            bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
            bizResp.setError_message("");
            return bizResp;
        } catch (Exception ex) {
            logger.error("内部出错", ex);
            bizResp.setResult_code(BusiError.ERR_CODE_DB_CONF);
            bizResp.setError_message(BusiError.ERR_MSG_DB_CONF);
            return bizResp;
        }
    }

    @Override
    public ValidateRet checkParam(MerchantListReq req, MerchantListResp resp) {
        ValidateRet vRet = super.checkParam(req, resp);
        if (!vRet.isValid())
            return vRet;

        List<ValidateParam> paramList = new ArrayList<ValidateParam>();
        paramList.add(new ValidateParam("longitude", ValidateType.NOTBLANK, req.getLongitude()));
        paramList.add(new ValidateParam("latitude", ValidateType.NOTBLANK, req.getLatitude()));
        paramList.add(new ValidateParam("page", ValidateType.NOTBLANK, req.getPage()));
        paramList.add(new ValidateParam("pagesize", ValidateType.NOTBLANK, req.getPagesize()));
        
        vRet = ValidateUtil.validate(paramList);

        try {
            Double longitude = Double.valueOf(req.getLongitude());
            Double latitude = Double.valueOf(req.getLatitude());
        } catch (Exception e) {
            vRet = new ValidateRet(false, BusiError.ERR_MSG_PARAM_BAD_REQ);
        }

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
