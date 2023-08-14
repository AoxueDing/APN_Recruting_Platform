package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.JobApplication;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
public interface JobApplicationRepo extends PagingAndSortingRepository<JobApplication, Long> {
    List<JobApplication> findByCandidateId(Long candidateId);
    List<JobApplication> findByJobId(Long jobId);
}