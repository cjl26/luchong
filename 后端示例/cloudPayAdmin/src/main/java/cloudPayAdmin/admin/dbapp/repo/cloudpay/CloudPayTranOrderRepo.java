package cloudPayAdmin.admin.dbapp.repo.cloudpay;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudPayTranOrder;

import java.util.List;

public interface CloudPayTranOrderRepo /*extends JpaRepository<TCloudPayTranOrder, Long>, JpaSpecificationExecutor<TCloudPayTranOrder>*/{
	List<TCloudPayTranOrder> findByUserId(String userId, Pageable pageable);
	TCloudPayTranOrder findByOutTradeNo(String outTradeNo);
	TCloudPayTranOrder findById(String id);
}
