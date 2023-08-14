package com.altomni.apn.service.impl;

import com.altomni.apn.model.CandidateCustomizedSkill;
import com.altomni.apn.model.CandidateSkill;
import com.altomni.apn.model.Skill;
import com.altomni.apn.repository.common.SkillRepo;
import com.altomni.apn.repository.vendor.CandidateCustomizedSkillRepo;
import com.altomni.apn.repository.vendor.CandidateSkillRepo;
import com.altomni.apn.service.CandidateSkillService;
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
@Service("candidateSkillService")
public class CandidateSkillServiceImpl implements CandidateSkillService {
    @Autowired
    private CandidateSkillRepo candidateSkillRepo;
    @Autowired
    private SkillRepo skillRepo;
    @Autowired
    private CandidateCustomizedSkillRepo candidateCustomizedSkillRepo;

    @Override
    public List<CandidateSkill> getCandidateSkills(Long candidateId) {
        log.debug("get candidate skills. candidateId: " + candidateId);

        try {
            return candidateSkillRepo.findByCandidateId(candidateId);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void batchInsertCandidateSkills(List<CandidateSkill> candidateSkills) {
        try {
            candidateSkillRepo.save(candidateSkills);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    @Override
    public CandidateCustomizedSkill insertCustomizedSkills(CandidateCustomizedSkill customizedSkill) {
        return candidateCustomizedSkillRepo.save(customizedSkill);
    }

    @Override
    public CandidateCustomizedSkill getCustomizedSkill(Long candidateId) {
        //TODO
        return null;
    }

    @Override
    public List<Skill> findByCandidateId(Long candidateId) {
        List<CandidateSkill> candidateSkills = getCandidateSkills(candidateId);
        List<Long> skillIds = candidateSkills.stream().map(CandidateSkill::getSkillId).collect(Collectors.toList());
        return skillRepo.findByIdIn(skillIds);
    }
}