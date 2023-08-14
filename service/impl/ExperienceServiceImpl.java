package com.altomni.apn.service.impl;

import com.altomni.apn.model.City;
import com.altomni.apn.model.Company;
import com.altomni.apn.model.Experience;
import com.altomni.apn.repository.common.CityRepo;
import com.altomni.apn.repository.vendor.ExperienceRepo;
import com.altomni.apn.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JIALIN on 4/22/2017.
 */
@Slf4j
@Service("experienceService")
public class ExperienceServiceImpl implements ExperienceService{
    @Autowired
    private ExperienceRepo experienceRepo;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CityRepo cityRepo;

    public ListWithIncludes<Experience> findByCandidateId(Long  candidateId) {
        log.debug("findByCandidateId is executed");

        List<Experience> experiences = experienceRepo.findByCandidateId(candidateId);

        ListWithIncludes<Company> companies = Utils.findForeignEntities(experiences, Experience::getCompanyId, ids -> companyService.findByIdIn(ids));
        List<City> cities = Utils.findForeignEntities(experiences, Experience::getCity, id -> cityRepo.findByIdIn(id));


        ListWithIncludes<Experience> experienceListWithInclude = new ListWithIncludes<>(experiences);
        experienceListWithInclude.getIncludes().includeCompanies(companies);
        experienceListWithInclude.getIncludes().includeCities(new ListWithIncludes<>(cities));

        return experienceListWithInclude;
    }

    @Override
    public WithIncludes<Experience> findById(Long id) {
        log.debug("find by id: " + id);

        try {
            return new WithIncludes<>(experienceRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }


    public void saveAllExperience (List<Experience> experienceList){
        log.debug("saveAllExperience is executed");

        try {
            experienceRepo.save(experienceList);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public WithIncludes<Experience> saveExperience (Experience experience){
        log.debug("saveExperience is executed");

        try {
            return new WithIncludes<>(experienceRepo.save(experience));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}
