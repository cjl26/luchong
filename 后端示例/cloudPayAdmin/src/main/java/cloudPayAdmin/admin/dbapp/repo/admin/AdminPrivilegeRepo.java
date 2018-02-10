package cloudPayAdmin.admin.dbapp.repo.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminPrivilege;

public interface AdminPrivilegeRepo /*extends JpaRepository<TAdminPrivilege, Long>, JpaSpecificationExecutor<TAdminPrivilege>*/{
	
	@Query("select u from TAdminPrivilege u where u.id in ?1")
	public List<TAdminPrivilege> findByIds(List<Long> ids);
	
	@Query("select u from TAdminPrivilege u where u.id not in ?1")
	public List<TAdminPrivilege> findByIdsNotIn(List<Long> ids);
}
