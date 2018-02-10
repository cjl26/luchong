package com.gzyct.m.api.busi.db.repo.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCoupon;

public interface UserCouponRepo extends JpaRepository<TUserCoupon, Long>, JpaSpecificationExecutor<TUserCoupon> {

	TUserCoupon findByUserCouponIdAndEnable(Long userCouponId, Boolean enable);

	List<TUserCoupon> findByUserIdAndEnable(Long userId, Boolean enable);

	List<TUserCoupon> findByUserIdAndEnableAndStatus(Long userId, Boolean enable, String status);

}
