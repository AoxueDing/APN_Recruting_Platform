package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alfred Yuan on 5/3/17.
 */
@Data
@Entity
@Table(name = "certificates")
public class Certificate extends EntityBase {
    @Column
    private String name;
}
