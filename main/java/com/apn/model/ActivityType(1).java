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
@Table(name = "activity_types")
public class ActivityType extends EntityBase {
    @Column
    private Long tenantId;

    @Column
    private String name;

    @Column
    private String enName;
}
