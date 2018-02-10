package cloudPayAdmin.admin.controller.hicatcard.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.card.TSystemPresentCard;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUser;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUserCard;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUserCardTransfer;
import cloudPayAdmin.admin.dbapp.vo.hicatcard.UserCardDonationVo;
import cloudPayAdmin.admin.dbapp.vo.hicatcard.VSystemPresentCard;
import cloudPayAdmin.admin.dbapp.vo.hicatcard.VUserCardService;
import cloudPayAdmin.admin.service.hicatcard.card.SystemPresentCardService;
import cloudPayAdmin.admin.service.hicatcard.user.UserCardService;
import cloudPayAdmin.admin.service.hicatcard.user.UserCardTransferService;
import cloudPayAdmin.admin.service.hicatcard.user.UserService;
import cloudPayAdmin.util.AdminLteDataTableResp;
import cloudPayAdmin.util.AdminLteUtil;
import cloudPayAdmin.util.TimeUtil;
import cloudPayAdmin.util.WebUtil;
import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
import cloudPayAdmin.util.pagebean.HqlParam;
import cloudPayAdmin.util.pagebean.PageBean;

@Controller
@RequestMapping("/hicatcard/userCard")
public class UserCardController {
	
	@Autowired
	HqlPageBeanHelper hqlPageBeanHelper;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	Environment env;
	
	@Autowired
	UserCardService userCardService;
	
	@Autowired
	UserCardTransferService userCardTransferService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SystemPresentCardService systemPresentCardService;
	
	@RequestMapping(value = "/toListPage")
	public String toListPage(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("statusMap", TUserCard.initStatusMap());
		return "hicatcard/user/user-card-list";
	}
	
	/**
	 * 跳转到查看用户转赠情况页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toCheckDonationPage")
	public String toCheckDonationPage(HttpServletRequest request) {
		request.setAttribute("statusMap", TUserCard.initStatusMap());
		return "hicatcard/user/user-card-donation-list";
	}
	
	/**
	 * 根据userCardId查找转赠记录
	 * @param request
	 * @param userCardId
	 * @return
	 */
	
	
	@RequestMapping(value = "/getDonationListData")
	@ResponseBody
	public String getDonationListData(HttpServletRequest request,Long userCardId,String systemPresentCardNumber) {
		
		//如果批量制卡卡号不是空，就是查询批量制卡的卡片赠送记录
		if(StringUtils.isNotBlank(systemPresentCardNumber)) {  
			List<TUserCardTransfer> userCardTransferList = userCardTransferService.findBySourceCardNumber(systemPresentCardNumber);
			
			if(!CollectionUtils.isEmpty(userCardTransferList)) {
				TUserCardTransfer userCardTransfer = userCardTransferList.get(0);
				if(userCardTransfer != null) {
					userCardId = userCardTransfer.getTargetUserCardId();
				}
			}					
		}
		
		List<UserCardDonationVo> userCardDonationVoList = new ArrayList<UserCardDonationVo>();
		if(userCardId != null) {
			userCardDonationVoList = getUserCardDonationVoList(userCardId,userCardDonationVoList);
		}
			
		PageBean<UserCardDonationVo> page = new PageBean<UserCardDonationVo>(userCardDonationVoList, 1, new Long(userCardDonationVoList.size()), 1, Integer.MAX_VALUE);
		
		AdminLteDataTableResp<UserCardDonationVo> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(page,request);
		return resp.toAdminLteJsonString(UserCardDonationVo.class);
	}
	
	/**
	 * 递归获取转赠记录
	 * @param userCardId
	 * @param userCardDonationVoList
	 * @return
	 */
	private List<UserCardDonationVo> getUserCardDonationVoList(Long userCardId,List<UserCardDonationVo> userCardDonationVoList) {
		TUserCardTransfer userCardTransfer = userCardTransferService.findByfindBySourceUserCardId(userCardId);
		if(userCardTransfer == null) {
			return userCardDonationVoList;
		} else {
			TUser sourceUser = userService.findById(userCardTransfer.getSourceUserId());
			TUser targetUser = userService.findById(userCardTransfer.getTargetUserId());
			TUserCard userCard = userCardService.findById(userCardTransfer.getTargetUserCardId());
			
			UserCardDonationVo vo = new UserCardDonationVo();
			vo.setUserCardTransferId(userCardTransfer.getUserCardTransferId());
			vo.setSourceUserCardId(userCardTransfer.getSourceUserCardId());
			vo.setSourceUserId(userCardTransfer.getSourceUserId());
			vo.setSourceNickname(sourceUser.getNickname());
			vo.setSourcePhone(sourceUser.getPhone());
			vo.setTargetUserCardId(userCardTransfer.getTargetUserCardId());
			vo.setTargetUserId(userCardTransfer.getTargetUserId());
			vo.setTargetNickname(targetUser.getNickname());
			vo.setTargetPhone(targetUser.getPhone());
			vo.setStatus(userCard.getStatus());
			vo.setEndTime(userCard.getEndTime());
			vo.setCreateTime(userCardTransfer.getCreateTime());
			vo.setTargetUserCardNumber(userCard.getUserCardNumber());
			userCardDonationVoList.add(vo);
			
			return getUserCardDonationVoList(userCardTransfer.getTargetUserCardId(),userCardDonationVoList);
		}
	}
	
	@RequestMapping("/getUserCardListData")
	@ResponseBody
	public String getUserCardListData(HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
		// 需要映射的实体类
		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, VUserCardService.class, request);
		String hql =hqlParam.getHql();
		
		Integer orderIndex = StringUtils.indexOf(hql, "order");
		String selectHql = StringUtils.substring(hql, 0,orderIndex);
		String orderHql = StringUtils.substring(hql, orderIndex,hql.length());		
		hqlParam.setHql(selectHql + " GROUP BY " + WebUtil.alias + ".userCardId " + orderHql);
		PageBean<VUserCardService> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
		
		for(VUserCardService vUserCardService : cloudpayCardPage.getContent()) {
			if(StringUtils.equals(vUserCardService.getStatus(), TUserCard.STATUS_HAS_GIVE)) {
				TUser user = userCardService.findTransferTargetUser(vUserCardService.getUserCardId());
				if(user != null) {
					vUserCardService.setTraferTargetNickname(user.getNickname());
					vUserCardService.setTraferTargetPhone(user.getPhone());
				}			
			}
		}
				
		AdminLteDataTableResp<VUserCardService> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,request);
		return resp.toAdminLteJsonString(VUserCardService.class);
	}
	
	
}
