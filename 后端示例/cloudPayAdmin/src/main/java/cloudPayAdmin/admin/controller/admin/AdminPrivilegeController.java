package cloudPayAdmin.admin.controller.admin;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudPayAdmin.admin.controller.BaseController;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminPrivilege;
import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayCard;
import cloudPayAdmin.admin.service.admin.AdminPrivilegeService;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@RequestMapping("/cloudpay/privilege")
@Controller
public class AdminPrivilegeController extends BaseController {
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;
	
	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	AdminPrivilegeService adminPrivilegeService;
	
	private final Logger logger = Logger.getLogger(getClass());
	
	/**
	 * 跳转到privilegeList页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toPrivilegeListPage")
	public String toPrivilegeListPage(HttpServletRequest request) {
		return "admin/privilege-list";
	}
	
	/**
	 * 保存编辑的权限信息
	 * @return
	 */
	@RequestMapping("/privilegeEditAction")
	public String privilegeEditAction(HttpServletRequest request,TAdminPrivilege adminPrivilege) {
		adminPrivilegeService.save(adminPrivilege);
		return "redirect:/cloudpay/privilege/toPrivilegeListPage";
	}
	
	/**
	 * 获得权限列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPrivilegeListData")
	@ResponseBody
	public String getPrivilegeListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TAdminPrivilege.class, request);
		PageBean<TCloudpayCard> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		AdminLteDataTableResp<TCloudpayCard> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(TAdminPrivilege.class);
	}
	
	/**
	 * 跳转到 编辑权限页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toPrivilegeEditPage")
	public String toPrivilegeEditPage(HttpServletRequest request,Long privilegeId) {
		TAdminPrivilege privilege = new TAdminPrivilege();
		if(privilegeId != null) {
			privilege = adminPrivilegeService.findById(privilegeId);
		}
		request.setAttribute("privilege", privilege);
		return "admin/privilege-edit";
	}
	
	/**
	 * 删除权限
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletePrivilegeAction")
	public String deletePrivilegeAction(HttpServletRequest request,Long privilegeId) {
		adminPrivilegeService.deletePrivilege(privilegeId);
		return "redirect:/cloudpay/privilege/toPrivilegeListPage";
	}
}
