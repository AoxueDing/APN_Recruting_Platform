package com.altomni.apn.repository.vendor;

/**
 * Created by JIALIN on 4/22/2017.
 */

import com.altomni.apn.model.CandidateCollege;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CandidateCollegeRepo extends PagingAndSortingRepository<CandidateCollege, Long> {
    List<CandidateCollege> findByCandidateId(long candidateId);

}
