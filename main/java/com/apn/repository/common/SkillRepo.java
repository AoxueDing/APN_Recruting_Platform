package com.altomni.apn.repository.common;

import com.altomni.apn.model.Skill;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Alfred Yuan on 4/21/17.
 */
public interface SkillRepo extends PagingAndSortingRepository<Skill, Long> {
    List<Skill> findByIdIn(Collection<Long> ids);

    Skill findByName(String name);
}