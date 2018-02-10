package cloudPayAdmin.admin.controller.cloudpay;

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
import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudPayTranOrder;
import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayCard;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/cloudpay/tranorder")
public class CloudpayTranOrderController extends BaseController {
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	/**
	 * 跳转到 tran-order-list页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toListPage")
	public String toListPage(HttpServletRequest request) {
		return "cloudpay/tran-order-list";
	}
	
	/**
	 * 查询交易记录列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTranOrderListData")
	@ResponseBody
	public String getTranOrderListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, TCloudPayTranOrder.class, request);
		PageBean<TCloudpayCard> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		AdminLteDataTableResp<TCloudpayCard> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(TCloudPayTranOrder.class);
	}
}
