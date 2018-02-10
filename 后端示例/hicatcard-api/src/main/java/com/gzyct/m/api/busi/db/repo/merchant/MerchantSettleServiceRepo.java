package com.gzyct.m.api.busi.db.repo.merchant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchantSettleService;


public interface MerchantSettleServiceRepo extends JpaRepository<TMerchantSettleService, Long>, JpaSpecificationExecutor<TMerchantSettleService> {
	
	@Query("select t from TMerchantSettleService t where t.merchantSettleId = ?1")
	public List<TMerchantSettleService> findByMerchantSettleId(Long merchantSettleId);

}
