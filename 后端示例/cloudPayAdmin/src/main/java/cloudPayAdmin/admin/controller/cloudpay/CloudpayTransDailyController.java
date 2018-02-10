package cloudPayAdmin.admin.controller.cloudpay;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudPayAdmin.admin.controller.BaseController;
import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayCard;
import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayTransDaily;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/cloudpay/transdaily")
public class CloudpayTransDailyController extends BaseController {
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	/**
	 * 跳转到 trans
	 * @param request
	 * @return
	 */
	@RequestMapping("/toListPage")
	public String toListPage(HttpServletRequest request) {
		return "/cloudpay/trans-daily-list";
	}
	
	/**
	 * 查询trans-daily数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTransDailyData")
	@ResponseBody
	public String getTransDailyData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_ONLYDATE_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TCloudpayTransDaily.class, request);
		PageBean<TCloudpayCard> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		AdminLteDataTableResp<TCloudpayCard> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,
				request);
		return resp.toAdminLteJsonString(TCloudpayTransDaily.class);
	}
}
