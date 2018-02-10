package com.gzyct.m.api.busi.handlers.hicatcard.card;


import com.gzyct.m.api.busi.bean.hicatcard.card.CardItem;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardListReq;
import com.gzyct.m.api.busi.bean.hicatcard.card.CardListResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.service.hicatcard.card.CardService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;
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
public class CardListBizHandler extends DefaultBizParamChecker<CardListReq, CardListResp>
        implements BizHandler<CardListReq, CardListResp> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CardService cardService;

    @Override
    public CardListResp handle(CardListReq bizRequest) throws Exception {
        CardListResp bizResp = new CardListResp();
        // 参数检查
        ValidateRet vRet = checkParam(bizRequest, bizResp);
        if (!vRet.isValid()) {
            return bizResp;
        }

        try {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
            Sort sort = new Sort(order);
            Pageable pageable = new PageRequest(bizRequest.getPage() - 1, bizRequest.getPagesize(), sort);
            List<CardItem> cardItemList = cardService.findByTypeAndSearchText(bizRequest.getType(), bizRequest.getSearchText(), pageable);

            bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
            bizResp.setError_message("");
            bizResp.setCard(cardItemList);
            return bizResp;
        } catch (Exception e) {
            logger.error("CardListBizHandler", e);
            bizResp.setResult_code(BusiError.ERR_CODE_DB_SAVE);
            bizResp.setError_message(BusiError.ERR_MSG_DB_SAVE);
            return bizResp;
        }
    }

    @Override
    public ValidateRet checkParam(CardListReq req, CardListResp resp) {
        ValidateRet vRet = super.checkParam(req, resp);
        if (!vRet.isValid())
            return vRet;
        List<ValidateParam> paramList = new ArrayList<ValidateParam>();
        paramList.add(new ValidateParam("page", ValidateType.NOTBLANK, req.getPage()));
        paramList.add(new ValidateParam("pagesize", ValidateType.NOTBLANK, req.getPagesize()));
        paramList.add(new ValidateParam("page", ValidateType.INT, req.getPage()));
        paramList.add(new ValidateParam("pagesize", ValidateType.INT, req.getPagesize()));
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
