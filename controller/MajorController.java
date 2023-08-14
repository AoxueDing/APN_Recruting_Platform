package com.altomni.apn.controller;

import com.altomni.apn.model.Major;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.MajorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
@Slf4j
@RestController
public class MajorController {
    @Autowired
    private MajorService majorService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/majors", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<Major>> getALlMajors() {
        log.debug("get all majors");

        ListWithIncludes<Major> majors = majorService.getAllMajors();

        if (CollectionUtils.isEmpty(majors.getData())) {
            log.debug("no majors");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(majors, HttpStatus.OK);
    }
}