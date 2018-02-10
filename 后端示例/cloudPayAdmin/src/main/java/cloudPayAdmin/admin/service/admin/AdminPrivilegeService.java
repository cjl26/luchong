package cloudPayAdmin.admin.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminPrivilege;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminRolePrivilege;
import cloudPayAdmin.admin.dbapp.repo.admin.AdminPrivilegeRepo;
import cloudPayAdmin.admin.dbapp.repo.admin.AdminRolePrivilegeRepo;

@Component
@Transactional
public class AdminPrivilegeService {
	
	/*@Autowired
	AdminRolePrivilegeRepo adminRolePrivilegeRepo;
	
	@Autowired
	AdminPrivilegeRepo adminPrivilegeRepo;*/
	
	/**
	 * 根据id查找记录
	 * @param privilegeId
	 * @return
	 */
	public TAdminPrivilege findById(Long privilegeId) {
		//return adminPrivilegeRepo.findOne(privilegeId);
		return null;
	}
	
	/**
	 * 保存权限记录
	 * @param adminPrivilege
	 * @return
	 */
	public TAdminPrivilege save(TAdminPrivilege adminPrivilege) {
		/*adminPrivilege.setCreateTime(new Date());
		adminPrivilege.setUpdateTime(new Date());
		return adminPrivilegeRepo.save(adminPrivilege);*/
		return null;
	}
	
	/**
	 * 根据不存在的多个id查找记录
	 * @param ids
	 * @return
	 */
	public List<TAdminPrivilege> findByIdsNotIn(List<Long> ids) {
		/*if(CollectionUtils.isEmpty(ids)) {
			return adminPrivilegeRepo.findAll();
		} else {
			return adminPrivilegeRepo.findByIdsNotIn(ids);
		}*/
		return null;
	}
	
	/**
	 * 根据角色id查找权限
	 * @param roleId
	 * @return
	 */
	public List<TAdminPrivilege> findByRoleId(Long roleId) {
	/*	List<TAdminRolePrivilege> rolePriList = adminRolePrivilegeRepo.findByRoleId(roleId);
		List<Long> privilegeIds = new ArrayList<Long>();
		for(TAdminRolePrivilege rp : rolePriList) {
			privilegeIds.add(rp.getPrivilegeId());
		}
		if(CollectionUtils.isEmpty(privilegeIds)) {
			return new ArrayList<TAdminPrivilege>();
		} else {
			return adminPrivilegeRepo.findByIds(privilegeIds);
		}*/
		return null;
	}	
	
	/**
	 * 删除权限和权限-角色表的数据
	 * @param privilegeId
	 */
	public void deletePrivilege(Long privilegeId) {
		/*adminPrivilegeRepo.delete(privilegeId);
		List<TAdminRolePrivilege> rps = adminRolePrivilegeRepo.findByPrivilegeId(privilegeId);
		adminRolePrivilegeRepo.delete(rps);*/
	}
	
}
