package com.altomni.apn.service.impl;

import com.altomni.apn.model.WorkAuth;
import com.altomni.apn.repository.common.WorkAuthRepo;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import com.altomni.apn.service.WorkAuthService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
@Slf4j
@Service("workAuthService")
public class WorkAuthServiceImpl implements WorkAuthService{
    @Autowired
    private WorkAuthRepo workAuthRepo;

    @Override
    public WithIncludes<WorkAuth> getWorkAuth(Long id) {
        log.debug("get work auth with id: " + id);
        try {
            return new WithIncludes<>(workAuthRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public ListWithIncludes<WorkAuth> getAllWorkAuths() {
        log.debug("get all work auth");
        try {
            return new ListWithIncludes<>(Lists.newArrayList(workAuthRepo.findAll()));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }
}