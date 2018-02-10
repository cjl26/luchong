package cloudPayAdmin.admin.dbapp.repo.hicatcard.user;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUserCardTransfer;

public interface UserCardTransferRepo extends JpaRepository<TUserCardTransfer, Long>, JpaSpecificationExecutor<TUserCardTransfer> {
	
	@Query("select t from TUserCardTransfer t where t.sourceUserCardId = ?1 and t.enable = true")
	public List<TUserCardTransfer> findBySourceUserCardId(Long userCardId);
	
	@Query("select t from TUserCardTransfer t where t.userCardNumber = ?1 and t.enable = true")
	public TUserCardTransfer findByUserCardNumber(String userCardNumber);
	
	@Query("select t from TUserCardTransfer t where t.sourceCardNumber = ?1 and t.enable = true")
	public List<TUserCardTransfer> findBySourceCardNumber(String sourceCardNumber);
}
