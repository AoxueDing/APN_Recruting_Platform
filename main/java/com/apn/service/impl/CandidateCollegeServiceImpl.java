package com.altomni.apn.service.impl;

import com.altomni.apn.model.CandidateCollege;
import com.altomni.apn.repository.vendor.CandidateCollegeRepo;
import com.altomni.apn.service.CandidateCollegeService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by JIALIN on 4/20/2017.
 */

@Slf4j
@Service("candidateCollegeService")
public class CandidateCollegeServiceImpl implements CandidateCollegeService{
    @Autowired
    private CandidateCollegeRepo candidateCollegeRepo;

    public WithIncludes<CandidateCollege> findByCandidateCollegeId(Long collegeId) {
        log.debug("findByCandidateCollegeId is executed");

        try {
            return new WithIncludes<>(candidateCollegeRepo.findOne(collegeId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public ListWithIncludes<CandidateCollege> findByCandidateId(Long candidateId) {
        log.debug("findByCandidateId is executed");

        try {
            return new ListWithIncludes<>(candidateCollegeRepo.findByCandidateId(candidateId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }

    public void saveAllCandidateCollege(List<CandidateCollege> candidateCollegeList) {
        log.debug("saveAllCandidateCollege is executed");

        try {
            candidateCollegeRepo.save(candidateCollegeList);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public WithIncludes<CandidateCollege> saveCandidateCollege(CandidateCollege candidateCollege) {
        log.debug("saveCandidateCollege is executed");

        try {
            return new WithIncludes<>(candidateCollegeRepo.save(candidateCollege));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
