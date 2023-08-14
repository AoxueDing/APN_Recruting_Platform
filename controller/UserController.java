package com.altomni.apn.controller;

import com.altomni.apn.model.User;
import com.altomni.apn.service.UserService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

/**
 * Created by JIALIN on 5/2/2017.
 */
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<User>> getUser () {
        AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        Long userId = userInfo.getUserId();
        WithIncludes<User> user = userService.findById(userId);
        if(user == null) {
            log.debug("User with id " + userId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // create user
    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/users/", method = RequestMethod.POST)
    public ResponseEntity<User> createUser (@RequestBody User user) {
        AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        Long userId = userInfo.getUserId();
        Long tenantId = userInfo.getTenantId();

        log.debug("Creating User" + userId + tenantId);

        WithIncludes<User> savedUser=userService.saveUser(user);
        if (savedUser == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User createdUser=savedUser.getData();
        createdUser.setCreatedAt(createdUser.getCreatedAt());
        createdUser.setCreatedBy(createdUser.getCreatedBy());

        URI location = new UriTemplate("/users").expand();
        return ResponseEntity.created(location).body(createdUser);
    }

    // update user
    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<WithIncludes<User>> updateUser(@RequestBody User user){
        AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        Long userId = userInfo.getUserId();
        log.debug("Updating user" + userId);
        WithIncludes<User> currentUserWithIncludes = userService.findById(userId);

        if (currentUserWithIncludes == null) {
            log.debug("User with id " + userId + "not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User currentUser = currentUserWithIncludes.getData();

        currentUser.setPassword((currentUser.getPassword()));
        currentUser.setFirstName(currentUser.getLastName());
        currentUser.setLastName(currentUser.getLastName());
        currentUser.setUpdatedAt(currentUser.getUpdatedAt());
        currentUser.setUpdatedBy((currentUser.getUpdatedBy()));
        currentUser.setStatus(currentUser.getStatus());
        currentUser.setFrom(currentUser.getFrom());

        WithIncludes<User> updatedUserWithIncludes = userService.saveUser(user);
        return new ResponseEntity<>(updatedUserWithIncludes, HttpStatus.OK);
    }
}
