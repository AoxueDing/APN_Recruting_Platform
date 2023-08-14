package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by JIALIN on 5/1/2017.
 */

@Data
@Entity
@Table(name = "permissions")
public class Permission extends EntityBase{
    @Column(name = "name")
    private String name;
}
