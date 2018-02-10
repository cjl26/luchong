package com.gzyct.m.api.busi.db.service.hicatcard.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gzyct.m.api.busi.bean.hicatcard.card.CardServiceItem;
import com.gzyct.m.api.busi.bean.hicatcard.user.UserCardItem;
import com.gzyct.m.api.busi.bean.hicatcard.user.UserCardServiceItem;
import com.gzyct.m.api.busi.bean.merchant.MerchantTransactionPerformServiceItem;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.TService;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TCardService;
import com.gzyct.m.api.busi.db.entity.hicatcard.card.TSystemPresentCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchantService;
import com.gzyct.m.api.busi.db.entity.hicatcard.pay.TPayOrder;
import com.gzyct.m.api.busi.db.entity.hicatcard.transaction.TTransaction;
import com.gzyct.m.api.busi.db.entity.hicatcard.transaction.TTransactionService;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCard;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCardService;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUserCardTransfer;
import com.gzyct.m.api.busi.db.repo.card.CardRepo;
import com.gzyct.m.api.busi.db.repo.card.CardServiceRepo;
import com.gzyct.m.api.busi.db.repo.card.SystemPresentCardRepo;
import com.gzyct.m.api.busi.db.repo.merchant.MerchantServiceRepo;
import com.gzyct.m.api.busi.db.repo.pay.PayOrderRepo;
import com.gzyct.m.api.busi.db.repo.service.ServiceRepo;
import com.gzyct.m.api.busi.db.repo.transaction.TransactionRepo;
import com.gzyct.m.api.busi.db.repo.transaction.TransactionServiceRepo;
import com.gzyct.m.api.busi.db.repo.user.UserCardRepo;
import com.gzyct.m.api.busi.db.repo.user.UserCardServiceRepo;
import com.gzyct.m.api.busi.db.repo.user.UserCardTransferRepo;
import com.gzyct.m.api.busi.db.repo.user.UserRepo;
import com.gzyct.m.api.busi.util.OrderStatus;
import com.gzyct.m.api.busi.util.TimeUtil;

