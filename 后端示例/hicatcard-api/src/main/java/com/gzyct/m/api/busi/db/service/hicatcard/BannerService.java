package com.gzyct.m.api.busi.db.service.hicatcard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gzyct.m.api.busi.db.entity.hicatcard.TBanner;
import com.gzyct.m.api.busi.db.repo.hicatcard.BannerRepo;

@Component
@Transactional
public class BannerService {

	@Autowired
	BannerRepo bannerRepo;

	@Transactional(readOnly = true)
	public List<TBanner> findByStatusAndEnableAndPlace(String status, Boolean enable, String place) {
		return bannerRepo.findByStatusAndEnableAndPlaceOrderByOrderNumDesc(status, enable, place);
	}

}
