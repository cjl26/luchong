package com.gzyct.m.api.busi.bean.hicatcard;

import java.util.List;

import com.project.m.api.common.biz.resp.BizResp;

public class TransactionListResp extends BizResp{
    List<TransactionItem> transactions;

    public List<TransactionItem> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionItem> transactions) {
        this.transactions = transactions;
    }
}
