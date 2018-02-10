package com.gzyct.m.api.busi.db.service.hicatcard.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.gzyct.m.api.busi.bean.hicatcard.coupon.UserCouponItem;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCoupon;
import com.gzyct.m.api.busi.db.repo.user.UserCouponRepo;
import com.gzyct.m.api.busi.util.CommonConvertor;

@Component
@Transactional
public class UserCouponService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserCouponRepo userCouponRepo;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<UserCouponItem> findByStatusAndCardIdAndUserId(String status, String type, String card_id, Long user_id,
			Pageable pageable) throws Exception {

		StringBuilder sql = new StringBuilder(
				" SELECT uc.USER_COUPON_ID, uc.USER_ID, uc.COUPON_ID, uc.USER_COUPON_NUMBER, "
						+ " uc.`STATUS`, uc.CREATE_TIME, uc.UPDATE_TIME, uc.USE_TIME, "
						+ " c.COUPON_NAME, c.TYPE, c.CARD_ID, c.FEE, c.PICTURE_URL, c.DETAIL "
						+ " FROM t_user_coupon uc "
						+ " LEFT JOIN t_coupon c ON uc.COUPON_ID = c.COUPON_ID AND uc.`ENABLE` = 1 "
						+ " WHERE uc.USER_ID = ?1 ");
		if (!StringUtils.isEmpty(status)) {
			sql.append(" AND c.`STATUS` = ?2 ");
		}

		if (!StringUtils.isEmpty(type)) {
			sql.append(" AND c.TYPE = ?3 ");
		}

		if (!StringUtils.isEmpty(card_id)) {
			sql.append(" AND c.CARD_ID = ?4 ");
		}

		sql.append("LIMIT " + pageable.getPageNumber() * pageable.getPageSize() + "," + pageable.getPageSize());

		logger.info("sql = " + sql);

		Query query = entityManager.createNativeQuery(sql.toString());
		query.setParameter(1, user_id);
		if (!StringUtils.isEmpty(status)) {
			query.setParameter(2, status);
		}

		if (!StringUtils.isEmpty(type)) {
			query.setParameter(3, type);
		}

		if (!StringUtils.isEmpty(String.valueOf(card_id))) {
			query.setParameter(4, card_id);
		}

		List sqlResult = query.getResultList();
		logger.info("resultList = " + JSON.toJSONString(sqlResult));

		List<UserCouponItem> userCouponItemList = new ArrayList<UserCouponItem>();
		for (int i = 0; i < sqlResult.size(); i++) {
			Object[] objects = (Object[]) sqlResult.get(i);
			UserCouponItem userCouponItem = new UserCouponItem();

			String userCouponId = CommonConvertor.convertObjectToStringAvoidNull(objects[0]);
			userCouponItem.setUser_coupon_id(userCouponId.isEmpty() ? -1 : Long.valueOf(userCouponId));
			String userId = CommonConvertor.convertObjectToStringAvoidNull(objects[1]);
			userCouponItem.setUser_id(userId.isEmpty() ? -1 : Long.valueOf(userId));
			String couponId = CommonConvertor.convertObjectToStringAvoidNull(objects[2]);
			userCouponItem.setCoupon_id(couponId.isEmpty() ? -1 : Long.valueOf(couponId));
			userCouponItem.setUser_coupon_number(CommonConvertor.convertObjectToStringAvoidNull(objects[3]));
			userCouponItem.setStatus(CommonConvertor.convertObjectToStringAvoidNull(objects[4]));
			userCouponItem.setCreate_time(CommonConvertor.convertObjectToStringAvoidNull(objects[5]));
			userCouponItem.setUpdate_time(CommonConvertor.convertObjectToStringAvoidNull(objects[6]));
			userCouponItem.setUse_time(CommonConvertor.convertObjectToStringAvoidNull(objects[7]));
			userCouponItem.setCoupon_name(CommonConvertor.convertObjectToStringAvoidNull(objects[8]));
			userCouponItem.setType(CommonConvertor.convertObjectToStringAvoidNull(objects[9]));
			String cardId = CommonConvertor.convertObjectToStringAvoidNull(objects[10]);
			userCouponItem.setCard_id(cardId.isEmpty() ? -1 : Long.valueOf(cardId));
			String fee = CommonConvertor.convertObjectToStringAvoidNull(objects[11]);
			userCouponItem.setFee(fee.isEmpty() ? -1 : Integer.valueOf(fee));
			userCouponItem.setPicture_url(CommonConvertor.convertObjectToStringAvoidNull(objects[11]));
			userCouponItem.setDetail(CommonConvertor.convertObjectToStringAvoidNull(objects[12]));
			userCouponItemList.add(userCouponItem);
		}

		return userCouponItemList;
	}

	@Transactional(readOnly = true)
	public TUserCoupon findByUserCouponIdAndEnable(Long userCouponId, Boolean enable) {
		return userCouponRepo.findByUserCouponIdAndEnable(userCouponId, enable);
	}

	public void saveUserCoupon(TUserCoupon userCoupon) {
		userCouponRepo.save(userCoupon);
	}

}
