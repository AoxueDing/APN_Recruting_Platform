package com.altomni.apn.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by JIALIN on 5/2/2017.
 */
@Data
@Entity
@Table(name="users")
public class User extends EntityBase{
    @Column(name = "tenantId")
    private Long tenantId;
    @Column(name = "password")
    private String password;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName ;
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
    @Column(name = "status")
    private Long status;
    @Column(name = "from")
    private Long  from;
}
