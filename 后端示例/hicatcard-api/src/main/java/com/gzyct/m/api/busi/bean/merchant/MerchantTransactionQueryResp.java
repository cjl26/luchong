package com.gzyct.m.api.busi.bean.merchant;

import java.util.List;

import com.project.m.api.common.biz.resp.BizResp;

public class MerchantTransactionQueryResp extends BizResp {

	private List<MerchantTransactionItem> transactions;
	private int totalAmount;
	private String begin_date;
	private String end_date;

	public List<MerchantTransactionItem> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<MerchantTransactionItem> transactions) {
		this.transactions = transactions;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	// List<TransactionItem> transactions;
	//
	// public List<TransactionItem> getTransactions() {
	// return transactions;
	// }
	//
	// public void setTransactions(List<TransactionItem> transactions) {
	// this.transactions = transactions;
	// }
}
