package cloudPayAdmin.realm;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminPrivilege;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminRole;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminUser;
import cloudPayAdmin.admin.service.admin.AdminPrivilegeService;
import cloudPayAdmin.admin.service.admin.AdminRoleService;
import cloudPayAdmin.admin.service.admin.AdminUserService;

@Component("authRealm")
public class AuthRealm  extends AuthorizingRealm{
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
		
//		AdminRoleService adminRoleService = applicationContext.getBean(AdminRoleService.class);
//		AdminPrivilegeService adminPrivilegeService = applicationContext.getBean(AdminPrivilegeService.class);
//		
//		String userName=(String)principals.getPrimaryPrincipal();
//		
//		//如果是超级管理员，则设置所有权限
//		if(StringUtils.equals(userName, "admin")) {
//			List<TAdminPrivilege> privileges = adminPrivilegeService.findByIdsNotIn(null);
//			Set<String> privilegeNames = new HashSet<String>();
//			for(TAdminPrivilege privilege : privileges) {
//				privilegeNames.add(privilege.getPrivilegeName());
//			}
//			SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
//			authorizationInfo.setStringPermissions(privilegeNames);
//			return authorizationInfo;
//		}
//				
//		List<TAdminRole> roles = adminRoleService.findByUserName(userName);
//		Set<String> roleNames = new HashSet<String>();
//		Set<String> privilegeNames = new HashSet<String>();
//		for(TAdminRole role : roles) {
//			roleNames.add(role.getRoleName());
//			List<TAdminPrivilege> privileges = adminPrivilegeService.findByRoleId(role.getId());
//			for(TAdminPrivilege privilege : privileges) {
//				privilegeNames.add(privilege.getPrivilegeName());
//			}
//		}
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
//		authorizationInfo.setRoles(roleNames);
//		authorizationInfo.setStringPermissions(privilegeNames);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		AdminUserService adminUserService = applicationContext.getBean(AdminUserService.class);
		
		String username=(String)token.getPrincipal();
		String password =  new String((char[])token.getCredentials());
		
		TAdminUser adminUser = adminUserService.findAvailableUserByUsernameAndPassword(username, password);
		//如果有这个用户
		if(adminUser != null) {
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(username,password,this.getName());
			return authcInfo;
		} else {
			return null;
		}
	
	}

}
