package cloudPayAdmin.admin.service.cloudpay;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.user.TUser;
import cloudPayAdmin.admin.entity.excel.EImportUser;
import cloudPayAdmin.exception.ImportUserException;

@Component("userservice2")
@Transactional
public class UserService {

	private final Logger logger = Logger.getLogger(getClass());

/*	@Autowired
	UserRepo userRepo;

	@Autowired
	UserAcctRepo userAcctRepo;

	@Autowired
	UserAuthRepo userAuthRepo;

	@Autowired
	UserQrConfigRepo userQrConfigRepo;

	@Autowired
	CloudpayCardRepo cloudpayCardRepo;

	@Autowired
	CloudPayWhitelistRepo cloudPayWhitelistRepo;

	@Autowired
	Environment env;*/

	/**
	 * 导入用户
	 * 
	 * @param importUser
	 * @param errorMsgList
	 * @param operUserName
	 *            操作人姓名
	 * @throws ImportUserException
	 */
	public void importUser(EImportUser importUser, List<String> errorMsgList, String operUserName)
			throws ImportUserException {
		/*TUser user = userRepo.findByPhone(importUser.getPhone());
		if (user == null) { // 如果这记录不是羊城通宝用户
			// 保存用户
			user = saveUser(importUser);
			// 为了防止保存了用户，但是cloudpaycard没有保存的情况，
			if (checkAndSaveCloudPayCard(importUser, operUserName, user, errorMsgList)) {
				saveCloudPayWhitelist(importUser, operUserName, user);
			} else {
				throw new ImportUserException(errorMsgList.get(errorMsgList.size() - 1));
			}
		} else {
			if (checkAndSaveCloudPayCard(importUser, operUserName, user, errorMsgList)) {
				saveCloudPayWhitelist(importUser, operUserName, user);
			} else {
				throw new ImportUserException(errorMsgList.get(errorMsgList.size() - 1));
			}
			
		}*/
	}

	/**
	 * 增加用户
	 */
	private TUser saveUser(EImportUser importUser) {
		/*Date date = new Date();
		// 首先创建用户对象
		TUser user = new TUser();
		user.setStatus(TUser.STATUS_VALID);

		user.setPhone(importUser.getPhone());
		user.setPayFlag(TUser.PAY_FLAG_UNSET);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setRealNameFlag(TUser.REALNAME_FLAG_NOTSET);
		String defaultName = "用户";
		if (importUser.getPhone() != null && importUser.getPhone().length() > 4) {
			defaultName += importUser.getPhone().substring(importUser.getPhone().length() - 4,
					importUser.getPhone().length());// 取手机后4位
		}
		user.setUserName(defaultName);

		// 创建账户认证
		TUserAuth userAuth = new TUserAuth();
		userAuth.setIdentityType(TUserAuth.IDENTITY_TYPE_PHONE);
		userAuth.setIdentifier(importUser.getPhone());
		userAuth.setCredential(MD5Util.MD5Encode("123456", Constant.CHARSET_UTF));
		userAuth.setLastLogin(date);

		// 创建账户
		TUserAcct userAcct = new TUserAcct();
		userAcct.setAcctType(TUserAcct.USER_ACCT_ACCT_TYPE_MAIN);
		userAcct.setBalance(0);
		userAcct.setFrozen(0);
		userAcct.setStatus(TUserAcct.ACCT_STS_INUSE);
		userAcct.setCreateTime(date);
		userAcct.setUpdateTime(date);

		// 创建二维码配置
		user = userRepo.save(user);
		userAuth.setUserId(user.getId());
		userAuthRepo.save(userAuth);
		userAcct.setUserId(user.getId());
		userAcctRepo.save(userAcct);
		return user;*/
		return null;

	}

