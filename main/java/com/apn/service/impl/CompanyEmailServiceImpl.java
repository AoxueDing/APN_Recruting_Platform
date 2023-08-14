package com.altomni.apn.service.impl;

import com.altomni.apn.model.CompanyEmail;
import com.altomni.apn.repository.vendor.CompanyEmailRepo;
import com.altomni.apn.service.CompanyEmailService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JIALIN on 5/8/2017.
 */
@Slf4j
@Service("companyEmailService")
public class CompanyEmailServiceImpl implements CompanyEmailService{
    @Autowired
    private CompanyEmailRepo companyEmailRepo;
    public WithIncludes<CompanyEmail> findByEmailSuffix(String emailSuffix) {
        log.debug("get companyEmail by emailSuffix: " + emailSuffix);

        try {
            return new WithIncludes<>(companyEmailRepo.findOne(emailSuffix));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
