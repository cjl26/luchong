package cloudPayAdmin.admin.dbapp.repo.cloudpay;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayCard;


public interface CloudpayCardRepo /*extends JpaRepository<TCloudpayCard, Long>, JpaSpecificationExecutor<TCloudpayCard>*/ {
	Page<TCloudpayCard> findAll(Specification<TCloudpayCard> spec, Pageable pageable);  //分页按条件查询
	
	@Query("select u from TCloudpayCard u where u.userId = ?1 and u.available = ?2")
	List<TCloudpayCard> findByUserIdAndAvailable(Long userId,Integer available);
	
	@Query("select u from TCloudpayCard u where u.cardNum = ?1 and u.available = ?2")
	List<TCloudpayCard> findByCardNumAndAvailable(String cardNum,Integer available);
	
	List<TCloudpayCard> findByUserIdAndCardNumAndAvailable(Long userId, String cardNum, Integer available);

	List<TCloudpayCard> findByIdIn(List<Long> idList);

}
