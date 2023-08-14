package com.altomni.apn.service.impl;

import com.altomni.apn.model.Skill;
import com.altomni.apn.repository.common.SkillRepo;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.SkillService;
import com.altomni.apn.service.WithIncludes;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
@Slf4j
@Service("skillService")
public class SkillServiceImpl implements SkillService{
    @Autowired
    private SkillRepo skillRepo;

    @Override
    public WithIncludes<Skill> findById(Long id) {
        return new WithIncludes<>(skillRepo.findOne(id));
    }

    @Override
    public ListWithIncludes<Skill> getAllSkills() {
        log.debug("get all skills");
        try {
            return new ListWithIncludes<>(Lists.newArrayList(skillRepo.findAll()));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }
}