package cloudPayAdmin.admin.service.hicatcard.card;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCardService;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.card.CardServiceRepo;

@Service
@Transactional
public class CardServiceService {
	
	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	CardServiceRepo cardServiceRepo;
	
	public List<TCardService> findByCardId(Long cardId) {
		if(cardId != null) {
			return cardServiceRepo.findByCardId(cardId);
		}
		return null;
	}
	
	public TCardService save(TCardService cardService) {
		if(cardService != null) {
			return cardServiceRepo.save(cardService);
		}
		return null;
	}
	
	public void deleteById(Long cardServiceId) {
		if(cardServiceId != null) {
			TCardService cardService = cardServiceRepo.findOne(cardServiceId);
			if(cardService != null) {
				cardService.setEnable(Boolean.FALSE);
				cardServiceRepo.save(cardService);
			}
		}
	}
}
