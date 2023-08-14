package com.altomni.apn.service;

import com.altomni.apn.model.JobApplication;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
public interface JobApplicationService {
    ListWithIncludes<JobApplication> findListByCandidateId(Long candidateId);
    ListWithIncludes<JobApplication> findListByJobId(Long jobId);
    WithIncludes<JobApplication> findById(Long id);
    WithIncludes<JobApplication> saveJobApplication(JobApplication jobApplication);
}