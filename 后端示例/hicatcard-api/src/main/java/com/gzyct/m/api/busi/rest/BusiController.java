package com.gzyct.m.api.busi.rest;

import com.gzyct.m.api.busi.bean.wx.pay.WxNotifyBizReq;
import com.gzyct.m.api.busi.handlers.hicatcard.wx.NotiWxinBizHandler;
import com.gzyct.m.api.busi.util.XmlUtil;
import com.project.m.api.common.intf.InterfaceHandler;
import com.project.m.api.common.intf.req.InterfaceRequest;
import com.project.m.api.common.intf.req.ProtocolType;
import com.project.m.api.common.intf.req.RequestConvertor;
import com.project.m.api.common.intf.req.RequestConvertorFactory;
import com.project.m.api.common.intf.resp.InterfaceResp;
import com.project.m.api.common.intf.resp.RespRender;
import com.project.m.api.common.intf.resp.RespRenderFactory;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
// @RefreshScope
public class BusiController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	Environment env;

	@Resource(name = "busiInterfaceHandler")
	private InterfaceHandler interfaceHandler;

	@Resource(name = "busiInnerInterfaceHandler")
	private InterfaceHandler innerInterfaceHandler;

	@Autowired
	NotiWxinBizHandler notiWxinBizHandler;

	@ApiOperation(value = "xicatcard接口")
	@SuppressWarnings("unchecked")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "xiche.sms.code", required = false, value = "{\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.sms.code\",\"timestamp\":\"2017-11-13 04:01:01\",\"brand\":\"brand\",\"model\":\"model\",\"language\":\"language\",\"system\":\"system\",\"platform\":\"platform\",\"version\":\"1.0\",\"phone\":\"13570275903\",\"type\":\"1\"}"),
			@ApiImplicitParam(name = "xiche.car.licence.query", required = false, value = "{\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.car.licence.query\",\"timestamp\":\"2017-11-13 04:01:01\",\"brand\":\"brand\",\"model\":\"model\",\"language\":\"language\",\"system\":\"system\",\"platform\":\"platform\",\"version\":\"1.0\"}"),
			@ApiImplicitParam(name = "xiche.car.licence.update", required = false, value = "{\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.car.licence.update\",\"timestamp\":\"2017-11-13 04:01:01\",\"brand\":\"brand\",\"model\":\"model\",\"language\":\"language\",\"system\":\"system\",\"platform\":\"platform\",\"version\":\"1.0\",\"car_licence\":\"car_licence\",\"type\":\"2\"}"),
			@ApiImplicitParam(name = "xiche.card.collect", required = false, value = "{\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.card.collect\",\"timestamp\":\"2017-11-13 04:01:01\",\"brand\":\"brand\",\"model\":\"model\",\"language\":\"language\",\"system\":\"system\",\"platform\":\"platform\",\"version\":\"1.0\",\"user_card_id\":\"car_licence\"}"),
			@ApiImplicitParam(name = "xiche.card.detail.query", required = false, value = "{\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.card.detail.query\",\"timestamp\":\"2017-11-13 04:01:01\",\"brand\":\"brand\",\"model\":\"model\",\"language\":\"language\",\"system\":\"system\",\"platform\":\"platform\",\"version\":\"1.0\",\"user_card_id\":\"121\",\"system_card_number\":\"34000000201801151859294728722803\"}"),

			@ApiImplicitParam(name = "xiche.system.card.collect", required = false, value = "{\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.system.card.collect\",\"timestamp\":\"2017-11-13 04:01:01\",\"brand\":\"brand\",\"model\":\"model\",\"language\":\"language\",\"system\":\"system\",\"platform\":\"platform\",\"version\":\"1.0\",\"card_number\":\"34000000201712312126185381268985\"}"),
			@ApiImplicitParam(name = "xiche.system.qrcode.generate", required = false, value = "{\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.system.qrcode.generate\",\"timestamp\":\"2017-11-13 04:01:01\",\"brand\":\"brand\",\"model\":\"model\",\"language\":\"language\",\"system\":\"system\",\"platform\":\"platform\",\"version\":\"1.0\",\"scene\":\"scene\",\"page\":\"page\",\"width\":300}"),

			@ApiImplicitParam(name = "xiche.banner.query", required = false, value = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"place\":1,\"platform\":\"Plat-Test\",\"service\":\"xiche.banner.query\",\"system\":\"System-Test\",\"timestamp\":\"2017-12-18 11:00:28\",\"version\":\"1.0\"}"),
			@ApiImplicitParam(name = "xiche.user.info", required = false, value = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"place\":1,\"platform\":\"Plat-Test\",\"service\":\"xiche.user.info\",\"system\":\"System-Test\",\"timestamp\":\"2017-12-18 11:00:28\",\"version\":\"1.0\",\"nickName\":\"nickName\",\"avaterUrl\":\"www.baidu.com\",\"gender\":\"1\",\"country\":\"country\",\"province\":\"province\",\"city\":\"city\",\"language\":\"language\",\"phone\":\"12345678900\"}"),
			@ApiImplicitParam(name = "xiche.card.list", required = false, value = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"place\":1,\"platform\":\"Plat-Test\",\"service\":\"xiche.card.list\",\"system\":\"System-Test\",\"timestamp\":\"2017-12-18 11:00:28\",\"version\":\"1.0\",\"type\":\"\",\"searchText\":\"\",\"page\":1,\"pagesize\":5}"),
			@ApiImplicitParam(name = "xiche.card.order", required = false, value = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"place\":1,\"platform\":\"Plat-Test\",\"service\":\"xiche.card.order\",\"system\":\"System-Test\",\"timestamp\":\"2017-12-18 11:00:28\",\"version\":\"1.0\",\"card_id\":\"1\",\"total_fee\":1, \"pay_fee\":1}"),
			@ApiImplicitParam(name = "xiche.user.coupon.list", required = false, value = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"place\":1,\"platform\":\"Plat-Test\",\"service\":\"xiche.user.coupon.list\",\"system\":\"System-Test\",\"timestamp\":\"2017-12-18 11:00:28\",\"version\":\"1.0\",\"card_id\":\"2\",\"status\":\"2\", \"type\":\"1\", \"page\":1,\"pagesize\":5}"),
			@ApiImplicitParam(name = "xiche.card.activate", required = false, value = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"place\":1,\"platform\":\"Plat-Test\",\"service\":\"xiche.card.activate\",\"system\":\"System-Test\",\"timestamp\":\"2017-12-18 11:00:28\",\"version\":\"1.0\",\"user_card_id\":\"1\",\"sms_code\":\"1382\"}"),
			@ApiImplicitParam(name = "xiche.user.card.list", required = false, value = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"openid\":\"oj34W0ZxirWilma18w7Uiv4EMKHg\",\"place\":1,\"platform\":\"Plat-Test\",\"service\":\"xiche.user.card.list\",\"system\":\"System-Test\",\"timestamp\":\"2017-12-18 11:00:28\",\"version\":\"1.0\",\"page\":1,\"pagesize\":5, \"status\":\"\", \"source\":\"2\"}"),
			@ApiImplicitParam(name = "xiche.transaction.list", required = false, value = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"openid\":\"oj34W0emcX6EbAhSsCllfc_XZoUw\",\"place\":1,\"platform\":\"Plat-Test\",\"service\":\"xiche.transaction.list\",\"system\":\"System-Test\",\"timestamp\":\"2017-12-18 11:00:28\",\"version\":\"1.0\",\"page\":1,\"pagesize\":5, \"merchant_id\":\"52\",\"search_text\":\"汕头\",\"begin_date\":\"2018-01-10\",\"end_date\":\"2018-01-30\" }"),
			@ApiImplicitParam(name = "xiche.merchant.list", required = false, value = "{\"openid\":\"12345\",\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.merchant.list\",\"timestamp\":\"2017-11-13 04:01:01\",\"brand\":\"brand\",\"model\":\"model\",\"language\":\"language\",\"system\":\"system\",\"platform\":\"platform\",\"version\":\"1.0\",\"longitude\":\"113.289580\",\"latitude\":\"23.121070\",\"page\":1,\"pagesize\":5}"),

	})
	@RequestMapping(value = "/xicatcard/api", method = { RequestMethod.POST })
	public void xicatcardApi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long startTime = System.currentTimeMillis();
		InterfaceRequest ir = ((RequestConvertor<HttpServletRequest>) RequestConvertorFactory
				.getRequestConvertor(ProtocolType.HTTP_JSON)).convert(request);
		InterfaceResp interfaceResp = interfaceHandler.handle(ir);
		long endTime = System.currentTimeMillis();
		long handleTime = endTime - startTime;
		logger.info("handleTime:" + handleTime + "ms,service:" + ir.getService() + ",timestamp:" + ir.getTimestamp()
				+ ",openid:" + ir.getOpenid());
		((RespRender<HttpServletResponse>) RespRenderFactory.getRespConvertor(ProtocolType.HTTP_JSON)).render(response,
				interfaceResp);
	}


	/**

	 1. 没有结算的数据，分服务汇总返回，然后汇总一个时间和金额给你

	 2. 每次结算的汇总记录给我咯~！要分页的~！
	 可以按时间筛选结算单
	 同服务的合并成一个 给我总核销次数和单次多少钱
	 还有要可以关键字查询服务名称
	 */
	@ApiOperation(value = "xicatcard商户接口")
	@SuppressWarnings("unchecked")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "xiche.merchant.card.query", required = false, value = "{\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.merchant.card.query\",\"timestamp\":\"2017-11-13 04:01:01\",\"user_card_number\":\"32000000201801241738265740996576\"}"),
			@ApiImplicitParam(name = "xiche.merchant.transaction.query", required = false, value = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"place\":1,\"service\":\"xiche.merchant.transaction.query\",\"timestamp\":\"2017-12-1811:00:28\",\"version\":\"1.0\",\"merchant_id\":\"52\"}"),
			@ApiImplicitParam(name = "xiche.merchant.list.query", required = false, value = "{\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.merchant.list.query\",\"timestamp\":\"2017-11-13 04:01:01\"}"),
			@ApiImplicitParam(name = "xiche.merchant.transaction.perform", required = false, value = "{\"api_version\":\"1.0\",\"charset\":\"UTF-8\",\"service\":\"xiche.merchant.transaction.perform\",\"timestamp\":\"2017-11-13 04:01:01\",\"merchant_id\":\"52\",\"user_card_number\":\"32000000201802011557541878289948\", \"services\":[{\"user_card_service_id\":\"266\", \"service_time\":\"3\"}]}"), 
			@ApiImplicitParam(name = "xiche.merchant.settle.query", required = false, value = "{\"api_version\":\"1.0\",\"brand\":\"Brand-Test\",\"charset\":\"UTF-8\",\"language\":\"Chinese\",\"model\":\"Model-Test\",\"place\":1,\"service\":\"xiche.merchant.settle.query\",\"timestamp\":\"2017-12-1811:00:28\",\"version\":\"1.0\",\"merchant_id\":\"52\", \"begin_date\":\"2018-01-10\",\"end_date\":\"2018-02-30\", \"search_service_name\":\"洗车\", \"page\":1, \"pagesize\":10}"), })
	@RequestMapping(value = "/xicatcard/merchant/api", method = { RequestMethod.POST })
	public void xicatcardMerchantApi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InterfaceRequest ir = ((RequestConvertor<HttpServletRequest>) RequestConvertorFactory
				.getRequestConvertor(ProtocolType.HTTP_JSON)).convert(request);
		InterfaceResp interfaceResp = interfaceHandler.handle(ir);
		((RespRender<HttpServletResponse>) RespRenderFactory.getRespConvertor(ProtocolType.HTTP_JSON)).render(response,
				interfaceResp);
	}

	// @SuppressWarnings("unchecked")
	// @RequestMapping(value = "/gw/paycenter/noti/wxin", method = {
	// RequestMethod.GET, RequestMethod.POST })
	// public void notiWxin(HttpServletRequest request, HttpServletResponse
	// response) throws Exception {
	// ServletInputStream is = request.getInputStream();
	// byte[] bytes = IOUtils.toByteArray(is);
	// String requestBody = new String(bytes, "UTF-8");
	// NotiWxinBizReq notiWxinBizReq = (NotiWxinBizReq) XmlUtil.fromXml(requestBody,
	// NotiWxinBizReq.class);
	//
	// String resp = notiWxinBizHandler.handle(notiWxinBizReq,requestBody);
	// response.setCharacterEncoding("UTF-8");
	// response.getOutputStream().write(resp.getBytes("UTF-8"));
	// response.getOutputStream().flush();
	// }

	// @ApiOperation(value = "微信支付回调接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "", required = false, value = "<xml><appid><![CDATA[wx6f184c7366843273]]></appid>\r\n"
					+ "<bank_type><![CDATA[CFT]]></bank_type>\r\n" + "<cash_fee><![CDATA[1]]></cash_fee>\r\n"
					+ "<fee_type><![CDATA[CNY]]></fee_type>\r\n" + "<is_subscribe><![CDATA[N]]></is_subscribe>\r\n"
					+ "<mch_id><![CDATA[1445534102]]></mch_id>\r\n"
					+ "<nonce_str><![CDATA[95192c98732387165bf8e396c0f2dad2]]></nonce_str>\r\n"
					+ "<openid><![CDATA[oj34W0ZxirWilma18w7Uiv4EMKHg]]></openid>\r\n"
					+ "<out_trade_no><![CDATA[31000000201802011432491591719112]]></out_trade_no>\r\n"
					+ "<result_code><![CDATA[SUCCESS]]></result_code>\r\n"
					+ "<return_code><![CDATA[SUCCESS]]></return_code>\r\n"
					+ "<sign><![CDATA[05943221CD6717D87839AB50523274AF]]></sign>\r\n"
					+ "<time_end><![CDATA[20180110111024]]></time_end>\r\n" + "<total_fee>1</total_fee>\r\n"
					+ "<trade_type><![CDATA[JSAPI]]></trade_type>\r\n"
					+ "<transaction_id><![CDATA[4200000065201801101335739404]]></transaction_id>\r\n" + "</xml>"), })
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/xicatcard/api/notify", method = { RequestMethod.POST, RequestMethod.GET })
	public void deductNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServletInputStream is = request.getInputStream();
		byte[] bytes = IOUtils.toByteArray(is);
		String requestBody = new String(bytes, "UTF-8");
		logger.info("requestBody = " + requestBody);

