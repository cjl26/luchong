package com.gzyct.m.api.busi.db.repo.card;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gzyct.m.api.busi.db.entity.hicatcard.card.TSystemPresentCard;

public interface SystemPresentCardRepo
		extends JpaRepository<TSystemPresentCard, Long>, JpaSpecificationExecutor<TSystemPresentCard> {

	List<TSystemPresentCard> findByCardNumberAndEnable(String cardNumber, Boolean enable);

	List<TSystemPresentCard> findByCardNumberAndStatus(String cardNumber, String status);

	List<TSystemPresentCard> findByCardNumber(String cardNumber);
}
