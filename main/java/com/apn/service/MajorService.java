package com.altomni.apn.service;

import com.altomni.apn.model.Major;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
public interface MajorService {
    ListWithIncludes<Major> getAllMajors();
}