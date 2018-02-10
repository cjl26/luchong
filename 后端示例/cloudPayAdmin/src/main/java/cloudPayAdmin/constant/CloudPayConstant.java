package cloudPayAdmin.constant;

/**
 * Created by chenjiajian on 2017/8/11.
 */
public class CloudPayConstant {
    public static int CLOUDPAY_CARD_AVAILABLE = 1;
    public static int CLOUDPAY_CARD_UNAVAILABLE = 0;
    public static int BLACK_LIST_ADD = 1;
    public static int BLACK_LIST_REMOVE = 0;
    public static int BLACK_LIST_SOURCE_USER = 1;//用户发起的禁用卡片云支付
    public static int BLACK_LIST_SOURCE_SYS = 2;//系统发起的禁用卡片云支付
    public static int BLACK_LIST_IS_SYNC = 1;//已同步到终端后台
    public static int BLACK_LIST_NOT_SYNC = 0;//没同步到终端后台
}
