package com.altomni.apn.controller;

import com.altomni.apn.model.CandidateCollege;
import com.altomni.apn.service.CandidateCollegeService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import java.net.URI;


/**
 * Created by JIALIN on 4/21/2017.
 */
@Slf4j
@RestController
public class CandidateCollegeController {
    @Autowired
    private CandidateCollegeService candidateCollegeService;

    // get college information from the college ID
    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/candidates/{candidateId}/educations/{collegeId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<CandidateCollege>> getCandidateCollege(@PathVariable("collegeId") Long collegeId) {
        WithIncludes<CandidateCollege> education = candidateCollegeService.findByCandidateCollegeId(collegeId);

        if(education == null) {
            log.debug("CandidateCollege with collegeId" + collegeId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    // get all college information from the candidateId
    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/candidates/{candidateId}/educations", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<CandidateCollege>> getCandidateCollegeList(@PathVariable ("candidateId") Long candidateId) {
        ListWithIncludes<CandidateCollege> candidateCollegeList = candidateCollegeService.findByCandidateId(candidateId);

        if(candidateCollegeList == null) {
            log.debug("CandidateCollegeList with candidateId " + candidateId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(candidateCollegeList, HttpStatus.OK);
    }

    // create college
    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/candidates/{candidateId}/educations", method = RequestMethod.POST)
    public ResponseEntity<CandidateCollege> createCandidateCollege(@RequestBody CandidateCollege education) {
        log.debug("Creating CandidateCollege " + education.getCandidateId()+ education.getCollegeId());

        WithIncludes<CandidateCollege> savedCandidateCollege = candidateCollegeService.saveCandidateCollege(education);

        if (savedCandidateCollege == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        URI location = new UriTemplate("/education/{collegeId}").expand(savedCandidateCollege.getData().getCollegeId());
        return ResponseEntity.created(location).body(savedCandidateCollege.getData());
    }

    //update college
    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/candidates/{candidateId}/educations/{collegeId}", method = RequestMethod.PUT)
    public ResponseEntity<WithIncludes<CandidateCollege>> updateCandidateCollege(@PathVariable("collegeId") Long collegeId, @RequestBody CandidateCollege education) {
        log.debug("Updating CandidateCollege " + collegeId);
        WithIncludes<CandidateCollege> currentCandidateCollegeWithIncludes = candidateCollegeService.findByCandidateCollegeId(collegeId);

        if (currentCandidateCollegeWithIncludes == null) {
            log.debug("CandidateCollege with collegeId " + collegeId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CandidateCollege currentCandidateCollege = currentCandidateCollegeWithIncludes.getData();
        currentCandidateCollege.setCollegeId(education.getCollegeId());
        currentCandidateCollege.setStartDate(education.getStartDate());
        currentCandidateCollege.setEndDate(education.getEndDate());
        currentCandidateCollege.setDegree(education.getDegree());
        currentCandidateCollege.setMajorId(education.getMajorId());

        WithIncludes<CandidateCollege> updatedCandidateCollegeWithIncludes = candidateCollegeService.saveCandidateCollege(currentCandidateCollege);
        return new ResponseEntity<>(updatedCandidateCollegeWithIncludes, HttpStatus.OK);
    }

}
