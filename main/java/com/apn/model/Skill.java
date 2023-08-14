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
@Table(name = "skills")
public class Skill extends EntityBase {
    @Column
    private String name;
}