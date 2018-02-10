package cloudPayAdmin.admin.dbapp.repo.hicatcard.banner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.banner.TBanner;

public interface BannerRepo extends JpaRepository<TBanner, Long>, JpaSpecificationExecutor<TBanner> {

}
