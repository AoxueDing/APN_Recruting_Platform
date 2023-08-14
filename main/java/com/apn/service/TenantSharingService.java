package com.altomni.apn.service;

import com.altomni.apn.model.TenantSharing;

/**
 * Created by JIALIN on 5/4/2017.
 */
public interface TenantSharingService {
    WithIncludes<TenantSharing> findById(Long id);
    WithIncludes<TenantSharing> saveTenantSharing(TenantSharing tenantSharing);
}
