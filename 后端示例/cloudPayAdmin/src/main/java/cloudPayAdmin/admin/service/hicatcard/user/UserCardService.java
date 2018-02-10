package cloudPayAdmin.admin.service.hicatcard.user;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUser;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUserCard;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUserCardTransfer;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.user.UserCardRepo;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.user.UserCardTransferRepo;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.user.UserRepo;

@Component
@Transactional
public class UserCardService {
	
	@Autowired
	UserCardRepo userCardRepo;
	
	@Autowired
	UserCardTransferRepo userCardTransferRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Transactional(readOnly=true)
	public TUser findTransferTargetUser(Long userCardId) {
		List<TUserCardTransfer> userCardTransfer = userCardTransferRepo.findBySourceUserCardId(userCardId);
		if(CollectionUtils.isEmpty(userCardTransfer)) {
			return null;
		}
		return userRepo.findOne(userCardTransfer.get(0).getTargetUserId());
	}
	
	
	@Transactional(readOnly=true)
	public TUserCard findById(Long userCardId) {
		return userCardRepo.findOne(userCardId);
	}
	
	@Transactional(readOnly=true)
	public TUserCard findByUserCardNumber(String userCardNumber) {
		return userCardRepo.findByUserCardNumber(userCardNumber);
	}
}
