package cloudPayAdmin.constant;

public class BusiError {
	
	public static final String ERR_CODE_SUCCESS = "0";
	public static final String BLACK_HIS_SYNC_SUCCESS = "00";
	
	public static final String BUSI = "40";
	
	public static final String SESSION = "00";
	
	//param related yy=10
	public static final String PARAM = "10";
	public static final String ERR_CODE_PARAM_BAD_REQ = BUSI + PARAM + "002";
	public static final String ERR_MSG_PARAM_BAD_REQ = "请求数据错误";
	
	//用户异常
	public static final String USER = "16";
	public static final String ERR_CODE_USER_EXIST = BUSI + USER + "001";
	public static final String ERR_MSG_USER_EXIST = "用户已存在";	
	public static final String ERR_CODE_USER_NONE = BUSI + USER + "002";
	public static final String ERR_MSG_USER_NONE = "用户不存在";
	public static final String ERR_CODE_SAVE_USERROLE_FAIL = BUSI + USER + "003";
	public static final String ERR_MSG_SAVE_USERROLE_FAIL = "保存角色失败";
	public static final String ERR_CODE_IMG_VALIDATE_OVERDUE = BUSI + USER + "004";
	public static final String ERR_MSG_IMG_VALIDATE_OVERDUE = "验证码已过期";
	public static final String ERR_CODE_IMG_VALIDATE_ERROR = BUSI + USER + "004";
	public static final String ERR_MSG_IMG_VALIDATE_ERROR = "验证码错误";
	
	//卡片异常
	public static final String CARD = "17";
	public static final String ERR_CODE_EFFECTIVE_STATUS_FAIL = BUSI + CARD + "001";
	public static final String ERR_MSG_EFFECTIVE_STATUS_FAIL = "启用状态失败";
	public static final String ERR_CODE_UNEFFECTIVE_STATUS_FAIL = BUSI + CARD + "002";
	public static final String ERR_MSG_UNEFFECTIVE_STATUS_FAIL = "停用状态失败";
	public static final String ERR_CODE_AVAILABLE_EXIST_FAIL = BUSI + CARD + "003";
	public static final String ERR_MSG_AVAILABLE_EXIST_FAIL = "绑定失败";
	public static final String ERR_CODE_AVAILABLE_DELETE_FAIL = BUSI + CARD + "004";
	public static final String ERR_MSG_AVAILABLE_DELETE_FAIL = "解绑失败";
	public static final String ERR_CODE_CARD_HAS_BIND = BUSI + CARD + "005";
	public static final String ERR_MSG_CARD_HAS_BIND = "卡号已经被绑定";
	
	//角色异常
	public static final String ROLE = "18";
	public static final String ERR_CODE_SAVE_ROLEPRIVILEGE_FAIL = BUSI + ROLE + "001";
	public static final String ERR_MSG_SAVE_ROLEPRIVILEGE_FAIL = "保存权限失败";
	
	//菜单异常
	public static final String MENU= "19";
	public static final String ERR_CODE_MENU_NONE = BUSI + MENU + "001";
	public static final String ERR_MSG_MENU_NONE = "菜单不存在";
	public static final String ERR_CODE_MENU_TYPE_ERROR = BUSI + MENU + "002";
	public static final String ERR_MSG_MENU_TYPE_ERROR = "菜单类型不正确";
	public static final String ERR_CODE_MENU_PARENT_ID_ERROR = BUSI + MENU + "003";
	public static final String ERR_MSG_MENU_PARENT_ID_ERROR = "父菜单与本菜单ID不能相同";
	public static final String ERR_CODE_MENU_HAS_CHILDREN_ERROR = BUSI + MENU + "004";
	public static final String ERR_MSG_MENU_HAS_CHILDREN_ERROR = "父菜单下有子菜单";
	
