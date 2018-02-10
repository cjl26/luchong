package cloudPayAdmin.admin.controller.merchant;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudPayAdmin.admin.controller.BaseController;
import cloudPayAdmin.admin.dbapp.entity.merchant.TMerchantAcctTrans;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/cloudpay/merchant/acctTrans")
public class MerchantAcctTransController extends BaseController {
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;
	
	@PersistenceContext(unitName="entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	Environment env;
	
	/**
	 * 跳转到blackHisList页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String listPage(HttpServletRequest request) {
		request.setAttribute("flagMap", TMerchantAcctTrans.initFlagMap());
		request.setAttribute("tradeTypeMap", TMerchantAcctTrans.initTradeTypeMap());
		return "merchant/acct-trans-list";
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public String listData(HttpServletRequest request) {
		Map<String ,Object> paramMap = WebUtil.getSearchParam(request);
		paramMap.put("EQ_STRING_relatedAppid", env.getProperty("cloudPayCard.appId"));
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DEFAULT_DATE_PATTERN);
		 //需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TMerchantAcctTrans.class,request);
		PageBean<TMerchantAcctTrans> merchantAcctTransPage =  hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		AdminLteDataTableResp<TMerchantAcctTrans> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(merchantAcctTransPage, request);
		System.out.println("TMerchantAcctTrans List =  " + resp.toAdminLteJsonString(TMerchantAcctTrans.class));
		return resp.toAdminLteJsonString(TMerchantAcctTrans.class);
	}
}
