package cloudPayAdmin.admin.dbapp.repo.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cloudPayAdmin.admin.dbapp.entity.user.TUserAcct;

public interface UserAcctRepo /*extends JpaRepository<TUserAcct, Long>, JpaSpecificationExecutor<TUserAcct>*/{

	public TUserAcct findByUserId(Long userId);
	
	/**
	 * 根据 user_id 和 acct_type 找到账户记录
	 * @param userId
	 * @param type
	 * @return
	 */
	public TUserAcct findByUserIdAndAcctType(long userId,long type);
	
	/**
	 * 根据id找数据
	 * @param id
	 * @return
	 */
	public TUserAcct findById(Long id);

	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} set balance = balance -?2 where id = ?1")
	int deductBalanceById(Long id, long fee);

	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} set balance = balance +?2 where id = ?1")
	int addBalanceById(Long id, long fee);
}


