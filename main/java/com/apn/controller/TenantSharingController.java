package com.altomni.apn.controller;

import com.altomni.apn.model.TenantSharing;
import com.altomni.apn.service.TenantSharingService;
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
public class TenantSharingController {
    @Autowired
    private TenantSharingService tenantSharingService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/tenantSharings/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<TenantSharing>> getTenantSharing (@PathVariable("id") Long id) {
        WithIncludes<TenantSharing> tenantSharing = tenantSharingService.findById(id);
        if(tenantSharing == null) {
            log.debug("TenantSharing with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tenantSharing, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/tenantSharings/", method = RequestMethod.POST)
    public ResponseEntity<TenantSharing> createTenantSharing (@RequestBody TenantSharing tenantSharing) {
        log.debug("Creating TenantSharing" + tenantSharing.getTenantIdX()+tenantSharing.getTenantIdY()+tenantSharing.getFlag());

        WithIncludes<TenantSharing> savedTenantSharing=tenantSharingService.saveTenantSharing(tenantSharing);
        if (savedTenantSharing == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        TenantSharing createdTenantSharing = savedTenantSharing.getData();
        createdTenantSharing.setCreatedAt(createdTenantSharing.getCreatedAt());
        createdTenantSharing.setCreatedBy(createdTenantSharing.getCreatedBy());

        URI location = new UriTemplate("/tenantSharings/{id}").expand(createdTenantSharing.getId());
        return ResponseEntity.created(location).body(createdTenantSharing);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/tenantSharings/{id}", method = RequestMethod.PUT)
    public ResponseEntity<WithIncludes<TenantSharing>> updateTenantSharing(@PathVariable("id")Long id, @RequestBody TenantSharing tenantSharing){
        log.debug("Updating tenantSharing " + id);
        WithIncludes<TenantSharing> currentTenantSharingWithIncludes = tenantSharingService.findById(id);

        if (currentTenantSharingWithIncludes == null) {
            log.debug("TenantSharing with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TenantSharing currentTenantSharing = currentTenantSharingWithIncludes.getData();

        currentTenantSharing.setFlag(currentTenantSharing.getFlag());
        currentTenantSharing.setUpdatedAt(currentTenantSharing.getUpdatedAt());
        currentTenantSharing.setUpdatedBy((currentTenantSharing.getUpdatedBy()));

        WithIncludes<TenantSharing> updatedTenantSharingWithIncludes = tenantSharingService.saveTenantSharing(tenantSharing);
        return new ResponseEntity<>(updatedTenantSharingWithIncludes, HttpStatus.OK);
    }
}
