package com.altomni.apn.repository.common;

import com.altomni.apn.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by jianhui on 4/22/17.
 */
public interface CountryRepo extends PagingAndSortingRepository<Country, Long> {
    List<Country> findByIdIn(Collection<Long> ids);
}
