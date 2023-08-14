package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alfred Yuan on 4/25/17.
 */
@Data
@Entity
@Table(name = "colleges")
public class College extends EntityBase {
    @Column
    private String name;

    @Column
    private Long provinceId;

    @Column(name = "en_name")
    private String enName;
}
