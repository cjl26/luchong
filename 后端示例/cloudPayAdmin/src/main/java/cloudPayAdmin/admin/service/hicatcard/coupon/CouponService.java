package cloudPayAdmin.admin.service.hicatcard.coupon;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.BaseEntity;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.coupon.TCoupon;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.coupon.CouponRepo;

@Service
@Transactional
public class CouponService {
	
	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;

	@Autowired
	CouponRepo couponRepo;
	
	@Transactional(readOnly=true)
	public TCoupon findById(Long couponId) {
		TCoupon coupon = couponRepo.findOne(couponId);
		return coupon;
	}
	
	@Transactional(readOnly=true)
	public List<TCoupon> findAll() {
		return couponRepo.findAll();
	}
	
	@Transactional(readOnly=true)
	public List<TCoupon> findByCardIdAndNull(Long couponId) {
		return couponRepo.findByCardIdAndNull(couponId);
	}
	
	public void deleteById(Long couponId) {
		if(couponId != null) {
			TCoupon coupon = couponRepo.findOne(couponId);
			coupon.setEnable(BaseEntity.DISABLE);
			couponRepo.save(coupon);
		}
	}
	
	public TCoupon save(TCoupon coupon) {
		if(coupon != null) {
			return couponRepo.save(coupon);
		}
		return null;
	}
}
