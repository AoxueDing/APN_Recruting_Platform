package com.altomni.apn.service;

import com.altomni.apn.model.CandidateCollege;

import java.util.List;

/**
 * Created by JIALIN on 4/20/2017.
 */
public interface CandidateCollegeService {
    ListWithIncludes<CandidateCollege>  findByCandidateId(Long candidateId);
    WithIncludes<CandidateCollege> findByCandidateCollegeId(Long collegeId);
    void saveAllCandidateCollege (List<CandidateCollege> candidateCollegeList);
    WithIncludes<CandidateCollege> saveCandidateCollege (CandidateCollege candidateCollege);
}
