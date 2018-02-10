package cloudPayAdmin.admin.controller.hicatcard.card;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.project.m.api.common.biz.resp.BizResp;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TCard;
import cloudPayAdmin.admin.dbapp.vo.hicatcard.VCardService;
import cloudPayAdmin.admin.service.hicatcard.card.CardServiceService;
import cloudPayAdmin.constant.BusiError;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/hicatcard/cardService")
public class CardServiceController {
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	Environment env;
	
	@Autowired
	CardServiceService cardServiceService;
	
	@RequestMapping(value = "/toListPage")
	public String toListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("statusMap", TCard.initStatusMap());
		return "hicatcard/card/card-service-list";
	}
	
	@RequestMapping("/getCardServiceListData")
	@ResponseBody
	public String getCardListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, VCardService.class, request);
		PageBean<VCardService> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		
		AdminLteDataTableResp<VCardService> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(VCardService.class);
	}
	
	@RequestMapping("/deleteCardService")
	@ResponseBody
	public String deleteCardService(Long cardServiceId) {
		BizResp resp = new BizResp();
		cardServiceService.deleteById(cardServiceId);
		resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
		return JSON.toJSONString(resp);
	}
}
