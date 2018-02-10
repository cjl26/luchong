package cloudPayAdmin.admin.dbapp.repo.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminUserRole;


public interface AdminUserRoleRepo /*extends JpaRepository<TAdminUserRole, Long>, JpaSpecificationExecutor<TAdminUserRole>*/{
	
	@Query("select u from TAdminUserRole u where u.userId = ?1")
	List<TAdminUserRole> findByUserId(Long userId);
	
	@Modifying
	@Query("delete from TAdminUserRole p where p.userId = ?1")
	public void deleteByUserId(Long userId);
}
