package com.gzyct.m.api.busi.db.repo.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchantSettle;


public interface MerchantSettleRepo extends JpaRepository<TMerchantSettle, Long>, JpaSpecificationExecutor<TMerchantSettle> {
	
	@Query("select max(t.settleEndTime) from TMerchantSettle t where t.merchantId = ?1")
	public String getMaxEndTimeByMerchantId(Long merchantId);
}
