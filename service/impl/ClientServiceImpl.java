package com.altomni.apn.service.impl;

import com.altomni.apn.model.Client;
import com.altomni.apn.repository.vendor.ClientRepo;
import com.altomni.apn.service.ClientService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JIALIN on 5/11/2017.
 */
@Slf4j
@Service("clientService")
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepo clientRepo;

    public WithIncludes<Client> findById(Long id) {
        log.debug("findById is executed");

        try {
            return new WithIncludes<>(clientRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public WithIncludes<Client> saveClient(Client client) {
        log.debug("saveClient is executed");

        try {
            return new WithIncludes<>(clientRepo.save(client));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
