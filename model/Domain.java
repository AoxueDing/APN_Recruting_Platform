package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alfred Yuan on 4/21/17.
 */
@Data
@Entity
@Table(name = "domains")
public class Domain extends EntityBase {

    @Column(name = "name")
    private String name;

    @Column(name = "enName")
    private String enName;
}