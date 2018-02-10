package cloudPayAdmin.util;

import org.apache.shiro.SecurityUtils;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminUser;
import cloudPayAdmin.constant.SessionKeyValue;

public class AdminUserInfoUtil {
	
	public static TAdminUser getLoginAdminUser() {
		Object obj = SecurityUtils.getSubject().getSession().getAttribute(SessionKeyValue.KEY_ADMIN_USER);
		if(obj != null) {
			return (TAdminUser)obj;
		}
		return null;
	}
	
	public static String getLoginAdminUserName() {
		Object obj = SecurityUtils.getSubject().getSession().getAttribute(SessionKeyValue.KEY_ADMIN_USER);
		if(obj != null) {
			return ((TAdminUser)obj).getUserName();
		}
		return null;
	}
}
