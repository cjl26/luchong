package cloudPayAdmin.admin.dbapp.repo.hicatcard.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUser;


public interface UserRepo extends JpaRepository<TUser, Long>, JpaSpecificationExecutor<TUser> {

	List<TUser> findByOpenidAndEnable(String openid, Boolean enable);
}
