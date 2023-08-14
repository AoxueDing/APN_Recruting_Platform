package com.altomni.apn.service.impl;

import com.altomni.apn.model.User;
import com.altomni.apn.repository.vendor.UserRepo;
import com.altomni.apn.service.UserService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JIALIN on 5/2/2017.
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    public WithIncludes<User> findById(Long id) {
        log.debug("findById is executed");

        try {
            return new WithIncludes<>(userRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public WithIncludes<User> saveUser(User user) {
        log.debug("saveUser is executed");

        try {
            return new WithIncludes<>(userRepo.save(user));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
