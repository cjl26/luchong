package cloudPayAdmin.admin.dbapp.repo.hicatcard.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUserCard;


public interface UserCardRepo extends JpaRepository<TUserCard, Long>, JpaSpecificationExecutor<TUserCard> {
	
	@Query("select t from TUserCard t where t.userCardNumber = ?1 and t.enable = true")
	public TUserCard findByUserCardNumber(String userCardNumber);
	
}
