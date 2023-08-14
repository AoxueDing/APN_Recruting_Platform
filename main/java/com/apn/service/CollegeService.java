package com.altomni.apn.service;

import com.altomni.apn.model.College;

/**
 * Created by Alfred Yuan on 4/25/17.
 */
public interface CollegeService {
    WithIncludes<College> getCollege(long id);
}
