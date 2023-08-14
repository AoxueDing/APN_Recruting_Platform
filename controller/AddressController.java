package com.altomni.apn.controller;

import com.altomni.apn.model.City;
import com.altomni.apn.model.Country;
import com.altomni.apn.model.Province;
import com.altomni.apn.service.AddressService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
@Slf4j
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public ResponseEntity<ListWithIncludes<Country>> getAllCountries() {
        log.debug("get all countries");

        return ResponseEntity.ok(addressService.getAllCountries());
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/countries/{id}/provinces", method = RequestMethod.GET)
    public ResponseEntity<ListWithIncludes<Province>> getAllProvincesByCountryId(@PathVariable("id") Long id) {
        log.debug("get all provinces by countryId: " + id);

        if (addressService.getCountry(id).getData() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(addressService.getAllProvinces(id));
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/countries/{countryid}/provinces/{provinceid}/cities", method = RequestMethod.GET)
    public ResponseEntity<ListWithIncludes<City>> getAllCitiesByCountryIdAndProvinceId(@PathVariable("countryid") Long countryId, @PathVariable("provinceid") Long provinceId) {
        log.debug("get cities with countyId: " + countryId + ", provinceId: " + provinceId);

        WithIncludes<Country> country = addressService.getCountry(countryId);
        WithIncludes<Province> province = addressService.getProvince(provinceId);
        if (country.getData() == null || province.getData() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(addressService.getAllCities(provinceId));
    }
}