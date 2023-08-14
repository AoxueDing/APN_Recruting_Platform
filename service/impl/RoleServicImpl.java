package com.altomni.apn.service.impl;

import com.altomni.apn.model.Role;
import com.altomni.apn.repository.common.RoleRepo;
import com.altomni.apn.service.RoleService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JIALIN on 5/5/2017.
 */
@Slf4j
@Service("roleService")
public class RoleServicImpl implements RoleService{
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public WithIncludes<Role> getRole(Long id) {
        log.debug("get role by id: " + id);

        try {
            return new WithIncludes<>(roleRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
