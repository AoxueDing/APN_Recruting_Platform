package com.altomni.apn.service.impl;

import com.altomni.apn.model.UserContact;
import com.altomni.apn.repository.vendor.UserContactRepo;
import com.altomni.apn.service.UserContactService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JIALIN on 5/3/2017.
 */
@Slf4j
@Service("userContactService")
public class UserContactServiceImpl implements UserContactService{
    @Autowired
    private UserContactRepo userContactRepo;

    @Override
    public WithIncludes<UserContact> findByUserId(Long userId) {
        log.debug("find by userId: " + userId);

        try {
            return new WithIncludes<>(userContactRepo.findOne(userId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public WithIncludes<UserContact> saveUserContact(UserContact userContact) {
        log.debug("saveUserContact is executed");

        try {
            return new WithIncludes<>(userContactRepo.save(userContact));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
