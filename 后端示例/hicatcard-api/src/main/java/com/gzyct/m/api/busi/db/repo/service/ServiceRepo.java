package com.gzyct.m.api.busi.db.repo.service;

import com.gzyct.m.api.busi.db.entity.hicatcard.TService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepo extends JpaRepository<TService, Long>, JpaSpecificationExecutor<TService> {

    //List<TService> findByServiceId(Long serviceId);

    TService findByServiceId(Long serviceId);

    @Query("select u from TService u where u.serviceId not in ?1")
    public List<TService> findByIdNotIn(List<Long> serviceIds);
}
