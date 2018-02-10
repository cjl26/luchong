package cloudPayAdmin.admin.dbapp.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import cloudPayAdmin.admin.dbapp.entity.user.TUserAuth;

public interface UserAuthRepo /*extends JpaRepository<TUserAuth, Long>, JpaSpecificationExecutor<TUserAuth>*/{

	
	TUserAuth findByIdentityTypeAndIdentifier(String identityType,String identifier);
	TUserAuth findByUserIdAndIdentityType(Long userId,String identityType);
	
	//重置密码
	@Modifying
	@Query("update #{#entityName} a set a.credential=:password where a.identityType='phone' and a.identifier = :phone")
	int resetPassword(@Param("phone") String phone,@Param("password") String password);
	
	//修改密码
	@Modifying
	@Query("update #{#entityName} a set a.credential=:password where a.userId = :userId and a.identityType='phone' ")
	int modifyPassword(@Param("userId") Long userId,@Param("password") String password);
}
