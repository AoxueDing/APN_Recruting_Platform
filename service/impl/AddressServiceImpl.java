package com.altomni.apn.service.impl;

import com.altomni.apn.model.City;
import com.altomni.apn.model.Country;
import com.altomni.apn.model.Province;
import com.altomni.apn.repository.common.CityRepo;
import com.altomni.apn.repository.common.CountryRepo;
import com.altomni.apn.repository.common.ProvinceRepo;
import com.altomni.apn.service.AddressService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
@Slf4j
@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private ProvinceRepo provinceRepo;

    @Autowired
    private CityRepo cityRepo;

    @Override
    public ListWithIncludes<Country> getAllCountries() {
        log.debug("get all countries");

        return new ListWithIncludes<>(Lists.newArrayList(countryRepo.findAll()));
    }

    @Override
    public ListWithIncludes<Province> getAllProvinces(Long countryId) {
        log.debug("get all provinces by countryId: " + countryId);

        return new ListWithIncludes<>(provinceRepo.findByCountryId(countryId));
    }

    @Override
    public WithIncludes<Country> getCountry(Long countryId) {
        log.debug("get country with id: " + countryId);
        return new WithIncludes<>(countryRepo.findOne(countryId));
    }

    @Override
    public WithIncludes<Province> getProvince(Long provinceId) {
        log.debug("get province with id: " + provinceId);

        return new WithIncludes<>(provinceRepo.findOne(provinceId));
    }

    @Override
    public ListWithIncludes<City> getAllCities(Long provinceId) {
        log.debug("get all cities by provinceId: " + provinceId);

        return new ListWithIncludes<>(cityRepo.findByProvinceId(provinceId));
    }
}