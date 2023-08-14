package com.altomni.apn.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Alfred Yuan on 4/23/17.
 */
@Data
@Entity
@Builder
@Table(name = "candidate_notes")
public class CandidateNote extends EntityBase {
    @Column
    private Long candidateId;

    @Column
    private Long tenantId;

    @Column
    private String title;

    @Column
    private String note;

    @Column
    private Long createBy;

    @Column
    private Date createAt;

    @Column
    private Integer type;

    @Column
    private Integer priority;

    @Column
    private Integer visibility;
}