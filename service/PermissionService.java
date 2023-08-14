package com.altomni.apn.service;

import com.altomni.apn.model.Permission;

/**
 * Created by JIALIN on 5/1/2017.
 */
public interface PermissionService {
    WithIncludes<Permission> findById(Long id);
 }
