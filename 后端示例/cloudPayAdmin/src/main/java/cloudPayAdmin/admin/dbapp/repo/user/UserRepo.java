package cloudPayAdmin.admin.dbapp.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.user.TUser;



public interface UserRepo /*extends JpaRepository<TUser, Long>, JpaSpecificationExecutor<TUser>*/{
	
	@Query("select t from TUser t where t.phone = ?1")
	public TUser findByPhone(String phone);
	
}
