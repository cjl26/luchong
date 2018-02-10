package com.gzyct.m.api.busi.db.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCardService;

import java.util.List;

public interface UserCardServiceRepo
		extends JpaRepository<TUserCardService, Long>, JpaSpecificationExecutor<TUserCardService> {

	List<TUserCardService> findByUserCardIdAndEnable(Long userCardId, Boolean enable);

	TUserCardService findByUserCardServiceIdAndEnable(Long userCardServiceId, Boolean enable);
}
