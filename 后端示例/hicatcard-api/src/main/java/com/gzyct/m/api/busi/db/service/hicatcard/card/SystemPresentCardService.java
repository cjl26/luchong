package com.gzyct.m.api.busi.db.service.hicatcard.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TSystemPresentCard;
import com.gzyct.m.api.busi.db.repo.card.CardRepo;
import com.gzyct.m.api.busi.db.repo.card.SystemPresentCardRepo;

@Service
@Transactional
public class SystemPresentCardService {

	@Autowired
	SystemPresentCardRepo systemPresentCardRepo;

	@Autowired
	CardRepo cardRepo;

	@Transactional(readOnly = true)
	public List<TSystemPresentCard> findByCardNumberAndStatus(String cardNumber, String status) {
		return systemPresentCardRepo.findByCardNumberAndStatus(cardNumber, status);
	}

	@Transactional(readOnly = true)
	public TCard findByCardNumber(String cardNumber) {
		List<TSystemPresentCard> systemPresentCardList = systemPresentCardRepo.findByCardNumber(cardNumber);
		if (systemPresentCardList != null && systemPresentCardList.size() > 0) {
			return cardRepo.findByCardId(systemPresentCardList.get(0).getCardId());
		} else {
			return null;
		}
	}
}
