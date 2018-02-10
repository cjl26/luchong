package cloudPayAdmin.admin.service.hicatcard.merchant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.entity.hicatcard.BaseEntity;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchant;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantService;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantSettle;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.merchant.TMerchantSettleService;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.service.TService;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.transaction.TTransaction;
import cloudPayAdmin.admin.dbapp.entity.hicatcard.transaction.TTransactionService;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.merchant.MerchantRepo;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.merchant.MerchantServiceRepo;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.merchant.MerchantSettleRepo;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.merchant.MerchantSettleServiceRepo;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.service.ServiceRepo;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.transaction.TransactionRepo;
import cloudPayAdmin.admin.dbapp.repo.hicatcard.transaction.TransactionServiceRepo;
import cloudPayAdmin.admin.entity.http.MerchantSettleResp;
import cloudPayAdmin.admin.entity.http.MerchantSettleResult;
import cloudPayAdmin.util.AdminUserInfoUtil;
import cloudPayAdmin.util.IdGenerator;
import cloudPayAdmin.util.TencentMapUtil;
import cloudPayAdmin.util.TimeUtil;

@Component
@Transactional
public class MerchantService {
	
	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	MerchantRepo merchantRepo;
	
	@Autowired
	MerchantServiceRepo merchantServiceRepo;
	
	@Autowired
	ServiceRepo serviceRepo;
	
	@Autowired
	MerchantSettleRepo merchantSettleRepo;
	
	@Autowired
	MerchantSettleServiceRepo merchantSettleServiceRepo;
	
	@Autowired
	TransactionRepo transactionRepo;
	
	@Autowired
	TransactionServiceRepo transactionServiceRepo;
	
	public void deleteById(Long merchantId) {
		if(merchantId != null) {
			TMerchant merchant = merchantRepo.findOne(merchantId);
			if(merchant != null) {
				merchant.setEnable(BaseEntity.DISABLE);
				merchantRepo.save(merchant);
			}		
		}
	}
	
	@Transactional(readOnly=true)
	public List<TMerchantSettleService> findMerchantSettleServiceByMerchantSettleId(Long merchantSettleId) {
		return merchantSettleServiceRepo.findByMerchantSettleId(merchantSettleId);
	}
	
	@Transactional(readOnly=true)
	public TMerchant findById(Long merchantId) {
		TMerchant merchant = merchantRepo.findOne(merchantId);
		
		HibernateEntityManager hEntityManager = (HibernateEntityManager)entityManager;
		hEntityManager.getSession().evict(merchant);		
		
		return merchant ;
	}
	
	/**
	 * 根据id获得本商户还没选择的服务项
	 * @param merchantId
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<TService> findNotSelectSrviceById(Long merchantId) {
		
		List<TMerchantService> merchatnServiceList = merchantServiceRepo.findByMerchantId(merchantId);
		List<Long> selectedServiceList = new ArrayList<Long>();
		for(TMerchantService merchantService : merchatnServiceList) {
			selectedServiceList.add(merchantService.getServiceId());
		}	
		
		List<TService> serviceList = null;
		if(!CollectionUtils.isEmpty(selectedServiceList)) {
			serviceList = serviceRepo.findByIdNotIn(selectedServiceList);
		} else {
			serviceList = serviceRepo.findAll(new Specification<TService>() {				
				@Override
				public Predicate toPredicate(Root<TService> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Path<Boolean> enable = root.get("enable");
					query.where(cb.equal(enable, Boolean.TRUE));
					return null;
				}
			}); 
		}
		return serviceList;
	}
	
	/**
	 * 增加商户的服务项
	 * @param merchantId
	 * @param serviceId
	 * @param fee
	 * @param status
	 * @return
	 */
	public TMerchantService saveMerchantService(Long merchantId,Long serviceId,Integer fee,String status) {
		TMerchantService merchantService = new TMerchantService();
		merchantService.setMerchantId(merchantId);
		merchantService.setServiceId(serviceId);
		merchantService.setFee(fee);
		merchantService.setStatus(status);
		merchantService.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
		merchantService.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
		return merchantServiceRepo.save(merchantService);
	}
	
