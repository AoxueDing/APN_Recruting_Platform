package com.altomni.apn.service;

import com.altomni.apn.model.Company;
import com.altomni.apn.model.Country;
import com.altomni.apn.model.Industry;
import com.altomni.apn.repository.common.CompanyRepo;
import com.altomni.apn.repository.common.CountryRepo;
import com.altomni.apn.repository.common.IndustryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by jianhui on 4/22/17.
 */
@Slf4j
@Service("companyService")
public class CompanyService {
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private IndustryRepo industryRepo;
    @Autowired
    private CountryRepo countryRepo;

    public ListWithIncludes<Company> findAll() {
        List<Company> companies = (List<Company>) companyRepo.findAll();
        return buildCompanyListWithIncludes(companies);
    }

    public ListWithIncludes<Company> findByIdIn(Collection<Long> ids) {
        List<Company> companies = companyRepo.findByIdIn(ids);
        return buildCompanyListWithIncludes(companies);
    }

    private ListWithIncludes<Company> buildCompanyListWithIncludes(List<Company> companies) {
        List<Industry> industries = Utils.findForeignEntities(companies, Company::getIndustry, ids -> industryRepo.findByIdIn(ids));
        List<Country> countries = Utils.findForeignEntities(companies, Company::getCountry, ids -> countryRepo.findByIdIn(ids));

        ListWithIncludes<Company> companyListWithIncludes = new ListWithIncludes<>(companies);
        companyListWithIncludes.getIncludes().includeIndustries(new ListWithIncludes<>(industries));
        companyListWithIncludes.getIncludes().includeCountries(new ListWithIncludes<>(countries));
        return companyListWithIncludes;
    }
}
