package cloudPayAdmin.admin.service.hicatcard.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.BaseEntity;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.service.TService;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.service.ServiceRepo;
import cloudPayAdmin.util.TimeUtil;



@Component
@Transactional
public class ServiceService {
	
	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	ServiceRepo serviceRepo;
	
	@Transactional(readOnly=true)
 	public TService findById(Long serviceId) {
 		TService service = serviceRepo.findOne(serviceId);
 		return service;
 	}
	
	public void deleteById(Long serviceId) {
		if(serviceId != null) {
			TService service = serviceRepo.findOne(serviceId);
			if(service != null) {
				service.setEnable(BaseEntity.DISABLE);
				serviceRepo.save(service);
			}		
		}
	}
	
	public TService saveService(TService service) {
		Long serviceId = service.getServiceId();
		if(serviceId != null) {    // 更新
			TService serv = serviceRepo.findOne(serviceId);
			serv.setServiceName(service.getServiceName());
			serv.setStatus(service.getStatus());
			serv.setUpdateTIme(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			return serviceRepo.save(serv);
		} else {       //保存
			service.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			service.setUpdateTIme(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			return serviceRepo.save(service);
		}		
	}
	
	@Transactional(readOnly=true)
	public List<TService> findAll() {
		return serviceRepo.findAll(new Specification<TService>() {				
			@Override
			public Predicate toPredicate(Root<TService> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<Boolean> enable = root.get("enable");
				query.where(cb.equal(enable, Boolean.TRUE));
				return null;
			}
		});
	}
	
	/**
	 * 根据id查找不在id范围的服务记录
	 * @param serviceIds
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<TService> findByIdNotIn(List<Long> serviceIds) {
		if(!CollectionUtils.isEmpty(serviceIds)) {
			return serviceRepo.findByIdNotIn(serviceIds);
		} else {
			return serviceRepo.findAll(new Specification<TService>() {				
				@Override
				public Predicate toPredicate(Root<TService> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Path<Boolean> enable = root.get("enable");
					query.where(cb.equal(enable, Boolean.TRUE));
					return null;
				}
			});
		}
	}
	
}
