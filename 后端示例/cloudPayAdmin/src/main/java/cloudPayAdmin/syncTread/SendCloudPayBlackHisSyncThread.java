//package cloudPayAdmin.syncTread;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//
//import com.project.m.api.common.biz.req.BizRequest;
//import com.project.m.api.common.biz.resp.BizResp;
////import com.project.m.api.common.sign.SignWorker;
//import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayBlackHis;
//import cloudPayAdmin.admin.dbapp.entity.cloudpay.TCloudpayCard;
//import cloudPayAdmin.admin.entity.http.CloudPayBlackCard;
//import cloudPayAdmin.admin.entity.http.CloudPaySendBlackListReq;
//import cloudPayAdmin.admin.service.cloudpay.CloudpayCardService;
//import cloudPayAdmin.constant.BusiError;
//import cloudPayAdmin.constant.CloudPayConstant;
//import cloudPayAdmin.constant.Constant;
//import cloudPayAdmin.util.HttpRequestUtil;
//import cloudPayAdmin.util.TimeUtil;
//
///**
// * 调用 cloudPayBlackHis的异步线程方法
// * 
// * @author hyj
// *
// */
//@Component
//public class SendCloudPayBlackHisSyncThread {
//	
//	@Autowired
//	Environment env;
//	@Autowired
//	CloudpayCardService cloudpayCardService;
//	
//	private final Logger logger = Logger.getLogger(getClass());
//	
//	/**
//	 * 异步发送blackhis接口请求
//	 * @param his
//	 */
//	@Async
//	public void sendBlackHis(TCloudpayBlackHis his) {
//		send(his);
//	}
//	
//	/**
//	 * 同步发送blackhis接口请求
//	 * @param his
//	 */
//	public void sendBlackHisSync(TCloudpayBlackHis his) {
//		send(his);
//	}
//	
//	private void send(TCloudpayBlackHis his) {  
//		 //黑名单上曾经有记录，则发移除黑名单到终端后台
//        CloudPaySendBlackListReq req = new CloudPaySendBlackListReq();
//        req.setListsize(1);
//        CloudPayBlackCard cloudPayBlackCard = new CloudPayBlackCard();
//        cloudPayBlackCard.setId(his.getId());
//        cloudPayBlackCard.setOpt_type(his.getOpType());
//        cloudPayBlackCard.setYct_no(his.getCardNum());
//        List<CloudPayBlackCard> cloudPayBlackCards = new ArrayList<CloudPayBlackCard>();
//        cloudPayBlackCards.add(cloudPayBlackCard);
//        req.setBlacklist(cloudPayBlackCards);
//        buildBaseParams(req);
//        try {
//			SignWorker.sign(req, env.getProperty("yctapi_yctpay_channel_key"));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException("签名错误");
//		}
//        String jsonReq = JSON.toJSONString(req);
//        logger.info("黑名单同步接口Req:" + jsonReq);
//        String jsonResp = HttpRequestUtil.postRequeset(env.getProperty("term_server_url"), jsonReq);
//        logger.info("黑名单同步接口Resp:" + jsonResp);
//        if(!StringUtils.isBlank(jsonResp)) {
//        	 BizResp bizResp = JSON.parseObject(jsonResp, BizResp.class);
//        	 if(StringUtils.equals(bizResp.getResult_code(), BusiError.BLACK_HIS_SYNC_SUCCESS)) {
//             	 his.setSync(CloudPayConstant.BLACK_LIST_IS_SYNC);
//             	 cloudpayCardService.saveCloudPayBlackHis(his);
//             } else {
//             	
//             }
//        } else {
//        	logger.info(his.getId() + ":发送同步到终端请求失败");
//        }      
//	}
//
//	public TCloudpayBlackHis createCloudpayBlackHis(TCloudpayCard cloudpayCard, Integer opType) {
//		TCloudpayBlackHis his = new TCloudpayBlackHis();
//		his.setAppId(cloudpayCard.getAppId());
//		his.setCardNum(cloudpayCard.getCardNum());
//		his.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
//		his.setOpType(opType);
//		his.setSource(CloudPayConstant.BLACK_LIST_SOURCE_USER);
//		his.setUserId(cloudpayCard.getUserId());
//		his.setSync(CloudPayConstant.BLACK_LIST_NOT_SYNC);
//		return his;
//	}
//	
//	private void buildBaseParams(BizRequest baseParam) {
//		baseParam.setVersion("1.0");
//		baseParam.setChannel_code(env.getProperty("yctapi_yctpay_channel"));
//		baseParam.setTimestamp(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
//		baseParam.setCharset(Constant.CHARSET_UTF);
//		baseParam.setSign_type("MD5");
//	}
//}
