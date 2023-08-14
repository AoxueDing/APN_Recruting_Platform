package com.altomni.apn.service;

import com.altomni.apn.model.Skill;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
public interface SkillService {
    WithIncludes<Skill> findById(Long id);

    ListWithIncludes<Skill> getAllSkills();
}