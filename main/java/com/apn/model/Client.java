package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by JIALIN on 5/11/2017.
 */
@Data
@Entity
@Table(name="clients")
public class Client extends EntityBase{
    @Column(name = "tenantId")
    private Long tenantId;
    @Column(name = "companyId")
    private Long companyId;
    @Column(name = "name")
    private String name;
    @Column(name = "name2")
    private String name2;
    @Column(name = "contacts")
    private String contacts;
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
