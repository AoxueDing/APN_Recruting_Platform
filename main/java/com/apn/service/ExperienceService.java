package com.altomni.apn.service;

/**
 * Created by JIALIN on 4/22/2017.
 */
import com.altomni.apn.model.Experience;

import java.util.List;

public interface ExperienceService {
    ListWithIncludes<Experience> findByCandidateId(Long candidateId);
    WithIncludes<Experience> findById(Long id);
    void saveAllExperience (List<Experience> experienceList);
    WithIncludes<Experience> saveExperience (Experience experience);
}
