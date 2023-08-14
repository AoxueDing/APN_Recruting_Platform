package com.altomni.apn.controller;

/**
 * Created by JIALIN on 4/22/2017.
 */
import com.altomni.apn.model.Experience;
import com.altomni.apn.service.ExperienceService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import java.net.URI;


@Slf4j
@RestController
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/candidates/{candidateId}/experiences", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<Experience>> getExperienceList(@PathVariable ("candidateId") Long candidateId) {
        ListWithIncludes<Experience> experienceList = experienceService.findByCandidateId(candidateId);
        if(experienceList == null) {
            log.debug("ExperienceList with candidateId " + candidateId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(experienceList, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/candidates/{candidateId}/experiences", method = RequestMethod.POST)
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) {
        log.debug("Creating Experience" + experience.getCandidateId() + experience.getCompanyId());

        WithIncludes<Experience> savedExperience = experienceService.saveExperience(experience);
        if (savedExperience == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Experience createdExperience = savedExperience.getData();

        createdExperience.setCreatedAt(createdExperience.getCreatedAt());
        createdExperience.setCreatedBy(createdExperience.getCreatedBy());

        URI location = new UriTemplate("/experiences/{id}").expand(createdExperience.getId());
        return ResponseEntity.created(location).body(createdExperience);
    }

    //update experience
    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/candidates/{candidateId}/experiences/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Experience> updateExperience(@PathVariable("id")Long id, @RequestBody Experience experience) {
        log.debug("Updating experience" + id);
        WithIncludes<Experience> currentExperienceWithIncludes = experienceService.findById(id);

        if (currentExperienceWithIncludes == null) {
            log.debug("CandidateCollege with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Experience currentExperience = currentExperienceWithIncludes.getData();

        currentExperience.setCompanyId(experience.getCompanyId());
        currentExperience.setClientId(experience.getClientId());
        currentExperience.setStartDate(experience.getStartDate());
        currentExperience.setEndDate(experience.getEndDate());
        currentExperience.setTitle(experience.getTitle());
        currentExperience.setSalary(experience.getSalary());
        currentExperience.setBonus(experience.getBonus());
        currentExperience.setStock(experience.getStock());
        currentExperience.setCurrency(experience.getCurrency());
        currentExperience.setCity(experience.getCity());
        currentExperience.setCompany2(experience.getCompany2());
        currentExperience.setClient2(experience.getClient2());
        currentExperience.setUpdatedBy(experience.getUpdatedBy());
        currentExperience.setUpdatedAt(experience.getUpdatedAt());

        experienceService.saveExperience(currentExperience);
        return new ResponseEntity<>(currentExperience, HttpStatus.OK);
    }

}
