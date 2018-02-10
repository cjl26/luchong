package cloudPayAdmin.admin.controller.hicatcard.merchant;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.jfinal.kit.HttpKit;
import com.project.m.api.common.biz.resp.BizResp;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchant;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantService;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantSettle;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantSettleService;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.service.TService;
import cloudPayAdmin.admin.entity.http.MerchantSettleResp;
import cloudPayAdmin.admin.entity.http.MerchantSettleResult;
import cloudPayAdmin.admin.service.hicatcard.merchant.MerchantService;
import cloudPayAdmin.constant.BusiError;
import cloudPayAdmin.constant.Constant;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.HttpUtil;
import cloudPayAdmin.util.IdGenerator;
import cloudPayAdmin.util.ImageUrlUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/hicatcard/merchant")
public class MerchantController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	MerchantService merchantService;
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	Environment env;
	
	@RequestMapping(value = "/toListPage")
	public String toListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("statusMap", TMerchant.initStatusMap());
		return "hicatcard/merchant/merchant-list";
	}
	
	@RequestMapping("/getMerchantListData")
	@ResponseBody
	public String getMerchantListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TMerchant.class, request);
		PageBean<TMerchant> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		
		//为图片地址加上 http 服务器前缀
		for(TMerchant merchant : cloudpayCardPage.getContent()) {
			merchant.setPictureUrl(ImageUrlUtil.getFullHttpImageUrl(merchant.getPictureUrl()));
		}
		
		AdminLteDataTableResp<TMerchant> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(TMerchant.class);
	}
	
	/**
	 * 删除商户
	 */
	@RequestMapping("/deleteMerchant")
	@ResponseBody
	public String deleteMerchant(HttpServletRequest request,Long merchantId) {
		BizResp resp = new BizResp();
		merchantService.deleteById(merchantId);
		resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 去编辑商户页面
	 */
	@RequestMapping("/toMerchantEditPage")
	public String toMerchantEditPage(HttpServletRequest request,Long merchantId) {
		
		TMerchant merchant = new TMerchant();
		if(merchantId != null) {
			merchant =	merchantService.findById(merchantId);
		}
		
		//为图片地址加上 http 服务器前缀
		merchant.setPictureUrl(ImageUrlUtil.getFullHttpImageUrl(merchant.getPictureUrl()));
		merchant.setDetailPictureUrl(ImageUrlUtil.getFullHttpImageUrl(merchant.getDetailPictureUrl()));
		
		request.setAttribute("statusMap", TMerchant.initStatusMap());
		request.setAttribute("merchant", merchant);
		
		return "hicatcard/merchant/merchant-edit";
	}
	
	/**
	 * 修改/保存商户信息
	 * @param request
	 * @param merchant
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/editMerchant")
	public String editMerchant(HttpServletRequest request,TMerchant merchant, MultipartFile merchantPic,MultipartFile merchantDetailPic) throws Exception {
		if(merchant != null) {
			String merchantNumber = "";
			
			if(StringUtils.isBlank(merchant.getMerchantNumber())) {
				merchantNumber = IdGenerator.generateMerchantId(IdGenerator.ORDERID_PREFIX_MERCHANT, "00", "0000");
				merchant.setMerchantNumber(merchantNumber);
			} else {
				merchantNumber = merchant.getMerchantNumber();
			}
			
			//如果上传图片不等于空
			if(merchantPic != null && StringUtils.isNotBlank(merchantPic.getOriginalFilename())) {
				String fileName = merchantNumber + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(merchantPic.getOriginalFilename());
				// 存入数据库的路径
				String dataBasePath = env.getProperty("image.upload.database.suffix.path") + Constant.MERCHANT_PIC_FILE_PATH + HttpUtil.HTTP_PATH_SEPERATOR + fileName;
				String filePath = env.getProperty("image.upload.base.path") + StringUtils.replaceEach(dataBasePath, new String[]{env.getProperty("image.upload.database.suffix.path"),HttpUtil.HTTP_PATH_SEPERATOR}, new String[]{"",File.separator});
				File file = new File(filePath);
				File parentFile = file.getParentFile();
				if(!parentFile.exists()) {
					parentFile.mkdirs();
				}
				FileUtils.copyInputStreamToFile(merchantPic.getInputStream(), file);
				merchant.setPictureUrl(dataBasePath);
				
			}
			
			//如果商户详情图片不为空
			if(merchantDetailPic != null && StringUtils.isNotBlank(merchantDetailPic.getOriginalFilename())) {
				String fileName = merchantNumber + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(merchantDetailPic.getOriginalFilename());
				// 存入数据库的路径
				String dataBasePath = env.getProperty("image.upload.database.suffix.path") + Constant.MERCHANT_DETAIL_PIC_FILE_PATH + HttpUtil.HTTP_PATH_SEPERATOR + fileName;
				String filePath = env.getProperty("image.upload.base.path") + StringUtils.replaceEach(dataBasePath, new String[]{env.getProperty("image.upload.database.suffix.path"),HttpUtil.HTTP_PATH_SEPERATOR}, new String[]{"",File.separator});
				File file = new File(filePath);
				File parentFile = file.getParentFile();
				if(!parentFile.exists()) {
					parentFile.mkdirs();
				}
				FileUtils.copyInputStreamToFile(merchantDetailPic.getInputStream(), file);
				merchant.setDetailPictureUrl(dataBasePath);
			}
			
			merchantService.saveMerchant(merchant);
		}
		
		return "redirect:/hicatcard/merchant/toListPage";
	}
	
	/**
	 * 跳转到增加服务项页面
	 * @param request
	 * @param merchantId
	 * @return
	 */
	@RequestMapping("/toAddServicePage")
	public String toAddServicePage(HttpServletRequest request,Long merchantId) {
		TMerchant merchant = merchantService.findById(merchantId);
		List<TService> notSelectedList = merchantService.findNotSelectSrviceById(merchantId);
		request.setAttribute("notSelectedList", notSelectedList);
		request.setAttribute("merchant", merchant);
		request.setAttribute("statusMap", TMerchantService.initStatusMap());
		return "/hicatcard/merchant/merchant-service-add";
	}
	
	/**
	 * 为商户增加服务项
	 * @param request
	 * @param merchantId
	 * @param serviceId
	 * @param fee
	 * @return
	 */
	@RequestMapping("/addService")
	public String addService(HttpServletRequest request,Long merchantId,Long serviceId,String fee,String status) {
		BigDecimal decFee = new BigDecimal(fee);
		Integer intFee = decFee.multiply(new BigDecimal("100")).intValue();
		merchantService.saveMerchantService(merchantId, serviceId, intFee, status);		
		return "redirect:/hicatcard/merchant/toListPage";
	}
	
	
	/**
	 * 获得商户结算数据
	 */
	@RequestMapping("/getMerchantSettleData")
	@ResponseBody
	public MerchantSettleResp getMerchantSettleData(Long merchantId) {
	//	MerchantSettleResp resp = merchantService.getMerchantSettleData(merchantId);
		//return resp;
		return merchantService.getMerchantSettleData(merchantId);
	}
	
	/**
	 * 商户结算请求
	 */
	@RequestMapping("/dealMerchantSettle")
	@ResponseBody
	public String dealMerchantSettle(HttpServletRequest request) {
		
		BizResp resp = new BizResp();
		
		String reqJson = HttpUtil.readData(request);
		MerchantSettleResp settleData = JSON.parseObject(reqJson, MerchantSettleResp.class);
		
		List<MerchantSettleResult> merchantSettleResultList = settleData.getMerchantSettleResultList();
		//如果结算记录未空
		if(CollectionUtils.isEmpty(merchantSettleResultList)) {
			resp.setResult_code(BusiError.ERR_CODE_MERCHANT_SETTLE_NO_RESULT);
			resp.setError_message(BusiError.ERR_MSG_MERCHANT_SETTLE_NO_RESULT);
		    return JSON.toJSONString(resp);
		}
		
		TMerchantSettle merchantSettle = new TMerchantSettle();
		try {
			merchantSettle.setSettleStartTime(TimeUtil.transferDateFormat(settleData.getSettleStartTime(), TimeUtil.DATE_PATTERN_WITH_SLASH, TimeUtil.DATE_PATTERN_NOSEPARTOR));
		} catch (Exception e) {
			merchantSettle.setSettleStartTime(settleData.getSettleStartTime());
		}
		
		merchantSettle.setSettleEndTime(TimeUtil.transferDateFormat(settleData.getSettleEndTime(), TimeUtil.DATE_PATTERN_WITH_SLASH, TimeUtil.DATE_PATTERN_NOSEPARTOR));
		merchantSettle.setSettleFee(new BigDecimal(settleData.getSettleFee()).multiply(new BigDecimal("100")).intValue());
		merchantSettle.setMerchantId(settleData.getMerchantId());
		merchantSettle.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
		merchantSettle.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
		
		List<TMerchantSettleService> merchantSettleServiceList = new ArrayList<TMerchantSettleService>();
		
		for(MerchantSettleResult merchantSettleResult : merchantSettleResultList) {
			TMerchantSettleService merchantSettleService = new TMerchantSettleService();
			merchantSettleService.setTransactionTime(Integer.parseInt(merchantSettleResult.getTransactionTime()));
			//merchantSettleService.setTransactionFee(Integer.parseInt(merchantSettleResult.getTranscationFee()));
			merchantSettleService.setTransactionFee(new BigDecimal(merchantSettleResult.getTranscationFee()).multiply(new BigDecimal("100")).intValue());
			merchantSettleService.setServiceId(merchantSettleResult.getServiceId());
			merchantSettleService.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			merchantSettleService.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			merchantSettleServiceList.add(merchantSettleService);
		}
		
		merchantService.saveMerchantSettleData(merchantSettle, merchantSettleServiceList,settleData.getTransactionList());
		
		resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		resp.setError_message("");
	    return JSON.toJSONString(resp);
	}
	
}
