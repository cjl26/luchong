package cloudPayAdmin.admin.dbapp.repo.hicatcard.coupon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.coupon.TCoupon;

public interface CouponRepo extends JpaRepository<TCoupon, Long>, JpaSpecificationExecutor<TCoupon> {
	
	@Query("select t from TCoupon t where t.cardId = ?1 or t.cardId is null and t.enable = true")
	public List<TCoupon> findByCardIdAndNull(Long cardId);

}
