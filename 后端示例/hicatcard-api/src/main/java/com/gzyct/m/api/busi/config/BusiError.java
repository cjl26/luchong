package com.gzyct.m.api.busi.config;

public class BusiError {

	public static final String ERR_CODE_SUCCESS = "0";

	public static final String BUSI = "00";

	// param related yy=10
	public static final String PARAM = "10";
	public static final String ERR_CODE_PARAM_BAD_SIGN = BUSI + PARAM + "001";
	public static final String ERR_MSG_PARAM_BAD_SIGN = "签名错误";
	public static final String ERR_CODE_PARAM_BAD_REQ = BUSI + PARAM + "002";
	public static final String ERR_MSG_PARAM_BAD_REQ = "请求数据错误";

	// 数据库操作错误 yy=11
	public static final String DB = "11";
	public static final String ERR_CODE_DB_SAVE = BUSI + DB + "001";
	public static final String ERR_MSG_DB_SAVE = "数据保存错误";
	public static final String ERR_CODE_DB_CONF = BUSI + DB + "002";
	public static final String ERR_MSG_DB_CONF = "数据配置错误";

	// 网络错误 yy=12
	public static final String NET = "12";
	public static final String ERR_CODE_NET_INNER_POST = BUSI + NET + "001";
	public static final String ERR_MSG_NET_INNER_POST = "内部网络请求错误";
	public static final String ERR_CODE_NET_RESP_EMPTY = BUSI + NET + "002";
	public static final String ERR_MSG_NET_RESP_EMPTY = "内部网络返回为空";
	public static final String ERR_CODE_NET_RESULT_CODE_EMPTY = BUSI + NET + "003";
	public static final String ERR_MSG_NET_RESULT_CODE_EMPTY = "内部网络返回码为空";
	public static final String ERR_CODE_NET_JSON = BUSI + NET + "004";
	public static final String ERR_MSG_NET_JSON = "内部返回JSON转换出错";
	public static final String ERR_CODE_PAYCENTER_SDK_APPLY_ERROR = BUSI + NET + "005";
	public static final String ERR_MSG_PAYCENTER_SDK_APPLY_ERROR = "支付中心申请支付失败";
	public static final String ERR_CODE_REQUEST_EMPTY = BUSI + NET + "006";
	public static final String ERR_MSG_REQUEST_EMPTY = "内部网络请求参数为空";

	// 数据转换或系统异常 yy=13
	public static final String EXCP = "13";
	public static final String ERR_CODE_INNER_GEN_SIGN = BUSI + EXCP + "001";
	public static final String ERR_MSG_INNER_GEN_SIGN = "内部数据签名错误";
	public static final String ERR_CODE_INNER_TRANSFER = BUSI + EXCP + "002";
	public static final String ERR_MSG_INNER_TRANSFER = "内部数据转换错误";
	public static final String ERR_CODE_INNER_EXCEPTION = BUSI + EXCP + "003";
	public static final String ERR_MSG_INNER_EXCEPTION = "内部错误";
	public static final String ERR_CODE_PAUSE = BUSI + EXCP + "000";
	public static final String ERR_MSG_PAUSE = "系统升级维护中";
	public static final String ERR_CODE_INNER_UPDATING = BUSI + EXCP + "004";
	public static final String ERR_MSG_INNER_UPDATING = "系统升级中，请稍后再试";

	// 验证码失效
	public static final String VERIFY = "14";
	public static final String ERR_CODE_VERIFY = BUSI + VERIFY + "001";
	public static final String ERR_MSG_VERIFY = "验证码失效";
	public static final String ERR_CODE_TYPE_ERROR = BUSI + VERIFY + "002";
	public static final String ERR_MSG_TYPE_ERROR = "验证码类型错误";
	public static final String ERR_CODE_GET_SMSCODE_FAIL = BUSI + VERIFY + "003";
	public static final String ERR_MSG_GET_SMSCODE_FAIL = "获取短信验证码失败";

