package com.gzyct.m.api.busi.db.repo.user;

import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCardRepo extends JpaRepository<TUserCard, Long>, JpaSpecificationExecutor<TUserCard> {

	TUserCard findByUserCardId(Long userCardId);

	TUserCard findByUserCardIdAndEnable(Long userCardId, Boolean enable);

	List<TUserCard> findByUserCardNumberAndEnable(String userCardNumber, Boolean enable);

	List<TUserCard> findByUserIdAndEnable(Long userId, Boolean enable, Pageable pageable);

	List<TUserCard> findByUserIdAndSourceAndEnable(Long userId, String source, Boolean enable, Pageable pageable);

	@Query("select u from TUserCard u where u.userId = ?1 and (u.status = ?2 or u.status = ?3) and u.enable = ?4 order by status asc, endTime desc")
	List<TUserCard> findByUserIdAndStatusAndEnable(Long userId, String statusFirst, String statusSecond, Boolean enable,
			Pageable pageable);

	List<TUserCard> findByUserIdAndStatusAndEnable(Long userId, String status, Boolean enable, Pageable pageable);

	List<TUserCard> findByUserIdAndStatusAndEnableAndEndTimeGreaterThan(Long userId, String status, Boolean enable,
			String endTime, Pageable pageable);

	List<TUserCard> findByUserIdAndSourceAndStatusAndEnable(Long userId, String source, String status, Boolean enable,
			Pageable pageable);
}