package cloudPayAdmin.admin.service.hicatcard.card;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.BaseEntity;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCard;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCardService;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchant;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.service.TService;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.card.CardRepo;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.card.CardServiceRepo;

@Transactional
@Service
public class CardService {
	
	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	CardRepo repo;
	@Autowired
	CardServiceRepo cardServiceRepo;
	
	@Transactional(readOnly=true)
	public TCard findById(Long cardId) {
		TCard card = repo.findOne(cardId);
		return card;
	}
	
	public void deleteById(Long cardId) {
		if(cardId != null) {
			TCard card = repo.findOne(cardId);
			if(card != null) {
				card.setEnable(BaseEntity.DISABLE);
				repo.save(card);
			}
		}
	}
	
	public TCard save(TCard card) {
		if(card != null) {
			return repo.save(card);
		}
		return null;
	}
	
	public TCard saveCardAndCardService(TCard card,List<TCardService> cardServiceList) {
		TCard card1 = repo.save(card);
		for(TCardService cardService : cardServiceList) {
			if(cardService.getCardId() == null) {
				cardService.setCardId(card1.getCardId());
			}
		}
		cardServiceRepo.save(cardServiceList);
		return card1;
	}
	
	@Transactional(readOnly=true)
	public List<TCard> findAll() {
		return repo.findAll(new Specification<TCard>() {				
			@Override
			public Predicate toPredicate(Root<TCard> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<Boolean> enable = root.get("enable");
				query.where(cb.equal(enable, Boolean.TRUE));
				return null;
			}
		});
	}

}
