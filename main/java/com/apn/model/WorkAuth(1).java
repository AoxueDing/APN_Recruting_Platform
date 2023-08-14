package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
@Data
@Entity
@Table(name = "work_auth")
public class WorkAuth extends EntityBase {
    @Column
    private String name;

    @Column(name = "enName")
    private String enName;
}