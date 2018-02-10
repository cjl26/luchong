package com.gzyct.m.api.busi.db.service.hicatcard;

import com.gzyct.m.api.busi.db.entity.hicatcard.TWxToken;
import com.gzyct.m.api.busi.db.repo.hicatcard.WxTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class WxTokenService {

    @Autowired
    WxTokenRepo wxTokenRepo;

    @Transactional(readOnly = true)
    public List<TWxToken> findByEnable(Boolean enable) {
        return wxTokenRepo.findByEnable(enable);
    }

    public void saveWxToken(TWxToken wxToken) {
        wxTokenRepo.save(wxToken);
    }
}
