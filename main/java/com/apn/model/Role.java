package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by JIALIN on 5/5/2017.
 */
@Data
@Entity
@Table(name = "roles")
public class Role extends EntityBase{
    @Column(name = "mask")
    private Long mask;
    @Column(name = "name")
    private String name;
}
