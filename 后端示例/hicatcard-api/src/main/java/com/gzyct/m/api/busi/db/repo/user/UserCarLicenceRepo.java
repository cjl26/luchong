package com.gzyct.m.api.busi.db.repo.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCarLicence;

public interface UserCarLicenceRepo
		extends JpaRepository<TUserCarLicence, Long>, JpaSpecificationExecutor<TUserCarLicence> {

	List<TUserCarLicence> findByUserIdAndEnable(Long userId, Boolean enable);
}
