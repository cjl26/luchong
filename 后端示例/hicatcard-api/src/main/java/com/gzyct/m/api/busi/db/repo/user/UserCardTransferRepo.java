package com.gzyct.m.api.busi.db.repo.user;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCardTransfer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserCardTransferRepo
		extends JpaRepository<TUserCardTransfer, Long>, JpaSpecificationExecutor<TUserCardTransfer> {

	List<TUserCardTransfer> findBySourceUserCardIdAndSourceUserIdAndEnable(Long sourceUserCardId, Long sourceUserId,
			Boolean enable);
}
