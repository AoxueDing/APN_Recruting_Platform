package com.altomni.apn.service;

import com.altomni.apn.model.WorkAuth;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
public interface WorkAuthService {
    WithIncludes<WorkAuth> getWorkAuth(Long id);

    ListWithIncludes<WorkAuth> getAllWorkAuths();
}