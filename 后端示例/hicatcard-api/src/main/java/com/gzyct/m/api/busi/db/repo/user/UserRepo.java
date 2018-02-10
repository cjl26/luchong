package com.gzyct.m.api.busi.db.repo.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;

public interface UserRepo extends JpaRepository<TUser, Long>, JpaSpecificationExecutor<TUser> {

	List<TUser> findByOpenidAndEnable(String openid, Boolean enable);

	TUser findByUserId(Long userId);
}
