package com.altomni.apn.service.impl;

import com.altomni.apn.model.UserRole;
import com.altomni.apn.repository.vendor.UserRoleRepo;
import com.altomni.apn.service.UserRoleService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JIALIN on 5/5/2017.
 */
@Slf4j
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    private UserRoleRepo userRoleRepo;

    public WithIncludes<UserRole> findByUserId(Long userId) {
        log.debug("find by userId: " + userId);

        try {
            return new WithIncludes<>(userRoleRepo.findOne(userId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public WithIncludes<UserRole> saveUserRole(UserRole userRole) {
        log.debug("saveUserRole is executed");

        try {
            return new WithIncludes<>(userRoleRepo.save(userRole));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
