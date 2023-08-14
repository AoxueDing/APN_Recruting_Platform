package com.altomni.apn.repository.common;

import com.altomni.apn.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
public interface ProvinceRepo extends PagingAndSortingRepository<Province, Long> {
    List<Province> findByCountryId(Long countryId);
}