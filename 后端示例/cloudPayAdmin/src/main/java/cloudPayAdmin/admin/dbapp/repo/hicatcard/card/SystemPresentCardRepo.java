package cloudPayAdmin.admin.dbapp.repo.hicatcard.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TSystemPresentCard;

public interface SystemPresentCardRepo extends JpaRepository<TSystemPresentCard, Long>, JpaSpecificationExecutor<TSystemPresentCard> {
	
	@Query("select max(t.batch) from TSystemPresentCard t where t.enable = true")
	public Long getMaxBacth();
	
	@Query("select t from TSystemPresentCard t where t.cardNumber = ?1 and t.enable = true")
	public TSystemPresentCard findByCardNumber(String cardNumber);
}
