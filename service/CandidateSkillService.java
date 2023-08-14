package com.altomni.apn.service;

import com.altomni.apn.model.CandidateCustomizedSkill;
import com.altomni.apn.model.CandidateSkill;
import com.altomni.apn.model.Skill;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
public interface CandidateSkillService {
    List<CandidateSkill> getCandidateSkills(Long candidateId);

    List<Skill> findByCandidateId(Long candidateId);
    void batchInsertCandidateSkills(List<CandidateSkill> candidateSkills);

    CandidateCustomizedSkill insertCustomizedSkills(CandidateCustomizedSkill customizedSkill);
    CandidateCustomizedSkill getCustomizedSkill(Long candidateId);
}