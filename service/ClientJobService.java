package com.altomni.apn.service;

import com.altomni.apn.model.ClientJob;

/**
 * Created by JIALIN on 5/4/2017.
 */
public interface ClientJobService {
    WithIncludes<ClientJob> findById(Long id);
    WithIncludes<ClientJob> saveClientJob(ClientJob clientJob);
}
