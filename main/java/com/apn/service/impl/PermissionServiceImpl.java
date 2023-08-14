package com.altomni.apn.service.impl;

import com.altomni.apn.model.Permission;
import com.altomni.apn.repository.vendor.PermissionRepo;
import com.altomni.apn.service.PermissionService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JIALIN on 5/1/2017.
 */
@Slf4j
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepo permissionRepo;

    public WithIncludes<Permission> findById(Long id) {
        log.debug("findById is executed" + id);

        try {
            return new WithIncludes<>(permissionRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
