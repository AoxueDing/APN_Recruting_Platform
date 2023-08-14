package com.altomni.apn.service.impl;

import com.altomni.apn.model.CandidateContact;
import com.altomni.apn.model.enums.ContactType;
import com.altomni.apn.repository.vendor.CandidateContactRepo;
import com.altomni.apn.service.CandidateContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alfred Yuan on 5/2/17.
 */
@Slf4j
@Service("candidateContactService")
public class CandidateContactServiceImpl implements CandidateContactService {
    @Autowired
    private CandidateContactRepo candidateContactRepo;

    @Override
    public CandidateContact getContact(int type, String contact) {
        log.debug("get contact. type:{}, content:{}", type, contact);

        return candidateContactRepo.findByContactAndType(contact, type);
    }

    @Override
    public Map<ContactType, CandidateContact> getContacts(Long candidateId) {
        List<CandidateContact> candidateContacts = candidateContactRepo.findByCandidateId(candidateId);
        return candidateContacts.stream().collect(Collectors.toMap(contact -> ContactType.getContactTypeByCode(contact.getType()), contact -> contact));
    }

    @Override
    public void batchInsertContact(List<CandidateContact> contacts) {
        log.debug("batch insert contact");

        candidateContactRepo.save(contacts);
    }
}
