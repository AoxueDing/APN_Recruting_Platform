package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
@Data
@Entity
@Table(name = "activity_memo")
public class ActivityMemo extends EntityBase {
    @Column
    private Long activityId;

    @Column(name = "data")
    private String memo;

    @Column
    private Long createBy;

    @Column
    private Date createAt;
}
