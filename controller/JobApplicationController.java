package com.altomni.apn.controller;

import com.altomni.apn.model.JobApplication;
import com.altomni.apn.service.JobApplicationService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

/**
 * Created by JIALIN on 5/14/2017.
 */
@Slf4j
@RestController
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/jobApplications/candidates/{candidateId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<JobApplication>> getJobList(@PathVariable("candidateId") Long candidateId) {
        ListWithIncludes<JobApplication> jobApplicationList = jobApplicationService.findListByCandidateId(candidateId);

        if(jobApplicationList == null) {
            log.debug("JobApplicationList with candidateId " + candidateId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(jobApplicationList, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/jobApplications/jobs/{jobId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<JobApplication>> getCandidateList(@PathVariable("jobId") Long jobId) {
        ListWithIncludes<JobApplication> jobApplicationList = jobApplicationService.findListByJobId(jobId);

        if(jobApplicationList == null) {
            log.debug("JobApplicationList with jobId " + jobId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(jobApplicationList, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/jobApplications/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<JobApplication>> getJobApplication(@PathVariable("id") Long id) {
        WithIncludes<JobApplication> jobApplication = jobApplicationService.findById(id);

        if(jobApplication == null) {
            log.debug("JobApplication with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(jobApplication, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/jobApplications", method = RequestMethod.POST)
    public ResponseEntity<JobApplication> createJobApplication(@RequestBody JobApplication jobApplication) {
        log.debug("Creating JobApplication " + jobApplication.getCandidateId()+ jobApplication.getJobId());

        WithIncludes<JobApplication> savedJobApplication = jobApplicationService.saveJobApplication(jobApplication);

        if (savedJobApplication == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        URI location = new UriTemplate("/jobApplications/{JobId}").expand(savedJobApplication.getData().getJobId());
        return ResponseEntity.created(location).body(savedJobApplication.getData());
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/jobApplications/{id}", method = RequestMethod.PUT)
    public ResponseEntity<WithIncludes<JobApplication>> updateJobApplication(@PathVariable("id") Long id, @RequestBody JobApplication jobApplication) {
        log.debug("Updating JobApplicatoin " + id);
        WithIncludes<JobApplication> currentJobApplicationWithIncludes = jobApplicationService.findById(id);

        if (currentJobApplicationWithIncludes == null) {
            log.debug("JobApplication with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        JobApplication currentJobApplication = currentJobApplicationWithIncludes.getData();
        currentJobApplication.setJobId(jobApplication.getJobId());
        currentJobApplication.setAppliedBy(jobApplication.getAppliedBy());
        currentJobApplication.setStatus(jobApplication.getStatus());
        currentJobApplication.setType(jobApplication.getType());
        currentJobApplication.setUpdatedAt(jobApplication.getUpdatedAt());
        currentJobApplication.setUpdatedBy(jobApplication.getUpdatedBy());

        WithIncludes<JobApplication> updatedJobApplicationWithIncludes = jobApplicationService.saveJobApplication(currentJobApplication);
        return new ResponseEntity<>(updatedJobApplicationWithIncludes, HttpStatus.OK);
    }
}
