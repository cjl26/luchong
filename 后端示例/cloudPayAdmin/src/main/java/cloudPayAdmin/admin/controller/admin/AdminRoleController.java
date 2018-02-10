package cloudPayAdmin.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.project.m.api.common.biz.resp.BizResp;

import cloudPayAdmin.admin.controller.BaseController;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminPrivilege;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminRole;
import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayCard;
import cloudPayAdmin.admin.service.admin.AdminPrivilegeService;
import cloudPayAdmin.admin.service.admin.AdminRoleService;
import cloudPayAdmin.constant.BusiError;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/cloudpay/role")
public class AdminRoleController extends BaseController {

	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;

	@Autowired
	AdminPrivilegeService adminPrivilegeService;
	@Autowired
	AdminRoleService adminRoleService;

	private final Logger logger = Logger.getLogger(getClass());

	/**
	 * 跳转到roleList页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toRoleListPage")
	public String toRoleListPage(HttpServletRequest request) {
		return "admin/role-list";
	}

	/**
	 * 跳转到编辑角色页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toRoleEdit")
	public String toRoleEdit(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		TAdminRole role = new TAdminRole();
		if (StringUtils.isNotBlank(roleId)) {
			role = adminRoleService.findById(Long.parseLong(roleId));
		}
		request.setAttribute("role", role);
		return "admin/role-edit";
	}

	/**
	 * 编辑角色
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/roleEditAction")
	public String roleEditAction(HttpServletRequest request, TAdminRole adminRole) {
		adminRoleService.save(adminRole);
		return "redirect:/cloudpay/role/toRoleListPage";
	}

	/**
	 * 跳转到分配权限页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toRolePrivilegePage")
	public String toRolePrivilegePage(HttpServletRequest request, Long roleId) {
		// 本角色分配到的权限
		List<TAdminPrivilege> hasSelected = adminPrivilegeService.findByRoleId(roleId);

		// 本角色未分配到的权限
		List<Long> notIds = new ArrayList<Long>();
		for (TAdminPrivilege ap : hasSelected) {
			notIds.add(ap.getId());
		}
		List<TAdminPrivilege> notSelected = adminPrivilegeService.findByIdsNotIn(notIds);
		request.setAttribute("hasSelected", hasSelected);
		request.setAttribute("notSelected", notSelected);
		request.setAttribute("roleId", roleId);
		return "/admin/role-privilege-edit";
	}

	/**
	 * 查询角色列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRoleListData")
	@ResponseBody
	public String getRoleListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TAdminRole.class, request);
		PageBean<TCloudpayCard> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		AdminLteDataTableResp<TCloudpayCard> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,
				request);
		return resp.toAdminLteJsonString(TCloudpayCard.class);
	}

	/**
	 * 保存新分配后的权限
	 * 
	 * @param request
	 * @param privilegeIds
	 * @return
	 */
	@RequestMapping(value = "/savePrivileges", method = RequestMethod.POST)
	@ResponseBody
	public String savePrivileges(HttpServletRequest request, @RequestParam("privilegeIds[]") List<Long> privilegeIds,
			Long roleId) {
		BizResp resp = new BizResp();
		try {
			adminRoleService.changeRolePrivilege(privilegeIds, roleId);
			resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resp.setResult_code(BusiError.ERR_CODE_SAVE_ROLEPRIVILEGE_FAIL);
			resp.setError_message(BusiError.ERR_MSG_SAVE_ROLEPRIVILEGE_FAIL);
		}
		return JSONObject.toJSONString(resp);
	}
}
