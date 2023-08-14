package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by JIALIN on 5/3/2017.
 */
@Data
@Entity
@Table(name="users_contacts")
public class UserContact extends EntityBase {
    @Column(name = "userId")
    private Long userId;
    @Column(name = "contact")
    private String contact;
    @Column(name = "type")
    private Long type;
    @Column(name="createdAt")
    private Date createdAt;
    @Column(name="createdBy")
    private Long createdBy;
    @Column(name="updatedAt")
    private Date updatedAt;
    @Column(name="updatedBy")
    private Long updatedBy;

}
