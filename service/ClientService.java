package com.altomni.apn.service;

import com.altomni.apn.model.Client;

/**
 * Created by JIALIN on 5/11/2017.
 */
public interface ClientService {
    WithIncludes<Client> findById(Long id);
    WithIncludes<Client> saveClient(Client client);
}
