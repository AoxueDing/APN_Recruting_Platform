package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.CandidateSkill;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
public interface CandidateSkillRepo extends PagingAndSortingRepository<CandidateSkill, Long> {
    List<CandidateSkill> findByCandidateId(Long candidateId);
}