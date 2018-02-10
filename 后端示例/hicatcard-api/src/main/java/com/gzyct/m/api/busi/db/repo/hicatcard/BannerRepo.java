package com.gzyct.m.api.busi.db.repo.hicatcard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.TBanner;

public interface BannerRepo extends JpaRepository<TBanner, Long>, JpaSpecificationExecutor<TBanner> {

	List<TBanner> findByStatusAndEnableAndPlaceOrderByOrderNumDesc(String status, Boolean enable, String place);
}
