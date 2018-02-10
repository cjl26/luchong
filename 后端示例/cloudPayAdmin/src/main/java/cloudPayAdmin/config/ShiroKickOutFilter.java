package cloudPayAdmin.config;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import cloudPayAdmin.util.EhcacheUtil;
import cloudPayAdmin.util.HttpRequestUtil;

/**
 * shiro登录踢出的filter
 * 
 * @author hyj
 *
 */
public class ShiroKickOutFilter extends AccessControlFilter {

	private final Logger logger = Logger.getLogger(getClass());

	private SessionManager sessionManager;

	private boolean kickoutAfter = false; // true 为踢出之前登录的用户， false为踢出之后登录的用户

	private Integer maxSession;

	private String kickoutUrl;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return HttpRequestUtil.isStaticResouceRequest((HttpServletRequest) request);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			// 如果没有登录，直接进行之后的流程
			return true;
		}

		Session session = subject.getSession();
		String username = (String) subject.getPrincipal();
		Serializable sessionId = session.getId();

		// 同步控制
		Deque<Serializable> deque = (Deque<Serializable>) EhcacheUtil.get(EhcacheUtil.CACHE_KICKOUT, username,Deque.class);
		if (deque == null) {
			deque = new LinkedList<Serializable>();
			EhcacheUtil.put(EhcacheUtil.CACHE_KICKOUT, username, deque);
		}

		// 如果队列里没有此sessionId，且用户没有被踢出；放入队列
		if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
			deque.push(sessionId);
		}

		// 如果队列里的sessionId数超出最大会话数，开始踢人
		while (deque.size() > maxSession) {
			Serializable kickoutSessionId = null;
			if (kickoutAfter) { // 如果踢出后者
				kickoutSessionId = deque.removeFirst();
			} else { // 否则踢出前者
				kickoutSessionId = deque.removeLast();
			}

			Session kickoutSession = null;
			try {
				kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
			} catch (UnknownSessionException e) {
				//   由于用户loginout后，shiro里面原本的session会失效，如果用户马上登陆，
				//   浏览器吧原本已经失效的sessionId传到后台，经过这个这个filter执行这句话就会抛出这个异常，因此要catch掉
				logger.info("sessionId:" + kickoutSessionId.toString() + "已经退出登录，无法获取对应的session");
			}
					
			if (kickoutSession != null) {
				// 设置会话的kickout属性表示踢出了
				kickoutSession.setAttribute("kickout", true);
			}
		}
		
		  //如果被踢出了，直接退出，重定向到踢出后的地址  
	    if (session.getAttribute("kickout") != null) {  
	        //会话被踢出了  
	        try {  
	            subject.logout();  
	        } catch (Exception e) { //ignore  
	        }  
	        saveRequest(request);  
	        WebUtils.issueRedirect(request, response, kickoutUrl);  
	        return false;  
	    }  
	    return true;  
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public boolean isKickoutAfter() {
		return kickoutAfter;
	}

	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}

	public Integer getMaxSession() {
		return maxSession;
	}

	public void setMaxSession(Integer maxSession) {
		this.maxSession = maxSession;
	}

	public String getKickoutUrl() {
		return kickoutUrl;
	}

	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

}
