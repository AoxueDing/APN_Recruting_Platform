package com.altomni.apn.controller;

import com.altomni.apn.model.WorkAuth;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import com.altomni.apn.service.WorkAuthService;
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
 * Created by Alfred Yuan on 4/24/17.
 */
@Slf4j
@RestController
public class WorkAuthController {
    @Autowired
    private WorkAuthService workAuthService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/workauth/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<WorkAuth>> getWorkAuth(@PathVariable("id") Long id) {
        log.debug("get work auth with id: " + id);

        WithIncludes<WorkAuth> workAuth = workAuthService.getWorkAuth(id);

        if (workAuth == null) {
            log.debug("no work auth with id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(workAuth, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/workauth", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<WorkAuth>> getAllWorkAuths() {
        log.debug("get all work auths");

        ListWithIncludes<WorkAuth> workAuths = workAuthService.getAllWorkAuths();

        if (CollectionUtils.isEmpty(workAuths.getData())) {
            log.debug("no work auths");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(workAuths, HttpStatus.OK);
    }

}