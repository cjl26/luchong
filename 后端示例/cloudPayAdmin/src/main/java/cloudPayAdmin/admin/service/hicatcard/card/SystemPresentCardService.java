package cloudPayAdmin.admin.service.hicatcard.card;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TSystemPresentCard;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.card.SystemPresentCardRepo;

@Service
@Transactional
public class SystemPresentCardService {
	
	@Autowired
	SystemPresentCardRepo systemPresentCardRepo;
	
	public List<TSystemPresentCard> saveList(List<TSystemPresentCard> systemPresentCardList) {
		if(!CollectionUtils.isEmpty(systemPresentCardList)) {
			return systemPresentCardRepo.save(systemPresentCardList);
		}
		return null;
	}
	
	@Transactional(readOnly=true)
	public List<TSystemPresentCard> findByIds(List<Long> ids) {
		if(!CollectionUtils.isEmpty(ids)) {
			return systemPresentCardRepo.findAll(ids);
		}
		return null;
	}
	
	@Transactional(readOnly=true)
	public Long getMaxBacth() {
		Long batch = systemPresentCardRepo.getMaxBacth();
		return batch;
	}
	
	@Transactional(readOnly=true)
	public TSystemPresentCard findByCardNumber(String cardNumber) {
		return systemPresentCardRepo.findByCardNumber(cardNumber);
	}

}
