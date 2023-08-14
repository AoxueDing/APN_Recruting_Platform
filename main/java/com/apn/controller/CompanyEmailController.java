package com.altomni.apn.controller;

import com.altomni.apn.model.CompanyEmail;
import com.altomni.apn.service.CompanyEmailService;
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
 * Created by JIALIN on 5/8/2017.
 */
@Slf4j
@RestController
public class CompanyEmailController {
    @Autowired
    private CompanyEmailService companyEmailService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/companyEmails/{emailSuffix}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<CompanyEmail>> getCompanyEmail (@PathVariable("emailSuffix") String emailSuffix) {
        WithIncludes<CompanyEmail> companyEmail = companyEmailService.findByEmailSuffix(emailSuffix);
        if(companyEmail == null) {
            log.debug("CompanyEmail with emailSuffix " + emailSuffix + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(companyEmail, HttpStatus.OK);
    }
}
