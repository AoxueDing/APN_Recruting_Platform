package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.JobAssignment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Alfred Yuan on 4/28/17.
 */
public interface JobAssignmentRepo extends PagingAndSortingRepository<JobAssignment, Long> {
}
