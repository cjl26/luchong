package cloudPayAdmin.admin.controller.hicatcard.merchant;

import java.math.BigDecimal;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.project.m.api.common.biz.resp.BizResp;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchant;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantService;
import cloudPayAdmin.admin.dbapp.vo.hicatcard.VMerchantService;
import cloudPayAdmin.admin.service.hicatcard.merchant.MerchantServiceService;
import cloudPayAdmin.constant.BusiError;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/hicatcard/merchantService")
public class MerchantServiceController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	MerchantServiceService merchantServiceService;
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	Environment env;
	
	@RequestMapping(value = "/toListPage")
	public String toListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("statusMap", TMerchantService.initStatusMap());
		return "hicatcard/merchant/merchant-service-list";
	}
	
	
	@RequestMapping("/getMerchantServiceListData")
	@ResponseBody
	public String getMerchantServiceListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, VMerchantService.class, request);
		PageBean<TMerchant> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		
		AdminLteDataTableResp<TMerchant> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(VMerchantService.class);
	}
	
	@RequestMapping("/deleteMerchantService")
	@ResponseBody
	public String deleteMerchantService(HttpServletRequest request,Long merchantServiceId) {
		BizResp resp = new BizResp();
		merchantServiceService.deletById(merchantServiceId);
		resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		return JSON.toJSONString(resp);
	}
	
	@RequestMapping("/toMerchantServiceEditPage")
	public String toMerchantServiceEditPage(HttpServletRequest request,Long merchantServiceId) {
		TMerchantService merchantService = new TMerchantService();
		if(merchantServiceId != null) {
			TMerchantService merchantService2 = merchantServiceService.findById(merchantServiceId);
			if(merchantService2 != null) {
				merchantService = merchantService2;
			}
		}
		request.setAttribute("statusMap", TMerchantService.initStatusMap());
		request.setAttribute("merchantService",merchantService);
		return "hicatcard/merchant/merchant-service-edit";
	}
	
	/**
	 * 更新商户服务项
	 * @return
	 */
	@RequestMapping("/editMerchantService")
	public String editMerchantService(HttpServletRequest request,Long merchantServiceId,String fee,String status) {
		
		if(merchantServiceId != null) {
			TMerchantService merchantService = merchantServiceService.findById(merchantServiceId);
			merchantService.setFee(new BigDecimal(fee).multiply(new BigDecimal("100")).intValue());
			merchantService.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			merchantService.setStatus(status);
			merchantServiceService.update(merchantService);
		}
		
		return "redirect:/hicatcard/merchantService/toListPage";
	}
}
