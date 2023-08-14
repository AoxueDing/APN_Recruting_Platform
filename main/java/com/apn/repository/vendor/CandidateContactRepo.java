package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.CandidateContact;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Alfred Yuan on 5/1/17.
 */
public interface CandidateContactRepo extends PagingAndSortingRepository<CandidateContact, Long> {
    CandidateContact findByContactAndType(String contact, Integer type);

    List<CandidateContact> findByCandidateId(Long candidateId);
}
