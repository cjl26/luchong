//package com.gzyct.m.api.busi.handlers.hicatcard.user;
//
//import com.gzyct.m.api.busi.bean.hicatcard.UserInfoReq;
//import com.gzyct.m.api.busi.bean.hicatcard.UserInfoResp;
//import com.gzyct.m.api.busi.config.BusiError;
//import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
//import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
//import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
//import com.gzyct.m.api.busi.util.CommonConvertor;
//import com.gzyct.m.api.busi.util.TimeUtil;
//import com.gzyct.m.api.validates.ValidateParam;
//import com.gzyct.m.api.validates.ValidateRet;
//import com.gzyct.m.api.validates.ValidateUtil;
//import com.project.m.api.common.biz.BizHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 用户信息新增/更新
// *
// * @author cjl
// *
// */
//@Component
//public class UserInfoBizHandler extends DefaultBizParamChecker<UserInfoReq, UserInfoResp>
//		implements BizHandler<UserInfoReq, UserInfoResp> {
//
//	private final Logger logger = LoggerFactory.getLogger(getClass());
//
//	@Autowired
//	UserService userService;
//
//	@Override
//	public UserInfoResp handle(UserInfoReq bizRequest) throws Exception {
//		// TODO Auto-generated method stub
//		UserInfoResp bizResp = new UserInfoResp();
//		// 参数检查
//		ValidateRet vRet = checkParam(bizRequest, bizResp);
//		if (!vRet.isValid()) {
//			return bizResp;
//		}
//		TUser userInfo = null;
//		try {
//			List<TUser> userList = userService.findByOpenidAndEnable(bizRequest.getOpenid(), true);
//			if(userList == null || userList.size() == 0)
//			{
//				//新增
//				TUser user = new TUser();
//				user.setPhone(bizRequest.getPhone());
//				user.setNickname(bizRequest.getNickName());
//				user.setAvaterUrl(bizRequest.getAvaterUrl());
//				user.setGender(Integer.valueOf(bizRequest.getGender()));
//				user.setCountry(CommonConvertor.convertStringAvoidNull(bizRequest.getCountry()));
//				user.setProvince(CommonConvertor.convertStringAvoidNull(bizRequest.getProvince()));
//				user.setCity(CommonConvertor.convertStringAvoidNull(bizRequest.getCity()));
//				user.setLanguage(CommonConvertor.convertStringAvoidNull(bizRequest.getLanguage()));
//				user.setOpenid(CommonConvertor.convertStringAvoidNull(bizRequest.getOpenid()));
//				user.setUnionid("");
//				user.setCreateTime(TimeUtil.getCurrTime());
//				user.setUpdateTime(TimeUtil.getCurrTime());
//				user.setEnable(true);
//				user.setStatus(TUser.STATUS_IN_USE);
//				userService.saveUser(user);
//				userInfo = user;
//			}
//			else
//			{
//				//列表更新 - 只保留第一个，其他全部置为false
//				for (TUser user : userList) {
//					if(bizRequest.getPhone() != null && bizRequest.getPhone().length() > 0){
//						user.setPhone(bizRequest.getPhone());
//					}
//					user.setNickname(bizRequest.getNickName());
//					user.setAvaterUrl(bizRequest.getAvaterUrl());
//					user.setGender(Integer.valueOf(bizRequest.getGender()));
//					user.setCountry(CommonConvertor.convertStringAvoidNull(bizRequest.getCountry()));
//					user.setProvince(CommonConvertor.convertStringAvoidNull(bizRequest.getProvince()));
//					user.setCity(CommonConvertor.convertStringAvoidNull(bizRequest.getCity()));
//					user.setLanguage(CommonConvertor.convertStringAvoidNull(bizRequest.getLanguage()));
//					user.setUpdateTime(TimeUtil.getCurrTime());
//					user.setEnable(false);
//					userInfo = user;
//				}
//				userList.get(0).setEnable(true);
//				userService.saveUserList(userList);
//			}
//			bizResp.setUserInfo(userInfo);
//			bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
//			bizResp.setError_message("");
//			return bizResp;
//		} catch (Exception e) {
//			logger.error("内部出错", e);
//			bizResp.setResult_code(BusiError.ERR_CODE_DB_CONF);
//			bizResp.setError_message(BusiError.ERR_MSG_DB_CONF);
//			return bizResp;
//		}
//
//	}
//
//	@Override
//	public ValidateRet checkParam(UserInfoReq req, UserInfoResp resp) {
//		ValidateRet vRet = super.checkParam(req, resp);
//		if (!vRet.isValid())
//			return vRet;
//
//		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
//		// paramList.add(new ValidateParam("nickname", ValidateType.NOTBLANK,
//		// req.getNickName()));
//		// paramList.add(new ValidateParam("a", ValidateType.NOTBLANK,
//		// req.getAvaterUrl()));
//		vRet = ValidateUtil.validate(paramList);
//
//		if (vRet == null) {
//			vRet = new ValidateRet(true, "");
//		}
//
//		if (!TUser.genderValid(req.getGender())) {
//			vRet = new ValidateRet(false, BusiError.ERR_CODE_PARAM_BAD_REQ);
//		}
//
//		if (!vRet.isValid()) {
//			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
//			resp.setError_message(vRet.getErrMsg());
//		}
//
//		return vRet;
//	}
//
//}
