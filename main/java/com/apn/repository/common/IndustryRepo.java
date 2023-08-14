package com.altomni.apn.repository.common;

import com.altomni.apn.model.Industry;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by jianhui on 4/22/17.
 */
public interface IndustryRepo extends PagingAndSortingRepository<Industry, Long> {
    List<Industry> findByIdIn(Collection<Long> ids);
}