@Component
@Transactional
public class UserCardService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CardRepo cardRepo;

	@Autowired
	UserCardRepo userCardRepo;

	@Autowired
	UserCardServiceRepo userCardServiceRepo;

	@Autowired
	CardServiceRepo cardServiceRepo;

	@Autowired
	ServiceRepo serviceRepo;

	@Autowired
	PayOrderRepo payOrderRepo;

	@Autowired
	UserCardTransferRepo userCardTransferRepo;

	@Autowired
	TransactionRepo transactionRepo;

	@Autowired
	TransactionServiceRepo transactionServiceRepo;

	@Autowired
	SystemPresentCardRepo systemPresentCardRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	MerchantServiceRepo merchantServiceRepo;

	@Value("${image.upload.url}")
	private String imageUploadUrl;

	/**
	 * 保存用户卡片信息
	 * 
	 * @param userCard
	 */
	public void saveUserCardBySystem(TUserCard userCard, TSystemPresentCard systemPresentCard) {
		userCard = userCardRepo.save(userCard);
		List<TCardService> cardServiceList = cardServiceRepo.findByCardIdAndEnable(userCard.getCardId(), true);
		List<TUserCardService> userCardServiceList = new ArrayList<TUserCardService>();
		for (TCardService cardService : cardServiceList) {
			TUserCardService userCardService = new TUserCardService();
			userCardService.setUserCardId(userCard.getUserCardId());
			userCardService.setServiceId(cardService.getServiceId());
			userCardService.setServiceTime(cardService.getServiceTime());
			userCardService.setStatus("1");
			userCardService.setCreateTime(TimeUtil.getCurrTime());
			userCardService.setUpdateTime(TimeUtil.getCurrTime());
			userCardService.setEnable(true);
			userCardServiceList.add(userCardService);
		}
		TUserCardTransfer userCardTransfer = new TUserCardTransfer();
		userCardTransfer.setUserCardNumber(userCard.getUserCardNumber());
		userCardTransfer.setSourceUserCardId(-1l);
		userCardTransfer.setSourceUserId(-1l);
		userCardTransfer.setSourceCardNumber(systemPresentCard.getCardNumber());
		userCardTransfer.setTargetUserCardId(userCard.getUserCardId());
		userCardTransfer.setTargetUserId(userCard.getUserId());
		userCardTransfer.setCreateTime(TimeUtil.getCurrTime());
		userCardTransfer.setUpdateTime(TimeUtil.getCurrTime());
		userCardTransfer.setEnable(true);

		systemPresentCard.setUpdateTime(TimeUtil.getCurrTime());
		// systemPresentCard.setEnable(false);
		systemPresentCard.setStatus(TSystemPresentCard.STATUS_COLLECTED);
		userCardTransferRepo.save(userCardTransfer);
		userCardServiceRepo.save(userCardServiceList);
		systemPresentCardRepo.save(systemPresentCard);
	}

	/**
	 * 保存用户卡片信息-转移
	 *
	 * @param userCardOriginal
	 * @param userCardTarget
	 */
	public void saveUserCardByCollect(TUserCard userCardOriginal, TUserCard userCardTarget) {
		userCardOriginal = userCardRepo.save(userCardOriginal);
		userCardTarget = userCardRepo.save(userCardTarget);

		List<TUserCardService> userCardServiceList = userCardServiceRepo
				.findByUserCardIdAndEnable(userCardOriginal.getUserCardId(), true);
		List<TUserCardService> userCardServiceTargetList = new ArrayList<TUserCardService>();
		for (TUserCardService userCardService : userCardServiceList) {
			TUserCardService userCardServiceTarget = new TUserCardService();
			userCardServiceTarget.setUserCardId(userCardTarget.getUserCardId());
			userCardServiceTarget.setServiceId(userCardService.getServiceId());
			userCardServiceTarget.setServiceTime(userCardService.getServiceTime());
			userCardServiceTarget.setStatus("1");
			userCardServiceTarget.setCreateTime(TimeUtil.getCurrTime());
			userCardServiceTarget.setUpdateTime(TimeUtil.getCurrTime());
			userCardServiceTarget.setEnable(true);
			userCardServiceTargetList.add(userCardServiceTarget);

			userCardService.setUpdateTime(TimeUtil.getCurrTime());
			userCardService.setEnable(false);
		}

		TUserCardTransfer userCardTransfer = new TUserCardTransfer();
		userCardTransfer.setUserCardNumber(userCardTarget.getUserCardNumber());
		userCardTransfer.setSourceUserCardId(userCardOriginal.getUserCardId());
		userCardTransfer.setSourceUserId(userCardOriginal.getUserId());
		userCardTransfer.setSourceCardNumber(userCardOriginal.getUserCardNumber());
		userCardTransfer.setTargetUserCardId(userCardTarget.getUserCardId());
		userCardTransfer.setTargetUserId(userCardTarget.getUserId());
		userCardTransfer.setCreateTime(TimeUtil.getCurrTime());
		userCardTransfer.setUpdateTime(TimeUtil.getCurrTime());
		userCardTransfer.setEnable(true);

		userCardServiceRepo.save(userCardServiceTargetList);
		userCardServiceRepo.save(userCardServiceList);
		userCardTransferRepo.save(userCardTransfer);
	}

	/**
	 * 保存用户卡片信息
	 * 
	 * @param userCard
	 */
	public void saveUserCardByNotify(TUserCard userCard, TPayOrder payOrder) {

		userCard = userCardRepo.save(userCard);

		List<TCardService> cardServiceList = cardServiceRepo.findByCardIdAndEnable(userCard.getCardId(), true);
		List<TUserCardService> userCardServiceList = new ArrayList<TUserCardService>();
		for (TCardService cardService : cardServiceList) {
			TUserCardService userCardService = new TUserCardService();
			userCardService.setUserCardId(userCard.getUserCardId());
			userCardService.setServiceId(cardService.getServiceId());
			userCardService.setServiceTime(cardService.getServiceTime());
			userCardService.setStatus("1");
			userCardService.setCreateTime(TimeUtil.getCurrTime());
			userCardService.setUpdateTime(TimeUtil.getCurrTime());
			userCardService.setEnable(true);
			userCardServiceList.add(userCardService);
		}
		userCardServiceRepo.save(userCardServiceList);

		payOrder.setStatus(OrderStatus.STATUS_4_DELIVERED);
		payOrder.setUserCardId(userCard.getUserCardId());
		payOrder.setUpdateTime(TimeUtil.getCurrTime());

		payOrderRepo.save(payOrder);
	}

	@Transactional(readOnly = true)
	public List<UserCardServiceItem> findUserCardServiceItemListByUserCardId(Long userCardId, Boolean enable) {
		List<TUserCardService> userCardServiceList = userCardServiceRepo.findByUserCardIdAndEnable(userCardId, enable);
		List<UserCardServiceItem> userCardServiceItemList = new ArrayList<UserCardServiceItem>();
		for (TUserCardService userCardService : userCardServiceList) {
			UserCardServiceItem userCardServiceItem = new UserCardServiceItem();
			userCardServiceItem.setUserCardServiceId(userCardService.getUserCardServiceId());
			userCardServiceItem.setUserCardId(userCardService.getUserCardId());
			userCardServiceItem.setServiceId(userCardService.getServiceId());
			TService service = serviceRepo.findByServiceId(userCardService.getServiceId());
			if (service != null) {
				userCardServiceItem.setServiceName(service.getServiceName());
			} else {
				userCardServiceItem.setServiceName("");
			}
			userCardServiceItem.setServiceTime(userCardService.getServiceTime());
			userCardServiceItemList.add(userCardServiceItem);
		}
		return userCardServiceItemList;
	}

	/**
	 * 找用户卡片列表
	 *
	 * @param userId
	 * @param source
	 * @param status
	 * @param enable
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<UserCardItem> findUserCardItemList(Long userId, String source, String status, String attach,
			Boolean enable, Pageable pageable) {
		List<TUserCard> userCardList = new ArrayList<TUserCard>();
		if (!StringUtils.isEmpty(source)) {
			if (!StringUtils.isEmpty(status)) {
				// source + status
				logger.info("source + status");
				userCardList = userCardRepo.findByUserIdAndSourceAndStatusAndEnable(userId, source, status, enable,
						pageable);
			} else {
				// source
				logger.info("source");
				userCardList = userCardRepo.findByUserIdAndSourceAndEnable(userId, source, enable, pageable);
			}
		} else {
			if (!StringUtils.isEmpty(status)) {
				if (!StringUtils.isEmpty(attach) && status.equals(TUserCard.STATUS_ACTIVE)) {
					// status2+status4
					logger.info("status2+status4");
					userCardList = userCardRepo.findByUserIdAndStatusAndEnable(userId, TUserCard.STATUS_ACTIVE,
							TUserCard.STATUS_FINISH, enable, pageable);
				} else {
					if (status.equals(TUserCard.STATUS_ACTIVE)) {
						// status - 只核销
						userCardList = userCardRepo.findByUserIdAndStatusAndEnableAndEndTimeGreaterThan(userId, status,
								enable, TimeUtil.getCurrTime(), pageable);
					} else {
						userCardList = userCardRepo.findByUserIdAndStatusAndEnable(userId, status, enable, pageable);
					}

				}
			} else {
				// 全部
				userCardList = userCardRepo.findByUserIdAndEnable(userId, enable, pageable);
			}
		}

		// boolean expiredFlag = false;
		List<UserCardItem> userCardItemList = new ArrayList<UserCardItem>();
		for (TUserCard userCard : userCardList) {
			UserCardItem userCardItem = new UserCardItem();
			userCardItem.setUserCardId(userCard.getUserCardId());
			userCardItem.setUserId(userCard.getUserId());
			userCardItem.setCard_id(userCard.getCardId());

			// 获取卡片信息
			TCard card = cardRepo.findByCardId(userCard.getCardId());
			if (card != null) {
				userCardItem.setCard_name(card.getCardName());
				// userCardItem.setEffectiveDay("有效期为" + card.getEffectiveDay() / 30 + "个月");
				userCardItem.setEffectiveDay("" + card.getEffectiveDay() / 30);
				userCardItem.setType(card.getType());
				userCardItem.setPicture_url(imageUploadUrl + card.getPictureUlr());
				userCardItem.setFee(card.getFee());
				List<CardServiceItem> cardServiceItemList = new ArrayList<CardServiceItem>();
				// System.out.println("card.getCard_id() = " + card.getCardId());
				List<TCardService> cardServiceList = cardServiceRepo.findByCardIdAndEnable(card.getCardId(), true);
				for (TCardService cardService : cardServiceList) {
					TService service = serviceRepo.findByServiceId(cardService.getServiceId());
					CardServiceItem cardServiceItem = new CardServiceItem();
					cardServiceItem.setCard_service_id(cardService.getCardServiceId());
					cardServiceItem.setService_id(cardService.getServiceId());
					cardServiceItem.setService_name(service.getServiceName());
					cardServiceItem.setService_time(String.valueOf(cardService.getServiceTime()));
					cardServiceItemList.add(cardServiceItem);
				}
				userCardItem.setCardServices(cardServiceItemList);
			}
			userCardItem.setUserCardNumber(userCard.getUserCardNumber());
			userCardItem.setSource(userCard.getSource());
			userCardItem.setSourceUserId(userCard.getSourceUserId());

			// 设置卡片赠送来源
			if (userCardItem.getSourceUserId() == null) {
				userCardItem.setSourceUserName("");
			} else if (userCardItem.getSourceUserId() == -1l) {
				userCardItem.setSourceUserName("赠送");
			} else {
				TUser sourceUser = userRepo.findByUserId(userCardItem.getSourceUserId());
				if (sourceUser == null) {
					userCardItem.setSourceUserName("");
				} else {
					userCardItem.setSourceUserName(sourceUser.getNickname());
				}
			}
			userCardItem.setStatus(userCard.getStatus());

			// 设置卡片赠送对象
			if (userCardItem.getStatus().equalsIgnoreCase(TUserCard.STATUS_PRESENTED)) {
				List<TUserCardTransfer> userCardTransferList = userCardTransferRepo
						.findBySourceUserCardIdAndSourceUserIdAndEnable(userCardItem.getUserCardId(),
								userCardItem.getUserId(), true);
				if (userCardTransferList == null || userCardTransferList.size() == 0) {
					userCardItem.setTargetUserId(-1l);
					userCardItem.setTargetUserName("");
				}

				userCardItem.setTargetUserId(userCardTransferList.get(0).getTargetUserId());
				TUser targetUser = userRepo.findByUserId(userCardItem.getTargetUserId());
				if (targetUser == null) {
					userCardItem.setTargetUserName("");
				} else {
					userCardItem.setTargetUserName(targetUser.getNickname());
				}
			} else {
				userCardItem.setTargetUserId(-1l);
				userCardItem.setTargetUserName("");
			}

			userCardItem.setUpdateTime(userCard.getUpdateTime());
			// 判断是否过期
			if (!StringUtils.isEmpty(userCard.getEndTime())) {
				logger.info("current time = " + TimeUtil.getCurrTime());
				logger.info("endtime = " + userCard.getEndTime());
				if (TimeUtil.getCurrTime().compareToIgnoreCase(userCard.getEndTime()) > 0) {
					userCardItem.setIs_expired("1"); // 已经过期
				} else {
					userCardItem.setIs_expired("0"); // 未过期
				}
			} else {
				userCardItem.setIs_expired("0"); // 未过期
			}

			userCardItem.setEndTime(userCard.getEndTime());
			try {

				Date targetDate = TimeUtil.addDateNDay(TimeUtil.strToDate(userCard.getEndTime(), "yyyyMMddHHmmss"), -1);
				logger.info("userCard.getEndTime() = " + userCard.getEndTime());
				logger.info("targetDate = " + targetDate);

				// TOTEST 减一日
				userCardItem.setEndTime("有效期至" + TimeUtil.getFormatTime(targetDate, "yyyy/MM/dd"));
				userCardItem.setCreateTime(TimeUtil.getFormatTime(
						TimeUtil.strToDate(userCard.getCreateTime(), "yyyyMMddHHmmss"), "yyyy年MM月dd日 HH:mm"));
			} catch (Exception e) {
				logger.error("setCreateTime", e);
			}

			List<TUserCardService> userCardServiceList = userCardServiceRepo
					.findByUserCardIdAndEnable(userCardItem.getUserCardId(), true);
			if (userCardServiceList == null || userCardServiceList.size() == 0) {
				userCardItem.setDetail("卡片已领取");
			}
			List<UserCardServiceItem> userCardServiceItemList = new ArrayList<UserCardServiceItem>();
			for (TUserCardService userCardService : userCardServiceList) {
				UserCardServiceItem userCardServiceItem = new UserCardServiceItem();
				userCardServiceItem.setUserCardServiceId(userCardService.getUserCardServiceId());
				userCardServiceItem.setUserCardId(userCardService.getUserCardId());
				userCardServiceItem.setServiceId(userCardService.getServiceId());

				// 一卡一服务
				if (userCardItem.getCardServices() != null && userCardItem.getCardServices().size() > 0) {
					userCardServiceItem.setServiceName(userCardItem.getCardServices().get(0).getService_name());
					userCardServiceItem.setServiceTotalTime(
							Integer.valueOf(userCardItem.getCardServices().get(0).getService_time()));
					userCardItem.setDetail(card.getDetail()); // + "(" + userCardServiceItem.getServiceName() + "服务:"+
																// userCardItem.getCardServices().get(0).getService_time()
																// + "次)"
					userCardItem.setServiceName(userCardServiceItem.getServiceName());
					userCardItem.setServiceTime("" + userCardItem.getCardServices().get(0).getService_time());
				} else {
					userCardServiceItem.setServiceName("");
					userCardServiceItem.setServiceTotalTime(null);
					userCardItem.setDetail(card.getDetail());
					userCardItem.setServiceName("");
					userCardItem.setServiceTime("");
				}
				// 一卡多服务就用以下代码 - 20180122
				// TService service =
				// serviceRepo.findByServiceId(userCardService.getServiceId());
				// if (service != null) {
				// userCardServiceItem.setServiceName(service.getServiceName());
				// } else {
				// userCardServiceItem.setServiceName("");
				// }

				// if (card != null) {
				// List<TCardService> cardServiceList = cardServiceRepo
				// .findByCardIdAndServiceIdAndEnable(card.getCardId(), service.getServiceId(),
				// true);
				// if (cardServiceList != null && cardServiceList.size() > 0) {
				// userCardItem.setDetail(card.getDetail() + "(" + service.getServiceName() +
				// "服务:"
				// + cardServiceList.get(0).getServiceTime() + "次)");
				// } else {
				// userCardItem.setDetail(card.getDetail());
				// }
				// }
				//
				// if (card != null && service != null) {
				// List<TCardService> cardServiceList = cardServiceRepo
				// .findByCardIdAndServiceIdAndEnable(card.getCardId(), service.getServiceId(),
				// true);
				// if (cardServiceList != null && cardServiceList.size() > 0) {
				// userCardServiceItem.setServiceTotalTime(cardServiceList.get(0).getServiceTime());
				// } else {
				// userCardServiceItem.setServiceTotalTime(null);
				// }
				// }

				userCardServiceItem.setServiceTime(userCardService.getServiceTime());
				userCardServiceItemList.add(userCardServiceItem);
			}
			userCardItem.setUserCardService(userCardServiceItemList);
			userCardItem.setListDetail("");
			// 核销 status = 2 次数/总次数
			if (status.equals(TUserCard.STATUS_ACTIVE) && StringUtils.isEmpty(source)) {
				if (userCardServiceItemList.size() > 0) {
					userCardItem.setListDetail("" + userCardServiceItemList.get(0).getServiceTime() + "/"
							+ userCardServiceItemList.get(0).getServiceTotalTime());
				}
			}

			// 购卡记录 source = 1 状态 + 次数
			if (StringUtils.isEmpty(status) && source.equals(TUserCard.SOURCE_PURCHASE)) {
				String statusDetail = TUserCard.getStatusDetial(userCardItem.getStatus());
				logger.info("statusDetail = " + statusDetail);
				if (userCardServiceItemList.size() > 0) {
					if (userCardItem.getStatus().equals(TUserCard.STATUS_NOT_ACTIVE)) {
						userCardItem.setListDetail(
								statusDetail + ":" + userCardServiceItemList.get(0).getServiceTotalTime());
					} else if (userCardItem.getStatus().equals(TUserCard.STATUS_ACTIVE)
							|| userCardItem.getStatus().equals(TUserCard.STATUS_FINISH)) {
						userCardItem.setListDetail(userCardServiceItemList.get(0).getServiceTime() + "/"
								+ userCardServiceItemList.get(0).getServiceTotalTime());
					}
				} else {
					if (userCardItem.getStatus().equals(TUserCard.STATUS_PRESENTED)) {
						userCardItem.setListDetail(statusDetail + ":" + userCardItem.getTargetUserName());
					} else {
						userCardItem.setListDetail(statusDetail);
					}
				}
			}

			// 自用记录 status = 2
			if (status.equals(TUserCard.STATUS_ACTIVE) && StringUtils.isEmpty(source)) {
				if (userCardServiceItemList.size() > 0) {
					userCardItem.setListDetail("" + userCardServiceItemList.get(0).getServiceTime() + "/"
							+ userCardServiceItemList.get(0).getServiceTotalTime());
				}
				userCardItem.setCreateTime(null);// 自用记录，需求要求不显示时间
			}

			// 已经完成
			if (status.equals(TUserCard.STATUS_FINISH)) {
				if (userCardServiceItemList.size() > 0) {
					userCardItem.setListDetail("" + userCardServiceItemList.get(0).getServiceTime() + "/"
							+ userCardServiceItemList.get(0).getServiceTotalTime());
				}
			}

			// 转赠记录 status = 3 赠送的目标用户
			if (status.equals(TUserCard.STATUS_PRESENTED) && StringUtils.isEmpty(source)) {
				userCardItem.setListDetail(userCardItem.getTargetUserName());
			}

			// 友赠记录 source = 2 and status = 1 status.equals(TUserCard.STATUS_NOT_ACTIVE) &&
			if (source.equals(TUserCard.SOURCE_PRESENT)) {
				userCardItem.setListDetail(userCardItem.getSourceUserName());
			}

			userCardItemList.add(userCardItem);
		}
		return userCardItemList;
	}

	/**
	 * 保存交易
	 * 
	 * @param userCard
	 * @param transaction
	 * @param merchantTransactionPerformServiceItemList
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveCardTransaction(TUserCard userCard, TTransaction transaction,
			List<MerchantTransactionPerformServiceItem> merchantTransactionPerformServiceItemList) throws Exception {
		try {

			// Map<String, Object> resultMap = new HashMap<String, Object>();

			List<TUserCardService> userCardServiceList = new ArrayList<TUserCardService>();
			List<TTransactionService> transactionServiceList = new ArrayList<TTransactionService>();
			// 先保存transaction
			transaction = transactionRepo.save(transaction);

			boolean whethenDisable = true;
			String serviceContent = "";
			for (MerchantTransactionPerformServiceItem merchantTransactionPerformServiceItem : merchantTransactionPerformServiceItemList) {
				Long userCardServiceId = Long.valueOf(merchantTransactionPerformServiceItem.getUser_card_service_id());
				Long deductServiceTime = Long.valueOf(merchantTransactionPerformServiceItem.getService_time());
				logger.info("userCardServiceId = " + userCardServiceId + " deductServiceTime = " + deductServiceTime);

				TUserCardService userCardService = userCardServiceRepo
						.findByUserCardServiceIdAndEnable(userCardServiceId, true);

				// 用户卡id
				if (userCardService.getUserCardId().longValue() != userCard.getUserCardId().longValue()) {
					throw new Exception(BusiError.ERR_MSG_USER_CARD_ID_ERROR);
				}

				// 余额足够
				if (userCardService.getServiceTime().intValue() < deductServiceTime.intValue()) {
					throw new Exception(BusiError.ERR_MSG_USER_CARD_SERVICE_TIME_NOT_ENOUGH);
				}

				TTransactionService transactionService = new TTransactionService();
				List<TMerchantService> merchantServiceList = merchantServiceRepo.findByMerchantIdAndServiceIdAndEnable(
						transaction.getMerchantId(), userCardService.getServiceId(), true);
				if (merchantServiceList != null && merchantServiceList.size() > 0) {
					transactionService.setMerchantFee(merchantServiceList.get(0).getFee());
				} else {
					// 没有相应服务
					throw new Exception(BusiError.ERR_MSG_MERCHANT_SERVICE_NOT_EXIST);
				}

				transactionService.setTransactionId(transaction.getTransactionId());
				transactionService.setUserCardServiceId(userCardService.getUserCardServiceId());
				transactionService.setServiceId(userCardService.getServiceId());
				transactionService.setServiceTime(Integer.valueOf(deductServiceTime.intValue()));

				transactionService.setTimeBefore(userCardService.getServiceTime().intValue());
				transactionService.setTimeAfter(
						Integer.valueOf(userCardService.getServiceTime().intValue() - deductServiceTime.intValue()));
				transactionService.setCreateTime(TimeUtil.getCurrTime());
				transactionService.setUpdateTime(TimeUtil.getCurrTime());
				transactionService.setEnable(true);

				if (!StringUtils.isEmpty(serviceContent)) {
					serviceContent = serviceContent + " ";
				}
				TService service = serviceRepo.findByServiceId(userCardService.getServiceId());
				String serviceName = "";
				if (service != null) {
					serviceName = service.getServiceName();
				}
				serviceContent = serviceContent + serviceName + ":" + transactionService.getServiceTime() + "次";

				transactionServiceList.add(transactionService);

				// 扣减user card service
				userCardService.setServiceTime(
						Integer.valueOf(userCardService.getServiceTime().intValue() - deductServiceTime.intValue()));
				userCardService.setUpdateTime(TimeUtil.getCurrTime());
				userCardServiceList.add(userCardService);

				if (userCardService.getServiceTime() != 0) {
					whethenDisable = false;
				}
			}

			// resultMap.put("serviceContent", serviceContent);

			// 核销最后改成完结
			if (whethenDisable) {
				userCard.setStatus(TUserCard.STATUS_FINISH);
				userCard.setUpdateTime(TimeUtil.getCurrTime());
				userCardRepo.save(userCard);
			}

			// 保存transaction service
			transactionServiceRepo.save(transactionServiceList);
			userCardServiceRepo.save(userCardServiceList);
			// return resultMap;
		} catch (Exception e) {
			throw e;
		}

	}

	@Transactional(readOnly = true)
	public TUserCard findByUserCardIdAndEnable(Long userCardId, Boolean enable) {
		return userCardRepo.findByUserCardIdAndEnable(userCardId, enable);
	}

	@Transactional(readOnly = true)
	public List<TUserCard> findByUserCardNumberAndEnable(String userCardNumber, Boolean enable) {
		return userCardRepo.findByUserCardNumberAndEnable(userCardNumber, enable);
	}

	@Transactional(readOnly = true)
	public TCard findByUserCardId(Long userCardId) {
		TUserCard userCard = userCardRepo.findByUserCardId(userCardId);
		if (userCard == null) {
			return null;
		} else {
			return cardRepo.findByCardId(userCard.getCardId());
		}
	}

	public void saveUserCard(TUserCard userCard) {
		userCardRepo.save(userCard);
	}

	@Transactional(readOnly = true)
	public List<TUserCardService> findUserCardServiceByUserCardIdAndEnable(Long userCardId, Boolean enable) {
		return userCardServiceRepo.findByUserCardIdAndEnable(userCardId, enable);
	}

	// @Transactional(readOnly = true)
	// public TUserCard findByUserCardIdAndEnable(Long userCardId, Boolean enable) {
	// return userCardRepo.findByUserCardIdAndEnable(userCardId, enable);
	// }

	// public void saveUserCard(TUserCard userCard) {
	// userCardRepo.save(userCard);
	// }

	/*
	 * List<TUserCard> findByUserIdAndEnable(Long userId, Boolean enable, Pageable
	 * pageable);
	 * 
	 * List<TUserCard> findByUserIdAndSourceAndEnable(Long userId, String source,
	 * Boolean enable, Pageable pageable);
	 * 
	 * List<TUserCard> findByUserIdAndStatusAndEnable(Long userId, String status,
	 * Boolean enable, Pageable pageable);
	 * 
	 * List<TUserCard> findByUserIdAndSourceAndStatusAndEnable(Long userId, String
	 * source, String status, Boolean enable, Pageable pageable);
	 */

}
