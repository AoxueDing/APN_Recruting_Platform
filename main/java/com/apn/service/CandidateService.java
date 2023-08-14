package com.altomni.apn.service;

import com.altomni.apn.model.Candidate;
import org.springframework.data.domain.Pageable;

/**
 * Created by Alfred Yuan on 4/20/17.
 */
public interface CandidateService {
    ListWithIncludes<Candidate> findAll(Pageable pageable);
    WithIncludes<Candidate> findById(Long id);

    Candidate update(Candidate candidate);
    Candidate create(Candidate candidate);

    ListWithIncludes<Candidate> search(CandidateSearchRequest searchRequest);
}
