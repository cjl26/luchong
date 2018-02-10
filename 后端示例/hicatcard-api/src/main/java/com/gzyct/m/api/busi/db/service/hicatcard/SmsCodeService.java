package com.gzyct.m.api.busi.db.service.hicatcard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gzyct.m.api.busi.db.entity.hicatcard.TSmsCode;
import com.gzyct.m.api.busi.db.repo.hicatcard.SmsCodeRepo;

@Component
@Transactional
public class SmsCodeService {

	@Autowired
	SmsCodeRepo smsCodeRepo;

	public void saveSmsCode(TSmsCode smsCode) {
		smsCodeRepo.save(smsCode);
	}

	public void saveSmsCodeList(List<TSmsCode> smsCodeList) {
		smsCodeRepo.save(smsCodeList);
	}

	@Transactional(readOnly = true)
	public List<TSmsCode> findByTypeAndPhone(String type, String phone) {
		return smsCodeRepo.findByTypeAndPhone(type, phone);
	}
	
	@Transactional(readOnly = true)
	public List<TSmsCode> findByTypeAndPhoneAndEnable(String type, String phone, Boolean enable) {
		return smsCodeRepo.findByTypeAndPhoneAndEnable(type, phone, enable);
	}
	
}
