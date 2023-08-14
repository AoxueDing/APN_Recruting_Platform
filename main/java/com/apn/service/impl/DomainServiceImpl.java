package com.altomni.apn.service.impl;

import com.altomni.apn.model.Domain;
import com.altomni.apn.repository.common.DomainRepo;
import com.altomni.apn.service.DomainService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
@Slf4j
@Service("domainService")
public class DomainServiceImpl implements DomainService {
    @Autowired
    private DomainRepo domainRepo;

    @Override
    public WithIncludes<Domain> getDomain(Long id) {
        log.debug("get domain with id: " + id);
        try {
            return new WithIncludes<>(domainRepo.findOne(id));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ListWithIncludes<Domain> getAllDomains() {
        log.debug("get all domains");
        try {
            return new ListWithIncludes<>(Lists.newArrayList(domainRepo.findAll()));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }
}