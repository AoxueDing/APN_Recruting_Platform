package com.altomni.apn.controller;

import com.altomni.apn.model.Domain;
import com.altomni.apn.service.DomainService;
import com.altomni.apn.service.ListWithIncludes;
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
public class DomainController {
    @Autowired
    private DomainService domainService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/domains", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<Domain>> getAllDomains() {
        log.debug("get all domains");

        ListWithIncludes<Domain> domains = domainService.getAllDomains();

        if (CollectionUtils.isEmpty(domains.getData())) {
            log.debug("no domains");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(domains, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/domains/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<Domain>> getDomain(@PathVariable("id") Long id) {
        log.debug("get domain with id: " + id);

        WithIncludes<Domain> domain = domainService.getDomain(id);

        if (domain == null) {
            log.debug("no domain with id:" + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(domain, HttpStatus.OK);
    }
}