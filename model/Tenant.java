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
@Table(name="tenants")
public class Tenant extends EntityBase{
    @Column(name = "entityId")
    private Long entityId;
    @Column(name = "type")
    private Long type;
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
