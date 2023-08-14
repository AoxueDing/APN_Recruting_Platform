package com.altomni.apn.controller;

import com.altomni.apn.model.Industry;
import com.altomni.apn.service.IndustryService;
import com.altomni.apn.service.ListWithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jianhui on 4/22/17.
 */
@Slf4j
@RestController
@RequestMapping(value = "/industries", produces = {MediaType.APPLICATION_JSON_VALUE})
public class IndustryController {
    @Autowired
    private IndustryService industryService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ListWithIncludes<Industry>> listAll() {
        return ResponseEntity.ok(industryService.findAll());
    }
}
