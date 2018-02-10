package com.gzyct.m.api.busi.db.service.hicatcard.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.gzyct.m.api.busi.bean.hicatcard.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionItem;
import com.gzyct.m.api.busi.db.entity.hicatcard.TService;
import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchantService;
import com.gzyct.m.api.busi.db.entity.hicatcard.transaction.TTransaction;
import com.gzyct.m.api.busi.db.entity.hicatcard.transaction.TTransactionService;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import com.gzyct.m.api.busi.db.repo.merchant.MerchantRepo;
import com.gzyct.m.api.busi.db.repo.merchant.MerchantServiceRepo;
import com.gzyct.m.api.busi.db.repo.service.ServiceRepo;
import com.gzyct.m.api.busi.db.repo.transaction.TransactionRepo;
import com.gzyct.m.api.busi.db.repo.transaction.TransactionServiceRepo;
import com.gzyct.m.api.busi.db.repo.user.UserCardRepo;
import com.gzyct.m.api.busi.util.CommonConvertor;

@Component
@Transactional
public class TransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserCardRepo userCardRepo;

	@Autowired
	TransactionRepo transactionRepo;

	@Autowired
	TransactionServiceRepo transactionServiceRepo;

	@Autowired
	MerchantRepo merchantRepo;

	@Autowired
	ServiceRepo serviceRepo;

	@Autowired
	MerchantServiceRepo merchantServiceRepo;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;

	@Value("${image.upload.url}")
	private String imageUploadUrl;

	
	/*
SELECT max(t.CREATE_TIME), MIN(t.CREATE_TIME),  ts.MERCHANT_FEE, SUM(ts.SERVICE_TIME), s.SERVICE_ID, s.SERVICE_NAME FROM t_transaction t 
LEFT JOIN t_transaction_service ts ON t.TRANSACTION_ID = ts.TRANSACTION_ID 
LEFT JOIN t_service s ON ts.SERVICE_ID = s.SERVICE_ID
WHERE t.`STATUS` = 0 AND t.`ENABLE` = 1 AND s.SERVICE_ID IS NOT NULL AND t.merchant_Id = 52 GROUP BY s.SERVICE_ID, ts.MERCHANT_FEE
ORDER BY t.CREATE_TIME ; 


	 */
	@Transactional(readOnly = true)
	public List<MerchantTransactionItem> findTransactionItemMerchantId(Long merchantId) throws Exception{
		try
		{
			String sql = "SELECT max(t.CREATE_TIME), MIN(t.CREATE_TIME),  ts.MERCHANT_FEE, SUM(ts.SERVICE_TIME), s.SERVICE_ID, s.SERVICE_NAME FROM t_transaction t \n" +
					"LEFT JOIN t_transaction_service ts ON t.TRANSACTION_ID = ts.TRANSACTION_ID \n" +
					"LEFT JOIN t_service s ON ts.SERVICE_ID = s.SERVICE_ID\n" +
					"WHERE t.`STATUS` = 0 AND t.`ENABLE` = 1 AND s.SERVICE_ID IS NOT NULL AND t.merchant_Id = ?1 GROUP BY s.SERVICE_ID, ts.MERCHANT_FEE\n" +
					"ORDER BY t.CREATE_TIME ; ";

			logger.info("findTransactionItemMerchantId sql = " + sql);

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, merchantId);
			List sqlResult = query.getResultList();
			logger.info("sqlResult = " + JSON.toJSONString(sqlResult));

			List<MerchantTransactionItem>  merchantTransactionItemList = new ArrayList<MerchantTransactionItem>();
			for (int i = 0; i < sqlResult.size(); i++) {
				Object[] objects = (Object[]) sqlResult.get(i);
				MerchantTransactionItem x = new MerchantTransactionItem();
				x.setEnd_date(CommonConvertor.convertObjectToStringAvoidNull(objects[0]));
				x.setBegin_date(CommonConvertor.convertObjectToStringAvoidNull(objects[1]));
				x.setMerchant_fee(Integer.parseInt(CommonConvertor.convertObjectToStringAvoidNull(objects[2])));
				x.setService_time(Integer.parseInt(CommonConvertor.convertObjectToStringAvoidNull(objects[3])));
				x.setService_id(CommonConvertor.convertObjectToStringAvoidNull(objects[4]));
				x.setService_name(CommonConvertor.convertObjectToStringAvoidNull(objects[5]));
				merchantTransactionItemList.add(x);
			}
			return merchantTransactionItemList;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	
	
//	@Transactional(readOnly = true)
//	public List<MerchantTransactionItem> findTransactionItemMerchantId(Long merchantId, String beginDate,
//			String endDate) throws Exception {
//		try {
//			String sql = "SELECT t.TRANSACTION_ID, t.CREATE_TIME, t.UPDATE_TIME " + "FROM t_transaction t "
//					+ "WHERE t.`ENABLE` = 1 ";
//			if (merchantId != null) {
//				sql = sql + "AND t.MERCHANT_ID = " + merchantId + " ";
//			}
//			if (!StringUtils.isEmpty(beginDate) && !StringUtils.isEmpty(endDate)) {
//				sql = sql + "AND t.CREATE_TIME >= " + beginDate + " AND t.CREATE_TIME <= " + endDate + " ";
//			}
//			sql = sql + "ORDER BY t.CREATE_TIME DESC ";
//
//			logger.info("sql = " + sql);
//			Query query = entityManager.createNativeQuery(sql);
//
//			List sqlResult = query.getResultList();
//			logger.info("resultList = " + JSON.toJSONString(sqlResult));
//
//			List<MerchantTransactionItem> merchantTransactionItemList = new ArrayList<MerchantTransactionItem>();
//			for (int i = 0; i < sqlResult.size(); i++) {
//				Object[] objects = (Object[]) sqlResult.get(i);
//				MerchantTransactionItem merchantTransactionItem = new MerchantTransactionItem();
//				merchantTransactionItem.setMerchantId("" + merchantId);
//				merchantTransactionItem.setTransactionId(CommonConvertor.convertObjectToStringAvoidNull(objects[0]));
//				merchantTransactionItem.setCreateTime(CommonConvertor.convertObjectToStringAvoidNull(objects[1]));
//				merchantTransactionItem.setUpdateTime(CommonConvertor.convertObjectToStringAvoidNull(objects[2]));
//
//				Long transactionId = null;
//				try {
//					transactionId = Long.parseLong(merchantTransactionItem.getTransactionId());
//				} catch (Exception e) {
//					throw e;
//				}
//
//				List<TTransactionService> transactionServiceList = transactionServiceRepo
//						.findByTransactionIdAndEnable(transactionId, true);
//				if (transactionServiceList != null && transactionServiceList.size() > 0) {
//					TTransactionService transactionService = transactionServiceList.get(0);
//					TService service = serviceRepo.findByServiceId(transactionService.getServiceId());
//					merchantTransactionItem.setServiceName(service.getServiceName());
//					List<TMerchantService> merchantServiceList = merchantServiceRepo
//							.findByMerchantIdAndServiceIdAndEnable(merchantId, service.getServiceId(), true);
//					if (merchantServiceList != null && merchantServiceList.size() > 0) {
//						merchantTransactionItem.setFee(merchantServiceList.get(0).getFee());
//					} else {
//						merchantTransactionItem.setFee(0);
//					}
//					merchantTransactionItem.setServiceTimes(transactionService.getServiceTime());
//					merchantTransactionItem
//							.setTotal(merchantTransactionItem.getServiceTimes() * merchantTransactionItem.getFee());
//				} else {
//					merchantTransactionItem.setServiceName("");
//					merchantTransactionItem.setFee(0);
//					merchantTransactionItem.setServiceTimes(0);
//					merchantTransactionItem.setTotal(0);
//				}
//
//				merchantTransactionItemList.add(merchantTransactionItem);
//			}
//
//			return merchantTransactionItemList;
//		} catch (Exception e) {
//			throw e;
//		}
//	}

	@Transactional(readOnly = true)
	public List<TransactionItem> findTransactionItemByUserIdAndMerchantId(Long userId, Long merchantId,
			String searchText, String beginDate, String endDate, String userCardIdInput, Integer page, Integer pagesize,
			Pageable pageable) throws Exception {
		try {
			String sql = "SELECT t.TRANSACTION_ID, t.TRANSACTION_ORDER, t.MERCHANT_ID, t.USER_ID, t.USER_CARD_ID, t.CAR_LICENCE, t.CREATE_TIME, t.UPDATE_TIME, "
					+ "m.MERCHANT_NUMBER, m.`NAME`, m.PHONE, m.PROVINCE, m.CITY, m.ADDRESS, m.LONGITUDE, m.LATITUDE, m.PICTURE_URL, m.DETAIL_PICTURE_URL, m.WEB_URL, m.DETAIL, m.`STATUS` "
					+ "FROM t_transaction t LEFT JOIN t_merchant m ON t.MERCHANT_ID = m.MERCHANT_ID "
					+ "WHERE t.`ENABLE` = 1 ";
			if (userId != null) {
				sql = sql + "AND t.USER_ID = " + userId + " ";
			}
			if (merchantId != null) {
				sql = sql + "AND t.MERCHANT_ID = " + merchantId + " ";
			}
			if (!StringUtils.isEmpty(userCardIdInput)) {
				sql = sql + "AND t.USER_CARD_ID = " + userCardIdInput + " ";
			}
			if (!StringUtils.isEmpty(searchText)) {
				sql = sql + "AND (m.`NAME` LIKE '%" + searchText + "%' OR m.ADDRESS LIKE '%" + searchText + "%') ";
			}
			if (!StringUtils.isEmpty(beginDate) && !StringUtils.isEmpty(endDate)) {
				sql = sql + "AND t.CREATE_TIME >= " + beginDate + " AND t.CREATE_TIME <= " + endDate + " ";
			}
			sql = sql + "ORDER BY t.CREATE_TIME DESC " + " LIMIT " + page * pagesize + "," + pagesize;

			logger.info("sql = " + sql);
			Query query = entityManager.createNativeQuery(sql);

			List sqlResult = query.getResultList();
			logger.info("resultList = " + JSON.toJSONString(sqlResult));

			List<TTransaction> transactionList = new ArrayList<TTransaction>();
			List<TransactionItem> transactionItemList = new ArrayList<TransactionItem>();
			for (int i = 0; i < sqlResult.size(); i++) {
				Object[] objects = (Object[]) sqlResult.get(i);
				TransactionItem transactionItem = new TransactionItem();
				transactionItem.setTransaction_id(CommonConvertor.convertObjectToStringAvoidNull(objects[0]));
				transactionItem.setTransaction_order(CommonConvertor.convertObjectToStringAvoidNull(objects[1]));
				transactionItem.setMerchant_id(CommonConvertor.convertObjectToStringAvoidNull(objects[2]));
				transactionItem.setUser_id(CommonConvertor.convertObjectToStringAvoidNull(objects[3]));
				transactionItem.setUser_card_id(CommonConvertor.convertObjectToStringAvoidNull(objects[4]));
				transactionItem.setCar_licence(CommonConvertor.convertObjectToStringAvoidNull(objects[5]));
				transactionItem.setCreate_time(CommonConvertor.convertObjectToStringAvoidNull(objects[6]));

				try {
					Long userCardId = Long.valueOf(transactionItem.getUser_card_id());
					TUserCard userCard = userCardRepo.findByUserCardIdAndEnable(userCardId, true);
					if (userCard != null) {
						transactionItem.setUser_card_number(userCard.getUserCardNumber());
					} else {
						transactionItem.setUser_card_number("");
					}
				} catch (Exception e) {
					transactionItem.setUser_card_number("");
				}

				// 获取商户信息
				TransactionMerchantItem transactionMerchantItem = new TransactionMerchantItem();
				transactionMerchantItem.setMerchant_id(CommonConvertor.convertObjectToStringAvoidNull(objects[2]));
				transactionMerchantItem.setMerchant_number(CommonConvertor.convertObjectToStringAvoidNull(objects[8]));
				transactionMerchantItem.setName(CommonConvertor.convertObjectToStringAvoidNull(objects[9]));
				transactionMerchantItem.setPhone(CommonConvertor.convertObjectToStringAvoidNull(objects[10]));
				transactionMerchantItem.setProvince(CommonConvertor.convertObjectToStringAvoidNull(objects[11]));
				transactionMerchantItem.setCity(CommonConvertor.convertObjectToStringAvoidNull(objects[12]));
				transactionMerchantItem.setAddress(CommonConvertor.convertObjectToStringAvoidNull(objects[13]));
				transactionMerchantItem.setLongitude(CommonConvertor.convertObjectToStringAvoidNull(objects[14]));
				transactionMerchantItem.setLatitude(CommonConvertor.convertObjectToStringAvoidNull(objects[15]));
				transactionMerchantItem
						.setPicture_url(imageUploadUrl + CommonConvertor.convertObjectToStringAvoidNull(objects[16]));
				transactionMerchantItem.setDetail_picture_url(
						imageUploadUrl + CommonConvertor.convertObjectToStringAvoidNull(objects[17]));
				transactionMerchantItem.setWeb_url(CommonConvertor.convertObjectToStringAvoidNull(objects[18]));
				transactionMerchantItem.setDetail(CommonConvertor.convertObjectToStringAvoidNull(objects[19]));
				transactionMerchantItem.setStatus(CommonConvertor.convertObjectToStringAvoidNull(objects[20]));
				List<TMerchantService> merchantServiceList = merchantServiceRepo
						.findByMerchantIdAndEnable(Long.valueOf(transactionMerchantItem.getMerchant_id()), true);
				List<MerchantServiceItem> merchantServiceItemList = new ArrayList<MerchantServiceItem>();
				for (TMerchantService merchantService : merchantServiceList) {
					MerchantServiceItem merchantServiceItem = new MerchantServiceItem();
					merchantServiceItem.setMerchant_service_id(merchantService.getMerchantServiceId());
					merchantServiceItem.setService_id(merchantService.getServiceId());
					TService service = serviceRepo.findByServiceId(merchantService.getServiceId());
					if (service != null) {
						merchantServiceItem.setService_name(service.getServiceName());
					} else {
						merchantServiceItem.setService_name("");
					}
					// merchantServiceItem.setFee(merchantService.getFee());
					merchantServiceItemList.add(merchantServiceItem);
				}
				transactionMerchantItem.setService(merchantServiceItemList);
				transactionItem.setMerchantInfo(transactionMerchantItem);

				// set List<TransactionServiceItem> services;
				List<TransactionServiceItem> transactionServiceItemList = new ArrayList<TransactionServiceItem>();
				try {
					Long transactionId = Long.valueOf(transactionItem.getTransaction_id());
					List<TTransactionService> transactionServiceList = transactionServiceRepo
							.findByTransactionIdAndEnable(transactionId, true);
					for (TTransactionService transactionService : transactionServiceList) {
						TransactionServiceItem transactionServiceItem = new TransactionServiceItem();
						transactionServiceItem.setTransaction_service_id(
								String.valueOf(transactionService.getTransactionServiceId()));
						transactionServiceItem.setTransaction_id(String.valueOf(transactionService.getTransactionId()));
						transactionServiceItem
								.setUser_card_service_id(String.valueOf(transactionService.getUserCardServiceId()));
						transactionServiceItem.setService_id(String.valueOf(transactionService.getServiceId()));
						TService service = serviceRepo.findByServiceId(transactionService.getServiceId());
						if (service != null) {
							transactionServiceItem.setService_name(service.getServiceName());
						} else {
							transactionServiceItem.setService_name("");
						}
						transactionServiceItem.setService_time(transactionService.getServiceTime());
						transactionServiceItem.setTime_before(transactionService.getTimeBefore());
						transactionServiceItem.setTime_after(transactionService.getTimeAfter());
						transactionServiceItemList.add(transactionServiceItem);
					}
					transactionItem.setServices(transactionServiceItemList);
				} catch (Exception e) {
					transactionItem.setServices(transactionServiceItemList);
				}
				transactionItemList.add(transactionItem);
			}

			// if (userId != null && merchantId != null) {
			// transactionList = transactionRepo.findByUserIdAndMerchantIdAndEnable(userId,
			// merchantId, true,
			// pageable);
			// } else if (userId != null && merchantId == null) {
			// transactionList = transactionRepo.findByUserIdAndEnable(userId, true,
			// pageable);
			// } else if (userId == null && merchantId != null) {
			// transactionList = transactionRepo.findByMerchantIdAndEnable(merchantId, true,
			// pageable);
			// } else {
			// throw new Exception(BusiError.ERR_MSG_PARAM_BAD_REQ);
			// }
			//
			// // TODO 改成 left join
			// for (TTransaction transaction : transactionList) {
			// TransactionItem transactionItem = new TransactionItem();
			// transactionItem.setTransaction_id(String.valueOf(transaction.getTransactionId()));
			// transactionItem.setTransaction_order(transaction.getTransactionOrder());
			// transactionItem.setMerchant_id(String.valueOf(transaction.getMerchantId()));
			// transactionItem.setUser_id(String.valueOf(transaction.getUserId()));
			// transactionItem.setUser_card_id(String.valueOf(transaction.getUserCardId()));
			//
			// TUserCard userCard =
			// userCardRepo.findByUserCardIdAndEnable(transaction.getUserCardId(), true);
			// if (userCard != null) {
			// transactionItem.setUser_card_number(userCard.getUserCardNumber());
			// } else {
			// transactionItem.setUser_card_number("");
			// }
			// transactionItem.setCar_licence(transaction.getCarLicence());
			// transactionItem.setCreate_time(transaction.getCreateTime());
			//
			// TransactionMerchantItem transactionMerchantItem = null;
			// // 暂时用户查询才需要商户信息
			// if (userId != null) {
			// // set TransactionMerchantItem merchantInfo;
			// TMerchant merchant =
			// merchantRepo.findByMerchantIdAndEnable(transaction.getMerchantId(), true);
			// if (merchant != null) {
			// transactionMerchantItem = new TransactionMerchantItem();
			// transactionMerchantItem.setMerchant_id(String.valueOf(merchant.getMerchantId()));
			// transactionMerchantItem.setMerchant_number(merchant.getMerchantNumber());
			// transactionMerchantItem.setName(merchant.getName());
			// transactionMerchantItem.setPhone(merchant.getPhone());
			// transactionMerchantItem.setProvince(merchant.getProvince());
			// transactionMerchantItem.setCity(merchant.getCity());
			// transactionMerchantItem.setAddress(merchant.getAddress());
			// transactionMerchantItem.setLongitude(merchant.getLongitude());
			// transactionMerchantItem.setLatitude(merchant.getLatitude());
			// transactionMerchantItem.setPicture_url(merchant.getPictureUrl());
			// transactionMerchantItem.setWeb_url(merchant.getWebUrl());
			// transactionMerchantItem.setDetail(merchant.getDetail());
			// transactionMerchantItem.setStatus(merchant.getStatus());
			// List<TMerchantService> merchantServiceList =
			// merchantServiceRepo.findByMerchantIdAndEnable(
			// Long.valueOf(transactionMerchantItem.getMerchant_id()), true);
			// List<MerchantServiceItem> merchantServiceItemList = new
			// ArrayList<MerchantServiceItem>();
			// for (TMerchantService merchantService : merchantServiceList) {
			// MerchantServiceItem merchantServiceItem = new MerchantServiceItem();
			// merchantServiceItem.setMerchant_service_id(merchantService.getMerchantServiceId());
			// merchantServiceItem.setService_id(merchantService.getServiceId());
			// TService service =
			// serviceRepo.findByServiceId(merchantService.getServiceId());
			// if (service != null) {
			// merchantServiceItem.setService_name(service.getServiceName());
			// } else {
			// merchantServiceItem.setService_name("");
			// }
			// merchantServiceItem.setFee(merchantService.getFee());
			// merchantServiceItemList.add(merchantServiceItem);
			// }
			// transactionMerchantItem.setService(merchantServiceItemList);
			// }
			// } // if(userId != null)
			// transactionItem.setMerchantInfo(transactionMerchantItem);
			//
			// // set List<TransactionServiceItem> services;
			// List<TTransactionService> transactionServiceList = transactionServiceRepo
			// .findByTransactionIdAndEnable(transaction.getTransactionId(), true);
			// List<TransactionServiceItem> transactionServiceItemList = new
			// ArrayList<TransactionServiceItem>();
			// for (TTransactionService transactionService : transactionServiceList) {
			// TransactionServiceItem transactionServiceItem = new TransactionServiceItem();
			// transactionServiceItem
			// .setTransaction_service_id(String.valueOf(transactionService.getTransactionServiceId()));
			// transactionServiceItem.setTransaction_id(String.valueOf(transactionService.getTransactionId()));
			// transactionServiceItem
			// .setUser_card_service_id(String.valueOf(transactionService.getUserCardServiceId()));
			// transactionServiceItem.setService_id(String.valueOf(transactionService.getServiceId()));
			// TService service =
			// serviceRepo.findByServiceId(transactionService.getServiceId());
			// if (service != null) {
			// transactionServiceItem.setService_name(service.getServiceName());
			// } else {
			// transactionServiceItem.setService_name("");
			// }
			// transactionServiceItem.setService_time(transactionService.getServiceTime());
			// transactionServiceItem.setTime_before(transactionService.getTimeBefore());
			// transactionServiceItem.setTime_after(transactionService.getTimeAfter());
			// transactionServiceItemList.add(transactionServiceItem);
			// }
			// transactionItem.setServices(transactionServiceItemList);
			//
			// transactionItemList.add(transactionItem);
			// }
			return transactionItemList;
		} catch (Exception e) {
			throw e;
		}
	}
}
