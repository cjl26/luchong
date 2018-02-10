package cloudPayAdmin.admin.controller.hicatcard.coupon;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSON;
import com.project.m.api.common.biz.resp.BizResp;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.BaseEntity;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCard;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.coupon.TCoupon;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchant;
import cloudPayAdmin.admin.service.hicatcard.card.CardService;
import cloudPayAdmin.admin.service.hicatcard.coupon.CouponService;
import cloudPayAdmin.constant.BusiError;
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
@RequestMapping("/hicatcard/coupon")
public class CouponController {
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	CouponService couponService;
	
	@Autowired
	CardService cardService;
	
	@Autowired
	Environment env;
	
	@RequestMapping(value = "/toListPage")
	public String toListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("typeMap", TCoupon.initTypeMap());
		return "hicatcard/coupon/coupon-list";
	}
	
	@RequestMapping("/getCouponListData")
	@ResponseBody
	public String getCardListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TCoupon.class, request);
		PageBean<TCoupon> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		
		AdminLteDataTableResp<TCoupon> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(TCoupon.class);
	}
	
	/**
	 * 删除优惠券
	 */
	@RequestMapping("/deleteCoupon")
	@ResponseBody
	public String deleteCoupon(HttpServletRequest request,Long couponId) {
		BizResp resp = new BizResp();
		couponService.deleteById(couponId);
		resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 调转到编辑优惠券页面
	 * @param request
	 * @param couponId
	 * @return
	 */
	@RequestMapping("/toCouponEditPage")
	public String toCouponEditPage(HttpServletRequest request,Long couponId) {
		TCoupon coupon = new TCoupon();
		if(couponId != null) {
			coupon = couponService.findById(couponId);			
		}
		
		//为图片地址加上 http 服务器前缀
		coupon.setPictureUrl(ImageUrlUtil.getFullHttpImageUrl(coupon.getPictureUrl()));
				
		List<TCard> cardList = cardService.findAll();
				
		request.setAttribute("cardList", cardList);
		request.setAttribute("coupon", coupon);
		return "hicatcard/coupon/coupon-edit";
	}
	
	/**
	 * 编辑优惠券
	 * @param request
	 * @param cuoponName
	 * @param detail
	 * @param fee
	 * @param startAndEndDate
	 * @param couponId
	 * @param cardId
	 * @param couponPic
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/editCoupon")
	public String editCoupon(HttpServletRequest request,String couponName,String detail,String fee,String startAndEndDate,Long couponId,Long cardId, MultipartFile couponPic) throws IllegalStateException, IOException {		
		//String id = request.getParameter("couponId");
		if(couponId != null) {			//修改
			TCoupon oriCoupon = couponService.findById(couponId);
			oriCoupon.setFee(new BigDecimal(fee).multiply(new BigDecimal("100")).intValue());
			setCardId(oriCoupon,cardId);
			setStartTimeAndEndTime(oriCoupon,startAndEndDate);			
			oriCoupon.setDetail(detail);
			oriCoupon.setCouponName(couponName);
			oriCoupon.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			if(couponPic != null && StringUtils.isNotBlank(couponPic.getOriginalFilename())) {
				String fileName = couponId + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(couponPic.getOriginalFilename());
				// 存入数据库的路径
				String dataBasePath = env.getProperty("image.upload.database.suffix.path") + Constant.COUPON_PIC_FILE_PATH + HttpUtil.HTTP_PATH_SEPERATOR + fileName;
				String filePath = env.getProperty("image.upload.base.path") + StringUtils.replaceEach(dataBasePath, new String[]{env.getProperty("image.upload.database.suffix.path"),HttpUtil.HTTP_PATH_SEPERATOR}, new String[]{"",File.separator});
				File file = new File(filePath);
				File parentFile = file.getParentFile();
				if(!parentFile.exists()) {
					parentFile.mkdirs();
				}
				
				FileUtils.copyInputStreamToFile(couponPic.getInputStream(), file);
				oriCoupon.setPictureUrl(dataBasePath);
			}
			couponService.save(oriCoupon);
			
		} else {       //新增
			TCoupon coupon = new TCoupon();
			coupon.setStatus(BaseEntity.STATUS_IN_USE);
			coupon.setCouponName(couponName);
			coupon.setFee(new BigDecimal(fee).multiply(new BigDecimal("100")).intValue());
			coupon.setDetail(detail);
			coupon.setCreator(AdminUserInfoUtil.getLoginAdminUserName());
			coupon.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			coupon.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			setCardId(coupon,cardId);
			setStartTimeAndEndTime(coupon,startAndEndDate);	
			coupon = couponService.save(coupon);
			
			if(couponPic != null && StringUtils.isNotBlank(couponPic.getOriginalFilename())) {
				String fileName = coupon.getCouponId() + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(couponPic.getOriginalFilename());
				// 存入数据库的路径
				String dataBasePath = env.getProperty("image.upload.database.suffix.path") + Constant.COUPON_PIC_FILE_PATH + HttpUtil.HTTP_PATH_SEPERATOR + fileName;
				String filePath = env.getProperty("image.upload.base.path") + StringUtils.replaceEach(dataBasePath, new String[]{env.getProperty("image.upload.database.suffix.path"),HttpUtil.HTTP_PATH_SEPERATOR}, new String[]{"",File.separator});
				File file = new File(filePath);
				System.out.println(file.getCanonicalPath());
				File parentFile = file.getParentFile();
				if(!parentFile.exists()) {
					parentFile.mkdirs();
				}
				
				FileUtils.copyInputStreamToFile(couponPic.getInputStream(), file);
				//couponPic.transferTo(new File(file.getCanonicalPath()));
				coupon.setPictureUrl(dataBasePath);
				couponService.save(coupon);
			}
			
			
		}
		
		return "redirect:/hicatcard/coupon/toListPage";
	}
	
	/**
	 * set CardId
	 * @param coupon
	 * @param cardId
	 */
	private void setCardId(TCoupon coupon,Long cardId) {
		if(cardId != null) {
			coupon.setCardId(cardId);
			coupon.setType(TCoupon.TYPE_ONE);
		} else {
			coupon.setCardId(cardId);
			coupon.setType(TCoupon.TYPE_ALL);
		}
	}
	
	/**
	 * 设置 startTime 和 endTime
	 * @param coupon
	 * @param startAndEndDate
	 */
	private void setStartTimeAndEndTime(TCoupon coupon,String startAndEndDate) {
		if(StringUtils.isNotBlank(startAndEndDate)) {
			String[] time = StringUtils.splitByWholeSeparator(startAndEndDate, WebUtil.DATERANGE_SEPARATOR);
			coupon.setStartTime(StringUtils.replace(time[0], "-", "") + "000000");
			coupon.setEndTime(StringUtils.replace(time[1], "-", "") + "235959");
		}		
	}
}
