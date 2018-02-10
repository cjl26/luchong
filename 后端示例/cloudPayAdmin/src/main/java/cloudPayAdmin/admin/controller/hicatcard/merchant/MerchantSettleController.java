package cloudPayAdmin.admin.controller.hicatcard.merchant;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchant;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantSettle;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantSettleService;
import cloudPayAdmin.admin.dbapp.vo.hicatcard.MerchantSettleServiceVo;
import cloudPayAdmin.admin.dbapp.vo.hicatcard.MerchantSettleVo;
import cloudPayAdmin.admin.service.hicatcard.merchant.MerchantService;
import cloudPayAdmin.admin.service.hicatcard.service.ServiceService;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.ImageUrlUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

/**
 * 商户结算 controller
 * @author hyj
 *
 */
@Controller
@RequestMapping("/hicatcard/merchantSettle")
public class MerchantSettleController {
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	Environment env;
	
	@Autowired
	MerchantService merchantService;
	@Autowired
	ServiceService serviceService;
	
	@RequestMapping(value = "/toListPage")
	public String toListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("statusMap", TMerchant.initStatusMap());
		return "hicatcard/merchant/merchant-settle-list";
	}
	
	@RequestMapping("/getMerchantSettleListData")
	@ResponseBody
	public String getMerchantListData(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TMerchantSettle.class, request);
		PageBean<TMerchantSettle> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		
		List<MerchantSettleVo> voList = new ArrayList<MerchantSettleVo>();
		
		for(TMerchantSettle merchantSettlte : cloudpayCardPage.getContent()) {
			MerchantSettleVo vo = new MerchantSettleVo();
			PropertyUtils.copyProperties(vo, merchantSettlte);
			vo.setMerchantName(merchantService.findById(merchantSettlte.getMerchantId()).getName());
			List<TMerchantSettleService> merchantSettleServiceList = merchantService.findMerchantSettleServiceByMerchantSettleId(merchantSettlte.getMerchantSettleId());
			List<MerchantSettleServiceVo> mssvoList = new ArrayList<MerchantSettleServiceVo>();
			for(TMerchantSettleService mechantSettleService : merchantSettleServiceList) {
				MerchantSettleServiceVo mssvo = new MerchantSettleServiceVo();
				PropertyUtils.copyProperties(mssvo, mechantSettleService);
				mssvo.setServiceName(serviceService.findById(mechantSettleService.getServiceId()).getServiceName());
				mssvoList.add(mssvo);
			}
			vo.setMerchantSettleServiceList(mssvoList);
			voList.add(vo);
		}
	 
		PageBean<MerchantSettleVo> merchantSettleVoPage = new PageBean<MerchantSettleVo>(voList,cloudpayCardPage.getTotalPage(),cloudpayCardPage.getTotalCount(),cloudpayCardPage.getCurrentPage(),cloudpayCardPage.getPageSize());
		
		AdminLteDataTableResp<MerchantSettleVo> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(merchantSettleVoPage,request);
		return resp.toAdminLteJsonString(MerchantSettleVo.class);
	}
}
