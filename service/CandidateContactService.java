package com.altomni.apn.service;

import com.altomni.apn.model.CandidateContact;
import com.altomni.apn.model.enums.ContactType;

import java.util.List;
import java.util.Map;

/**
 * Created by Alfred Yuan on 5/2/17.
 */
public interface CandidateContactService {
    CandidateContact getContact(int type, String contact);

    Map<ContactType, CandidateContact> getContacts(Long candidateId);

    void batchInsertContact(List<CandidateContact> contacts);
}
