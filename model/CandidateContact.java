package com.altomni.apn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Alfred Yuan on 5/1/17.
 */
@Data
@Builder
@Entity
@Table(name = "candidate_contacts")
public class CandidateContact extends EntityBase {
    @Column
    private Long candidateId;

    @Column
    private Long tenantId;

    @JsonIgnore
    @Column
    private String contact;

    @JsonIgnore
    @Column
    private Integer type;

    @JsonIgnore
    @Column
    private Date createAt;

    @JsonIgnore
    @Column
    private Long createdBy;

    @JsonIgnore
    @Column
    private Date updateAt;

    @JsonIgnore
    @Column
    private Long updateBy;

    @JsonIgnore
    @Column
    private Date deleteAt;

    @JsonIgnore
    @Column
    private Long deleteBy;
}
