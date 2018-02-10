package com.gzyct.m.api.busi.db.service.hicatcard.card;

import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCardService;
import com.gzyct.m.api.busi.db.repo.card.CardServiceRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardServiceService {

	@Autowired
	CardServiceRepo cardServiceRepo;

	@Transactional(readOnly = true)
	public List<TCardService> findByCardIdAndEnable(Long cardId, Boolean enable) {
		return cardServiceRepo.findByCardIdAndEnable(cardId, enable);
	}

	/*
	 * 
	 * public TCardService save(TCardService cardService) { if(cardService != null)
	 * { return cardServiceRepo.save(cardService); } return null; }
	 */
}
