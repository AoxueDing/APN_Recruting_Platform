package com.altomni.apn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alfred Yuan on 5/3/17.
 */
@Data
@Entity
@AllArgsConstructor
@Table(name = "candidate_certificates")
public class CandidateCertificate extends EntityBase {
    @Column
    private Long candidateId;

    @Column
    private Long certificateId;
}
