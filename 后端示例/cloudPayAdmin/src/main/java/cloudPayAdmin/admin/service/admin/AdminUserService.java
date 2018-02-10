package cloudPayAdmin.admin.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminRoleChangeRecord;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminUser;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminUserRole;
import cloudPayAdmin.admin.dbapp.repo.admin.AdminUserRepo;
import cloudPayAdmin.admin.dbapp.repo.admin.AdminUserRoleRepo;
import cloudPayAdmin.util.AdminUserInfoUtil;


@Component
@Transactional
public class AdminUserService {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	AdminUserRepo adminUserRepo;
	
	/*@Autowired
	AdminUserRoleRepo adminUserRoleRepo;*/
	
	@Transactional(readOnly = true)
	public TAdminUser findUserByUsername(String username) {
		return adminUserRepo.findByUserName(username);
	}
	
	@Transactional(readOnly = true)
	public TAdminUser findAvailableUserByUsernameAndPassword(String username, String password){
		return adminUserRepo.findByUserNameAndPasswordAndStatus(username, password, TAdminUser.USER_STATUS_IN_USE);
	}
	
	@Transactional(readOnly = true)
	public TAdminUser findUserById(Long id) {
		return adminUserRepo.findById(id);
		
	}
	
	@Transactional(readOnly = true)
	public List<TAdminUser> findByIdIn(List<Long> idList){
		return adminUserRepo.findByIdIn(idList);
	}
	
	public void saveAdminUserList(List<TAdminUser> adminUserList) {
		adminUserRepo.save(adminUserList);
	}

	public void saveAdminUser(TAdminUser adminUser) {
		adminUserRepo.save(adminUser);
	}
	
	/**
	 * 修改用戶角色
	 * @param roleIds
	 * @param userId
	 */
	public void changeUserRole(List<Long> roleIds,Long userId) {
		/*//刪除原有的角色信息
		adminUserRoleRepo.deleteByUserId(userId);
		//保存新的角色信息
		if(!CollectionUtils.isEmpty(roleIds)) {
			List<TAdminUserRole> urs = new ArrayList<TAdminUserRole>();
			for(Long roleId : roleIds) {
				TAdminUserRole ur = new TAdminUserRole();
				ur.setUserId(userId);
				ur.setRoleId(roleId);
				ur.setCreateTime(new Date());
				ur.setUpdateTime(new Date());
				urs.add(ur);
			}
			//adminUserRoleRepo.save(urs);
		}
		
		//保存角色變動歷史信息
		TAdminRoleChangeRecord arcr = new TAdminRoleChangeRecord();
		arcr.setOperId(AdminUserInfoUtil.getLoginAdminUser().getId());
		arcr.setOperName(AdminUserInfoUtil.getLoginAdminUserName());
		arcr.setUserId(userId);
		arcr.setUpdateTime(new Date());
		arcr.setCreateTime(new Date());
		if(!CollectionUtils.isEmpty(roleIds)) {
			StringBuilder roleIdsStr = new StringBuilder();
			for(Long roleId : roleIds) {
				roleIdsStr.append(roleId).append(",");
			}
			arcr.setRoleIds(StringUtils.substring(roleIdsStr.toString(), 0,roleIdsStr.length()-1));
		}*/
	}
}
