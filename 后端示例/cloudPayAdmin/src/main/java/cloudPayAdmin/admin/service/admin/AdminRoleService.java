package cloudPayAdmin.admin.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminPrivilegeChangeRecord;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminRole;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminRolePrivilege;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminUser;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminUserRole;
import cloudPayAdmin.admin.dbapp.repo.admin.AdminPrivilegeChangeRecordRepo;
import cloudPayAdmin.admin.dbapp.repo.admin.AdminRolePrivilegeRepo;
import cloudPayAdmin.admin.dbapp.repo.admin.AdminRoleRepo;
import cloudPayAdmin.admin.dbapp.repo.admin.AdminUserRepo;
import cloudPayAdmin.admin.dbapp.repo.admin.AdminUserRoleRepo;
import cloudPayAdmin.util.AdminUserInfoUtil;

@Service
@Transactional
public class AdminRoleService {
	
	/*@Autowired
	AdminUserRepo adminUserRepo;
	
	@Autowired
	AdminRoleRepo adminRoleRepo;
	
	@Autowired
	AdminRolePrivilegeRepo adminRolePrivilegeRepo;
	
	@Autowired
	AdminUserRoleRepo adminUserRoleRepo;
	
	@Autowired
	AdminPrivilegeChangeRecordRepo adminPrivilegeChangeRecordRepo;*/
	
	/**
	 * 根据id查找AdminRole
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public TAdminRole findById(Long id) {
	/*	return adminRoleRepo.findOne(id);*/
		return null;
	}
	
	/**
	 * 保存AdminRole
	 * @param role
	 */
	public void save(TAdminRole role) {
	/*	role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		adminRoleRepo.save(role);*/
	}
	
	/**
	 * 修改角色权限
	 */
	public void changeRolePrivilege(List<Long> privilegeIds,Long roleId) {
	/*	//先删除原有记录
		adminRolePrivilegeRepo.deleteByRoleId(roleId);
		//保存新的权限信息
		if(!CollectionUtils.isEmpty(privilegeIds)) {
			List<TAdminRolePrivilege> rps = new ArrayList<TAdminRolePrivilege>();
			for(Long privilegeId : privilegeIds) {
				TAdminRolePrivilege rp = new TAdminRolePrivilege();
				rp.setPrivilegeId(privilegeId);
				rp.setRoleId(roleId);
				rp.setCreateTime(new Date());
				rp.setUpdateTime(new Date());
				rps.add(rp);
			}
			
			adminRolePrivilegeRepo.save(rps);
		}
		
		//保存权限变动历史信息
		TAdminPrivilegeChangeRecord apcr = new TAdminPrivilegeChangeRecord();
		apcr.setOper_id(AdminUserInfoUtil.getLoginAdminUser().getId());
		apcr.setOperName(AdminUserInfoUtil.getLoginAdminUserName());
		apcr.setRoleId(roleId);
		apcr.setCreateTime(new Date());
		apcr.setUpdateTime(new Date());
		if(!CollectionUtils.isEmpty(privilegeIds)) {
			StringBuilder privilegeIdsStr = new StringBuilder();
			for(Long privilegeId : privilegeIds) {
				privilegeIdsStr.append(privilegeId).append(",");
			}
			apcr.setPrivilegesIds(StringUtils.substring(privilegeIdsStr.toString(), 0,privilegeIdsStr.length()-1));
		}
		adminPrivilegeChangeRecordRepo.save(apcr);*/
 	}
	
	/**
	 * 根据用户名查找 角色
	 * @param userName
	 * @return
	 */
	public List<TAdminRole> findByUserName(String userName) {
	/*	List<TAdminRole> roleList = new ArrayList<TAdminRole>();
		TAdminUser adminUser = adminUserRepo.findByUserName(userName);
		List<TAdminUserRole> userRoleList = adminUserRoleRepo.findByUserId(adminUser.getId());
		for(TAdminUserRole userRole : userRoleList) {
			TAdminRole role = adminRoleRepo.findOne(userRole.getRoleId());
			roleList.add(role);
		}
		return roleList;*/
		return null;
	}
	
	/**
	 * 根据userId查找角色
	 * @param userId
	 * @return
	 */
	public List<TAdminRole> findByUserId(Long userId) {
		/*List<TAdminUserRole> urList = adminUserRoleRepo.findByUserId(userId);
		List<Long> roleIds = new ArrayList<Long>();
		for(TAdminUserRole ur : urList) {
			roleIds.add(ur.getRoleId());
		}
		if(CollectionUtils.isEmpty(roleIds)) {
			return new ArrayList<TAdminRole>();
		} else {
			return adminRoleRepo.findByIds(roleIds);
		}*/
		return null;
	}
	
	/**
	 * 根据不存在的多个id查找记录
	 * @param ids
	 * @return
	 */
	public List<TAdminRole> findByIdsNotIn(List<Long> ids) {
		/*if(CollectionUtils.isEmpty(ids)) {
			return adminRoleRepo.findAll();
		} else {
			return adminRoleRepo.findByIdsNotIn(ids);
		}*/
		return null;
	}
	
}
