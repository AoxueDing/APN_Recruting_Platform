package com.altomni.apn.service.impl;

import com.altomni.apn.model.Tenant;
import com.altomni.apn.model.User;
import com.altomni.apn.repository.vendor.TenantRepo;
import com.altomni.apn.repository.vendor.UserRepo;
import com.altomni.apn.service.TenantService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JIALIN on 5/4/2017.
 */
@Slf4j
@Service("tenantService")
public class TenantServiceImpl implements TenantService{
    @Autowired
    private TenantRepo tenantRepo;

    public WithIncludes<Tenant> findById(Long id) {
        log.debug("findById is executed");

        try {
            return new WithIncludes<>(tenantRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public WithIncludes<Tenant> saveTenant(Tenant tenant) {
        log.debug("saveTenant is executed");

        try {
            return new WithIncludes<>(tenantRepo.save(tenant));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
