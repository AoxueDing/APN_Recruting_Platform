package com.altomni.apn.repository.common;

import com.altomni.apn.model.Domain;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Alfred Yuan on 4/21/17.
 */
public interface DomainRepo extends PagingAndSortingRepository<Domain, Long> {
}