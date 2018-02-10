package com.gzyct.m.api.busi.db.repo.card;

import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCardService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CardServiceRepo extends JpaRepository<TCardService, Long>, JpaSpecificationExecutor<TCardService> {

	public List<TCardService> findByCardId(Long cardId);

	public List<TCardService> findByCardIdAndEnable(Long cardId, Boolean enable);

	public List<TCardService> findByCardIdAndServiceIdAndEnable(Long cardId, Long serviceId, Boolean enable);

}
