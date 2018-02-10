//package cloudPayAdmin.admin.controller.cloudpay;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.project.m.api.common.biz.resp.BizResp;
//
//import cloudPayAdmin.admin.controller.BaseController;
//import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayBlackHis;
//import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayCard;
//import cloudPayAdmin.admin.dbapp.vo.VCloudpayCardUser;
//import cloudPayAdmin.admin.entity.excel.EImportUser;
//import cloudPayAdmin.admin.service.cloudpay.CloudpayCardService;
//import cloudPayAdmin.admin.service.cloudpay.UserService;
//import cloudPayAdmin.constant.BusiError;
//import cloudPayAdmin.constant.CloudPayConstant;
//import cloudPayAdmin.exception.ImportUserException;
//import cloudPayAdmin.util.AdminLteDataTableResp;
//import cloudPayAdmin.util.AdminLteUtil;
//import cloudPayAdmin.util.AdminUserInfoUtil;
//import cloudPayAdmin.util.CardNumUtil;
//import cloudPayAdmin.util.POIExcelUtils;
//import cloudPayAdmin.util.TimeUtil;
//import cloudPayAdmin.util.WebUtil;
//import cloudPayAdmin.util.pagebean.HqlPageBeanHelper;
//import cloudPayAdmin.util.pagebean.HqlParam;
//import cloudPayAdmin.util.pagebean.PageBean;
//
//
//
//@Controller
//@RequestMapping("/cloudpay/card")
//public class CloudpayCardController extends BaseController {
//
//	private final Logger logger = Logger.getLogger(getClass());
//
////	@Autowired
////	SendCloudPayBlackHisSyncThread sendCloudPayBlackHisSyncThread;
//
//	@Autowired
//	UserService userService;
//
//	@Autowired
//	CloudpayCardService cloudpayCardService;
//
//	@Autowired
//	HqlPageBeanHelper hqlPageBeanHelper;
//
//	@PersistenceContext(unitName = "entityManagerFactory")
//	EntityManager entityManager;
//
//	/**
//	 * 跳转到cardList页面
//	 * 
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/toListPage")
//	public String toListPage(HttpServletRequest request) {
//		request.setAttribute("statusMap", TCloudpayCard.initStatusMap());
//		request.setAttribute("availableMap", TCloudpayCard.initAvailableMap());
//		return "cloudpay/card-list";
//	}
//
//	@RequestMapping("/toCardAddPage")
//	public String toCardAddPage(HttpServletRequest request) {
//		request.setAttribute("statusMap", TCloudpayCard.initStatusMap());
//		request.setAttribute("availableMap", TCloudpayCard.initAvailableMap());
//		return "cloudpay/card-add";
//	}
//
//	/**
//	 * 查询卡片列表
//	 * 
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/getCardListData")
//	@ResponseBody
//	public String getCardListData(HttpServletRequest request) {
//
//		Map<String, Object> paramMap = WebUtil.getSearchParam(request);
//		paramMap = WebUtil.formatDateRange(paramMap, TimeUtil.DATE_PATTERN_NOSEPARTOR);
//		// 需要映射的实体类
//		HqlParam hqlParam = WebUtil.buildHqlParam(paramMap, VCloudpayCardUser.class, request);
//		PageBean<TCloudpayCard> cloudpayCardPage = hqlPageBeanHelper.findHqlPageBean(entityManager, hqlParam);
//		AdminLteDataTableResp<TCloudpayCard> resp = AdminLteUtil.pageBeanToAdminLteDataTableResp(cloudpayCardPage,
//				request);
//		return resp.toAdminLteJsonString(VCloudpayCardUser.class);
//	}
//
//	/**
//	 * excel导入用户
//	 * 
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping("/importUserAction")
//	public String importUserAction(MultipartFile userFile, HttpServletRequest request) throws IOException {
//
//		List<String> errorMsgList = new ArrayList<String>();
//
//		// 如果用户上传文件为空
//		if (userFile == null) {
//			errorMsgList.add("上传文件为空");
//			request.setAttribute("errorMsgList", errorMsgList);
//			return "forward:/cloudpay/card/toListPage";
//		}
//
//		List<EImportUser> importUserList = POIExcelUtils.excelToObject(userFile, EImportUser.class, 0);
//
//		String operUserName = AdminUserInfoUtil.getLoginAdminUserName();
//		for (EImportUser importUser : importUserList) {
//			importUser.setCardNum(CardNumUtil.completeCardNum(importUser.getCardNum())); // 把卡号补全为16位
//			// 先判断羊城通卡号是否合法，待实现
//			if (true) { // 如果合法
//				try {
//					userService.importUser(importUser, errorMsgList, operUserName);
//				} catch (ImportUserException e) {
//					logger.info("importUserAction:" + e.getMessage());
//				} catch (Exception e) {
//					logger.error(e.getMessage(), e);
//					String errorMsg = "序号:" + importUser.getIndex() + ",错误原因：系统内部错误";
//					errorMsgList.add(errorMsg);
//				}
//			} else { // 如果不合法
//				String errorMsg = "序号:" + importUser.getIndex() + ",错误原因:卡号"
//						+ CardNumUtil.subCardNum(importUser.getCardNum()) + "为非法卡";
//				errorMsgList.add(errorMsg);
//			}
//		}
//		request.setAttribute("errorMsgList", errorMsgList);
//		return "forward:/cloudpay/card/toListPage";
//	}
//
//	/**
//	 * 增加用户卡片绑定信息
//	 * 
//	 * @param request
//	 * @param model
//	 * @param phone
//	 * @param cardNumber
//	 * @return
//	 */
//	@RequestMapping(value = "/addUserCardAction", method = RequestMethod.POST)
//	@ResponseBody
//	public String addUserCardAction(HttpServletRequest request, Map<String, Object> model, String phone,
//			String cardNumber) {
//		System.out.println("phone = " + phone);
//		System.out.println("cardNumber = " + cardNumber);
//
//		BizResp response = new BizResp();
//		if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(cardNumber)) {
//			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
//			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
//			System.out.println("JSON.toJSONString(response) = " + JSON.toJSONString(response));
//			return JSON.toJSONString(response);
//		}
//
//		if (phone.length() != 11 || cardNumber.length() != 10) {
//			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
//			response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ);
//			return JSON.toJSONString(response);
//		}
//
//		EImportUser importUser = new EImportUser();
//		importUser.setIndex("1");
//		importUser.setCardNum(CardNumUtil.completeCardNum(cardNumber));
//		importUser.setPhone(phone);
//
//		/*
//		 * TAdminUser adminUser = (TAdminUser)
//		 * request.getSession().getAttribute(SessionKeyValue.KEY_ADMIN_USER); if
//		 * (adminUser == null) { Subject currentUser = SecurityUtils.getSubject(); if
//		 * (currentUser != null) { currentUser.logout(); }
//		 * response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
//		 * response.setError_message(BusiError.ERR_MSG_PARAM_BAD_REQ); return
//		 * JSON.toJSONString(response); }
//		 */
//
//		// 先找到手机号+卡号是否已经绑定，如果绑定返回成功
//		List<TCloudpayCard> cloudpayCardList = cloudpayCardService.findByPhoneAndCardNumAndAvailable(phone,
//				CardNumUtil.completeCardNum(cardNumber), TCloudpayCard.AVAILABLE_EXIST);
//		if (cloudpayCardList != null && cloudpayCardList.size() > 0) {
//			response.setResult_code(BusiError.ERR_CODE_SUCCESS);
//			response.setError_message("");
//			return JSON.toJSONString(response);
//		}
//
//		List<String> errorMsgList = new ArrayList<String>();
//		// 先判断羊城通卡号是否合法，待实现
//		if (true) { // 如果合法
//			try {
//				userService.importUser(importUser, errorMsgList, AdminUserInfoUtil.getLoginAdminUserName());
//			} catch (ImportUserException e) {
//				// TODO Auto-generated catch block
//				logger.info("绑定用户失败：" + errorMsgList.get(0));
//				e.printStackTrace();
//			}
//		} else { // 如果不合法
//			String errorMsg = "卡号 " + importUser.getCardNum() + " 为非法卡";
//			errorMsgList.add(errorMsg);
//		}
//
//		if (errorMsgList.isEmpty()) {
//			response.setResult_code(BusiError.ERR_CODE_SUCCESS);
//			response.setError_message("");
//			return JSON.toJSONString(response);
//
//			// return "redirect:/cloudpay/card/toCardAddPage";
//		} else {
//			response.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
//			response.setError_message(errorMsgList.get(0));
//			model.put("addCardResponse", JSON.toJSONString(response));
//			logger.info("JSON.toJSONString(response) = " + JSON.toJSONString(response));
//			return JSON.toJSONString(response);
//			// return "redirect:/cloudpay/card/toCardAddPage";
//		}
//	}
//
//	/**
//	 * 把状态设置为 valid
//	 * 
//	 * @param request
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/statusToEffectiveAction", method = RequestMethod.POST)
//	@ResponseBody
//	public String statusToEffectiveAction(HttpServletRequest request, Map<String, Object> model,
//			@RequestParam("recordIds[]") List<Long> recordIdList) throws IOException {
//		BizResp resp = new BizResp();
//		try {
//
//			List<TCloudpayCard> cloudpayCardList = cloudpayCardService.findCloudpayCardListByIdIn(recordIdList);
//
//			for (TCloudpayCard cloudpayCard : cloudpayCardList) {
//				if (cloudpayCard.getStatus() == TCloudpayCard.STATUS_UNEFFECITIVE) {
//					cloudpayCard.setStatus(TCloudpayCard.STATUS_EFFECITIVE);
//					cloudpayCard.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
//					cloudpayCard.setOperator(AdminUserInfoUtil.getLoginAdminUserName());
//				}
//			}
//			cloudpayCardService.saveCloudpayCardList(cloudpayCardList);
//			resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//			resp.setResult_code(BusiError.ERR_CODE_EFFECTIVE_STATUS_FAIL);
//			resp.setError_message(BusiError.ERR_MSG_EFFECTIVE_STATUS_FAIL);
//		}
//
//		return JSONObject.toJSONString(resp);
//	}
//
//	@RequestMapping(value = "/statusToUneffectiveAction", method = RequestMethod.POST)
//	@ResponseBody
//	public String statusToUneffectiveAction(HttpServletRequest request, Map<String, Object> model,
//			@RequestParam("recordIds[]") List<Long> recordIdList) throws IOException {
//		BizResp resp = new BizResp();
//		try {
//			List<TCloudpayCard> cloudpayCardList = cloudpayCardService.findCloudpayCardListByIdIn(recordIdList);
//			for (TCloudpayCard cloudpayCard : cloudpayCardList) {
//				if (cloudpayCard.getStatus() == TCloudpayCard.STATUS_EFFECITIVE) {
//					cloudpayCard.setStatus(TCloudpayCard.STATUS_UNEFFECITIVE);
//					cloudpayCard.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
//					cloudpayCard.setOperator(AdminUserInfoUtil.getLoginAdminUserName());
//				}
//			}
//			cloudpayCardService.saveCloudpayCardList(cloudpayCardList);
//			resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//			resp.setResult_code(BusiError.ERR_CODE_UNEFFECTIVE_STATUS_FAIL);
//			resp.setError_message(BusiError.ERR_MSG_UNEFFECTIVE_STATUS_FAIL);
//		}
//
//		return JSONObject.toJSONString(resp);
//	}
//
//	/**
//	 * 把 available标志位设置为 exist,即绑定(bind)
//	 * 
//	 * @param request
//	 * @param model
//	 * @param recordIds
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/availableExistAction", method = RequestMethod.POST)
//	@ResponseBody
//	public String availableExistAction(HttpServletRequest request, Map<String, Object> model,
//			@RequestParam("recordIds[]") List<Long> recordIdList) throws IOException {
//		BizResp resp = new BizResp();
//		try {
//			List<TCloudpayCard> cloudpayCardList = cloudpayCardService.findCloudpayCardListByIdIn(recordIdList);
//			// 先检查这些卡号有没有被其他绑定
//			for (TCloudpayCard cloudpayCard : cloudpayCardList) {
//				List<TCloudpayCard> list = cloudpayCardService.findByCardNumAndAvailable(cloudpayCard.getCardNum(),
//						TCloudpayCard.AVAILABLE_EXIST);
//				if (!CollectionUtils.isEmpty(list)) {
//					resp.setResult_code(BusiError.ERR_CODE_CARD_HAS_BIND);
//					resp.setError_message(BusiError.ERR_MSG_AVAILABLE_EXIST_FAIL + ":" + cloudpayCard.getCardNum());
//					return JSONObject.toJSONString(resp);
//				}
//			}
//
//			// 如果卡号合法，没有被绑定
//			for (TCloudpayCard cloudpayCard : cloudpayCardList) {
//
//				// 如果记录的状态是删除，才进行绑定(把状态改为 Exist)
//				if (cloudpayCard.getAvailable() == TCloudpayCard.AVAILABLE_DELETED) {
//					cloudpayCard.setAvailable(TCloudpayCard.AVAILABLE_EXIST);
//					cloudpayCard.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
//					cloudpayCard.setOperator(AdminUserInfoUtil.getLoginAdminUserName());
//					// 查询是否在黑名单列表
//					List<TCloudpayBlackHis> hisList = cloudpayCardService
//							.findBlackHisByCardNum(cloudpayCard.getCardNum());
//					if (!CollectionUtils.isEmpty(hisList)) {
//						TCloudpayBlackHis his = sendCloudPayBlackHisSyncThread.createCloudpayBlackHis(cloudpayCard,
//								CloudPayConstant.BLACK_LIST_REMOVE);
//						his = cloudpayCardService.saveCloudPayBlackHis(his);
//						sendCloudPayBlackHisSyncThread.sendBlackHis(his);
//					}
//				}
//			}
//
//			cloudpayCardService.saveCloudpayCardList(cloudpayCardList);
//			resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//			resp.setResult_code(BusiError.ERR_CODE_AVAILABLE_EXIST_FAIL);
//			resp.setError_message(BusiError.ERR_MSG_AVAILABLE_EXIST_FAIL);
//		}
//
//		return JSONObject.toJSONString(resp);
//	}
//
//	/**
//	 * 把available 标志位设置为 delete(删除),即解绑
//	 * 
//	 * @param request
//	 * @param model
//	 * @param recordIds
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/availableDeleteAction", method = RequestMethod.POST)
//	@ResponseBody
//	public String availableDeleteAction(HttpServletRequest request, Map<String, Object> model,
//			@RequestParam("recordIds[]") List<Long> recordIdList) throws IOException {
//		BizResp resp = new BizResp();
//		try {
//			List<TCloudpayCard> cloudpayCardList = cloudpayCardService.findCloudpayCardListByIdIn(recordIdList);
//			for (TCloudpayCard cloudpayCard : cloudpayCardList) {
//				// 如果记录的状态是存在，才进行删除(解绑操作)
//				if (cloudpayCard.getAvailable() == TCloudpayCard.AVAILABLE_EXIST) {
//
//					boolean hasNotPayOrder = Boolean.FALSE;
//
//					cloudpayCard.setAvailable(TCloudpayCard.AVAILABLE_DELETED);
//					cloudpayCard.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
//
//					cloudpayCard.setOperator(AdminUserInfoUtil.getLoginAdminUserName());
//
////					TCloudpayBlackHis his = sendCloudPayBlackHisSyncThread.createCloudpayBlackHis(cloudpayCard,
////							CloudPayConstant.BLACK_LIST_ADD);
////					// 事务保存 cloudpayBlackHis 和 更新cloudPayCard
////					cloudpayCardService.unbindAndBlackListHis(cloudpayCard, his);
////					sendCloudPayBlackHisSyncThread.sendBlackHis(his);
//				}
//			}
//			resp.setResult_code(BusiError.ERR_CODE_SUCCESS);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//			resp.setResult_code(BusiError.ERR_CODE_AVAILABLE_DELETE_FAIL);
//			resp.setError_message(BusiError.ERR_MSG_AVAILABLE_DELETE_FAIL);
//		}
//
//		return JSONObject.toJSONString(resp);
//	}
//
//}