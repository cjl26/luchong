package com.gzyct.m.api.busi.db.service.hicatcard.pay;

import com.gzyct.m.api.busi.db.entity.hicatcard.pay.TPayOrder;
import com.gzyct.m.api.busi.db.repo.pay.PayOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class PayOrderService {

	@Autowired
	PayOrderRepo payOrderRepo;

	@Transactional(readOnly = true)
	public List<TPayOrder> findByPayOrderNumber(String payOrderNumber){
		return payOrderRepo.findByPayOrderNumber(payOrderNumber);
	}

	@Transactional(readOnly = true)
	public List<TPayOrder> findByUserCardId(Long userCardId) {
		return payOrderRepo.findByUserCardId(userCardId);
	}


	public void savePayOrder(TPayOrder payOrder) {
		payOrderRepo.save(payOrder);
	}
}
