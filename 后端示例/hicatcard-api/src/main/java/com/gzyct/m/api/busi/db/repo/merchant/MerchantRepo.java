package com.gzyct.m.api.busi.db.repo.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchant;

public interface MerchantRepo extends JpaRepository<TMerchant, Long>, JpaSpecificationExecutor<TMerchant> {

	public TMerchant findByMerchantIdAndEnable(Long merchantId, Boolean enable);
}
