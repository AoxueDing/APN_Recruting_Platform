package com.altomni.apn.service;

import com.altomni.apn.model.Domain;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
public interface DomainService {
    WithIncludes<Domain> getDomain(Long id);

    ListWithIncludes<Domain> getAllDomains();
}