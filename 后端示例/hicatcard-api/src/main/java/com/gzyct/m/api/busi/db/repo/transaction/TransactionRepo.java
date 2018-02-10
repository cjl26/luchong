package com.gzyct.m.api.busi.db.repo.transaction;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.transaction.TTransaction;

public interface TransactionRepo extends JpaRepository<TTransaction, Long>, JpaSpecificationExecutor<TTransaction> {

	List<TTransaction> findByUserIdAndEnable(Long userId, Boolean enable, Pageable pageable);

	List<TTransaction> findByUserIdAndMerchantIdAndEnable(Long userId, Long merchantId, Boolean enable,
			Pageable pageable);

	List<TTransaction> findByMerchantIdAndEnable(Long merchantId, Boolean enable, Pageable pageable);
}
