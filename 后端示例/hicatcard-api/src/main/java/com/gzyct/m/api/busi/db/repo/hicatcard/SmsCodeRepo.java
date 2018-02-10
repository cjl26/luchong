package com.gzyct.m.api.busi.db.repo.hicatcard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.TSmsCode;

public interface SmsCodeRepo extends JpaRepository<TSmsCode, Long>, JpaSpecificationExecutor<TSmsCode> {

	List<TSmsCode> findByTypeAndPhone(String type, String phone);

	List<TSmsCode> findByTypeAndPhoneAndEnable(String type, String phone, Boolean enable);

	// List<TSmsCode> findById(Long id);
	// List<TSmsCode> findByIdentifierAndCodeTypeAndStatus(String identifier, String
	// codeType, Integer status);
	// List<TSmsCode> findByIdentifierAndCodeTypeAndCodeAndStatus(String identifier,
	// String codeType,String code, Integer status);
	//
	// //修改验证码状态
	// @Modifying
	// @Query("update #{#entityName} a set a.status=:status where a.identifier =
	// :identifier and a.codeType=:codeType and a.code=:code")
	// int setSmsCode(@Param("identifier") String identifier, @Param("codeType")
	// String codeType,@Param("code") String code,@Param("status") Integer status);
	//

}