	/**
	 * 检查并保存cloudPayCard
	 * 
	 * @param importUser
	 * @param operUserName
	 * @param user
	 * @param errorMsgList
	 */
	private boolean checkAndSaveCloudPayCard(EImportUser importUser, String operUserName, TUser user,
			List<String> errorMsgList) {
		/*boolean hasSave = Boolean.FALSE;
		List<TCloudpayCard> cardNumCloudPayCardList = cloudpayCardRepo.findByCardNumAndAvailable(importUser.getCardNum(), TCloudpayCard.AVAILABLE_EXIST);
		if (!CollectionUtils.isEmpty(cardNumCloudPayCardList)) {
			String errorMsg = "序号:" + importUser.getIndex() + ",错误原因：卡号" + CardNumUtil.subCardNum(importUser.getCardNum()) + "已经被绑定";
			errorMsgList.add(errorMsg);
		} else {
			List<TCloudpayCard> userIdcloudPayCardList = null;
			if (user != null) {
				userIdcloudPayCardList = cloudpayCardRepo.findByUserIdAndAvailable(user.getId(),TCloudpayCard.AVAILABLE_EXIST);
			}
			// List<TCloudpayCard> userIdcloudPayCardList =
			// cloudpayCardRepo.findByUserIdAndAvailable(user.getId(),TCloudpayCard.AVAILABLE_EXIST);
			if (!CollectionUtils.isEmpty(userIdcloudPayCardList)) { // 如果cloudpaycard已经有记录，就报错
				String errorMsg = "序号:" + importUser.getIndex() + ",错误原因：手机号码" + importUser.getPhone() + "已经绑定其他卡号";
				errorMsgList.add(errorMsg);
			} else { // 没有就保存新纪录
				saveCloudPayCard(importUser, operUserName, user);
				hasSave = Boolean.TRUE;
			}

		}
		return hasSave;*/
		return true;
	}

	/**
	 * 增加cloudPayCard记录
	 * 
	 * @param importUser
	 * @param operUserName
	 * @param user
	 */
	private void saveCloudPayCard(EImportUser importUser, String operUserName, TUser user) {
		/*
		List<TCloudpayCard> cloudPayCardList = cloudpayCardRepo.findByUserIdAndCardNumAndAvailable(user.getId(), importUser.getCardNum(), TCloudpayCard.AVAILABLE_DELETED);
		
		if(!CollectionUtils.isEmpty(cloudPayCardList)) {     //如果查到已经被解绑的记录，就置为1，重新绑定,理论上只会查到一条记录
			for(TCloudpayCard cloudPayCard : cloudPayCardList) {
				cloudPayCard.setAvailable(TCloudpayCard.AVAILABLE_EXIST);
				cloudPayCard.setUpdateTime(TimeUtil.getFormatTime(new Date(), TimeUtil.DATE_PATTERN_NOSEPARTOR));
				cloudPayCard.setOperator(operUserName);
			}
			cloudpayCardRepo.save(cloudPayCardList);
		
		} else {     //如果没有查到记录，就新增一条记录
			TCloudpayCard cloudPayCard = new TCloudpayCard();
			cloudPayCard.setUserId(user.getId());
			cloudPayCard.setCardNum(importUser.getCardNum());
			cloudPayCard.setStatus(TCloudpayCard.STATUS_EFFECITIVE);
			cloudPayCard.setAppId(env.getProperty("cloudPayCard.appId"));
			cloudPayCard.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			cloudPayCard.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			cloudPayCard.setAvailable(TCloudpayCard.AVAILABLE_EXIST);
			cloudPayCard.setOperator(operUserName);
			cloudpayCardRepo.save(cloudPayCard);
		}	*/
	}

	private void saveCloudPayWhitelist(EImportUser importUser, String operUserName, TUser user) {
		/*TCloudpayWhitelist cloudpayWhitelist1 = new TCloudpayWhitelist();
		cloudpayWhitelist1.setAppId(env.getProperty("cloudPayCard.appId"));
		// cloudpayWhitelist1.setCardNum(importUser.getCardNum());
		cloudpayWhitelist1.setUserId(user.getId());
		cloudpayWhitelist1.setOperator(operUserName);
		cloudpayWhitelist1.setStatus(TCloudpayWhitelist.STATUS_CAN_USE);
		cloudpayWhitelist1.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DEFAULT_DATE_PATTERN));
		cloudpayWhitelist1.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DEFAULT_DATE_PATTERN));
		cloudPayWhitelistRepo.save(cloudpayWhitelist1);

		TCloudpayWhitelist cloudpayWhitelist2 = new TCloudpayWhitelist();
		cloudpayWhitelist2.setAppId(env.getProperty("cloudPayCard.appId"));
		cloudpayWhitelist2.setCardNum(importUser.getCardNum());
		// cloudpayWhitelist2.setUserId(user.getId());
		cloudpayWhitelist2.setOperator(operUserName);
		cloudpayWhitelist2.setStatus(TCloudpayWhitelist.STATUS_CAN_USE);
		cloudpayWhitelist2.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DEFAULT_DATE_PATTERN));
		cloudpayWhitelist2.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DEFAULT_DATE_PATTERN));
		cloudPayWhitelistRepo.save(cloudpayWhitelist2);*/
	}
}
