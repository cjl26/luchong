package cloudPayAdmin.admin.dbapp.repo.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminMenu;

public interface AdminMenuRepo extends JpaRepository<TAdminMenu, Long>, JpaSpecificationExecutor<TAdminMenu>{

	TAdminMenu findById(Long adminMenuId);
	
	List<TAdminMenu> findByParentId(Long parentId);
}
