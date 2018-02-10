package cloudPayAdmin.admin.dbapp.repo.hicatcard.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.service.TService;

public interface ServiceRepo extends JpaRepository<TService, Long>, JpaSpecificationExecutor<TService> {
	
	@Query("select u from TService u where u.serviceId not in ?1 and u.enable = true")
	public List<TService> findByIdNotIn(List<Long> serviceIds);
}
