package cloudPayAdmin.admin.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.m.api.common.biz.resp.BizResp;

import cloudPayAdmin.admin.controller.BaseController;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminRole;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminUser;
import cloudPayAdmin.admin.service.admin.AdminRoleService;
import cloudPayAdmin.admin.service.admin.AdminUserService;
import cloudPayAdmin.constant.BusiError;
import cloudPayAdmin.constant.Constant;
import cloudPayAdmin.constant.SessionKeyValue;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.CommonConvertor;
import cloudPayAdmin.util.MD5Util;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

/**
 * 云平台管理用户controller
 * 
 * @author hyj
 *
 */

@Controller
@RequestMapping("/cloudpay/admin")
public class AdminUserController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());

	public final static String RESETPASSWORDFLAG = "resetPasswordFlag";

	@Autowired
	AdminUserService adminUserService;

	@Autowired
	AdminRoleService adminRoleService;

	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;

	@Autowired
	Environment env;

	@RequestMapping(value = "/toMainPage")
	public String toMainPage(HttpServletRequest request) {
		return "admin/main";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String loginPage(Map<String, Object> model) {
		return "admin/login";
	}

	/**
	 * 跳转到重置密码页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toResetPasswordPage")
	public String toResetPasswordPage(HttpServletRequest request) {
		return "admin/password-reset";
	}

	// , method = RequestMethod.GET
	@RequestMapping(value = "/user/logout")
	public String logoutPage(HttpServletRequest request, Map<String, Object> model) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		if (request.getAttribute(RESETPASSWORDFLAG) != null) {
			return "redirect:/cloudpay/admin/login?resetPassword=true";
		}
		return "redirect:/cloudpay/admin/login";
	}

	@RequestMapping(value = "/user/list")
	public String userListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("statusMap", TAdminUser.initStatusMap());
		return "admin/user-list";
	}

	@RequestMapping(value = "/user/add")
	public String userAddPage(HttpServletRequest request, Map<String, Object> model) {
		return "admin/user-add";
	}

	@RequestMapping(value = "/user/edit")
	public String userEditPage(HttpServletRequest request, Map<String, Object> model, String id) {
		// System.out.println("id = " + request.getAttribute("id"));
		// System.out.println("id = " + id);
		Long idLong = Long.parseLong(id);
		TAdminUser adminUser = adminUserService.findUserById(idLong);
		adminUser.setPassword("");
		model.put("adminUser", JSON.toJSONString(adminUser));
		return "admin/user-edit";
	}

	@RequestMapping(value = "/user/listData")
	@ResponseBody
	public String userListData(HttpServletRequest request, Map<String, Object> model) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DEFAULT_DATE_PATTERN);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TAdminUser.class, request);
		PageBean<TAdminUser> adminUserPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		AdminLteDataTableResp<TAdminUser> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(adminUserPage, request);
		List<TAdminUser> adminUserList = resp.getData();
		for (TAdminUser adminUser : adminUserList) {
			adminUser.setPassword("");
		}
		resp.setData(adminUserList);
		// System.out.println("TAdminUser List = " +
		// resp.toAdminLteJsonString(TAdminUser.class));
		return resp.toAdminLteJsonString(TAdminUser.class);
	}

	@RequestMapping(value = "/user/loginAction", method = RequestMethod.POST)
	// @ResponseBody
	public String loginAction(HttpServletRequest request, Map<String, Object> model, String username, String password,
			String validateCode) {
		BizResp response = new BizResp();

		// logger.info("username = " + username);
		// logger.info("password = " + password);

		// 先校验图片验证码
		Object code = request.getSession().getAttribute("validateCode");
		if (code == null) {
			response.setResult_code(BusiError.ERR_CODE_IMG_VALIDATE_OVERDUE);
			response.setError_message(BusiError.ERR_MSG_IMG_VALIDATE_OVERDUE);
			model.put("loginResponse", JSON.toJSONString(response));
			return loginPage(model);
		} else {
			String codeStr = (String) code;
			if (!StringUtils.equalsIgnoreCase(codeStr, validateCode)) {
				response.setResult_code(BusiError.ERR_CODE_IMG_VALIDATE_ERROR);
				response.setError_message(BusiError.ERR_MSG_IMG_VALIDATE_ERROR);
				model.put("loginResponse", JSON.toJSONString(response));
				return loginPage(model);
			}
		}

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
			model.put("loginResponse", JSON.toJSONString(response));
			return loginPage(model);
		}

		try {
			Subject current = SecurityUtils.getSubject();
			if (!current.isAuthenticated()) {
				password = MD5Util.MD5Encode(password, Constant.CHARSET_UTF);
				password = password.toUpperCase();
				System.out.println("usper password = " + password);
				UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
				current.login(token);
				Session session = current.getSession();
				TAdminUser adminUser = adminUserService.findAvailableUserByUsernameAndPassword(username, password);
				session.setAttribute(SessionKeyValue.KEY_ADMIN_USER, adminUser);
			}
			// 暂定主页
			return "redirect:/cloudpay/admin/toMainPage";
		} catch (Exception e) {
			logger.info(username + ":因账号或密码错误登录失败");
			response.setResult_code(BusiError.ERR_CODE_USER_NONE);
			response.setError_message(BusiError.ERR_MSG_USER_NONE);
			model.put("loginResponse", JSON.toJSONString(response));
			model.put("loginFailMsg", "因账号或密码错误登录失败");
			return loginPage(model);
		}

	}

	/**
	 * 用户名校验
	 * 
	 * @param request
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/user/usernameValidation", method = RequestMethod.POST)
	@ResponseBody
	public boolean usernameValidation(HttpServletRequest request, String username) {
		System.out.println("usernameValidation userName = " + username);
		// 查看用户名有无被用过
		TAdminUser adminUserOri = adminUserService.findUserByUsername(username);
		if (adminUserOri != null) {
			return false;
		}
		return true;
	}

	/**
	 * 通过接口添加用户
	 * 
	 * @param request
	 * @param model
	 * @param username
	 * @param account
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/user/addAction", method = RequestMethod.POST)
	@ResponseBody
	public String addAction(HttpServletRequest request, Map<String, Object> model, String username, String account,
			String phone) {

		// System.out.println("username = " + username);
		// System.out.println("account = " + account);
		// System.out.println("phone = " + phone);

		BizResp response = new BizResp();
		if (StringUtils.isEmpty(username)) {
			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
			return JSON.toJSONString(response);
		}

		String password = env.getProperty("default.password");
		if (password == null || StringUtils.isEmpty(password)) {
			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
			return JSON.toJSONString(response);
		}

		account = CommonConvertor.convertStringAvoidNull(account);
		phone = CommonConvertor.convertStringAvoidNull(phone);

		// 查看用户名有无被用过
		if (!usernameValidation(request, username)) {
			response.setResult_code(BusiError.ERR_CODE_USER_EXIST);
			response.setError_message(BusiError.ERR_MSG_USER_EXIST);
			return JSON.toJSONString(response);
		}

		TAdminUser adminUser = new TAdminUser();
		adminUser.setUserName(username);
		adminUser.setAccount(account);
		password = MD5Util.MD5Encode(password, Constant.CHARSET_UTF);
		adminUser.setPassword(password.toUpperCase());
		adminUser.setPhone(phone);
		adminUser.setEmail("");
		adminUser.setStatus(TAdminUser.USER_STATUS_IN_USE);
		adminUser.setCreateTime(new Date());
		adminUser.setUpdateTime(new Date());
		adminUser.setRemark("");
		adminUserService.saveAdminUser(adminUser);
		response.setResult_code(BusiError.ERR_CODE_SUCCESS);
		response.setError_message("");
		return JSON.toJSONString(response);
	}

	@RequestMapping(value = "/user/editAction", method = RequestMethod.POST)
	@ResponseBody
	public String editAction(HttpServletRequest request, Map<String, Object> model, String id, String account,
			String phone) {
		// System.out.println("username = " + username);
		System.out.println("id = " + id);
		System.out.println("account = " + account);
		System.out.println("phone = " + phone);

		BizResp response = new BizResp();
		long idLong = -1l;
		try {
			idLong = Long.parseLong(id);
		} catch (Exception e) {
			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
			return JSON.toJSONString(response);
		}

		TAdminUser adminUser = adminUserService.findUserById(idLong);
		if (adminUser == null) {
			response.setResult_code(BusiError.ERR_CODE_USER_NONE);
			response.setError_message(BusiError.ERR_MSG_USER_NONE);
			return JSON.toJSONString(response);
		}

		adminUser.setAccount(account);
		adminUser.setPhone(phone);
		adminUser.setUpdateTime(new Date());
		adminUserService.saveAdminUser(adminUser);

		response.setResult_code(BusiError.ERR_CODE_SUCCESS);
		response.setError_message("");
		return JSON.toJSONString(response);
	}

	@RequestMapping(value = "/user/resetPasswordAction", method = RequestMethod.POST)
	@ResponseBody
	public String resetPasswordAction(HttpServletRequest request, Map<String, Object> model, String id) {
		// System.out.println("id = " + id);
		BizResp response = new BizResp();
		long idLong = -1l;
		try {
			idLong = Long.parseLong(id);
		} catch (Exception e) {
			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
			return JSON.toJSONString(response);
		}

		String password = env.getProperty("default.password");
		if (password == null || StringUtils.isEmpty(password)) {
			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
			return JSON.toJSONString(response);
		}

		TAdminUser adminUser = adminUserService.findUserById(idLong);
		if (adminUser == null) {
			response.setResult_code(BusiError.ERR_CODE_USER_NONE);
			response.setError_message(BusiError.ERR_MSG_USER_NONE);
			return JSON.toJSONString(response);
		}

		adminUser.setPassword(password);
		adminUser.setUpdateTime(new Date());
		adminUserService.saveAdminUser(adminUser);

		response.setResult_code(BusiError.ERR_CODE_SUCCESS);
		response.setError_message("");
		return JSON.toJSONString(response);
	}

	/**
	 * 设置为可用
	 * 
	 * @param request
	 * @param model
	 * @param recordIdList
	 * @return
	 */
	@RequestMapping(value = "/user/statusToEffectiveAction", method = RequestMethod.POST)
	@ResponseBody
	public String statusToEffectiveAction(HttpServletRequest request, Map<String, Object> model,
			@RequestParam("recordIds[]") List<Long> recordIdList) {
		// System.out.println("statusToEffectiveAction");
		BizResp resp = new BizResp();
		try {
			List<TAdminUser> adminUserList = adminUserService.findByIdIn(recordIdList);
			for (TAdminUser adminUser : adminUserList) {
				adminUser.setStatus(TAdminUser.USER_STATUS_IN_USE);
				adminUser.setUpdateTime(new Date());
			}
			adminUserService.saveAdminUserList(adminUserList);
			resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			resp.setResult_code(BusiError.ERR_CODE_UNEFFECTIVE_STATUS_FAIL);
			resp.setError_message(BusiError.ERR_MSG_UNEFFECTIVE_STATUS_FAIL);
		}
		return JSONObject.toJSONString(resp);
	}

	/**
	 * 设置为不可用
	 * 
	 * @param request
	 * @param model
	 * @param recordIdList
	 * @return
	 */
	@RequestMapping(value = "/user/statusToUneffectiveAction", method = RequestMethod.POST)
	@ResponseBody
	public String statusToUneffectiveAction(HttpServletRequest request, Map<String, Object> model,
			@RequestParam("recordIds[]") List<Long> recordIdList) {
		// System.out.println("statusToUneffectiveAction");
		BizResp resp = new BizResp();
		try {
			List<TAdminUser> adminUserList = adminUserService.findByIdIn(recordIdList);
			for (TAdminUser adminUser : adminUserList) {
				adminUser.setStatus(TAdminUser.USER_STATUS_NOT_USE);
				adminUser.setUpdateTime(new Date());
			}
			adminUserService.saveAdminUserList(adminUserList);
			resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			resp.setResult_code(BusiError.ERR_CODE_UNEFFECTIVE_STATUS_FAIL);
			resp.setError_message(BusiError.ERR_MSG_UNEFFECTIVE_STATUS_FAIL);
		}
		return JSONObject.toJSONString(resp);
	}

	@RequestMapping("/toUserRolePage")
	public String toUserRolePage(HttpServletRequest request, Long userId) {
		// 已经分配到的角色
		List<TAdminRole> hasSelected = adminRoleService.findByUserId(userId);

		List<Long> notIds = new ArrayList<Long>();
		for (TAdminRole ar : hasSelected) {
			notIds.add(ar.getId());
		}

		// 本用户未分配到的角色
		List<TAdminRole> notSelected = adminRoleService.findByIdsNotIn(notIds);

		request.setAttribute("hasSelected", hasSelected);
		request.setAttribute("notSelected", notSelected);
		request.setAttribute("userId", userId);
		return "/admin/user-role-edit";
	}

	@RequestMapping(value = "/saveRoles", method = RequestMethod.POST)
	@ResponseBody
	public String saveRoles(HttpServletRequest request, @RequestParam("roleIds[]") List<Long> roleIds, Long userId) {
		BizResp resp = new BizResp();
		try {
			adminUserService.changeUserRole(roleIds, userId);
			resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resp.setResult_code(BusiError.ERR_CODE_SAVE_USERROLE_FAIL);
			resp.setError_message(BusiError.ERR_MSG_SAVE_USERROLE_FAIL);
		}
		return JSONObject.toJSONString(resp);
	}

	/**
	 * 校验重置密码时，输入的原密码是否正确，
	 * 
	 * @param oldPassword
	 *            md5加密过的密码
	 * @return
	 */
	@RequestMapping(value = "/validateOldPassword")
	@ResponseBody
	public boolean validateOldPassword(String oldPassword) {
		boolean isRight = Boolean.FALSE;
		Subject current = SecurityUtils.getSubject();
		Object obj = current.getSession().getAttribute(SessionKeyValue.KEY_ADMIN_USER);
		if (obj != null) {
			TAdminUser adminUser = (TAdminUser) obj;
			if (StringUtils.equals(oldPassword.toUpperCase(), adminUser.getPassword())) {
				isRight = Boolean.TRUE;
			}
		}
		return isRight;
	}

	/**
	 * 充值密码接口
	 * 
	 * @param oldPassword
	 *            md5加密后的原密码
	 * @param newPassword
	 *            md5加密后的新密码
	 * @param confirmPassword
	 *            md5加密后的确认密码
	 * @return
	 */
	@RequestMapping(value = "/resetPasswordAction", method = RequestMethod.POST)
	public String resetPasswordAction(String md5NewPassword, HttpServletRequest request) {

		Subject current = SecurityUtils.getSubject();
		Object obj = current.getSession().getAttribute(SessionKeyValue.KEY_ADMIN_USER);
		if (obj != null) {
			TAdminUser adminUser = (TAdminUser) obj;
			adminUser.setPassword(md5NewPassword);
			adminUser.setUpdateTime(new Date());
			adminUserService.saveAdminUser(adminUser);
		}

		// 充值密码后跳转到登出的action，并重新登录
		request.setAttribute(RESETPASSWORDFLAG, Boolean.TRUE);
		return "forward:/cloudpay/admin/user/logout";
	}

}
