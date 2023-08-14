package com.altomni.apn.controller;

import com.altomni.apn.model.Skill;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.SkillService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
@Slf4j
@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/skills/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<Skill>> getSkill(@PathVariable("id") Long id) {
        log.debug("fetch skill with id: " + id);

        WithIncludes<Skill> skill = skillService.findById(id);
        if (skill == null) {
            log.debug("no skill with id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/skills", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<Skill>> getAllSkills() {
        log.debug("get all skills");

        ListWithIncludes<Skill> skills = skillService.getAllSkills();

        if (CollectionUtils.isEmpty(skills.getData())) {
            log.debug("no skills");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

}