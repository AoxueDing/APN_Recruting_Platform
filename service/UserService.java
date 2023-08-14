package com.altomni.apn.service;

import com.altomni.apn.model.User;

/**
 * Created by JIALIN on 5/2/2017.
 */
public interface UserService {
    WithIncludes<User> findById(Long id);
    WithIncludes<User> saveUser(User user);
}
