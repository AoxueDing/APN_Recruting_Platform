package com.altomni.apn.repository.common;

import com.altomni.apn.model.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by jianhui on 4/22/17.
 */
public interface CompanyRepo extends PagingAndSortingRepository<Company, Long> {
    List<Company> findByIdIn(Collection<Long> ids);
}
