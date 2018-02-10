package cloudPayAdmin.admin.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
//import com.project.m.api.common.biz.resp.BizResp;
import com.project.m.api.common.biz.resp.BizResp;

import cloudPayAdmin.admin.controller.BaseController;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminMenu;
import cloudPayAdmin.admin.service.admin.AdminMenuService;
import cloudPayAdmin.constant.BusiError;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

/**
 * 菜单controller
 * 
 * @author hyj
 *
 */
@Controller
@RequestMapping("/cloudpay/menu")
public class AdminMenuController extends BaseController  {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	AdminMenuService adminMenuService;

	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	//存放菜单列表，用于缓存
	private static List<TAdminMenu> list = new ArrayList<TAdminMenu>();
	

	/**
	 * 菜单列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String menuListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("typeMap", TAdminMenu.initTypeMap());
		return "admin/menu-list";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String menuEditPage(HttpServletRequest request, Map<String, Object> model, String menuId) {
		// logger.info("menuEditPage = " + menuId);
		request.setAttribute("typeMap", TAdminMenu.initTypeMap());
		/*String sqlString = "SELECT " + WebUtil.alias + ".* FROM " + "(SELECT * FROM t_admin_menu a START WITH ID in "
				+ "(select id FROM t_admin_menu b where b.parent_id = -1) " + "CONNECT BY a.parent_id  = PRIOR ID) "
				+ WebUtil.alias + " where 1 = 1 ";
		Query queryTest = entityManager.createNativeQuery(sqlString, TAdminMenu.class);*/
		
		
		
		List<TAdminMenu> menuList = new ArrayList<TAdminMenu>();
		
		if(!CollectionUtils.isEmpty(list)) {
			menuList = list;
		} else {
			List<TAdminMenu> parentMenuList = adminMenuService.findAdminMenuListByParentId(-1L);				
			getMenuList(parentMenuList,menuList);
			list = menuList;
		}
		
		request.setAttribute("menuList", menuList);
		// logger.info("queryTest.getResultList() = " +
		// JSON.toJSONString(queryTest.getResultList()));

