package com.altomni.apn.controller;

import com.altomni.apn.model.UserContact;
import com.altomni.apn.service.UserContactService;
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
 * Created by JIALIN on 5/3/2017.
 */
@Slf4j
@RestController
public class UserContactController {
    @Autowired
    UserContactService userContactService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/userContacts", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<UserContact>> getUserContact() {
        AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        Long userId = userInfo.getUserId();
        WithIncludes<UserContact> userContact = userContactService.findByUserId(userId);

        if(userContact == null) {
            log.debug("UserContact with userId " + userId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userContact, HttpStatus.OK);
    }

    // create and update methods could be left unused, based on need
    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/userContact", method = RequestMethod.POST)
    public ResponseEntity<UserContact> createUserContact(@RequestBody UserContact userContact) {
        log.debug("Creating UserContact" + userContact.getUserId() + userContact.getContact());

        WithIncludes<UserContact> savedUserContact = userContactService.saveUserContact(userContact);
        if (savedUserContact == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UserContact createdUserContact = savedUserContact.getData();
        createdUserContact.setCreatedAt(createdUserContact.getCreatedAt());
        createdUserContact.setCreatedBy(createdUserContact.getCreatedBy());

        URI location = new UriTemplate("/userContacts").expand();
        return ResponseEntity.created(location).body(createdUserContact);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/userContacts", method = RequestMethod.PUT)
    public ResponseEntity<UserContact> updateUserContact(@RequestBody UserContact userContact) {
        AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        Long userId = userInfo.getUserId();
        log.debug("Updating userContact" + userId);
        WithIncludes<UserContact> currentUserContactWithIncludes = userContactService.findByUserId(userId);

        if (currentUserContactWithIncludes == null) {
            log.debug("UserContact with userId " + userId + " not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserContact currentUserContact = currentUserContactWithIncludes.getData();

        currentUserContact.setContact(userContact.getContact());
        currentUserContact.setType(userContact.getType());
        currentUserContact.setUpdatedBy(userContact.getUpdatedBy());
        currentUserContact.setUpdatedAt(userContact.getUpdatedAt());

        userContactService.saveUserContact(currentUserContact);
        return new ResponseEntity<>(currentUserContact, HttpStatus.OK);
    }
}
