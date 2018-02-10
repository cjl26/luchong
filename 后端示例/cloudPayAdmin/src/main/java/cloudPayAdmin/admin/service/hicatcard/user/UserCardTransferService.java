package cloudPayAdmin.admin.service.hicatcard.user;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUserCardTransfer;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.user.UserCardTransferRepo;

@Component
@Transactional
public class UserCardTransferService {
	
	@Autowired
	UserCardTransferRepo userCardTransferRepo;

	@Transactional(readOnly=true)
	public TUserCardTransfer findByfindBySourceUserCardId(Long userCardId) {
		List<TUserCardTransfer> list = userCardTransferRepo.findBySourceUserCardId(userCardId);
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
	
	@Transactional(readOnly=true)
	public TUserCardTransfer findByUserCardNumber(String userCardNumber) {
		return userCardTransferRepo.findByUserCardNumber(userCardNumber);
	}
	
	@Transactional(readOnly=true)
	public List<TUserCardTransfer> findBySourceCardNumber(String sourceCardNumber) {
		return userCardTransferRepo.findBySourceCardNumber(sourceCardNumber);
	}

}
