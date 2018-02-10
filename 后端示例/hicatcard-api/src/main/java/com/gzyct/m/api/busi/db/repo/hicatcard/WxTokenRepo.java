package com.gzyct.m.api.busi.db.repo.hicatcard;

import com.gzyct.m.api.busi.db.entity.hicatcard.TWxToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WxTokenRepo extends JpaRepository<TWxToken, Long>, JpaSpecificationExecutor<TWxToken> {

    List<TWxToken> findByEnable(Boolean enable);
}
