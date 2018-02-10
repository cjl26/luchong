package com.gzyct.m.api.busi.db.repo.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.coupon.TCoupon;

public interface CouponRepo extends JpaRepository<TCoupon, Long>, JpaSpecificationExecutor<TCoupon> {

	TCoupon findByCouponIdAndEnable(Long coupondId, Boolean enable);
}
