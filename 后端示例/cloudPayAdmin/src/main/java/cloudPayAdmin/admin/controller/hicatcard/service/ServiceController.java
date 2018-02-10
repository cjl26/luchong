package cloudPayAdmin.admin.controller.hicatcard.service;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.project.m.api.common.biz.resp.BizResp;
import cloudPayAdmin.admin.controller.BaseController;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminMenu;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.service.TService;
import cloudPayAdmin.admin.service.hicatcard.service.ServiceService;
import cloudPayAdmin.constant.BusiError;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/hicatcard/service")
public class ServiceController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	ServiceService serviceService;
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;

	@RequestMapping(value = "/toListPage")
	public String toListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("statusMap", TService.initStatusMap());
		return "hicatcard/service/service-list";
	}
	
	@RequestMapping("/getServiceListData")
	@ResponseBody
	public String getServiceListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TService.class, request);
		PageBean<TService> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		AdminLteDataTableResp<TService> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(TService.class);
	}
	
	@RequestMapping("/deleteService")
	@ResponseBody
	public String deleteService(HttpServletRequest request,Long serviceId) {
		BizResp resp = new BizResp();
		serviceService.deleteById(serviceId);
		resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 去编辑服务项页面
	 * @param request
	 * @param serviceId
	 * @return
	 */
	@RequestMapping("/toServiceEditPage")
	public String toServiceEditPage(HttpServletRequest request,Long serviceId) {
		TService service = new TService();
		if (serviceId != null) {
			service = serviceService.findById(serviceId);
		}
		request.setAttribute("statusMap", TService.initStatusMap());
		request.setAttribute("service", service);
		return "hicatcard/service/service-edit";
	}
	
	@RequestMapping("/editService")
	public String editService(HttpServletRequest request,TService service) {
		if(service != null) {
			
			serviceService.saveService(service);
		}
		
		return "redirect:/hicatcard/service/toListPage";
	}

}
