package cloudPayAdmin.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminUser;
import cloudPayAdmin.admin.service.admin.AdminUserService;
import cloudPayAdmin.constant.SessionKeyValue;

/**
 * 登录时，用于使用rememeberme的是时候检查session里面有没有登录信息，没有就为session设置登录信息
 * @author hyj
 *
 */
public class LoginSessionInteceptor implements HandlerInterceptor {
	
	@Autowired
	AdminUserService adminUserService;

	private final Logger logger = Logger.getLogger(getClass());
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception e) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest rquest, HttpServletResponse response, Object arg2, ModelAndView arg3) throws Exception {
		
	}
	
	/**
	 * 由于重启tomcat后，session消失，但是不关闭浏览器的话，shiro仍然有rememberme功能，这样导致登录成功，但是没有session信息，需要在这里设置session登录信息
	 */
	@Override
	public boolean preHandle(HttpServletRequest rquest, HttpServletResponse response, Object obj) throws Exception {
		Subject current = SecurityUtils.getSubject();
		String username= current.getPrincipal() == null ? "" : (String)current.getPrincipal();		
		if(current.isRemembered() && StringUtils.isNotBlank(username)) {
			Session session = current.getSession();
			if(session.getAttribute(SessionKeyValue.KEY_ADMIN_USER) == null) {
				TAdminUser adminUser = adminUserService.findUserByUsername(username);
				session.setAttribute(SessionKeyValue.KEY_ADMIN_USER, adminUser);
			}
		}
		return true;
		
	}

}
