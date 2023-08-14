package com.altomni.apn.repository.vendor;

/**
 * Created by JIALIN on 4/22/2017.
 */

import com.altomni.apn.model.Experience;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ExperienceRepo extends PagingAndSortingRepository<Experience, Long> {
    List<Experience> findByCandidateId(Long candidateId);
}
