package cloudPayAdmin.admin.service.hicatcard.merchant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.BaseEntity;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantService;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.merchant.MerchantServiceRepo;

@Component
@Transactional
public class MerchantServiceService {
	
	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	MerchantServiceRepo merchantServiceRepo;
	
 	public void deletById(Long merchantServiceId) {
 		if(merchantServiceId != null) {
 			TMerchantService merchantService = merchantServiceRepo.findOne(merchantServiceId);
 			if(merchantService != null) {
 				merchantService.setEnable(BaseEntity.DISABLE);
 	 			merchantServiceRepo.save(merchantService);
 			}
		
 		}
 	}
 	
 	@Transactional(readOnly=true)
 	public TMerchantService findById(Long merchantServiceId) {
 		if(merchantServiceId != null) {
 			TMerchantService merchantService = merchantServiceRepo.findOne(merchantServiceId);
 			HibernateEntityManager hEntityManager = (HibernateEntityManager)entityManager;
 			hEntityManager.getSession().evict(merchantService);
 			return	merchantService;
 		}
 		return null;
 	}
 	
 	public TMerchantService update(TMerchantService merchantService) {
 		return merchantServiceRepo.save(merchantService);
 	}
}
