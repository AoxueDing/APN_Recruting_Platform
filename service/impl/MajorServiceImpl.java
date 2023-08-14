package com.altomni.apn.service.impl;

import com.altomni.apn.model.Major;
import com.altomni.apn.repository.common.MajorRepo;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.MajorService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
@Slf4j
@Service("majorService")
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorRepo majorRepo;

    @Override
    public ListWithIncludes<Major> getAllMajors() {
        log.debug("get all majors");
        try {
            return new ListWithIncludes<>(Lists.newArrayList(majorRepo.findAll()));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }
}