//		requestBody = "<xml><appid>wx6f184c7366843273</appid>\r\n"
//				+ "<bank_type>CFT</bank_type>\r\n" + "<cash_fee>1</cash_fee>\r\n"
//				+ "<fee_type>CNY</fee_type>\r\n" + "<is_subscribe>N</is_subscribe>\r\n"
//				+ "<mch_id>1445534102</mch_id>\r\n"
//				+ "<nonce_str>95192c98732387165bf8e396c0f2dad2</nonce_str>\r\n"
//				+ "<openid>oj34W0ZxirWilma18w7Uiv4EMKHg</openid>\r\n"
//				+ "<out_trade_no>31000000201802011557011335155910</out_trade_no>\r\n"
//				+ "<result_code>SUCCESS</result_code>\r\n"
//				+ "<return_code>SUCCESS</return_code>\r\n"
//				+ "<sign>05943221CD6717D87839AB50523274AF</sign>\r\n"
//				+ "<time_end>20180110111024</time_end>\r\n" + "<total_fee>1</total_fee>\r\n"
//				+ "<trade_type>JSAPI</trade_type>\r\n"
//				+ "<transaction_id>4200000065201801101335739404</transaction_id>\r\n" + "</xml>";

		WxNotifyBizReq notiWxinBizReq = (WxNotifyBizReq) XmlUtil.fromXml(requestBody, WxNotifyBizReq.class);

		String resp = notiWxinBizHandler.handle(notiWxinBizReq, requestBody);
		logger.info("resp = " + resp);
		response.setCharacterEncoding("UTF-8");
		response.getOutputStream().write(resp.getBytes("UTF-8"));
		response.getOutputStream().flush();

	}

	@Value("${code.version}")
	private String version;

	@RequestMapping(value = "/xicatcard/codeversion", method = { RequestMethod.POST, RequestMethod.GET })
	public String codeVersion() throws Exception {
		logger.info("/xicatcard/codeversion version:" + version);
		return version;
	}

	// @ApiOperation(value = "学生卡接口")
	// @SuppressWarnings("unchecked")
	// @RequestMapping(value = "/yctpay/student", method = { RequestMethod.POST })
	// public void orderCreate(HttpServletRequest request, HttpServletResponse
	// response) throws Exception {
	//
	// InterfaceRequest ir = ((RequestConvertor<HttpServletRequest>)
	// RequestConvertorFactory
	// .getRequestConvertor(ProtocolType.HTTP_BASE64_JSON)).convert(request); //
	// .HTTP_JSON
	// InterfaceResp interfaceResp = interfaceHandler.handle(ir);
	// ((RespRender<HttpServletResponse>)
	// RespRenderFactory.getRespConvertor(ProtocolType.HTTP_JSON)).render(response,
	// interfaceResp);
	// }
	//
	// @ApiOperation(value = "学生卡接口内部调试")
	// @ApiImplicitParams({
	// @ApiImplicitParam(name = "yctb.student.fee.info", required = false, value =
	// "{\"businessType\":\"4\",\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"service\":\"yctb.student.fee.info\",\"sign\":\"72a5e69cdda8ad4dade4a4ef564568d9\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-11-13
	// 04:01:01\",\"user_id\":\"100004\",\"version\":\"1.0\"}"),
	// @ApiImplicitParam(name = "yctb.student.face.list", required = false, value =
	// "{ \"channel_code\" : \"70000016\", \"pagesize\" : 10, \"version\" : \"1.0\",
	// \"timestamp\" : \"20170901163743\", \"attach\" : \"\", \"card_num\" :
	// \"0504405818\", \"user_id\" : \"100004\", \"charset\" : \"UTF-8\",
	// \"service\" : \"yctb.student.face.list\", \"sign_type\" : \"MD5\", \"page\" :
	// 1, \"name\" : \"李雪\", \"sign\" : \"E557C0E3DFC5138CB382EE8C5DBEBE89\"}"),
	// @ApiImplicitParam(name = "yctb.student.area.list", required = false, value =
	// "{\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"service\":\"yctb.student.area.list\",\"sign\":\"f0201b24cc744bd6f4b1b9621937b3d4\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-11-13
	// 04:00:58\",\"user_id\":\"100004\",\"version\":\"1.0\"}"),
	// @ApiImplicitParam(name = "yctb.student.school.list", required = false, value
	// =
	// "{\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"districtNo\":1,\"page\":1,\"pagesize\":10,\"service\":\"yctb.student.school.list\",\"sign\":\"2ccd4aad1ea39ecf56f04536a0846778\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-11-13
	// 04:01:01\",\"type\":1,\"user_id\":\"100004\",\"version\":\"1.0\"}"),
	// @ApiImplicitParam(name = "yctb.student.apply.personinfo", required = false,
	// value =
	// "{\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"gender\":\"1\",\"grade\":\"1\",\"idNum\":\"441100199910102020\",\"idPictureType\":\"0\",\"idPictureUrl\":\"student/9000000020_id_20171020103624.jpg\",\"name\":\"王者荣耀\",\"phone\":\"13800138000\",\"receiveArea\":\"广东省广州市越秀区\",\"receiveDetail\":\"东华南路37号\",\"receiveName\":\"最终王者\",\"receivePhone\":\"138001380001\",\"schoolDistrict\":\"0\",\"schoolName\":\"广东广雅中学\",\"schoolType\":\"2\",\"service\":\"yctb.student.apply.personinfo\",\"sign\":\"b9ce8a32f7456605c670d8c450b524ae\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-11-13
	// 04:01:01\",\"user_id\":\"100004\",\"version\":\"1.0\"}"),
	// @ApiImplicitParam(name = "yctb.student.apply.photo", required = false, value
	// =
	// "{\"applicationPictureType\":\"0\",\"applicationPictureUrl\":\"student/9000000020_application_20171020105057.jpg\",\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"holdPictureType\":\"0\",\"holdPictureUrl\":\"student/9000000020_hold_20171020105102.jpg\",\"itemId\":1682,\"service\":\"yctb.student.apply.photo\",\"sign\":\"4347641d283919a712b17d0d7b87eada\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-11-13
	// 04:01:04\",\"user_id\":\"100004\",\"version\":\"1.0\"}"),
	// @ApiImplicitParam(name = "yctb.student.apply.query", required = false, value
	// =
	// "{\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"page\":1,\"pagesize\":10,\"service\":\"yctb.student.apply.query\",\"sign\":\"aaefe65d62643b46fffdd3352a15e105\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-11-13
	// 04:01:05\",\"user_id\":\"100004\",\"version\":\"1.0\"}"),
	// @ApiImplicitParam(name = "yctb.student.order.create", required = false, value
	// =
	// "{\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"itemId\":\"1682\",\"payChannel\":\"YPAY\",\"payFee\":1,\"service\":\"yctb.student.order.create\",\"sign\":\"4e69b7dbf65bf177cff2bf1932ea4ada\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-11-13
	// 04:01:05\",\"totalFee\":1,\"user_id\":\"100004\",\"version\":\"1.0\"}"),
	// @ApiImplicitParam(name = "yctb.student.card.delivery", required = false,
	// value =
	// "{\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"service\":\"yctb.student.card.delivery\",\"sign\":\"640ba2abf6713fc3d64a86444764b443\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-10-12
	// 10:05:46\",\"user_id\":\"100004\",\"version\":\"1.0\",\"pay_channel\":\"WXIN\",\"total_fee\":1800,\"pay_fee\":1800,
	// \"order_id\":\"31161006201710241721278113081953\", \"third_pay_id\":\"1111\",
	// \"pay_status\":\"2\", \"yct_extend\":\"\"}"),
	// @ApiImplicitParam(name = "yctb.student.apply.delete", required = false, value
	// =
	// "{\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"service\":\"yctb.student.apply.delete\",\"sign\":\"640ba2abf6713fc3d64a86444764b443\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-10-12
	// 10:05:46\",\"user_id\":\"100004\",\"version\":\"1.0\",\"itemId\":1060}"),
	// @ApiImplicitParam(name = "yctb.student.refund.perform", required = false,
	// value =
	// "{\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"service\":\"yctb.student.refund.perform\",\"sign\":\"640ba2abf6713fc3d64a86444764b443\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-10-12
	// 10:05:46\",\"user_id\":\"100004\",\"version\":\"1.0\",
	// \"pay_channel\":\"WXIN\", \"order_id\":\"yctapp\"}"), })
	//
	// @SuppressWarnings("unchecked")
	// @RequestMapping(value = "/yctpay/student/inner", method = {
	// RequestMethod.POST })
	// public void inner(HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// InterfaceRequest ir = ((RequestConvertor<HttpServletRequest>)
	// RequestConvertorFactory
	// .getRequestConvertor(ProtocolType.HTTP_JSON)).convert(request); // .HTTP_JSON
	// InterfaceResp interfaceResp = interfaceHandler.handle(ir);
	// ((RespRender<HttpServletResponse>)
	// RespRenderFactory.getRespConvertor(ProtocolType.HTTP_JSON)).render(response,
	// interfaceResp);
	// }
	//
	// @ApiOperation(value = "学生卡退款内部调试")
	// @ApiImplicitParams({
	// @ApiImplicitParam(name = "yctb.student.refund.perform", required = false,
	// value =
	// "{\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"service\":\"yctb.student.refund.perform\",\"sign\":\"640ba2abf6713fc3d64a86444764b443\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-10-12
	// 10:05:46\",\"user_id\":\"100004\",\"version\":\"1.0\",
	// \"pay_channel\":\"YPAY\", \"order_id\":\"31161006201710301620238063188592\",
	// \"pay_fee\":1, \"refund_fee\":1}"),
	// @ApiImplicitParam(name = "yctb.student.refund.query", required = false, value
	// =
	// "{\"channel_code\":\"70000016\",\"charset\":\"UTF-8\",\"service\":\"yctb.student.refund.query\",\"sign\":\"640ba2abf6713fc3d64a86444764b443\",\"sign_type\":\"MD5\",\"timestamp\":\"2017-10-12
	// 10:05:46\",\"user_id\":\"100004\",\"version\":\"1.0\", \"page\":1,
	// \"pageSize\":1}"), })
	// @SuppressWarnings("unchecked")
	// @RequestMapping(value = "/yctpay/student/refund/test", method = {
	// RequestMethod.POST })
	// public void refundTest(HttpServletRequest request, HttpServletResponse
	// response) throws Exception {
	// InterfaceRequest ir = ((RequestConvertor<HttpServletRequest>)
	// RequestConvertorFactory
	// .getRequestConvertor(ProtocolType.HTTP_JSON)).convert(request);
	// InterfaceResp interfaceResp = innerInterfaceHandler.handle(ir);
	// ((RespRender<HttpServletResponse>)
	// RespRenderFactory.getRespConvertor(ProtocolType.HTTP_JSON)).render(response,
	// interfaceResp);
	// }
	//
	// @ApiOperation(value = "学生卡内部接口，暂时只有退款")
	// @SuppressWarnings("unchecked")
	// @RequestMapping(value = "/yctpay/student/refund", method = {
	// RequestMethod.POST })
	// public void innerRefund(HttpServletRequest request, HttpServletResponse
	// response) throws Exception {
	// InterfaceRequest ir = ((RequestConvertor<HttpServletRequest>)
	// RequestConvertorFactory
	// .getRequestConvertor(ProtocolType.HTTP_JSON)).convert(request);
	// InterfaceResp interfaceResp = innerInterfaceHandler.handle(ir);
	// ((RespRender<HttpServletResponse>)
	// RespRenderFactory.getRespConvertor(ProtocolType.HTTP_JSON)).render(response,
	// interfaceResp);
	// }
	//
	// @RequestMapping(value = "/yctpay/student/inner/java", method = {
	// RequestMethod.POST, RequestMethod.GET })
	// public String java() throws Exception {
	// System.out.println("java--- start");
	// return "java";
	// }

	// @RequestMapping(value = "/yctpay/student/application", method = {
	// RequestMethod.POST })
	// public ApplicationResp application(@RequestBody ApplicationReq req)
	// throws Exception {
	// return studentApplicationPrintHandler.handle(req);
	// }

}
