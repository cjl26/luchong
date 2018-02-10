package cloudPayAdmin.admin.service.cloudpay;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayBlackHis;
import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayCard;
import cloudPayAdmin.admin.dbapp.entity.user.TUser;
import cloudPayAdmin.admin.dbapp.repo.cloudpay.CloudpayBlackHisRepo;
import cloudPayAdmin.admin.dbapp.repo.cloudpay.CloudpayCardRepo;
import cloudPayAdmin.admin.dbapp.repo.user.UserRepo;

@Component
@Transactional
public class CloudpayCardService {
	private final Logger logger = Logger.getLogger(getClass());

/*	@Autowired
	CloudpayCardRepo cloudpayCardRepo;

	@Autowired
	CloudpayBlackHisRepo cloudpayBlackHisRepo;

	@Autowired
	UserRepo userRepo;*/

	@Transactional(readOnly = true)
	public Page<TCloudpayCard> findCloudpayCard(Specification<TCloudpayCard> spec, Pageable pageable) {
		/*return cloudpayCardRepo.findAll(spec, pageable);*/
		return null;
	}

	@Transactional(readOnly = true)
	public List<TCloudpayCard> findCloudpayCardListByIdIn(List<Long> idList) {
		/*return cloudpayCardRepo.findByIdIn(idList);*/
		return null;
	}

	public void saveCloudpayCardList(List<TCloudpayCard> cloudpayCardList) {
		/*cloudpayCardRepo.save(cloudpayCardList);*/
	}

	@Transactional(readOnly = true)
	public List<TCloudpayBlackHis> findBlackHisByCardNum(String cardNum) {
		/*return cloudpayBlackHisRepo.findByCardNumOrderByIdDesc(cardNum);*/
		return null;
	}

	public TCloudpayBlackHis saveCloudPayBlackHis(TCloudpayBlackHis cloudPayBlackHis) {
		/*return cloudpayBlackHisRepo.save(cloudPayBlackHis);*/
		return null;
	}

	public void unbindAndBlackListHis(TCloudpayCard cloudpayCard, TCloudpayBlackHis his) {
		/*cloudpayCardRepo.save(cloudpayCard);
		cloudpayBlackHisRepo.save(his);*/
	}

	/**
	 * 根据卡号 和 绑定状态查找记录
	 * 
	 * @param cardNum
	 * @param available
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TCloudpayCard> findByCardNumAndAvailable(String cardNum, Integer available) {
		/*return cloudpayCardRepo.findByCardNumAndAvailable(cardNum, available);*/
		return null;
	}

	@Transactional(readOnly = true)
	public List<TCloudpayCard> findByPhoneAndCardNumAndAvailable(String phone, String cardNum, Integer available) {
		/*List<TCloudpayCard> cloudpayCardList = new ArrayList<TCloudpayCard>();
		// 通过phone来找user
		TUser user = userRepo.findByPhone(phone);
		if (user == null) {
			return cloudpayCardList;
		}
		// 通过userid + cardNum + available找List
		return cloudpayCardRepo.findByUserIdAndCardNumAndAvailable(user.getId(), cardNum, available);*/
		return null;
	}

}
