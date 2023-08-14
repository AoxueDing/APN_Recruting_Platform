package com.altomni.apn.service;

import com.altomni.apn.model.Role;

/**
 * Created by JIALIN on 5/5/2017.
 */
public interface RoleService {
    WithIncludes<Role> getRole(Long id);
}