	// 密码
	public static final String PASSWORD = "15";
	public static final String ERR_CODE_PASSWORD = BUSI + PASSWORD + "001";
	public static final String ERR_MSG_PASSWORD = "登录密码不正确";
	public static final String ERR_CODE_PASSWORD_EMPTY = BUSI + PASSWORD + "002";
	public static final String ERR_MSG_PASSWORD_EMPTY = "登录密码未设置";
	public static final String ERR_CODE_PAY_PWD_WRONG_3 = BUSI + PASSWORD + "003";
	public static final String ERR_MSG_PAY_PWD_WRONG_3 = "密码输入错误超过3次，支付账户已锁定，请通过忘记密码修改";
	public static final String ERR_CODE_PAY_PASS_ALREADY_SET = BUSI + PASSWORD + "004";
	public static final String ERR_MSG_PAY_PASS_ALREADY_SET = "支付密码已设置";
	public static final String ERR_CODE_PAY_PASS_NOT_SET = BUSI + PASSWORD + "005";
	public static final String ERR_MSG_PAY_PASS_NOT_SET = "支付密码未设置";
	public static final String ERR_CODE_PAY_PASSWORD = BUSI + PASSWORD + "006";
	public static final String ERR_MSG_PAY_PASSWORD = "支付密码不正确";
	public static final String ERR_CODE_OLD_PAY_PASSWORD = BUSI + PASSWORD + "007";
	public static final String ERR_MSG_OLD_PAY_PASSWORD = "旧支付密码不正确";
	public static final String ERR_CODE_OLD_PAY_PASSWORD_EMPTY = BUSI + PASSWORD + "008";
	public static final String ERR_MSG_OLD_PAY_PASSWORD_EMPTY = "支付密码尚未设置，请点击忘记密码，以短信验证码方式设置";

	// 用户异常
	public static final String USER = "16";
	public static final String ERR_CODE_USER_EXIST = BUSI + USER + "001";
	public static final String ERR_MSG_USER_EXIST = "用户已存在";
	public static final String ERR_CODE_USER_NONE = BUSI + USER + "002";
	public static final String ERR_MSG_USER_NONE = "用户不存在";
	public static final String ERR_CODE_USER_STATUS = BUSI + USER + "003";
	public static final String ERR_MSG_USER_STATUS = "用户无效";
	public static final String ERR_CODE_USER_AUTH = BUSI + USER + "004";
	public static final String ERR_MSG_USER_AUTH = "用户认证失败";
	public static final String ERR_CODE_USER_ACCT_NOT_EXIST = BUSI + USER + "005";
	public static final String ERR_MSG_USER_ACCT_NOT_EXIST = "账户不存在";
	public static final String ERR_CODE_USER_ACCT_CHECKING = BUSI + USER + "006";
	public static final String ERR_MSG_USER_ACCT_CHECKING = "信息认证中，请勿重复提交";
	public static final String ERR_CODE_USER_ACCT_TOO_MUCH_INFO = BUSI + USER + "007";
	public static final String ERR_MSG_USER_ACCT_TOO_MUCH_INFO = "多条认证信息，请联系客服处理";
	public static final String ERR_CODE_USER_RN_SET = BUSI + USER + "008";
	public static final String ERR_MSG_USER_RN_SET = "实名信息已认证";
	public static final String ERROR_CODE_QUERY_USER_ACCT_TRANS = BUSI + USER + "010";
	public static final String ERROR_MSG_QUERY_USER_ACCT_TRANS = "查询账户交易明细错误";

	// 金额异常
	public static final String FEE = "17";
	public static final String ERR_CODE_FEE_NONE = BUSI + FEE + "001";
	public static final String ERR_MSG_FEE_NONE = "用户余额不足";

	public static final String ERR_CODE_FEE_OVER = BUSI + FEE + "002";
	public static final String ERR_MSG_FEE_OVER = "转账金额大于用户余额";

	// 订单号异常
	public static final String ORDER = "19";
	public static final String ERR_CODE_ORDER_NONE = BUSI + ORDER + "001";
	public static final String ERR_MSG_ORDER_NONE = "订单号不存在";
	public static final String ERR_CODE_ORDER_PAYED = BUSI + ORDER + "002";
	public static final String ERR_MSG_ORDER_PAYED = "订单已支付";
	public static final String ERR_CODE_ORDER_DUPLICATE = BUSI + ORDER + "003";
	public static final String ERR_MSG_ORDER_DUPLICATE = "商户订单重复";
	public static final String ERR_CODE_ORDER_STATUS_ERROR = BUSI + ORDER + "004";
	public static final String ERR_MSG_ORDER_STATUS_ERROR = "订单支付状态异常";
	public static final String ERR_CODE_ORDER_EXPIRE = BUSI + ORDER + "004";
	public static final String ERR_MSG_ORDER_EXPIRE = "订单有效期超时";
	public static final String ERR_CODE_ORDER_CREATE = BUSI + ORDER + "005";
	public static final String ERR_MSG_ORDER_CREATE = "订单创建失败";

