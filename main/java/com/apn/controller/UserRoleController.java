package com.altomni.apn.controller;

import com.altomni.apn.model.UserContact;
import com.altomni.apn.model.UserRole;
import com.altomni.apn.service.UserContactService;
import com.altomni.apn.service.UserRoleService;
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
 * Created by JIALIN on 5/5/2017.
 */
@Slf4j
@RestController
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/userRoles", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<UserRole>> getUserRole() {
        AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        Long userId = userInfo.getUserId();
        WithIncludes<UserRole> userRole = userRoleService.findByUserId(userId);

        if(userRole == null) {
            log.debug("UserRole with userId " + userId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userRole, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/userRoles", method = RequestMethod.POST)
    public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole) {
        log.debug("Creating UserRole " + userRole.getUserId() + userRole.getRoleId());

        WithIncludes<UserRole> savedUserRole = userRoleService.saveUserRole(userRole);
        if (savedUserRole == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UserRole createdUserRole = savedUserRole.getData();
        createdUserRole.setCreatedAt(createdUserRole.getCreatedAt());
        createdUserRole.setCreatedBy(createdUserRole.getCreatedBy());

        URI location = new UriTemplate("/userRoles").expand();
        return ResponseEntity.created(location).body(createdUserRole);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/userRoles", method = RequestMethod.PUT)
    public ResponseEntity<UserRole> updateUserRole(@RequestBody UserRole userRole) {
        AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        Long userId = userInfo.getUserId();
        log.debug("Updating userRole " + userId);
        WithIncludes<UserRole> currentUserRoleWithIncludes = userRoleService.findByUserId(userId);

        if (currentUserRoleWithIncludes == null) {
            log.debug("UserRole with userId " + userId + " not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserRole currentUserRole = currentUserRoleWithIncludes.getData();

        currentUserRole.setRoleId(userRole.getRoleId());
        currentUserRole.setUpdatedBy(userRole.getUpdatedBy());
        currentUserRole.setUpdatedAt(userRole.getUpdatedAt());

        userRoleService.saveUserRole(currentUserRole);
        return new ResponseEntity<>(currentUserRole, HttpStatus.OK);
    }
}
