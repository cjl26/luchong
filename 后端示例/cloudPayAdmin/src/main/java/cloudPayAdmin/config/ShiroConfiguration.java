package cloudPayAdmin.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;

import cloudPayAdmin.realm.AuthRealm;
import cloudPayAdmin.util.EhcacheUtil;


@Configuration
public class ShiroConfiguration {
	

	
	/*
	  * cookie对象;
	  * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
	  * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie(){
	      //System.out.println("ShiroConfiguration.rememberMeCookie()");
	      //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
	      SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
	      //<!-- 记住我cookie生效时间30天 ,单位秒;-->
	      simpleCookie.setMaxAge(7200);
	      return simpleCookie;
	}
	
	/**
	  * cookie管理对象;
	  * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
	  * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(){
	      //System.out.println("ShiroConfiguration.rememberMeManager()");
	      CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	      cookieRememberMeManager.setCookie(rememberMeCookie());
	      //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
	      cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
	      return cookieRememberMeManager;
	}


	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager ehcacheManager = new EhCacheManager();
		ehcacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		return ehcacheManager;
	}
	
	@Bean(name = "sessionIdGenerator")
	public JavaUuidSessionIdGenerator getJavaUuidSessionIdGenerator() {
		return new JavaUuidSessionIdGenerator();
	}
	
	@Bean(name = "sesssionDao")
	public EnterpriseCacheSessionDAO getEnterpriseCacheSessionDAO(@Qualifier("sessionIdGenerator")JavaUuidSessionIdGenerator javaUuidSessionIdGenerator) {
		EnterpriseCacheSessionDAO sessionDao = new EnterpriseCacheSessionDAO();
		sessionDao.setActiveSessionsCacheName(EhcacheUtil.CACHE_ACTIVESESSIONCACHE);
		sessionDao.setSessionIdGenerator(javaUuidSessionIdGenerator);
		return sessionDao;
	}
	
	// 会话管理器
	@Bean(name = "sessionManager")
	public DefaultWebSessionManager getDefaultWebSessionManager(@Qualifier("sesssionDao")EnterpriseCacheSessionDAO enterpriseCacheSessionDAO) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(1800000);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionDAO(enterpriseCacheSessionDAO);
		return sessionManager;
	}
	
	/**
	 * 自定义多用户登录踢出拦截器
	 * @return
	 */
	@Bean(name = "shiroKickOutFilter")
	public ShiroKickOutFilter getShiroKickOutFilter(@Qualifier("sessionManager") DefaultWebSessionManager defaultWebSessionManager) {
		ShiroKickOutFilter shiroKickOutFilter = new ShiroKickOutFilter();
		shiroKickOutFilter.setMaxSession(1);  //最大允许一个用户登录
		shiroKickOutFilter.setKickoutUrl("/cloudpay/admin/user/login?otherLogin=true");
		shiroKickOutFilter.setKickoutAfter(false);   // true 为踢出之前登录的用户， false为踢出之后登录的用户
		shiroKickOutFilter.setSessionManager(defaultWebSessionManager);
		return shiroKickOutFilter;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager,@Qualifier("shiroKickOutFilter")ShiroKickOutFilter shiroKickOutFilter) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		
		//增加自定义过滤器
		Map<String, Filter> filters = bean.getFilters();
		filters.put("kickout",shiroKickOutFilter);
		
		bean.setSecurityManager(manager);
		
		// 配置登录的url和登录成功的url
		bean.setLoginUrl("/cloudpay/admin/user/login");
		//设置为授权页面 url
		bean.setUnauthorizedUrl("/cloudpay/system/toUnAuthorizationPage");
		// 配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		
		// ============静态资源，不需要登录访问=================
		filterChainDefinitionMap.put("/cloudpay/adminlte/**", "anon"); // 表示可以匿名访问
		filterChainDefinitionMap.put("/cloudpay/css/**", "anon"); // 表示可以匿名访问
		filterChainDefinitionMap.put("/cloudpay/exceltemplate/**", "anon"); // 表示可以匿名访问
		filterChainDefinitionMap.put("/cloudpay/img/**", "anon"); // 表示可以匿名访问
		filterChainDefinitionMap.put("/cloudpay/js/**", "anon"); // 表示可以匿名访问	
		filterChainDefinitionMap.put("/cloudpay/imgValidate/**", "anon"); // 表示可以匿名访问
		filterChainDefinitionMap.put("/cloudpay/embed/**", "anon"); // 表示可以匿名访问
		// ============静态资源，不需要登录访问=================
		
		// 登录页面，可以匿名访问
		filterChainDefinitionMap.put("/cloudpay/admin/user/log*", "anon"); // 表示可以匿名访问	
		filterChainDefinitionMap.put("/cloudpay/menu/getMenuListData", "anon"); // 获取菜单信息可以匿名访问
		/*	filterChainDefinitionMap.put("/cloudpay/admin/toResetPasswordPage", "user"); // 获取菜单信息可以匿名访问
		
		
		filterChainDefinitionMap.put("/cloudpay/admin/user/", "perms[\"用户管理\"]"); 
		filterChainDefinitionMap.put("/cloudpay/admin/user/**", "perms[\"用户管理\"]"); 
		filterChainDefinitionMap.put("/cloudpay/role/**", "perms[\"角色管理\"]"); 
		filterChainDefinitionMap.put("/cloudpay/privilege/**", "perms[\"权限管理\"]"); 
		filterChainDefinitionMap.put("/cloudpay/menu/**", "perms[\"菜单管理\"]"); 
		
		filterChainDefinitionMap.put("/cloudpay/tranorder/**", "perms[\"交易记录查询\"]"); 
		filterChainDefinitionMap.put("/cloudpay/transdaily/**", "perms[\"统计交易记录\"]"); 
		
		
		filterChainDefinitionMap.put("/cloudpay/card/**", "perms[\"卡片管理\"]");
		filterChainDefinitionMap.put("/cloudpay/blackHis/**", "perms[\"卡片管理\"]");
		//filterChainDefinitionMap.put("/cloudpay/card/toListPage", "perms[\"测试权限20\"]");   //需要有测试权限20权限
*/		//配置记住我或认证通过可以访问
		filterChainDefinitionMap.put("/**", "kickout,user");

		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	// 配置核心安全事务管理器
	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm,EhCacheManager ehCacheManager,@Qualifier("sessionManager")DefaultWebSessionManager defaultWebSessionManager) {
		System.err.println("--------------shiro已经加载----------------");
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		authRealm.setCacheManager(ehCacheManager);
		manager.setRealm(authRealm);
		manager.setRememberMeManager(rememberMeManager());
		manager.setSessionManager(defaultWebSessionManager);
		return manager;
	}

/*	// 配置自定义的权限登录器
	@Bean(name = "authRealm1")
	public AuthRealm authRealm(EhCacheManager ehCacheManager,@Qualifier("authRealm")AuthRealm authRealm) {
		//AuthRealm authRealm = new AuthRealm();
		authRealm.setCacheManager(ehCacheManager);
		return authRealm;
	}*/

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}
	
	//解决springboot整合shiro 自定义filter报的UnavailableSecurityManagerException错误
	@Bean
	public FilterRegistrationBean delegatingFilterProxy(){
	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	    DelegatingFilterProxy proxy = new DelegatingFilterProxy();
	    proxy.setTargetFilterLifecycle(true);
	    proxy.setTargetBeanName("shiroFilter");
	    filterRegistrationBean.setFilter(proxy);
	    return filterRegistrationBean;
	}

}
