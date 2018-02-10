package cloudPayAdmin.admin.controller.hicatcard.banner;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.banner.TBanner;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCard;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCardService;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.coupon.TCoupon;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.service.TService;
import cloudPayAdmin.admin.dbapp.vo.hicatcard.VCardCoupon;
import cloudPayAdmin.admin.service.hicatcard.banner.BannerService;
import cloudPayAdmin.constant.Constant;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.AdminUserInfoUtil;
import cloudPayAdmin.util.HttpUtil;
import cloudPayAdmin.util.ImageUrlUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/hicatcard/banner")
public class BannerController {
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	Environment env;
	
	@Autowired
	BannerService bannerService;
	
	@RequestMapping(value = "/toListPage")
	public String toListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("statusMap", TBanner.initStatusMap());
		request.setAttribute("placeMap", TBanner.initPlaceMap());
		return "hicatcard/banner/banner-list";
	}
	
	@RequestMapping("/getBannerListData")
	@ResponseBody
	public String getBannerListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TBanner.class, request);
		PageBean<TBanner> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		
		AdminLteDataTableResp<TBanner> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(TBanner.class);
	}
	
	/**
	 * 去编辑卡片页面
	 * @param request
	 * @param cardId
	 * @return
	 */
	@RequestMapping("/toBannerEditPage")
	public String toBannerEditPage(HttpServletRequest request,Long bannerId) {

		TBanner banner = new TBanner();
		
		if(bannerId != null) {
			TBanner banner2 = bannerService.findById(bannerId);
			if(banner2 != null) {
				banner = banner2;
			}
		}
		
		String pictureUrl = banner.getPictureUrl();
		if(StringUtils.isNotBlank(pictureUrl)) {
			banner.setPictureUrl(ImageUrlUtil.getFullHttpImageUrl(pictureUrl));
		}
		
		request.setAttribute("banner", banner);
		request.setAttribute("statusMap", TBanner.initStatusMap());
		request.setAttribute("placeMap", TBanner.initPlaceMap());
		
		return "hicatcard/banner/banner-edit";
	}
	
	@RequestMapping("/editBanner")
	public String editCard(HttpServletRequest request,TBanner banner,MultipartFile bannerPic) throws Exception {
			
			Long bannerId = banner.getBannerId();
		
			if(bannerId != null) {  //更新
				TBanner oriBanner = bannerService.findById(bannerId);
				oriBanner.setTitle(banner.getTitle());
				oriBanner.setOrderNum(banner.getOrderNum());
				oriBanner.setStatus(banner.getStatus());
				oriBanner.setPlace(banner.getPlace());
				if(bannerPic != null && StringUtils.isNotBlank(bannerPic.getOriginalFilename())) {
					String fileName = bannerId + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(bannerPic.getOriginalFilename());
					// 存入数据库的路径
					String dataBasePath =env.getProperty("image.upload.database.suffix.path") + Constant.BANNER_PIC_FILE_PATH + HttpUtil.HTTP_PATH_SEPERATOR + fileName;
					String filePath = env.getProperty("image.upload.base.path") + StringUtils.replaceEach(dataBasePath, new String[]{env.getProperty("image.upload.database.suffix.path"),HttpUtil.HTTP_PATH_SEPERATOR}, new String[]{"",File.separator});
					File file = new File(filePath);
					File parentFile = file.getParentFile();
					if(!parentFile.exists()) {
						parentFile.mkdirs();
					}
					
					FileUtils.copyInputStreamToFile(bannerPic.getInputStream(), file);
					oriBanner.setPictureUrl(dataBasePath);
				}
				bannerService.save(oriBanner);
				
			} else {   //保存
				
				banner.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
				banner.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
				
				banner = bannerService.save(banner);
				if(bannerPic != null && StringUtils.isNotBlank(bannerPic.getOriginalFilename())) {
					String fileName = banner.getBannerId() + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(bannerPic.getOriginalFilename());
					// 存入数据库的路径
					String dataBasePath =env.getProperty("image.upload.database.suffix.path") +  Constant.BANNER_PIC_FILE_PATH + HttpUtil.HTTP_PATH_SEPERATOR + fileName;
					String filePath = env.getProperty("image.upload.base.path") + StringUtils.replaceEach(dataBasePath, new String[]{env.getProperty("image.upload.database.suffix.path"),HttpUtil.HTTP_PATH_SEPERATOR}, new String[]{"",File.separator});
					File file = new File(filePath);
					File parentFile = file.getParentFile();
					if(!parentFile.exists()) {
						parentFile.mkdirs();
					}
				
					FileUtils.copyInputStreamToFile(bannerPic.getInputStream(), file);
					banner.setPictureUrl(dataBasePath);
					bannerService.save(banner);
				}
				
			}
			
		return "redirect:/hicatcard/banner/toListPage";
	}
	

}
