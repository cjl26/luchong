package cloudPayAdmin.admin.dbapp.repo.hicatcard.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.transaction.TTransaction;

public interface TransactionRepo extends JpaRepository<TTransaction, Long>, JpaSpecificationExecutor<TTransaction> {
	
	@Query("select t from TTransaction t where t.merchantId = ?1 and t.createTime > ?2 and t.createTime <= ?3 and t.enable = true")
	public List<TTransaction> findByCreateTime(Long merchantId ,String startTime,String endTime);
	
	@Query("select t from TTransaction t where t.merchantId = ?1 and t.createTime <= ?2 and t.enable = true")
	public List<TTransaction> findByCreateTime(Long merchantId ,String endTime);

}
