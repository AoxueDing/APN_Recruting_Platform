package com.altomni.apn.controller;

import com.altomni.apn.model.ClientJob;
import com.altomni.apn.service.ClientJobService;
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
 * Created by JIALIN on 5/4/2017.
 */
@Slf4j
@RestController
public class ClientJobController {
    @Autowired
    private ClientJobService clientJobService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/clientJobs/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<ClientJob>> getClientJob (@PathVariable("id") Long id) {
        WithIncludes<ClientJob> clientJob = clientJobService.findById(id);
        if(clientJob == null) {
            log.debug("ClientJob with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(clientJob, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/clientJobs", method = RequestMethod.POST)
    public ResponseEntity<ClientJob> createClientJob (@RequestBody ClientJob clientJob) {
        log.debug("Creating ClientJob" + clientJob.getJobId()+clientJob.getClientId());

        WithIncludes<ClientJob> savedClientJob=clientJobService.saveClientJob(clientJob);
        if (savedClientJob == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ClientJob createdClientJob=savedClientJob.getData();
        createdClientJob.setCreatedAt(createdClientJob.getCreatedAt());
        createdClientJob.setCreatedBy(createdClientJob.getCreatedBy());

        URI location = new UriTemplate("/clientJobs/{id}").expand(createdClientJob.getId());
        return ResponseEntity.created(location).body(createdClientJob);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/clientJobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<WithIncludes<ClientJob>> updateClientJob(@PathVariable("id")Long id, @RequestBody ClientJob clientJob){
        log.debug("Updating clientJob" + id);
        WithIncludes<ClientJob> currentClientJobWithIncludes = clientJobService.findById(id);

        if (currentClientJobWithIncludes == null) {
            log.debug("ClientJob with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ClientJob currentClientJob = currentClientJobWithIncludes.getData();

        currentClientJob.setClientId(currentClientJob.getClientId());
        currentClientJob.setHiringManager(currentClientJob.getHiringManager());
        currentClientJob.setUpdatedAt(currentClientJob.getUpdatedAt());
        currentClientJob.setUpdatedBy((currentClientJob.getUpdatedBy()));

        WithIncludes<ClientJob> updatedClientJobWithIncludes = clientJobService.saveClientJob(clientJob);
        return new ResponseEntity<>(updatedClientJobWithIncludes, HttpStatus.OK);
    }
}
