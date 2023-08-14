package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Alfred Yuan on 4/22/17.
 */
@Data
@Entity
@Table(name = "job_applications")
public class JobApplication extends EntityBase {
    @Column(name = "candidateId")
    private Long candidateId;

    @Column(name = "jobId")
    private Long jobId;

    @Column(name = "appliedBy")
    private Long appliedBy;

    @Column(name = "status")
    private Integer status;

    @Column(name = "type")
    private Integer type;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "createdBy")
    private Long createdBy;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "updatedBy")
    private Long updatedBy;

    @Column(name = "deletedAt")
    private Date deletedAt;

    @Column(name = "deletedBy")
    private Long deletedBy;
}