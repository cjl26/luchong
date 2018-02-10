//package cloudPayAdmin.admin.controller.cloudpay;
//
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import cloudPayAdmin.admin.controller.BaseController;
//import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayBlackHis;
//import cloudPayAdmin.admin.service.cloudpay.CloudPayBlackHisService;
////import cloudPayAdmin.syncTread.SendCloudPayBlackHisSyncThread;
//import cloudPayAdmin.util.AdminLteDataTableResp;
//import cloudPayAdmin.util.AdminLteUtil;
//import cloudPayAdmin.util.TimeUtil;
//import cloudPayAdmin.util.WebUtil;
//import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
//import cloudPayAdmin.util.pagebean.HqlParam;
//import cloudPayAdmin.util.pagebean.PageBean;
//
//@Controller
//@RequestMapping("/cloudpay/blackHis")
//public class CloudpayBlackHisController extends BaseController {
//	@Autowired
//	HqlPageBeanHelper hqlPageBeanHelper;
//	
//	@PersistenceContext(unitName="entityManagerFactory")
//	EntityManager entityManager;
//	
//	@Autowired
//	CloudPayBlackHisService cloudPayBlackHisService;
//	
//	@Autowired
//	SendCloudPayBlackHisSyncThread sendCloudPayBlackHisSyncThread;
//	
//	/**
//	 * 跳转到blackHisList页面
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/list")
//	public String listPage(HttpServletRequest request) {
//		request.setAttribute("opTypeMap",TCloudpayBlackHis.initOpTypeMap());
//		request.setAttribute("sourceMap",TCloudpayBlackHis.initSourceMap());
//		request.setAttribute("syncMap",TCloudpayBlackHis.initSyncMap());
//		return "cloudpay/black-his-list";
//	}
//	
//	@RequestMapping("/listData")
//	@ResponseBody
//	public String listData(HttpServletRequest request) {
//		//System.out.println("in blackHisListData");
//		
//		System.out.println("search_DATERANGE_STRING_createTime = " + request.getParameter("search_DATERANGE_STRING_createTime"));
//		
//		Map<String ,Object> paramMap = WebUtil.getSearchParam(request);
//		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
//		 //需要映射的实体类
//		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TCloudpayBlackHis.class,request);
//		PageBean<TCloudpayBlackHis> cloudpayBlackHisPage =  hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
//		AdminLteDataTableResp<TCloudpayBlackHis> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayBlackHisPage, request);
//		System.out.println("resp.toAdminLteJsonString(TCloudpayBlackHis.class) = " + resp.toAdminLteJsonString(TCloudpayBlackHis.class));
//		return resp.toAdminLteJsonString(TCloudpayBlackHis.class);
//	}
//	
//	/**
//	 * 同步到 blackhis系统中
//	 * @param request
//	 * @param blackHisId
//	 * @return
//	 */
//	@RequestMapping("/sendSync")
//	public String sendSync(HttpServletRequest request,Long blackHisId) {
//		if(blackHisId != null) {
//			TCloudpayBlackHis cloudpayBlackHis = cloudPayBlackHisService.findById(blackHisId);
//			sendCloudPayBlackHisSyncThread.sendBlackHisSync(cloudpayBlackHis);
//		}
//		return "redirect:/cloudpay/blackHis/list";
//	}
//	
//}
