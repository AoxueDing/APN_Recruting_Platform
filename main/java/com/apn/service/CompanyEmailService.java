package com.altomni.apn.service;

import com.altomni.apn.model.CompanyEmail;

/**
 * Created by JIALIN on 5/8/2017.
 */
public interface CompanyEmailService {
    WithIncludes<CompanyEmail> findByEmailSuffix(String emailSuffix);
}
