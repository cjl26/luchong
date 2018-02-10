package com.gzyct.m.api.busi.db.service.hicatcard.merchant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.gzyct.m.api.busi.bean.hicatcard.MerchantListItem;
import com.gzyct.m.api.busi.bean.hicatcard.MerchantServiceItem;
import com.gzyct.m.api.busi.db.entity.hicatcard.TService;
import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchant;
import com.gzyct.m.api.busi.db.entity.hicatcard.merchant.TMerchantService;
import com.gzyct.m.api.busi.db.repo.merchant.MerchantRepo;
import com.gzyct.m.api.busi.db.repo.merchant.MerchantServiceRepo;
import com.gzyct.m.api.busi.db.repo.service.ServiceRepo;
import com.gzyct.m.api.busi.util.CommonConvertor;

@Component
@Transactional
public class MerchantService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${image.upload.url}")
	private String imageUploadUrl;

	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;

	@Autowired
	MerchantServiceRepo merchantServiceRepo;

	@Autowired
	MerchantRepo merchantRepo;

	@Autowired
	ServiceRepo serviceRepo;

	@Transactional(readOnly = true)
	public List<MerchantListItem> findMerchantListItemByPoint(String latitude, String longitude, Integer page,
			Integer pagesize) throws Exception {

		String sql = "SELECT ROUND( ST_DISTANCE( POINT(?1, ?2), point(t.LATITUDE, t.LONGITUDE) ) * 111195 ) AS distant, t.MERCHANT_ID, t.MERCHANT_NUMBER, t.NAME, t.PHONE, t.PROVINCE, t.CITY, t.ADDRESS, "
				+ "t.LONGITUDE, t.LATITUDE, t.PICTURE_URL, t.WEB_URL, t.DETAIL, t.STATUS, t.DETAIL_PICTURE_URL "
				+ " FROM t_merchant t WHERE t.enable = 1 and STATUS = '1'" + " ORDER BY distant ASC LIMIT 10";// 老板要求只返回最近的5个

		// String sql = "SELECT ROUND( ST_DISTANCE( POINT(?1, ?2), point(t.LATITUDE,
		// t.LONGITUDE) ) * 111195 ) AS distant, t.MERCHANT_ID, t.MERCHANT_NUMBER,
		// t.NAME, t.PHONE, t.PROVINCE, t.CITY, t.ADDRESS, "
		// + "t.LONGITUDE, t.LATITUDE, t.PICTURE_URL, t.WEB_URL, t.DETAIL, t.STATUS "
		// + " FROM t_merchant t WHERE t.enable = 1 " + " ORDER BY distant ASC LIMIT " +
		// (page - 1) * pagesize
		// + "," + pagesize;

		logger.info("findUserWithholdChannel sql = " + sql);

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, latitude);
		query.setParameter(2, longitude);

		List sqlResult = query.getResultList();
		logger.info("resultList = " + JSON.toJSONString(sqlResult));

		// t.MERCHANT_ID, t.MERCHANT_NUMBER, t.NAME, t.PHONE, t.PROVINCE, t.CITY,
		// t.ADDRESS, "
		// + "t.LONGITUDE, t.LATTITUDE, t.PICTURE_URL, t.WEB_URL, t.DETAIL, t.STATUS
		List<MerchantListItem> merchantListItemList = new ArrayList<MerchantListItem>();
		for (int i = 0; i < sqlResult.size(); i++) {
			Object[] objects = (Object[]) sqlResult.get(i);
			MerchantListItem merchantListItem = new MerchantListItem();
			String distance = CommonConvertor.convertObjectToStringAvoidNull(objects[0]);
			merchantListItem.setDistance((Float.parseFloat(distance) / 1000) + "km");
			merchantListItem.setMerchant_id(CommonConvertor.convertObjectToStringAvoidNull(objects[1]));
			merchantListItem.setMerchant_number(CommonConvertor.convertObjectToStringAvoidNull(objects[2]));
			merchantListItem.setName(CommonConvertor.convertObjectToStringAvoidNull(objects[3]));
			merchantListItem.setPhone(CommonConvertor.convertObjectToStringAvoidNull(objects[4]));
			merchantListItem.setProvince(CommonConvertor.convertObjectToStringAvoidNull(objects[5]));
			merchantListItem.setCity(CommonConvertor.convertObjectToStringAvoidNull(objects[6]));
			merchantListItem.setAddress(CommonConvertor.convertObjectToStringAvoidNull(objects[7]));
			merchantListItem.setLongitude(CommonConvertor.convertObjectToStringAvoidNull(objects[8]));
			merchantListItem.setLatitude(CommonConvertor.convertObjectToStringAvoidNull(objects[9]));
			String picUrlDb = CommonConvertor.convertObjectToStringAvoidNull(objects[10]);
			if (picUrlDb == null || picUrlDb.length() == 0) {
				picUrlDb = "http://hicatcitycardimage.6so2o.com/wxappimage/mch_icon_default.png";
			}
			if (picUrlDb.startsWith(imageUploadUrl)) {
				merchantListItem.setPicture_url(picUrlDb);
			} else {
				merchantListItem.setPicture_url(imageUploadUrl + picUrlDb);
			}

			merchantListItem.setWeb_url(CommonConvertor.convertObjectToStringAvoidNull(objects[11]));
			merchantListItem.setDetail(CommonConvertor.convertObjectToStringAvoidNull(objects[12]));
			merchantListItem.setStatus(CommonConvertor.convertObjectToStringAvoidNull(objects[13]));
			merchantListItem.setDetail_picture_url(
					imageUploadUrl + CommonConvertor.convertObjectToStringAvoidNull(objects[14]));

			// TODO 可以改成left join + group
			if (StringUtils.isNotBlank(merchantListItem.getMerchant_id())) {
				List<TMerchantService> merchantServiceList = merchantServiceRepo
						.findByMerchantIdAndEnable(Long.valueOf(merchantListItem.getMerchant_id()), true);
				List<MerchantServiceItem> merchantServiceItemList = new ArrayList<MerchantServiceItem>();
				for (TMerchantService merchantService : merchantServiceList) {
					MerchantServiceItem merchantServiceItem = new MerchantServiceItem();
					merchantServiceItem.setMerchant_service_id(merchantService.getMerchantServiceId());
					TService service = serviceRepo.findByServiceId(merchantService.getServiceId());
					if (service != null) {
						merchantServiceItem.setService_name(service.getServiceName());
					} else {
						merchantServiceItem.setService_name("");
					}
					merchantServiceItem.setFee(merchantService.getFee());
					merchantServiceItemList.add(merchantServiceItem);
				}
				merchantListItem.setService(merchantServiceItemList);
			}
			merchantListItemList.add(merchantListItem);
		}
		return merchantListItemList;
	}

	@Transactional(readOnly = true)
	public TMerchant findByMerchantId(Long merchant_Id, Boolean enable) throws Exception {
		return merchantRepo.findByMerchantIdAndEnable(merchant_Id, enable);
	}

	@Transactional(readOnly = true)
	public List<TMerchant> findAllMerchant() throws Exception {
		return merchantRepo.findAll();
	}
}