	/**
	 * 保存/修改商户
	 * @param merchant
	 * @return
	 */
	public TMerchant saveMerchant(TMerchant merchant) {
		Long mercantId = merchant.getMerchantId();
		
		if(mercantId != null) {    //修改
			TMerchant merch = merchantRepo.findOne(mercantId);
			merch.setPhone(merchant.getPhone());
			merch.setWebUrl(merchant.getWebUrl());
			merch.setStatus(merchant.getStatus());   //status字段没用，暂时不更新
			merch.setDetail(merchant.getDetail());
			merch.setName(merchant.getName());
			merch.setPictureUrl(StringUtils.isBlank(merchant.getPictureUrl()) ? merch.getPictureUrl() : merchant.getPictureUrl());
			merch.setDetailPictureUrl(StringUtils.isBlank(merchant.getDetailPictureUrl()) ? merch.getDetailPictureUrl() : merchant.getDetailPictureUrl());
			merch.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			
			//如果 地址不等于空并且新地址不等于旧地址
			if(StringUtils.isNotBlank(merchant.getAddress()) && !StringUtils.equals(merchant.getAddress(), merch.getAddress())) {
				Map locationMap = TencentMapUtil.getLocationByAddress(merchant.getAddress());
				
				//经度
				Float longitude = MapUtils.getFloat(MapUtils.getMap(MapUtils.getMap(locationMap,"result"), "location") , "lng");
				//纬度
				Float latitude = MapUtils.getFloat(MapUtils.getMap(MapUtils.getMap(locationMap,"result"), "location") , "lat");
				
				String province = MapUtils.getString(MapUtils.getMap(MapUtils.getMap(locationMap,"result"), "address_components") , "province");
				String city = MapUtils.getString(MapUtils.getMap(MapUtils.getMap(locationMap,"result"), "address_components") , "city");
				
				merch.setLongitude(String.valueOf(longitude));
				merch.setLatitude(String.valueOf(latitude));
				merch.setProvince(province);
				merch.setCity(city);
				merch.setAddress(merchant.getAddress());
			}
			
			return merchantRepo.save(merch);
		} else {    //保存			
			merchant.setCreator(AdminUserInfoUtil.getLoginAdminUserName());
			merchant.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			merchant.setUpdateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
			//调用腾讯地图api获得经度、纬度、省份，城市
			if(StringUtils.isNotBlank(merchant.getAddress())) {
				Map locationMap = TencentMapUtil.getLocationByAddress(merchant.getAddress());
								
				//经度
				Float longitude = MapUtils.getFloat(MapUtils.getMap(MapUtils.getMap(locationMap,"result"), "location") , "lng");
				//纬度
				Float latitude = MapUtils.getFloat(MapUtils.getMap(MapUtils.getMap(locationMap,"result"), "location") , "lat");
				
				String province = MapUtils.getString(MapUtils.getMap(MapUtils.getMap(locationMap,"result"), "address_components") , "province");
				String city = MapUtils.getString(MapUtils.getMap(MapUtils.getMap(locationMap,"result"), "address_components") , "city");
				
				merchant.setLongitude(String.valueOf(longitude));
				merchant.setLatitude(String.valueOf(latitude));
				merchant.setProvince(province);
				merchant.setCity(city);
			}
					
			return merchantRepo.save(merchant);
		}
	}
	
