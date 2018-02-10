package cloudPayAdmin.admin.service.hicatcard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.user.TUser;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.user.UserRepo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class UserService {
	
	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;

	@Autowired
	UserRepo userRepo;

	@Transactional(readOnly = true)
	public List<TUser> findByOpenidAndEnable(String openid, Boolean enable) {
		return userRepo.findByOpenidAndEnable(openid, enable);
	}

	public void saveUser(TUser user) {
		userRepo.save(user);
	}

	public void saveUserList(List<TUser> userList) {
		userRepo.save(userList);
	}
	
	@Transactional(readOnly = true)
	public TUser findById(Long userId) {
		return userRepo.findOne(userId);
	}
}
