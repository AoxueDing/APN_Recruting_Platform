package com.altomni.apn.service.impl;

import com.altomni.apn.model.TenantSharing;
import com.altomni.apn.repository.vendor.TenantSharingRepo;
import com.altomni.apn.service.TenantSharingService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JIALIN on 5/4/2017.
 */
@Slf4j
@Service("tenantSharingService")
public class TenantSharingServiceImpl implements TenantSharingService{
    @Autowired
    private TenantSharingRepo tenantSharingRepo;

    public WithIncludes<TenantSharing> findById(Long id) {
        log.debug("findById is executed");

        try {
            return new WithIncludes<>(tenantSharingRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public WithIncludes<TenantSharing> saveTenantSharing(TenantSharing tenantSharing) {
        log.debug("saveTenantSharing is executed");

        try {
            return new WithIncludes<>(tenantSharingRepo.save(tenantSharing));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
