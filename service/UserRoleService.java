package com.altomni.apn.service;

import com.altomni.apn.model.UserRole;

/**
 * Created by JIALIN on 5/5/2017.
 */
public interface UserRoleService {
    WithIncludes<UserRole> findByUserId (Long userId);
    WithIncludes<UserRole> saveUserRole (UserRole userRole);
}
