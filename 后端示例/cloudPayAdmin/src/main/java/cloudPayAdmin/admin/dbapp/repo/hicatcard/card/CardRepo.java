package cloudPayAdmin.admin.dbapp.repo.hicatcard.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCard;

public interface CardRepo extends JpaRepository<TCard, Long>, JpaSpecificationExecutor<TCard> {

}
