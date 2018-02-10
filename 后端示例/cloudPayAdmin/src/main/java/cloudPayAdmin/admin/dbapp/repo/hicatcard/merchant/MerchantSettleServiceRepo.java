package cloudPayAdmin.admin.dbapp.repo.hicatcard.merchant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantService;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantSettle;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantSettleService;

public interface MerchantSettleServiceRepo extends JpaRepository<TMerchantSettleService, Long>, JpaSpecificationExecutor<TMerchantSettleService> {
	
	@Query("select t from TMerchantSettleService t where t.merchantSettleId = ?1")
	public List<TMerchantSettleService> findByMerchantSettleId(Long merchantSettleId);

}
