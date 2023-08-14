package com.altomni.apn.service;

import com.altomni.apn.model.UserContact;

/**
 * Created by JIALIN on 5/3/2017.
 */
public interface UserContactService {
    WithIncludes<UserContact> findByUserId (Long userId);
    WithIncludes<UserContact> saveUserContact (UserContact userContact);
}