	//商户异常
	public static final String MERCHANT= "20";
	public static final String ERR_CODE_MERCHANT_SETTLE_NO_RESULT = BUSI + MENU + "001";
	public static final String ERR_MSG_MERCHANT_SETTLE_NO_RESULT = "结算数据为空";

	
//	public static final String ERR_CODE_SESSION_NOT_EXIST = BUSI + SESSION + "001";
//	public static final String ERR_MSG_SESSION_NOT_EXIST = "登录信息不存在，请重新登录";
//	public static final String ERR_CODE_SESSION_DIFFERENT = BUSI + SESSION + "001";
//	public static final String ERR_MSG_SESSION_DIFFERENT = "登录状态已失效，请重新登录";
//	public static final String ERR_CODE_STATUS_CODE_ERROR = BUSI + SESSION + "001";
//	public static final String ERR_MSG_STATUS_CODE_ERROR = "状态码不正确，请重新登录";
//	public static final String ERR_CODE_BAD_SESSION = BUSI + SESSION + "001";
//	public static final String ERR_MSG_BAD_SESSION = "登录信息异常，请重新登录";
//	public static final String ERR_CODE_BAD_SESSION_EXPIRE = BUSI + SESSION + "001";
//	public static final String ERR_MSG_BAD_SESSION_EXPIRE = "登录信息超时，请重新登录";
//	
//	//param related yy=10
//	public static final String PARAM = "10";
//	public static final String ERR_CODE_PARAM_BAD_SIGN = BUSI + PARAM + "001";
//	public static final String ERR_MSG_PARAM_BAD_SIGN = "签名错误";
//	public static final String ERR_CODE_PARAM_BAD_REQ = BUSI + PARAM + "002";
//	public static final String ERR_MSG_PARAM_BAD_REQ = "请求数据错误";
//
//	
//	//数据库操作错误 yy=11
//	public static final String DB = "11";
//	public static final String ERR_CODE_DB_SAVE = BUSI + DB + "001";
//	public static final String ERR_MSG_DB_SAVE = "数据保存错误";
//	public static final String ERR_CODE_DB_CONF = BUSI + DB + "002";
//	public static final String ERR_MSG_DB_CONF = "数据配置错误";
//	
//	//网络错误 yy=12
//	public static final String NET = "12";
//	public static final String ERR_CODE_NET_INNER_POST = BUSI + NET + "001";
//	public static final String ERR_MSG_NET_INNER_POST = "内部网络请求错误";
//	public static final String ERR_CODE_NET_RESP_EMPTY = BUSI + NET + "002";
//	public static final String ERR_MSG_NET_RESP_EMPTY = "内部网络返回为空";
//	public static final String ERR_CODE_NET_RESULT_CODE_EMPTY = BUSI + NET + "003";
//	public static final String ERR_MSG_NET_RESULT_CODE_EMPTY = "内部网络返回码为空";
//	public static final String ERR_CODE_NET_JSON = BUSI + NET + "004";
//	public static final String ERR_MSG_NET_JSON = "内部返回JSON转换出错";
//    public static final String ERR_CODE_PAYCENTER_SDK_APPLY_ERROR = BUSI + NET + "005";
//    public static final String ERR_MSG_PAYCENTER_SDK_APPLY_ERROR = "支付中心申请支付失败";
//    public static final String ERR_CODE_REQUEST_EMPTY = BUSI + NET + "006";
//    public static final String ERR_MSG_REQUEST_EMPTY = "内部网络请求参数为空";
//	
//	//数据转换或系统异常 yy=13
//	public static final String EXCP = "13";
//	public static final String ERR_CODE_INNER_GEN_SIGN = BUSI + EXCP + "001";
//	public static final String ERR_MSG_INNER_GEN_SIGN = "内部数据签名错误";
//	public static final String ERR_CODE_INNER_TRANSFER = BUSI + EXCP + "002";
//	public static final String ERR_MSG_INNER_TRANSFER = "内部数据转换错误";
//	public static final String ERR_CODE_INNER_EXCEPTION = BUSI + EXCP + "003";
//	public static final String ERR_MSG_INNER_EXCEPTION = "内部错误";
//	
//	
//	//验证码失效
//	public static final String VERIFY = "14";
//	public static final String ERR_CODE_VERIFY = BUSI + VERIFY + "001";
//	public static final String ERR_MSG_VERIFY = "验证码失效";	
//	
//	//密码不对
//	public static final String PASSWORD = "15";
//	public static final String ERR_CODE_PASSWORD = BUSI + PASSWORD + "001";
//	public static final String ERR_MSG_PASSWORD = "密码不对";
//	public static final String ERR_CODE_PAYPASSWORD_NOTSET = BUSI + PASSWORD + "002";
//	public static final String ERR_MSG_PAYPASSWORD_NOTSET = "支付密码未设置";
//	public static final String ERR_CODE_PWD_WRONG = BUSI + PASSWORD + "003";
//	public static final String ERR_MSG_PWD_WRONG = "用户名或密码错误";
//	public static final String ERR_CODE_PWD_WRONG_3 = BUSI + PASSWORD + "003";
//	public static final String ERR_MSG_PWD_WRONG_3 = "密码输入错误超过3次，账户已锁定，请通过忘记密码修改";
//	public static final String ERR_CODE_PAY_PASS_ALREADY_SET = BUSI + PASSWORD + "004";
//	public static final String ERR_MSG_PAY_PASS_ALREADY_SET = "支付密码已设置";
//	
//	//用户异常
//	public static final String USER = "16";
//	public static final String ERR_CODE_USER_EXIST = BUSI + USER + "001";
//	public static final String ERR_MSG_USER_EXIST = "用户已存在";	
//	
//	public static final String ERR_CODE_USER_NONE = BUSI + USER + "002";
//	public static final String ERR_MSG_USER_NONE = "用户不存在";
//	
//	public static final String ERR_CODE_USER_STATUS = BUSI + USER + "003";
//	public static final String ERR_MSG_USER_STATUS = "用户无效";
//
//	
//	public static final String ERR_CODE_USER_AUTH = BUSI + USER + "004";
//	public static final String ERR_MSG_USER_AUTH = "用户验证信息无效";
//	
//	public static final String ERR_CODE_USER_ACCT_NOT_EXIST = BUSI + USER + "005";
//	public static final String ERR_MSG_USER_ACCT_NOT_EXIST = "账户不存在";
//
//	public static final String ERR_CODE_USER_ACCT_CHECKING = BUSI + USER + "006";
//	public static final String ERR_MSG_USER_ACCT_CHECKING = "信息认证中，请勿重复提交";
//
//	public static final String ERR_CODE_USER_ACCT_TOO_MUCH_INFO = BUSI + USER + "007";
//	public static final String ERR_MSG_USER_ACCT_TOO_MUCH_INFO = "多条认证信息，请联系客服处理";
//
//	public static final String ERR_CODE_USER_RN_SET= BUSI + USER + "008";
//	public static final String ERR_MSG_USER_RN_SET = "实名信息已认证";
//	
//	public static final String ERROR_CODE_QUERY_USER_ACCT_TRANS = BUSI + USER + "009";
//	public static final String ERROR_MSG_QUERY_USER_ACCT_TRANS = "查询账户交易明细错误";
//	
//	//金额异常
//	public static final String  FEE = "17";
//	public static final String ERR_CODE_FEE_NONE = BUSI + FEE + "001";
//	public static final String ERR_MSG_FEE_NONE = "用户余额不足";	
//	
//	public static final String ERR_CODE_FEE_OVER = BUSI + FEE + "002";
//	public static final String ERR_MSG_FEE_OVER = "转账金额大于用户余额";
//	
//	
//	//羊城通卡片异常
//	public static final String  CARD= "18";
//	public static final String CARD_CODE_NONE = BUSI + CARD + "001";
//	public static final String CARD_MSG_NONE = "羊城通卡不存在";	
//	
//	public static final String CARD_CODE_EXIST = BUSI + CARD + "002";
//	public static final String CARD_MSG_EXIST = "绑定失败：羊城通卡已被绑定";
//	
//	public static final String CARD_CODE_BIND_FAIL = BUSI + CARD + "003";
//	public static final String CARD_MSG_BIND_FAIL = "绑定失败";
//	
//	
//	public static final String CARD_CODE_RN_STATUS_ERROR = BUSI + CARD + "003";
//	public static final String CARD_MSG_RN_STATUS_ERROR = "记名卡状态异常";
//	
//	//订单号异常
//	public static final String  ORDER= "19";
//	public static final String ERR_CODE_ORDER_NONE = BUSI + ORDER + "001";
//	public static final String ERR_MSG_ORDER_NONE = "订单号不存在";
//	public static final String ERR_CODE_ORDER_PAYED = BUSI + ORDER + "002";
//	public static final String ERR_MSG_ORDER_PAYED = "订单已支付";
//	public static final String ERR_CODE_ORDER_DUPLICATE = BUSI + ORDER + "003";
//	public static final String ERR_MSG_ORDER_DUPLICATE = "商户订单重复";
//	public static final String ERR_CODE_ORDER_STATUS_ERROR = BUSI + ORDER + "004";
//	public static final String ERR_MSG_ORDER_STATUS_ERROR = "订单支付状态异常";
//	public static final String ERR_CODE_ORDER_EXPIRE = BUSI + ORDER + "004";
//	public static final String ERR_MSG_ORDER_EXPIRE = "订单有效期超时";
//	//订单号异常
//	public static final String  BALANCE= "20";
//	public static final String ERR_CODE_BALANCE_ERROR = BUSI + BALANCE + "00";
//	public static final String ERR_MSG_BALANCE_ERROR = "余额查询失败";
//	public static final String ERR_CODE_FACE_BALANCE_ERROR = BUSI + BALANCE + "001";
//	public static final String ERR_MSG_FACE_BALANCE_ERROR = "非实时卡片余额查询失败";
//	public static final String ERR_CODE_ACCT_BALANCE_ERROR = BUSI + BALANCE + "002";
//	public static final String ERR_MSG_ACCT_BALANCE_ERROR = "卡片账户余额查询失败";
//	public static final String ERR_CODE_ACCT_DEPOSIT_DUPLICATE = BUSI + BALANCE + "003";
//	public static final String ERR_MSG_ACCT_DEPOSIT_DUPLICATE = "重复账户加值操作";
//	public static final String ERR_CODE_ACCT_DEPOSIT_ERROR = BUSI + BALANCE + "004";
//	public static final String ERR_MSG_ACCT_DEPOSIT_ERROR = "账户加值操作失败";
//	
//	//添加记名卡异常
//	public static final String  BIND= "21";
//	public static final String ERR_CODE_BIND_ERROR = BUSI + BIND + "001";
//	public static final String ERR_MSG_BIND_ERROR = "添加失败";
//
//	//支付异常
//	public static final String  PAY= "22";
//	public static final String ERR_CODE_PAY_CHANNEL_ERROR = BUSI + BIND + "001";
//	public static final String ERR_MSG_PAY_CHANNEL_ERROR = "支付方式不正确";
//	public static final String ERR_CODE_SIGN_ERROR = BUSI + PAY + "002";
//	public static final String ERR_MSG_SIGN_ERROR = "签名信息异常";
//	public static final String ERR_CODE_USER_ORDER_ERROR = BUSI + PAY + "003";
//	public static final String ERR_MSG_USER_ORDER_ERROR = "用户信息或订单信息异常";
//	public static final String ERR_CODE_BAL_NOT_ENOUGH_ERROR = BUSI + PAY + "004";
//	public static final String ERR_MSG_BAL_NOT_ENOUGH_ERROR = "羊城通宝余额不足";
//	public static final String ERR_CODE_PAYED_ERROR = BUSI + PAY + "005";
//	public static final String ERR_MSG_PAYED_ERROR = "订单已支付，请勿重复支付";
//	public static final String ERR_CODE_ALREADY_REFUND = BUSI + PAY + "006";
//	public static final String ERR_MSG_ALREADY_REFUND = "订单已退款，请勿重复申请";
//
//	//挂失异常
//	public static final String  LOSS= "23";
//	public static final String ERR_CODE_LOSS_INNER = BUSI + LOSS + "001";
//	public static final String ERR_MSG_LOSS_INNER = "系统内部错误";
//	public static final String ERR_CODE_LOSS_FORBID = BUSI + LOSS + "002";
//	public static final String ERR_MSG_LOSS_FORBID = "该卡禁止挂失";
//	public static final String ERR_CODE_LOSS_NO_NAME_CARD = BUSI + LOSS + "003";
//	public static final String ERR_MSG_LOSS_NO_NAME_CARD = "没有记名卡";
//
//
//
//	//余额转移登记异常
//	public static final String  TRANSBAL= "24";
//	public static final String ERR_CODE_TRANSBAL_INNER = BUSI + TRANSBAL + "001";
//	public static final String ERR_MSG_TRANSBAL_INNER = "系统内部错误";
//	public static final String ERR_CODE_TRANSBAL_FORBID = BUSI + LOSS + "002";
//	public static final String ERR_MSG_TRANSBAL_FORBID = "该卡禁止余额转移";
//	public static final String ERR_CODE_CARD_SAME = BUSI + LOSS + "003";
//	public static final String ERR_MSG_CARD_SAME = "转入卡不能和挂失卡相同";
//	public static final String ERR_CODE_TRANS_CARD = BUSI + LOSS + "004";
//	public static final String ERR_MSG_TRANS_CARD = "待转入卡无效，请重新输入";
//	public static final String ERR_CODE_REQUEST_NO_REGISTER_RECORD = BUSI + TRANSBAL + "004";
//	public static final String ERR_MSG_REQUEST_NO_REGISTER_RECORD = "该卡未做转移登记";
//	
//	//终端公交二维码交易数据上传
//	public static final String  TRANSACTION= "25";
//	public static final String ERR_CODE_TRANSACTION_UPLOAD = BUSI + TRANSACTION + "001";
//	public static final String ERR_MSG_TRANSACTION_UPLOAD = "数据转换错误，无法保存";
//	public static final String ERR_CODE_TRANSACTION_UPLOAD_PART = BUSI + TRANSACTION + "002";
//	public static final String ERR_MSG_TRANSACTION_UPLOAD_PART = "数据上传有误";
//	public static final String ERR_CODE_TRANSACTION_UPLOAD_FAILURE = BUSI + TRANSACTION + "003";
//	public static final String ERR_MSG_TRANSACTION_UPLOAD_FAILURE = "数据格式有误";
//
//	//活动异常
//	public static final String  ACTIVITY = "26";
//	public static final String ERR_CODE_SELECT_ACTIVITY = BUSI + ACTIVITY + "001";
//	public static final String ERR_MSG_SELECT_ACTIVITY = "查询活动信息错误";
//	
//	//用户消息异常
//	public static final String USERMSG = "27";
//	public static final String ERROR_CODE_SAVE_USER_FEEDBACK = BUSI + USERMSG + "001";
//	public static final String ERROR_MSG_SAVE_USER_FEEDBACK = "保存用户信息反馈异常";
//	public static final String ERROR_CODE_UPDATE_MSG_STATUS_FAIL = BUSI + USERMSG + "002";
//	public static final String ERROR_MSG_UPDATE_MSG_STATUS_FAIL = "更新消息状态失败";
//	public static final String ERROR_CODE_QUERY_USERMSG_FAIL = BUSI + USERMSG + "003";
//	public static final String ERROR_MSG_QUERY_USERMSG_FAIL = "查询用户消息失败";
//	
//	//app更新消息异常
//	public static final String APPUPDATE = "28";
//	public static final String ERROR_CODE_QUERY_APPUPDATE_FAIL = BUSI + APPUPDATE + "001";
//	public static final String ERROR_MSG_QUERY_APPUPDATE_FAIL = "查询应用更新信息失败";
//	
//	public static final String QA = "29";
//	public static final String ERROR_CODE_QUERY_YCTQA_FAIL = BUSI + QA + "001";
//	public static final String ERROR_MSG_QUERY_YCTQA_FAIL = "查询客服问答失败";
//
//	public static final String STU = "30";
//	public static final String ERROR_CODE_STU_INFO_ERROR = BUSI + STU + "001";
//	public static final String ERROR_MSG_STU_INFO_ERROR= "查询信息不正确";


}
