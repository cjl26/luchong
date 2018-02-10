package cloudPayAdmin.admin.dbapp.repo.hicatcard.transaction;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.transaction.TTransactionService;

public interface TransactionServiceRepo extends JpaRepository<TTransactionService, Long>, JpaSpecificationExecutor<TTransactionService> {

	List<TTransactionService> findByTransactionIdAndEnable(Long transactionId, Boolean enable);
	
	@Query("select t from TTransactionService t where t.transactionId in ?1 and t.enable = true")
	List<TTransactionService> findByTransactionIds(List<Long> transactionId,Pageable pagealbe);
}
