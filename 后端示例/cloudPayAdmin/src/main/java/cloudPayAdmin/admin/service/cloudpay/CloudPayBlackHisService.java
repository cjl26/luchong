package cloudPayAdmin.admin.service.cloudpay;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayBlackHis;
import cloudPayAdmin.admin.dbapp.repo.cloudpay.CloudpayBlackHisRepo;

@Component
@Transactional
public class CloudPayBlackHisService {
	private final Logger logger = Logger.getLogger(getClass());
	
/*	@Autowired
	CloudpayBlackHisRepo cloudpayBlackHisRepo;*/
	
	public TCloudpayBlackHis findById(Long id) {
		/*return cloudpayBlackHisRepo.findOne(id);*/
		return null;
	}
	
}
