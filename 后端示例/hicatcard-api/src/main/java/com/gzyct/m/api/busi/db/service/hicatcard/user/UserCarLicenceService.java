package com.gzyct.m.api.busi.db.service.hicatcard.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCarLicence;
import com.gzyct.m.api.busi.db.repo.user.UserCarLicenceRepo;

@Component
@Transactional
public class UserCarLicenceService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserCarLicenceRepo userCarLicenceRepo;

	@Transactional(readOnly = true)
	public List<TUserCarLicence> findByUserIdAndEnable(Long userId, Boolean enable) {
		return userCarLicenceRepo.findByUserIdAndEnable(userId, enable);
	}

	public void saveUserCarLicence(TUserCarLicence userCarLicence) {
		userCarLicenceRepo.save(userCarLicence);
	}
}
