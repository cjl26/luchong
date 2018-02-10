package cloudPayAdmin.admin.dbapp.repo.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import cloudPayAdmin.admin.dbapp.entity.admin.TAdminRole;

public interface AdminRoleRepo /*extends JpaRepository<TAdminRole, Long>, JpaSpecificationExecutor<TAdminRole>*/{
	
	@Query("select u from TAdminRole u where u.id in ?1")
	public List<TAdminRole> findByIds(List<Long> ids);
	
	@Query("select u from TAdminRole u where u.id not in ?1")
	public List<TAdminRole> findByIdsNotIn(List<Long> ids);
}
