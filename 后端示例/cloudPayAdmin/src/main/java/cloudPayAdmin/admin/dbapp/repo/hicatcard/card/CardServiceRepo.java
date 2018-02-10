package cloudPayAdmin.admin.dbapp.repo.hicatcard.card;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCardService;

public interface CardServiceRepo extends JpaRepository<TCardService, Long>, JpaSpecificationExecutor<TCardService> {
	
	@Query("select t from TCardService t where t.cardId = ?1 and t.enable = true")
	public List<TCardService> findByCardId(Long cardId);
}
