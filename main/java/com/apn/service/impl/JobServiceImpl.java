package com.altomni.apn.service.impl;

import com.altomni.apn.model.ContactGroupMember;
import com.altomni.apn.model.Job;
import com.altomni.apn.model.JobAssignment;
import com.altomni.apn.repository.common.JobRepo;
import com.altomni.apn.repository.vendor.ContactGroupMemberRepo;
import com.altomni.apn.repository.vendor.JobAssignmentRepo;
import com.altomni.apn.service.JobService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
@Slf4j
@Service("jobService")
public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepo jobRepo;

    @Override
    public WithIncludes<Job> findById(Long id) {
        log.debug("findById is executed");

        try {
            return new WithIncludes<>(jobRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public WithIncludes<Job> saveJob(Job job) {
        log.debug("saveJobApplication is executed");

        try {
            return new WithIncludes<>(jobRepo.save(job));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public ListWithIncludes<Job> findByCompany (Long company) {
        log.debug("findByCompany is executed");

        try {
            return new ListWithIncludes<>(jobRepo.findByCompany(company));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }

    @Override
    public ListWithIncludes<Job> findByPostingCompany(Long postingCompany) {
        log.debug("findByPostingCompany is executed");

        try {
            return new ListWithIncludes<>(jobRepo.findByPostingCompany(postingCompany));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }
}