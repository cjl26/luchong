package com.gzyct.m.api.busi.db.repo.merchant;

import java.util.List;

import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchantService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface MerchantServiceRepo
		extends JpaRepository<TMerchantService, Long>, JpaSpecificationExecutor<TMerchantService> {

	@Query("select u from TMerchantService u where u.merchantId = ?1")
	public List<TMerchantService> findByMerchantId(Long merchantId);

	public List<TMerchantService> findByMerchantIdAndEnable(Long merchantId, Boolean enable);

	public List<TMerchantService> findByMerchantIdAndServiceIdAndEnable(Long merchantId, Long serviceId,
			Boolean enable);

}
