package cloudPayAdmin.admin.dbapp.repo.cloudpay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayWhitelist;

import java.util.List;

public interface CloudPayWhitelistRepo /*extends JpaRepository<TCloudpayWhitelist, Long>, JpaSpecificationExecutor<TCloudpayWhitelist>*/{
	List<TCloudpayWhitelist> findByUserIdAndCardNumAndStatus(long userId, String cardNum ,long status);
	List<TCloudpayWhitelist> findByUserIdAndStatus(long userId, long status);
}
