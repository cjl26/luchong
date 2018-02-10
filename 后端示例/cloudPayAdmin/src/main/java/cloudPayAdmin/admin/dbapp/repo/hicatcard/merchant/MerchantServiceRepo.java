package cloudPayAdmin.admin.dbapp.repo.hicatcard.merchant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantService;

public interface MerchantServiceRepo extends JpaRepository<TMerchantService, Long>, JpaSpecificationExecutor<TMerchantService> {
	
	@Query("select u from TMerchantService u where u.merchantId = ?1 and u.enable = true")
	public List<TMerchantService> findByMerchantId(Long merchantId);

}
