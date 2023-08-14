package com.altomni.apn.service.impl;

import com.altomni.apn.model.JobApplication;
import com.altomni.apn.repository.vendor.JobApplicationRepo;
import com.altomni.apn.service.JobApplicationService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
@Slf4j
@Service("jobApplicationService")
public class JobApplicationServiceImpl implements JobApplicationService {
    @Autowired
    private JobApplicationRepo jobApplicationRepo;

    public ListWithIncludes<JobApplication> findListByCandidateId(Long candidateId) {
        log.debug("findByCandidateId is executed");

        try {
            return new ListWithIncludes<>(jobApplicationRepo.findByCandidateId(candidateId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }

    public ListWithIncludes<JobApplication> findListByJobId(Long jobId) {
        log.debug("findByJobId is executed");

        try {
            return new ListWithIncludes<>(jobApplicationRepo.findByJobId(jobId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }

    public WithIncludes<JobApplication> findById(Long id) {
        log.debug("findById is executed");

        try {
            return new WithIncludes<>(jobApplicationRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public WithIncludes<JobApplication> saveJobApplication(JobApplication jobApplication) {
        log.debug("saveJobApplication is executed");

        try {
            return new WithIncludes<>(jobApplicationRepo.save(jobApplication));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}