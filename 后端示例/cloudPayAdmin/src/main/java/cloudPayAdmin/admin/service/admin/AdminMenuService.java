package cloudPayAdmin.admin.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.admin.TAdminMenu;
import cloudPayAdmin.admin.dbapp.repo.admin.AdminMenuRepo;

@Component
@Transactional
public class AdminMenuService {
	
	@Autowired
	AdminMenuRepo adminMenuRepo;
	
	@Transactional(readOnly = true)
	public List<TAdminMenu> findAllOrderByOrderNum() {
		Sort sort = new Sort(Sort.Direction.ASC, "orderNum");
		return adminMenuRepo.findAll(sort);
	}
	
	@Transactional(readOnly = true)
	public TAdminMenu findAdminMenuById(Long adminMenuId){
		return adminMenuRepo.findById(adminMenuId);
	}
	
	@Transactional(readOnly = true)
	public List<TAdminMenu> findAdminMenuListByParentId(Long parentId){
		return adminMenuRepo.findByParentId(parentId);
	}
	
	public void saveAdminMenu(TAdminMenu adminMenu){
		adminMenuRepo.save(adminMenu);
	}
	
	public void deleteAdminMenu(TAdminMenu adminMenu){
		adminMenuRepo.delete(adminMenu);
	}
}
