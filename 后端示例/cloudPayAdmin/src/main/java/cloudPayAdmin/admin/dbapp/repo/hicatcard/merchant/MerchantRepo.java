package cloudPayAdmin.admin.dbapp.repo.hicatcard.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchant;

public interface MerchantRepo extends JpaRepository<TMerchant, Long>, JpaSpecificationExecutor<TMerchant> {

}
