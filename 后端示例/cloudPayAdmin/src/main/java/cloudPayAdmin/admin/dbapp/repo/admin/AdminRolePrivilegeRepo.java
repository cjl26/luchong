package cloudPayAdmin.admin.dbapp.repo.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminRolePrivilege;

public interface AdminRolePrivilegeRepo/* extends JpaRepository<TAdminRolePrivilege, Long>, JpaSpecificationExecutor<TAdminRolePrivilege>*/{
	
	@Query("select u from TAdminRolePrivilege u where u.roleId = ?1")
	public List<TAdminRolePrivilege> findByRoleId(Long roleId);
	
	@Modifying
	@Query("delete from TAdminRolePrivilege p where p.roleId = ?1")
	public void deleteByRoleId(Long roldId);
	
	@Query("select u from TAdminRolePrivilege u where u.privilegeId = ?1")
	public List<TAdminRolePrivilege> findByPrivilegeId(Long privilegeId);
}
