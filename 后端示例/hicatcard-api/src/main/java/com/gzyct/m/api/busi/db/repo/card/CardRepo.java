package com.gzyct.m.api.busi.db.repo.card;

import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CardRepo extends JpaRepository<TCard, Long>, JpaSpecificationExecutor<TCard> {

	TCard findByCardId(Long cardId);

    TCard findByCardIdAndEnable(Long cardId, Boolean enable);

	List<TCard> findByEnableOrderByOrdernumAsc(Boolean enable, Pageable pageable);

	List<TCard> findByTypeAndEnableOrderByOrdernumAsc(String type, Boolean enable, Pageable pageable);

	List<TCard> findByTypeAndCardNameContainingAndEnableAndStatusOrderByOrdernumAsc(String type, String cardName, Boolean enable, String status,
			Pageable pageable);

	List<TCard> findByCardNameContainingAndEnableAndStatusOrderByOrdernumAsc(String cardName, Boolean enable, String status, Pageable pageable);

}
