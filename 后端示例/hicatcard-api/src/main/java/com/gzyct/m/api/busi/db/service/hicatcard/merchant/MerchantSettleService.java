package com.gzyct.m.api.busi.db.service.hicatcard.merchant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.gzyct.m.api.busi.bean.merchant.MerchantSettleQueryItem;
import com.gzyct.m.api.busi.bean.merchant.MerchantSettleServiceQueryItem;
import com.gzyct.m.api.busi.db.repo.merchant.MerchantSettleRepo;
import com.gzyct.m.api.busi.db.repo.merchant.MerchantSettleServiceRepo;
import com.gzyct.m.api.busi.util.CommonConvertor;

@Component
@Transactional
public class MerchantSettleService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MerchantSettleServiceRepo merchantSettleServiceRepo;

	@Autowired
	MerchantSettleRepo merchantSettleRepo;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<MerchantSettleQueryItem> findByMerchantId(Long merchantId, String beginTime, String endTime,
			String searchServiceName, Integer page, Integer pagesize) {
		try {
			/*
			 * SELECT ms.MERCHANT_SETTLE_ID, ms.SETTLE_START_TIME, ms.SETTLE_END_TIME,
			 * mss.TRANSACTION_TIME, mss.TRANSACTION_FEE, s.SERVICE_NAME, ms.CREATE_TIME
			 * FROM t_merchant_settle ms LEFT JOIN t_merchant_settle_service mss ON
			 * ms.MERCHANT_SETTLE_ID = mss.MERCHANT_SETTLE_ID LEFT JOIN t_service s ON
			 * mss.SERVICE_ID = s.SERVICE_ID WHERE ms.CREATE_TIME < '20180222201100' AND
			 * ms.CREATE_TIME > '20180101201100' AND s.SERVICE_NAME LIKE '%%' AND
			 * ms.MERCHANT_ID = 52 ORDER BY ms.MERCHANT_SETTLE_ID;
			 */
			String sql = "SELECT ms.MERCHANT_SETTLE_ID, ms.SETTLE_START_TIME, ms.SETTLE_END_TIME, mss.TRANSACTION_TIME, mss.TRANSACTION_FEE, mss.SERVICE_ID, s.SERVICE_NAME, ms.CREATE_TIME "
					+ "FROM t_merchant_settle ms "
					+ "LEFT JOIN t_merchant_settle_service mss ON ms.MERCHANT_SETTLE_ID = mss.MERCHANT_SETTLE_ID "
					+ "LEFT JOIN t_service s ON mss.SERVICE_ID = s.SERVICE_ID ";
			String whereString = "WHERE ms.MERCHANT_ID = " + merchantId;
			if (!StringUtils.isEmpty(beginTime)) {
				whereString = whereString + " AND ms.CREATE_TIME >= '" + beginTime + "' ";
			}
			if (!StringUtils.isEmpty(endTime)) {
				whereString = whereString + " AND ms.CREATE_TIME <= '" + endTime + "' ";
			}
			if (!StringUtils.isEmpty(searchServiceName)) {
				whereString = whereString + " AND s.SERVICE_NAME LIKE '%" + searchServiceName + "%'";
			}
			whereString = whereString + " ORDER BY ms.MERCHANT_SETTLE_ID DESC " +" LIMIT " + page * pagesize + "," + pagesize;
			sql = sql + whereString;
			logger.info("sql = " + sql);

			Query query = entityManager.createNativeQuery(sql);

			List sqlResult = query.getResultList();
			logger.info("resultList = " + JSON.toJSONString(sqlResult));

			List<MerchantSettleServiceQueryItem> merchantSettleServiceQueryItemList = new ArrayList<MerchantSettleServiceQueryItem>();
			for (int i = 0; i < sqlResult.size(); i++) {
				Object[] objects = (Object[]) sqlResult.get(i);

				MerchantSettleServiceQueryItem merchantSettleServiceQueryItem = new MerchantSettleServiceQueryItem();
				merchantSettleServiceQueryItem.setMerchantSettleId(
						Long.parseLong(CommonConvertor.convertObjectToStringAvoidNull(objects[0])));
				merchantSettleServiceQueryItem
						.setSettle_start_time(CommonConvertor.convertObjectToStringAvoidNull(objects[1]));
				merchantSettleServiceQueryItem
						.setSettle_end_time(CommonConvertor.convertObjectToStringAvoidNull(objects[2]));
				merchantSettleServiceQueryItem.setTransactionTime(
						Integer.parseInt(CommonConvertor.convertObjectToStringAvoidNull(objects[3])));
				merchantSettleServiceQueryItem.setTransactionFee(
						Integer.parseInt(CommonConvertor.convertObjectToStringAvoidNull(objects[4])));
				merchantSettleServiceQueryItem
						.setServiceId(Long.parseLong(CommonConvertor.convertObjectToStringAvoidNull(objects[5])));
				merchantSettleServiceQueryItem
						.setServiceName(CommonConvertor.convertObjectToStringAvoidNull(objects[6]));
				merchantSettleServiceQueryItem
						.setCreate_time(CommonConvertor.convertObjectToStringAvoidNull(objects[7]));
				merchantSettleServiceQueryItem.setTotal_fee(merchantSettleServiceQueryItem.getTransactionTime()
						* merchantSettleServiceQueryItem.getTransactionFee());
				merchantSettleServiceQueryItemList.add(merchantSettleServiceQueryItem);
			}

			List<MerchantSettleQueryItem> merchantSettleQueryItemList = new ArrayList<MerchantSettleQueryItem>();
			for (MerchantSettleServiceQueryItem merchantSettleServiceQueryItem : merchantSettleServiceQueryItemList) {
				boolean whetherHas = false;
				for (MerchantSettleQueryItem merchantSettleQueryItem : merchantSettleQueryItemList) {
					// 一样就加service + total
					if (merchantSettleQueryItem.getMerchant_settle_id().longValue() == merchantSettleServiceQueryItem
							.getMerchantSettleId().longValue()) {
						merchantSettleQueryItem.getSettleServiceList().add(merchantSettleServiceQueryItem);
						merchantSettleQueryItem.setSettle_fee(merchantSettleServiceQueryItem.getTotal_fee()
								+ merchantSettleQueryItem.getSettle_fee());
						whetherHas = true;
						break;
					}
				}

				if (!whetherHas) {
					MerchantSettleQueryItem merchantSettleQueryItem = new MerchantSettleQueryItem();
					merchantSettleQueryItem.setMerchant_settle_id(merchantSettleServiceQueryItem.getMerchantSettleId());
					merchantSettleQueryItem.setSettle_start_time(merchantSettleServiceQueryItem.getSettle_start_time());
					merchantSettleQueryItem.setSettle_end_time(merchantSettleServiceQueryItem.getSettle_end_time());
					merchantSettleQueryItem.setSettle_fee(merchantSettleServiceQueryItem.getTotal_fee());
					merchantSettleQueryItem.setMerchant_id(merchantId);
					merchantSettleQueryItem.setCreate_time(merchantSettleServiceQueryItem.getCreate_time());
					List<MerchantSettleServiceQueryItem> merchantSettleServiceQueryItemListIn = new ArrayList<MerchantSettleServiceQueryItem>();
					merchantSettleServiceQueryItemListIn.add(merchantSettleServiceQueryItem);
					merchantSettleQueryItem.setSettleServiceList(merchantSettleServiceQueryItemListIn);

					merchantSettleQueryItemList.add(merchantSettleQueryItem);
				}
			}

			return merchantSettleQueryItemList;

		} catch (Exception e) {
			throw e;
		}
	}
}
