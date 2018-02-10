package cloudPayAdmin.admin.service.hicatcard.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.banner.TBanner;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.banner.BannerRepo;

@Service
@Transactional
public class BannerService {
	
	@Autowired
	BannerRepo bannerRepo;
	
	public TBanner save(TBanner banner) {
		return bannerRepo.save(banner);
	}
	
	@Transactional(readOnly=true)
	public TBanner findById(Long bannerId) {
		return bannerRepo.findOne(bannerId);
	}
}
