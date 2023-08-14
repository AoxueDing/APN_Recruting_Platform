package com.altomni.apn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
@Data
@Entity
@Table(name = "candidate_skills")
@AllArgsConstructor
public class CandidateSkill extends EntityBase {

    private Long candidateId;

    private Long skillId;
}