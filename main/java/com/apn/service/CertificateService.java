package com.altomni.apn.service;

import com.altomni.apn.model.CandidateCertificate;
import com.altomni.apn.model.CandidateCustomizedCertificate;

import java.util.List;

/**
 * Created by Alfred Yuan on 5/3/17.
 */
public interface CertificateService {
    void saveCertificates(List<CandidateCertificate> candidateCertificates);

    void saveCustomizedCertificates(List<CandidateCustomizedCertificate> candidateCustomizedCertificates);
}
