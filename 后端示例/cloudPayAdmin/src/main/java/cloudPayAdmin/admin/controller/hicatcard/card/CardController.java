package cloudPayAdmin.admin.controller.hicatcard.card;

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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.project.m.api.common.biz.resp.BizResp;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCard;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCardService;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.coupon.TCoupon;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantService;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.service.TService;
import cloudPayAdmin.admin.dbapp.vo.hicatcard.VCardCoupon;
import cloudPayAdmin.admin.service.hicatcard.card.CardService;
import cloudPayAdmin.admin.service.hicatcard.card.CardServiceService;
import cloudPayAdmin.admin.service.hicatcard.coupon.CouponService;
import cloudPayAdmin.admin.service.hicatcard.service.ServiceService;
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
@RequestMapping("/hicatcard/card")
public class CardController {
	
	@Autowired
	CardService cardService;
	@Autowired
	CouponService couponService;
	@Autowired
	CardServiceService cardServiceService;
	@Autowired
	ServiceService serviceService;
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	Environment env;
	
	@RequestMapping(value = "/toListPage")
	public String toListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("statusMap", TCard.initStatusMap());
		return "hicatcard/card/card-list";
	}
	
	@RequestMapping("/getCardListData")
	@ResponseBody
	public String getCardListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, VCardCoupon.class, request);
		PageBean<VCardCoupon> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		
		AdminLteDataTableResp<VCardCoupon> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(VCardCoupon.class);
	}
	
	/**
	 * 删除卡片
	 * @param request
	 * @param cardId
	 * @return
	 */
	@RequestMapping("/deleteCard")
	@ResponseBody
	public String deleteCard(HttpServletRequest request,Long cardId) {
		BizResp resp = new BizResp();
		cardService.deleteById(cardId);
		resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 去编辑卡片页面
	 * @param request
	 * @param cardId
	 * @return
	 */
	@RequestMapping("/toCardEditPage")
	public String toCardEditPage(HttpServletRequest request,Long cardId) {
		TCard card = new TCard();
		if(cardId != null) {
			TCard card2 = cardService.findById(cardId);
			if(card2 != null) {
				card = card2;
			}
		}
		
		card.setPictureUlr(ImageUrlUtil.getFullHttpImageUrl(card.getPictureUlr()));
		List<TCoupon> couponList = couponService.findByCardIdAndNull(cardId); 
		List<TService> serviceList = serviceService.findAll();	
		request.setAttribute("serviceList", serviceList);
		request.setAttribute("couponList", couponList);
		request.setAttribute("card", card);
		request.setAttribute("statusMap", TCard.initStatusMap());
		return "hicatcard/card/card-edit";
	}
	
	@RequestMapping("/editCard")
	public String editCard(HttpServletRequest request,Integer effectiveDay,Long cardId,String cardName,String fee,String detail,String couponId,String status,MultipartFile cardPic,Long serviceId,Long orderNum,Integer serviceTime) throws Exception {
		
			if(cardId != null) {  //更新
				TCard oriCard = cardService.findById(cardId);
				
				oriCard.setFee(new BigDecimal(fee).multiply(new BigDecimal("100")).intValue());
				oriCard.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
				oriCard.setCouponId(StringUtils.isBlank(couponId) ? null : Long.parseLong(couponId));
				oriCard.setStatus(status);
				oriCard.setEffectiveDay(effectiveDay);
				oriCard.setCardName(cardName);
				oriCard.setDetail(detail);
				oriCard.setOrderNum(orderNum);
				if(cardPic != null && StringUtils.isNotBlank(cardPic.getOriginalFilename())) {
					String fileName = cardId + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(cardPic.getOriginalFilename());
					// 存入数据库的路径
					String dataBasePath =env.getProperty("image.upload.database.suffix.path") + Constant.CARD_PIC_FILE_PATH + HttpUtil.HTTP_PATH_SEPERATOR + fileName;
					String filePath = env.getProperty("image.upload.base.path") + StringUtils.replaceEach(dataBasePath, new String[]{env.getProperty("image.upload.database.suffix.path"),HttpUtil.HTTP_PATH_SEPERATOR}, new String[]{"",File.separator});
					File file = new File(filePath);
					File parentFile = file.getParentFile();
					if(!parentFile.exists()) {
						parentFile.mkdirs();
					}
					
					FileUtils.copyInputStreamToFile(cardPic.getInputStream(), file);
					oriCard.setPictureUlr(dataBasePath);
				}
				cardService.save(oriCard);
				
			} else {   //保存
				TCard card = new TCard();
				card.setFee(new BigDecimal(fee).multiply(new BigDecimal("100")).intValue());
				card.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
				card.setCouponId(StringUtils.isBlank(couponId) ? null : Long.parseLong(couponId));
				card.setStatus(status);
				card.setCardName(cardName);
				card.setDetail(detail);
				card.setOrderNum(orderNum);
				card.setEffectiveDay(effectiveDay);
				card.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
				card.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
				card.setCreator(AdminUserInfoUtil.getLoginAdminUserName());
				
				List<TCardService> cardServiceList = new ArrayList<TCardService>();
				TCardService tcardService = new TCardService();
				tcardService.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
				tcardService.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
				tcardService.setCardServiceId(serviceId);
				tcardService.setCreator(AdminUserInfoUtil.getLoginAdminUserName());
				tcardService.setServiceTime(serviceTime);
				tcardService.setServiceId(serviceId);
				cardServiceList.add(tcardService);
				
				TCard card2 = cardService.saveCardAndCardService(card, cardServiceList);
				if(cardPic != null && StringUtils.isNotBlank(cardPic.getOriginalFilename())) {
					String fileName = card2.getCardId() + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(cardPic.getOriginalFilename());
					// 存入数据库的路径
					String dataBasePath =env.getProperty("image.upload.database.suffix.path") +  Constant.CARD_PIC_FILE_PATH + HttpUtil.HTTP_PATH_SEPERATOR + fileName;
					String filePath = env.getProperty("image.upload.base.path") + StringUtils.replaceEach(dataBasePath, new String[]{env.getProperty("image.upload.database.suffix.path"),HttpUtil.HTTP_PATH_SEPERATOR}, new String[]{"",File.separator});
					File file = new File(filePath);
					File parentFile = file.getParentFile();
					if(!parentFile.exists()) {
						parentFile.mkdirs();
					}
					
					FileUtils.copyInputStreamToFile(cardPic.getInputStream(), file);
					card2.setPictureUlr(dataBasePath);
					cardService.save(card2);
				}
				
			}
			
		return "redirect:/hicatcard/card/toListPage";
	}
	
	/**
	 * 去增加服务项页面
	 */
	@RequestMapping("/toAddServicePage")
	public String toAddServicePage(HttpServletRequest request,Long cardId) {
		List<TCardService> cardServiceList = cardServiceService.findByCardId(cardId);
		List<Long> serviceIds = new ArrayList<Long>();
		for(TCardService cardService : cardServiceList) {
			Long serviceId = cardService.getServiceId();
			if(serviceId != null) {
				serviceIds.add(serviceId);
			}				
		}
		List<TService> serviceList = serviceService.findByIdNotIn(serviceIds);
		TCard card = cardService.findById(cardId);
		request.setAttribute("card", card);
		request.setAttribute("serviceList", serviceList);
		request.setAttribute("cardId", cardId);
		return "/hicatcard/card/card-service-add";
	}
	
	@RequestMapping("/addService")
	public String addService(HttpServletRequest request,Long cardId,Integer serviceTime,Long serviceId) {
		TCardService cardService = new TCardService();
		cardService.setCardId(cardId);
		cardService.setServiceId(serviceId);
		cardService.setServiceTime(serviceTime);
		cardService.setCreator(AdminUserInfoUtil.getLoginAdminUserName());
		cardService.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
		cardService.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
		cardServiceService.save(cardService);
		return "redirect:/hicatcard/card/toListPage";
	}
	
}
