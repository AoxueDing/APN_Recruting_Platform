package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by JIALIN on 5/4/2017.
 */
@Data
@Entity
@Table(name="client_jobs")
public class ClientJob extends EntityBase {
    @Column(name = "jobId")
    private Long jobId;
    @Column(name = "clientId")
    private Long clientId;
    @Column(name = "hiringManager")
    private String hiringManager;
    @Column(name = "createdAt")
    private Date createdAt ;
    @Column(name = "createdBy")
    private Long createdBy;
    @Column(name = "updatedAt")
    private Date updatedAt ;
    @Column(name = "updatedBy")
    private Long updatedBy;
    @Column(name = "deletedAt")
    private Date deletedAt ;
    @Column(name = "deletedBy")
    private Long deletedBy;
}
