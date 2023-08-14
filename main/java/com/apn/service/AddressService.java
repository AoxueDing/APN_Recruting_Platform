package com.altomni.apn.service;

import com.altomni.apn.model.City;
import com.altomni.apn.model.Country;
import com.altomni.apn.model.Province;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
public interface AddressService {
    ListWithIncludes<Country> getAllCountries();

    ListWithIncludes<Province> getAllProvinces(Long countryId);

    WithIncludes<Country> getCountry(Long countryId);

    WithIncludes<Province> getProvince(Long provinceId);

    ListWithIncludes<City> getAllCities(Long provinceId);
}