	/**
	 * 保存结算数据
	 * @param merchantSettle
	 * @param merchantSettleServiceList
	 */
	public void saveMerchantSettleData(TMerchantSettle merchantSettle,List<TMerchantSettleService> merchantSettleServiceList,List<TTransaction> transactionList) {
		TMerchantSettle newMerchantSettle = null;
		if(merchantSettle != null) {
			newMerchantSettle =	merchantSettleRepo.save(merchantSettle);
		}
		
		if(!CollectionUtils.isEmpty(merchantSettleServiceList) && newMerchantSettle != null) {
			for(TMerchantSettleService merchantSettleService : merchantSettleServiceList) {				
				merchantSettleService.setMerchantSettleId(newMerchantSettle.getMerchantSettleId());
			}
			merchantSettleServiceRepo.save(merchantSettleServiceList);
		}
		
		String updateTime = TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR);
		for(TTransaction transaction : transactionList) {
			transaction.setStatus(TTransaction.STATUS_HAS_SETTLE);
			transaction.setUpdateTime(updateTime);
		}
		transactionRepo.save(transactionList);
		
	}
	
	/**
	 * 获得结算数据
	 * @param merchantId
	 * @return
	 */
	@Transactional(readOnly=true)
	public MerchantSettleResp getMerchantSettleData(Long merchantId) {
		
		MerchantSettleResp resp = new MerchantSettleResp();
				
		//获得结算开始时间
		String startSettleTime = merchantSettleRepo.getMaxEndTimeByMerchantId(merchantId);
		String endSettleTime = TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR);
		
		List<TTransaction> transactionList = null;
		
		//之前没有结算记录
		if(StringUtils.isBlank(startSettleTime)) {
			transactionList = transactionRepo.findByCreateTime(merchantId, endSettleTime);
		} else {   //之前有结算记录
			transactionList = transactionRepo.findByCreateTime(merchantId, startSettleTime, endSettleTime);
		}
		
		List<TTransactionService> transactionServiceList = new ArrayList<TTransactionService>();
		
		if(!CollectionUtils.isEmpty(transactionList)) {
			List<Long> transactionIds = new ArrayList<Long>();
			for(TTransaction transaction : transactionList) {
				transactionIds.add(transaction.getTransactionId());
			}
			
			Sort.Order serviceIdOrder = new Sort.Order(Sort.Direction.DESC, "serviceId");
			Sort.Order merchantFeeOrder = new Sort.Order(Sort.Direction.DESC, "merchantFee");
			Sort sort = new Sort(serviceIdOrder,merchantFeeOrder);
			Pageable pageable = new PageRequest(0, Integer.MAX_VALUE, sort);
			transactionServiceList = transactionServiceRepo.findByTransactionIds(transactionIds,pageable);
		}
		
		//下面根据 服务项和价格分类
		Long lastServiceId = null;
		Integer lastMerchantFee = null;
		
		List<List<TTransactionService>> list = new ArrayList<List<TTransactionService>>();
		
		List<TTransactionService> sortTransactionServiceList = null;
		
		for(TTransactionService transactionService : transactionServiceList) {
			Long serviceId = transactionService.getServiceId();
			Integer merchantFee = transactionService.getMerchantFee();
			//如果是同一组
			if(serviceId == lastServiceId && merchantFee == lastMerchantFee) {
				
				sortTransactionServiceList.add(transactionService);
		
			} else {    //不是同一组
							
				if(!CollectionUtils.isEmpty(sortTransactionServiceList)) {
					list.add(sortTransactionServiceList);
				}
				
				sortTransactionServiceList = new ArrayList<TTransactionService>();
				sortTransactionServiceList.add(transactionService);
			}
			
			//如果这个对象是 transactionServiceList 的最后一个
			if(transactionServiceList.indexOf(transactionService) == transactionServiceList.size()-1) {
				list.add(sortTransactionServiceList);
			}
			
			lastServiceId = transactionService.getServiceId();
			lastMerchantFee = transactionService.getMerchantFee();
		}
		
		List<MerchantSettleResult> sellteResultList = new ArrayList<MerchantSettleResult>();
		String settleTotal = "0";
		for(List<TTransactionService> sortedtransactionServiceList : list) {
			MerchantSettleResult settleResult = new MerchantSettleResult();
			String serviceName = serviceRepo.findOne(sortedtransactionServiceList.get(0).getServiceId()).getServiceName();
			String transcationFee = sortedtransactionServiceList.get(0).getMerchantFee() + "";
			String transactionTime = sortedtransactionServiceList.size() + "";
			String transactionTotal = new BigDecimal(transcationFee).multiply(new BigDecimal(transactionTime)).toString();
			settleResult.setServiceId(sortedtransactionServiceList.get(0).getServiceId());
			settleResult.setServiceName(serviceName);
			settleResult.setTranscationFee(new BigDecimal(transcationFee).multiply(new BigDecimal("0.01")).toString());
			settleResult.setTransactionTime(transactionTime);
			settleResult.setTransactionTotal(new BigDecimal(transactionTotal).multiply(new BigDecimal("0.01")).toString());
			sellteResultList.add(settleResult);
			
			settleTotal = new BigDecimal(settleTotal).add(new BigDecimal(transactionTotal)).toString();
		}
		resp.setMerchantId(merchantId);
		resp.setSettleStartTime(StringUtils.isNotBlank(startSettleTime) ? TimeUtil.transferDateFormat(startSettleTime, TimeUtil.DATE_PATTERN_NOSEPARTOR, "yyyy/MM/dd HH:mm") : "2017/01/01 10:10");
		resp.setSettleEndTime(TimeUtil.transferDateFormat(endSettleTime, TimeUtil.DATE_PATTERN_NOSEPARTOR, TimeUtil.DATE_PATTERN_WITH_SLASH));
		resp.setSettleFee(new BigDecimal(settleTotal).multiply(new BigDecimal("0.01")).toString());
		resp.setMerchantSettleResultList(sellteResultList);
		resp.setTransactionList(transactionList);
		return resp;
	}
}
