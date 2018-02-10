package com.gzyct.m.api.busi.db.service.hicatcard.service;

import com.gzyct.m.api.busi.db.entity.hicatcard.TService;
import com.gzyct.m.api.busi.db.repo.service.ServiceRepo;
import com.gzyct.m.api.busi.util.TimeUtil;
//import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional
public class ServiceService {

    @Autowired
    ServiceRepo serviceRepo;

    public TService findById(Long serviceId) {
        return serviceRepo.findOne(serviceId);
    }

    public void deleteById(Long serviceId) {
        if (serviceId != null) {
            TService service = serviceRepo.findOne(serviceId);
            if (service != null) {
                service.setEnable(Boolean.FALSE);
                serviceRepo.save(service);
            }
        }
    }

    public TService saveService(TService service) {
        Long serviceId = service.getServiceId();
        if (serviceId != null) {    // 更新
            TService serv = serviceRepo.findOne(serviceId);
            serv.setServiceName(service.getServiceName());
            serv.setStatus(service.getStatus());
            serv.setUpdateTIme(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
            return serviceRepo.save(serv);
        } else {       //保存
            service.setCreateTime(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
            service.setUpdateTIme(TimeUtil.getCurrTime(TimeUtil.DATE_PATTERN_NOSEPARTOR));
            return serviceRepo.save(service);
        }
    }

    /**
     * 根据id查找不在id范围的服务记录
     *
     * @param serviceIds
     * @return
     */
//    public List<TService> findByIdNotIn(List<Long> serviceIds) {
////        if (!CollectionUtils.isEmpty(serviceIds)) {
////            return serviceRepo.findByIdNotIn(serviceIds);
////        } else {
////            return serviceRepo.findAll();
////        }
//    }

}
