package com.altomni.apn.service.impl;

import com.altomni.apn.model.CandidateCertificate;
import com.altomni.apn.model.CandidateCustomizedCertificate;
import com.altomni.apn.repository.vendor.CandidateCertificateRepo;
import com.altomni.apn.repository.vendor.CandidateCustomizedCertificateRepo;
import com.altomni.apn.service.CertificateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alfred Yuan on 5/3/17.
 */
@Slf4j
@Service("certificateService")
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CandidateCertificateRepo candidateCertificateRepo;
    @Autowired
    private CandidateCustomizedCertificateRepo candidateCustomizedCertificateRepo;

    @Override
    public void saveCertificates(List<CandidateCertificate> candidateCertificates) {
        log.debug("save certificates");

        candidateCertificateRepo.save(candidateCertificates);
    }

    @Override
    public void saveCustomizedCertificates(List<CandidateCustomizedCertificate> candidateCustomizedCertificates) {
        log.debug("save customized certificates");

        candidateCustomizedCertificateRepo.save(candidateCustomizedCertificates);
    }
}
