package com.altomni.apn.service;

import com.altomni.apn.model.Industry;
import com.altomni.apn.repository.common.IndustryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jianhui on 4/22/17.
 */
@Slf4j
@Service("industryService")
public class IndustryService {
    @Autowired
    private IndustryRepo industryRepo;

    public ListWithIncludes<Industry> findAll () {
        List<Industry> industries = (List<Industry>) industryRepo.findAll();
        return new ListWithIncludes<Industry>(industries);
    }
}
