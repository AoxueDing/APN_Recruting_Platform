package com.altomni.apn.service;

import com.altomni.apn.model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by jianhui on 4/24/17.
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Includes {
    private Map<String, Industry> industries;
    private Map<String, Country> countries;
    private Map<String, City> cities;
    private Map<String, Company> companies;

    public void includeIndustries(ListWithIncludes<Industry> listWithIncludes) {
        industries = includeT(listWithIncludes, Includes::getIndustries);
    }

    public void includeCountries(ListWithIncludes<Country> listWithIncludes) {
        countries = includeT(listWithIncludes, Includes::getCountries);
    }

    public void includeCities(ListWithIncludes<City> listWithIncludes) {
        cities = includeT(listWithIncludes, Includes::getCities);
    }

    public void includeCompanies(ListWithIncludes<Company> listWithIncludes) {
        companies = includeT(listWithIncludes, Includes::getCompanies);
    }

    public void include(Industry entity) {
        if (entity == null) return;
        if (industries == null) industries = new HashMap<>();
        industries.put(entity.getIdString(), entity);
    }

    public void include(Country entity) {
        if (entity == null) return;
        if (countries == null) countries = new HashMap<>();
        countries.put(entity.getIdString(), entity);
    }

    public void include(City entity) {
        if (entity == null) return;
        if (cities == null) cities = new HashMap<>();
        cities.put(entity.getIdString(), entity);
    }

    public void include(Company entity) {
        if (entity == null) return;
        if (companies == null) companies = new HashMap<>();
        companies.put(entity.getIdString(), entity);
    }

    public void mergeFrom(Includes other) {
        if (other == null) return;
        industries = merge(industries, other.getIndustries());
        countries = merge(countries, other.getCountries());
        cities = merge(cities, other.getCities());
    }

    private <T>
    Map<String, T> merge(Map<String, T> m1, Map<String, T> m2) {
        if (m2 == null) return m1;
        if (m1 == null) m1 = new HashMap<>();
        m1.putAll(m2);
        return m1;
    }

    private <T extends EntityBase>
    Map<String, T> includeT(ListWithIncludes<T> tWithIncludes, Function<Includes, Map<String, T>> fnField) {
        Map<String, T> field = fnField.apply(this);
        if (tWithIncludes == null || tWithIncludes.getData() == null || tWithIncludes.getData().isEmpty()) return field;
        if (field == null) field = new HashMap<>();
        field.putAll(Utils.toMap(tWithIncludes.getData()));
        mergeFrom(tWithIncludes.getIncludes());
        return field;
    }
}
