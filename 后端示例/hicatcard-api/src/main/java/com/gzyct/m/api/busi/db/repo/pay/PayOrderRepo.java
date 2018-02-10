package com.gzyct.m.api.busi.db.repo.pay;

import com.gzyct.m.api.busi.db.entity.hicatcard.pay.TPayOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PayOrderRepo extends JpaRepository<TPayOrder, Long>, JpaSpecificationExecutor<TPayOrder> {

    List<TPayOrder> findByPayOrderNumber(String payOrderNumber);

    List<TPayOrder> findByUserCardId(Long userCardId);
}
