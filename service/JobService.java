package com.altomni.apn.service;

import com.altomni.apn.model.Job;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
public interface JobService {
    WithIncludes<Job> findById(Long id);
    WithIncludes<Job> saveJob(Job job);
    ListWithIncludes<Job> findByCompany(Long company);
    ListWithIncludes<Job> findByPostingCompany(Long postingCompany);
}