package com.gzyct.m.api.busi.db.service.hicatcard.user;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.repo.hicatcard.SmsCodeRepo;
import com.gzyct.m.api.busi.db.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserService {

	@Autowired
	SmsCodeRepo smsCodeRepo;

	@Autowired
	UserRepo userRepo;

	@Transactional(readOnly = true)
	public TUser findByUserId(Long userId) {
		return userRepo.findByUserId(userId);
	}

	@Transactional(readOnly = true)
	public List<TUser> findByOpenidAndEnable(String openid, Boolean enable) {
		return userRepo.findByOpenidAndEnable(openid, enable);
	}

	public void saveUser(TUser user) {
		userRepo.save(user);
	}

	public void saveUserList(List<TUser> userList) {
		userRepo.save(userList);
	}
}
