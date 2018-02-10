package cloudPayAdmin.admin.dbapp.repo.cloudpay;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayBlackHis;

public interface CloudpayBlackHisRepo /*extends JpaRepository<TCloudpayBlackHis, Long>, JpaSpecificationExecutor<TCloudpayBlackHis>*/{
	List<TCloudpayBlackHis> findByCardNumOrderByIdDesc(String cardnum);
}
