package com.altomni.apn.controller;

import com.altomni.apn.model.College;
import com.altomni.apn.service.CollegeService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alfred Yuan on 4/25/17.
 */
@Slf4j
@RestController
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/colleges/{collegeId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<College>> getCollege(@PathVariable("id") long id) {
        log.debug("get college by id: " + id);

        WithIncludes<College> college = collegeService.getCollege(id);
        if (college == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(college, HttpStatus.OK);
    }
}
