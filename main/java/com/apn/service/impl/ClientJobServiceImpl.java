package com.altomni.apn.service.impl;

import com.altomni.apn.model.ClientJob;
import com.altomni.apn.repository.vendor.ClientJobRepo;
import com.altomni.apn.service.ClientJobService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JIALIN on 5/4/2017.
 */
@Slf4j
@Service("clientJobService")
public class ClientJobServiceImpl implements ClientJobService{
    @Autowired
    ClientJobRepo clientJobRepo;

    public WithIncludes<ClientJob> findById(Long id) {
        log.debug("findById is executed");

        try {
            return new WithIncludes<>(clientJobRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public WithIncludes<ClientJob> saveClientJob(ClientJob clientJob) {
        log.debug("saveClientJob is executed");

        try {
            return new WithIncludes<>(clientJobRepo.save(clientJob));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
