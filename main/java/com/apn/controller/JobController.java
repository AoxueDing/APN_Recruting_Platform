package com.altomni.apn.controller;

import com.altomni.apn.model.Job;
import com.altomni.apn.model.JobApplication;
import com.altomni.apn.service.JobService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
@Slf4j
@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/jobs/company/{company}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<Job>> getJobByCompany(@PathVariable("company") Long company) {
        ListWithIncludes<Job> jobList = jobService.findByCompany(company);

        if(jobList == null) {
            log.debug("JobList with company " + company + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/jobs/postingCompany/{postingCompany}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<Job>> getJobByPostingCompany(@PathVariable("postingCompany") Long postingCompany) {
        ListWithIncludes<Job> jobList = jobService.findByPostingCompany(postingCompany);

        if(jobList == null) {
            log.debug("JobList with postingCompany " + postingCompany + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<Job>> getJob(@PathVariable("id") Long id) {
        WithIncludes<Job> job = jobService.findById(id);

        if (job == null) {
            log.debug("job with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/jobs", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        log.debug("creating job " + job.getTitle());
        WithIncludes<Job> savedJob = jobService.saveJob(job);

        if (savedJob == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        URI location = new UriTemplate("/jobs/{id}").expand(savedJob.getData().getId());
        return ResponseEntity.created(location).body(savedJob.getData());
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<Job>> updateJob(@PathVariable("id") Long id, @RequestBody Job job) {
        log.debug("Updating Job " + id);
        WithIncludes<Job> currentJobWithIncludes = jobService.findById(id);

        if (currentJobWithIncludes == null) {
            log.debug("Job with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Job currentJob = currentJobWithIncludes.getData();
        currentJob.setCode(job.getCode());
        currentJob.setOverview(job.getOverview());
        currentJob.setResponsibility(job.getResponsibility());
        currentJob.setRequirement(job.getRequirement());
        currentJob.setDivision(job.getDivision());
        currentJob.setMinSalary(job.getMinSalary());
        currentJob.setMaxSalary(job.getMaxSalary());
        currentJob.setMinSalary2(job.getMinSalary2());
        currentJob.setMaxSalary2(job.getMaxSalary2());
        currentJob.setCurrency(job.getCurrency());
        currentJob.setUnit(job.getUnit());
        currentJob.setStartDate(job.getStartDate());
        currentJob.setEndDate(job.getEndDate());
        currentJob.setCity(job.getCity());
        currentJob.setProvince(job.getProvince());
        currentJob.setZipcode(job.getZipcode());
        currentJob.setAddress(job.getAddress());
        currentJob.setDomain(job.getDomain());
        currentJob.setJobType(job.getJobType());
        currentJob.setPriority(job.getPriority());
        currentJob.setCompany(job.getCompany());
        currentJob.setPostingCompany(job.getPostingCompany());
        currentJob.setDuration(job.getDuration());
        currentJob.setStatus(job.getStatus());
        currentJob.setOriginalJD(job.getOriginalJD());
        currentJob.setTags(job.getTags());
        currentJob.setUpdatedAt(job.getUpdatedAt());
        currentJob.setUpdatedBy(job.getUpdatedBy());

        WithIncludes<Job> updatedJobWithIncludes = jobService.saveJob(currentJob);
        return new ResponseEntity<>(updatedJobWithIncludes, HttpStatus.OK);
    }
}