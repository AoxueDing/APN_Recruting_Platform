package com.altomni.apn.repository.common;

import com.altomni.apn.model.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/21/17.
 */
public interface JobRepo extends PagingAndSortingRepository<Job, Long> {
    List<Job> findByCompany(Long company);
    List<Job> findByPostingCompany(Long postingCompany);
}