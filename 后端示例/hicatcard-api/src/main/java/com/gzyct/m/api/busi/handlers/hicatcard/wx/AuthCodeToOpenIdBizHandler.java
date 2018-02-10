package com.gzyct.m.api.busi.handlers.hicatcard.wx;

import com.alibaba.fastjson.JSON;
import com.gzyct.m.api.busi.bean.ext.*;
import com.gzyct.m.api.busi.bean.ext.AuthCodeToOpenIdReq;
import com.gzyct.m.api.busi.bean.wx.WxAuthResp;
import com.gzyct.m.api.busi.config.BusiError;
import com.gzyct.m.api.busi.db.entity.hicatcard.user.TUser;
import com.gzyct.m.api.busi.db.service.hicatcard.user.UserService;
import com.gzyct.m.api.busi.handlers.DefaultBizParamChecker;
import com.gzyct.m.api.busi.util.CommonConvertor;
import com.gzyct.m.api.busi.util.HttpRequestUtil;
import com.gzyct.m.api.busi.util.SLEmojiFilter;
import com.gzyct.m.api.busi.util.TimeUtil;
import com.gzyct.m.api.validates.ValidateParam;
import com.gzyct.m.api.validates.ValidateRet;
import com.gzyct.m.api.validates.ValidateType;
import com.gzyct.m.api.validates.ValidateUtil;
import com.project.m.api.common.biz.BizHandler;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 滚动广告栏信息获取
 *
 */
@Component
public class AuthCodeToOpenIdBizHandler extends DefaultBizParamChecker<AuthCodeToOpenIdReq, AuthCodeToOpenIdResp> implements BizHandler<AuthCodeToOpenIdReq, AuthCodeToOpenIdResp> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${weixin.miniapp.appid}")
	private String appid;

	@Value("${weixin.miniapp.secret}")
	private String secret;

	@Autowired
	UserService userService;

	@Override
	public AuthCodeToOpenIdResp handle(AuthCodeToOpenIdReq bizRequest) throws Exception {
		//先进行emoji表情过滤
		String filterEmojiJson = SLEmojiFilter.filterEmoji(JSON.toJSONString(bizRequest));
		bizRequest = JSON.parseObject(filterEmojiJson,AuthCodeToOpenIdReq.class);
		logger.info("filterEmojiJson:"+JSON.toJSONString(bizRequest));

		AuthCodeToOpenIdResp bizResp = new AuthCodeToOpenIdResp();
		ValidateRet vRet = checkParam(bizRequest, bizResp);
		if(!vRet.isValid()){
			return bizResp;
		}
		String jsonResp = HttpRequestUtil.getRequest("https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+bizRequest.getCode()+"&grant_type=authorization_code");
		WxAuthResp wxAuthResp = JSON.parseObject(jsonResp,WxAuthResp.class);
		if(wxAuthResp.getOpenid()!=null && wxAuthResp.getOpenid().length() > 0){
			bizResp.setWxAuthOpenid(wxAuthResp.getOpenid());
			bizResp.setSession_key(wxAuthResp.getSession_key());

			//--
			TUser userInfo = null;
			try {
				List<TUser> userList = userService.findByOpenidAndEnable(wxAuthResp.getOpenid(), true);
				if(userList == null || userList.size() == 0)
				{
					//新增
					TUser user = new TUser();
					user.setPhone(bizRequest.getPhone());
//					String str = SLEmojiFilter.filterEmoji(bizRequest.getNickName());
					user.setNickname(bizRequest.getNickName());
					user.setAvaterUrl(bizRequest.getAvaterUrl());
					user.setGender(Integer.valueOf(bizRequest.getGender()));
					user.setCountry(CommonConvertor.convertStringAvoidNull(bizRequest.getCountry()));
					user.setProvince(CommonConvertor.convertStringAvoidNull(bizRequest.getProvince()));
					user.setCity(CommonConvertor.convertStringAvoidNull(bizRequest.getCity()));
					user.setLanguage(CommonConvertor.convertStringAvoidNull(bizRequest.getLanguage()));
					user.setOpenid(CommonConvertor.convertStringAvoidNull(wxAuthResp.getOpenid()));
					user.setUnionid("");
					user.setCreateTime(TimeUtil.getCurrTime());
					user.setUpdateTime(TimeUtil.getCurrTime());
					user.setEnable(true);
					user.setStatus(TUser.STATUS_IN_USE);
					userService.saveUser(user);
					userInfo = user;
				}
				else
				{
					//列表更新 - 只保留第一个，其他全部置为false
					for (TUser user : userList) {
						if(bizRequest.getPhone() != null && bizRequest.getPhone().length() > 0){
							user.setPhone(bizRequest.getPhone());
						}
//						String str = SLEmojiFilter.filterEmoji(bizRequest.getNickName());
						user.setNickname(bizRequest.getNickName());
//						user.setNickname(bizRequest.getNickName());
						user.setAvaterUrl(bizRequest.getAvaterUrl());
						if(StringUtils.isNotBlank(bizRequest.getGender())) {
							user.setGender(Integer.valueOf(bizRequest.getGender()));
						}					
						user.setCountry(CommonConvertor.convertStringAvoidNull(bizRequest.getCountry()));
						user.setProvince(CommonConvertor.convertStringAvoidNull(bizRequest.getProvince()));
						user.setCity(CommonConvertor.convertStringAvoidNull(bizRequest.getCity()));
						user.setLanguage(CommonConvertor.convertStringAvoidNull(bizRequest.getLanguage()));
						user.setUpdateTime(TimeUtil.getCurrTime());
						user.setEnable(false);
						userInfo = user;
					}
					userList.get(0).setEnable(true);
					userService.saveUserList(userList);
				}
				bizResp.setUserInfo(userInfo);
				bizResp.setResult_code(BusiError.ERR_CODE_SUCCESS);
				bizResp.setError_message("");
				return bizResp;
			} catch (Exception e) {
				logger.error("内部出错", e);
				bizResp.setResult_code(BusiError.ERR_CODE_DB_CONF);
				bizResp.setError_message(BusiError.ERR_MSG_DB_CONF);
				return bizResp;
			}
			//--
		}else {
			bizResp.setResult_code(BusiError.ERR_CODE_WX_AUTH);
			bizResp.setError_message(BusiError.ERR_MSG_WX_AUTH);
			return bizResp;
		}

	}
	

	@Override
	public ValidateRet checkParam(AuthCodeToOpenIdReq req, AuthCodeToOpenIdResp resp) {
		ValidateRet vRet = super.checkParam(req, resp);
		if(!vRet.isValid())return vRet;
		
		List<ValidateParam> paramList = new ArrayList<ValidateParam>();
		paramList.add(new ValidateParam("Code", ValidateType.NOTBLANK, req.getCode()));
		vRet = ValidateUtil.validate(paramList);
		
		if(vRet==null)vRet = new ValidateRet(true, "");
		if(!vRet.isValid()){
			resp.setResult_code(BusiError.ERR_CODE_PARAM_BAD_REQ);
			resp.setError_message(vRet.getErrMsg());
		}
		return vRet;
	}
}
