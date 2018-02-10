package com.gzyct.m.api.busi.db.service.hicatcard.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzyct.m.api.busi.db.entity.hicatcard.coupon.TCoupon;
import com.gzyct.m.api.busi.db.repo.coupon.CouponRepo;

@Service
@Transactional
public class CouponService {

	@Autowired
	CouponRepo couponRepo;

	@Transactional(readOnly = true)
	public List<TCoupon> findAll() {
		return couponRepo.findAll();
	}

	@Transactional(readOnly = true)
	public TCoupon findByCouponId(Long couponId, Boolean enable) {
		return couponRepo.findByCouponIdAndEnable(couponId, enable);
	}

}
