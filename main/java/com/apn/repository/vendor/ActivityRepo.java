package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.Activity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/23/17.
 */
public interface ActivityRepo extends PagingAndSortingRepository<Activity, Long> {
    List<Activity> findByCandidateId(Long candidateId);
}