		Long idLong = Long.parseLong(menuId);
		TAdminMenu adminMenu = adminMenuService.findAdminMenuById(idLong);
		request.setAttribute("adminMenu", JSON.toJSONString(adminMenu));
		request.setAttribute("adminMenuObject", adminMenu);
		return "admin/menu-edit";
	}
	
	/**
	 * 添加菜单信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String menuAddPage(HttpServletRequest request, Map<String, Object> model) {
		/*request.setAttribute("typeMap", TAdminMenu.initTypeMap());
		String sqlString = "SELECT " + WebUtil.alias + ".* FROM " + "(SELECT * FROM t_admin_menu a START WITH ID in "
				+ "(select id FROM t_admin_menu b where b.parent_id = -1) " + "CONNECT BY a.parent_id  = PRIOR ID) "
				+ WebUtil.alias + " where 1 = 1 ";
		Query queryTest = entityManager.createNativeQuery(sqlString, TAdminMenu.class);*/
		
		request.setAttribute("typeMap", TAdminMenu.initTypeMap());
		
		List<TAdminMenu> menuList = new ArrayList<TAdminMenu>();
		
		if(!CollectionUtils.isEmpty(list)) {
			menuList = list;
		} else {
			List<TAdminMenu> parentMenuList = adminMenuService.findAdminMenuListByParentId(-1L);				
			getMenuList(parentMenuList,menuList);
			list = menuList;
		}
		
		request.setAttribute("menuList", menuList);
		return "admin/menu-add";
	}
	
	private void getMenuList(List<TAdminMenu> parentMenuList,List<TAdminMenu> menuList) {
		if(!CollectionUtils.isEmpty(parentMenuList)) {
			for(TAdminMenu parentMenu :  parentMenuList) {
				menuList.add(parentMenu);
				List<TAdminMenu> childMenuList = adminMenuService.findAdminMenuListByParentId(parentMenu.getId());
				getMenuList(childMenuList,menuList);
			}
		};
	}

	@RequestMapping("/listData")
	@ResponseBody
	public String listData(HttpServletRequest request, Map<String, Object> model) {
		/*Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DEFAULT_DATE_PATTERN);
		String sqlString = "SELECT " + WebUtil.alias + ".* FROM " + "(SELECT * FROM t_admin_menu a START WITH ID in "
				+ "(select id FROM t_admin_menu b where b.parent_id = -1) " + "CONNECT BY a.parent_id  = PRIOR ID) "
				+ WebUtil.alias + " where 1 = 1 ";
		String countString = "SELECT COUNT(*) FROM t_admin_menu";
		logger.info("sqlString = " + sqlString);
		logger.info("countString = " + countString);

		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TAdminMenu.class, request, sqlString, false);
		// logger.info("hqlParam = " + hqlParam.getHql());

		PageBean<TAdminMenu> adminMenuPage = hqlPageBeanHelper.findHqlPageBeanByNativeQuery(entityManager, hqlParam,
				countString);
		AdminLteDataTableResp<TAdminMenu> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(adminMenuPage, request);
		return resp.toAdminLteJsonString(TAdminMenu.class);*/
		
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DEFAULT_DATE_PATTERN);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TAdminMenu.class, request);
		PageBean<TAdminMenu> adminUserPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		AdminLteDataTableResp<TAdminMenu> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(adminUserPage, request);
		List<TAdminMenu> adminUserList = resp.getData();
		resp.setData(adminUserList);
		//System.out.println("TAdminUser List =  " + resp.toAdminLteJsonString(TAdminUser.class));
		return resp.toAdminLteJsonString(TAdminMenu.class);
	}

	/**
	 * 获得菜单数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMenuListData")
	@ResponseBody
	public List<TAdminMenu> getMenuListData(HttpServletRequest request) {
		return adminMenuService.findAllOrderByOrderNum();
	}

	@RequestMapping(value = "/editAction", method = RequestMethod.POST)
	@ResponseBody
	public String editAction(HttpServletRequest request, Map<String, Object> model, @RequestParam("id") String menuId,
			String parendId, String name, String type, String orderNum, String url) {

		System.out.println("menuId = " + menuId);

//		System.out.println("parendId = " + parendId);
//		System.out.println("name = " + name);
//		System.out.println("type = " + type);
//		System.out.println("orderNum = " + orderNum);
//		System.out.println("url = " + url);

		BizResp response = new BizResp();
		long menuIdLong = -1l;
		long parentIdLong = -1l;
		int typeInt = -1;
		int orderNumberInt = -1;
		try {
			menuIdLong = Long.parseLong(menuId);
			parentIdLong = Long.parseLong(parendId);
			typeInt = Integer.parseInt(type);
			orderNumberInt = Integer.parseInt(orderNum);
		} catch (Exception e) {
			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
			return JSON.toJSONString(response);
		}

		//判断是否一样
		if (menuIdLong == parentIdLong) {
			response.setResult_code(BusiError.ERR_CODE_MENU_PARENT_ID_ERROR);
			response.setError_message(BusiError.ERR_MSG_MENU_PARENT_ID_ERROR);
			return JSON.toJSONString(response);
		}
		
		
		Map<String, String> typeMap = TAdminMenu.initTypeMap();
		
		System.out.println("typeMap.get(typeInt) = " + typeMap.get(type));
		
		if(typeMap.get(type) == null)
		{
			response.setResult_code(BusiError.ERR_CODE_MENU_TYPE_ERROR);
			response.setError_message(BusiError.ERR_MSG_MENU_TYPE_ERROR);
			return JSON.toJSONString(response);
		}
		
		TAdminMenu adminMenu = adminMenuService.findAdminMenuById(menuIdLong);
		if (adminMenu == null) {
			response.setResult_code(BusiError.ERR_CODE_MENU_NONE);
			response.setError_message(BusiError.ERR_MSG_MENU_NONE);
			return JSON.toJSONString(response);
		}
		
		adminMenu.setParentId(parentIdLong);
		adminMenu.setUrl(url);
		adminMenu.setName(name);
		adminMenu.setType(typeInt);
		adminMenu.setOrderNum(orderNumberInt);
		adminMenu.setUpdateTime(new Date());
		adminMenuService.saveAdminMenu(adminMenu);

		response.setResult_code(BusiError.ERR_CODE_SUCCESS);
		response.setError_message("");
		
		list.clear();
		return JSON.toJSONString(response);
	}
	
	
	/**
	 * 添加操作
	 * @param request
	 * @param model
	 * @param parendId
	 * @param name
	 * @param type
	 * @param orderNum
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/addAction", method = RequestMethod.POST)
	@ResponseBody
	public String addAction(HttpServletRequest request, Map<String, Object> model, String parendId, String name, String type, String orderNum, String url) {

		//System.out.println("parendId = " + parendId);
		//System.out.println("name = " + name);
		//System.out.println("type = " + type);
		//System.out.println("orderNum = " + orderNum);
		//System.out.println("url = " + url);

		BizResp response = new BizResp();
		long parentIdLong = -1l;
		int typeInt = -1;
		int orderNumberInt = -1;
		try {
			parentIdLong = Long.parseLong(parendId);
			typeInt = Integer.parseInt(type);
			orderNumberInt = Integer.parseInt(orderNum);
		} catch (Exception e) {
			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
			return JSON.toJSONString(response);
		}
		
		
		Map<String, String> typeMap = TAdminMenu.initTypeMap();
		
		System.out.println("typeMap.get(typeInt) = " + typeMap.get(type));
		
		if(typeMap.get(type) == null)
		{
			response.setResult_code(BusiError.ERR_CODE_MENU_TYPE_ERROR);
			response.setError_message(BusiError.ERR_MSG_MENU_TYPE_ERROR);
			return JSON.toJSONString(response);
		}
		
		TAdminMenu adminMenu = new TAdminMenu();
		adminMenu.setParentId(parentIdLong);
		adminMenu.setUrl(url);
		adminMenu.setName(name);
		adminMenu.setType(typeInt);
		adminMenu.setOrderNum(orderNumberInt);
		adminMenu.setRemark("");
		adminMenu.setCreateTime(new Date());
		adminMenu.setUpdateTime(new Date());
		
		//System.out.println("adminMenu id = " + adminMenu.getId());
		
		adminMenuService.saveAdminMenu(adminMenu);

		response.setResult_code(BusiError.ERR_CODE_SUCCESS);
		response.setError_message("");
		list.clear();
		return JSON.toJSONString(response);
	}
	
	@RequestMapping(value = "/deleteAction", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAction(HttpServletRequest request, Map<String, Object> model, @RequestParam("id") String menuId) {
		BizResp response = new BizResp();
		long menuIdLong = -1l;
		try {
			menuIdLong = Long.parseLong(menuId);
		} catch (Exception e) {
			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
			return JSON.toJSONString(response);
		}
		
		//1. 判断menuid 记录是否存在
		TAdminMenu adminMenu = adminMenuService.findAdminMenuById(menuIdLong);
		if (adminMenu == null) {
			response.setResult_code(BusiError.ERR_CODE_MENU_NONE);
			response.setError_message(BusiError.ERR_MSG_MENU_NONE);
			return JSON.toJSONString(response);
		}
		
		
		//2. 判断menuid下是否有子菜单
		List<TAdminMenu> adminMenuList = adminMenuService.findAdminMenuListByParentId(menuIdLong);
		if(adminMenuList != null && adminMenuList.size() > 0)
		{
			response.setResult_code(BusiError.ERR_CODE_MENU_HAS_CHILDREN_ERROR);
			response.setError_message(BusiError.ERR_MSG_MENU_HAS_CHILDREN_ERROR);
			return JSON.toJSONString(response);
		}
		//3. 删除数据
		adminMenuService.deleteAdminMenu(adminMenu);
		response.setResult_code(BusiError.ERR_CODE_SUCCESS);
		response.setError_message("");
		list.clear();
		return JSON.toJSONString(response);
	}
}