	// 订单号异常
	public static final String XICHE = "20";
	public static final String ERR_CODE_WX_AUTH = BUSI + XICHE + "001";
	public static final String ERR_MSG_WX_AUTH = "获取openid失败";
	public static final String ERR_CODE_CARD_NOT_EXIST = BUSI + XICHE + "002";
	public static final String ERR_MSG_CARD_NOT_EXIST = "卡片不存在";
	public static final String ERR_CODE_CARD_UNIFIED_ORDER_ERROR = BUSI + XICHE + "003";
	public static final String ERR_MSG_CARD_UNIFIED_ORDER_ERROR = "统一订单";
	public static final String ERR_CODE_USER_CARD_IS_ACTIVATED = BUSI + XICHE + "004";
	public static final String ERR_MSG_USER_CARD_IS_ACTIVATED = "卡片已经激活";
	public static final String ERR_CODE_USER_CARD_NOT_EXIST = BUSI + XICHE + "005";
	public static final String ERR_MSG_USER_CARD_NOT_EXIST = "用户卡片不存在";
	public static final String ERR_CODE_SMS_CODE_NOT_EXIST = BUSI + XICHE + "006";
	public static final String ERR_MSG_SMS_CODE_NOT_EXIST = "短信验证码不存在";
	public static final String ERR_CODE_SMS_CODE_ERROR = BUSI + XICHE + "007";
	public static final String ERR_MSG_SMS_CODE_ERROR = "短信验证码错误";
	public static final String ERR_CODE_SMS_CODE_EXPIRED = BUSI + XICHE + "008";
	public static final String ERR_MSG_SMS_CODE_EXPIRED = "短信验证码实效";
	public static final String ERR_CODE_CAR_LICENCE_NOT_EXIST = BUSI + XICHE + "009";
	public static final String ERR_MSG_CAR_LICENCE_NOT_EXIST = "车牌不存在";
	public static final String ERR_CODE_CAR_LICENCE_HAS_EXIST = BUSI + XICHE + "009";
	public static final String ERR_MSG_CAR_LICENCE_HAS_EXIST = "车牌已经存在";
	public static final String ERR_CODE_USER_CARD_USER_SAME = BUSI + XICHE + "010";
	public static final String ERR_MSG_USER_CARD_USER_SAME = "赠送和领取是同一个用户";
	public static final String ERR_CODE_MERCHANT_NOT_EXIST = BUSI + XICHE + "011";
	public static final String ERR_MSG_MERCHANT_NOT_EXIST = "商户不存在";
	public static final String ERR_CODE_USER_CARD_NOT_ACTIVATED = BUSI + XICHE + "012";
	public static final String ERR_MSG_USER_CARD_NOT_ACTIVATED = "卡片未激活";
	public static final String ERR_CODE_USER_CARD_ID_ERROR = BUSI + XICHE + "013";
	public static final String ERR_MSG_USER_CARD_ID_ERROR = "用户卡片ID不对";
	public static final String ERR_CODE_USER_CARD_SERVICE_TIME_NOT_ENOUGH = BUSI + XICHE + "014";
	public static final String ERR_MSG_USER_CARD_SERVICE_TIME_NOT_ENOUGH = "用户卡片剩余次数不足";
	public static final String ERR_CODE_COUPONE_FEE_ERROR = BUSI + XICHE + "015";
	public static final String ERR_MSG_COUPONE_FEE_ERROR = "优惠券金额不对";
	public static final String ERR_CODE_FEE_CALCULATION_ERROR = BUSI + XICHE + "016";
	public static final String ERR_MSG_FEE_CALCULATION_ERROR = "总金额应为支付金额加优惠金额";
	public static final String ERR_CODE_SYSTEM_CARD_NOT_EXIST = BUSI + XICHE + "017";
	public static final String ERR_MSG_SYSTEM_CARD_NOT_EXIST = "卡片已经领取";
	public static final String ERR_CODE_WX_TOKEN_ERROR = BUSI + XICHE + "018";
	public static final String ERR_MSG_WX_TOKEN_ERROR = "获取微信token失败";
	public static final String ERR_CODE_USER_CARD_HAS_PRESENT = BUSI + XICHE + "019";
	public static final String ERR_MSG_USER_CARD_HAS_PRESENT = "卡片已经领取";
	public static final String ERR_CODE_USER_PHONE_NOT_EXIST = BUSI + XICHE + "020";
	public static final String ERR_MSG_USER_PHONE_NOT_EXIST = "用户手机不存在";
	public static final String ERR_CODE_MERCHANT_SERVICE_NOT_EXIST = BUSI + XICHE + "021";
	public static final String ERR_MSG_MERCHANT_SERVICE_NOT_EXIST = "商户没有该服务";
}
