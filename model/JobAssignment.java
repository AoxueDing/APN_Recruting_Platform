package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Alfred Yuan on 4/28/17.
 */
@Data
@Entity
@Table(name = "req_assignment")
public class JobAssignment extends EntityBase {
    @Column(name = "jobId")
    private Long jobId;

    @Column(name = "assignedBy")
    private Long assignedBy;

    @Column(name = "assignedTo")
    private Long assignedTo;

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

    @Column(name = "note")
    private String note;
}
