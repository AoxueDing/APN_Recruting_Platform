package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jianhui on 4/22/17.
 */
@Data
@Entity @Table(name = "industries")
public class Industry extends EntityBase {
    @Column private String name;
}
