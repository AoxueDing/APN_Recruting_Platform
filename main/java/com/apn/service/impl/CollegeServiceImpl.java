package com.altomni.apn.service.impl;

import com.altomni.apn.model.College;
import com.altomni.apn.repository.common.CollegeRepo;
import com.altomni.apn.service.CollegeService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alfred Yuan on 4/25/17.
 */
@Slf4j
@Service("collegeService")
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeRepo collegeRepo;

    @Override
    public WithIncludes<College> getCollege(long id) {
        log.debug("get college by id: " + id);

        try {
            return new WithIncludes<>(collegeRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
