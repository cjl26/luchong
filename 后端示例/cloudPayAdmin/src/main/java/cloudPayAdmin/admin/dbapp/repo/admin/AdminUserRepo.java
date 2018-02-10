package cloudPayAdmin.admin.dbapp.repo.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminUser;

public interface AdminUserRepo extends JpaRepository<TAdminUser, Long>, JpaSpecificationExecutor<TAdminUser>{

	TAdminUser findByUserNameAndPasswordAndStatus(String username, String password, Integer status);
	
	TAdminUser findByUserName(String username);
	
	TAdminUser findById(Long id);
	
	List<TAdminUser> findByIdIn(List<Long> idList);
}
