package com.altomni.apn.service;

import com.altomni.apn.model.Tenant;

/**
 * Created by JIALIN on 5/4/2017.
 */
public interface TenantService {
    WithIncludes<Tenant> findById(Long id);
    WithIncludes<Tenant> saveTenant(Tenant tenant);
}
