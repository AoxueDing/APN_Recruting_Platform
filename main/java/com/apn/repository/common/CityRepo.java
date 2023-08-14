package com.altomni.apn.repository.common;

import com.altomni.apn.model.City;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
public interface CityRepo extends PagingAndSortingRepository<City, Long> {
    List<City> findByIdIn(Collection<Long> ids);

    List<City> findByProvinceId(Long provinceId);
}