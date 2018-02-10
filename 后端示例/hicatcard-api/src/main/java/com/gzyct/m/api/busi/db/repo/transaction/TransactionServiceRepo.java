package com.gzyct.m.api.busi.db.repo.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.transaction.TTransactionService;

public interface TransactionServiceRepo
		extends JpaRepository<TTransactionService, Long>, JpaSpecificationExecutor<TTransactionService> {

	List<TTransactionService> findByTransactionIdAndEnable(Long transactionId, Boolean enable);
}
