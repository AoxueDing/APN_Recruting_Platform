package com.altomni.apn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by Alfred Yuan on 4/23/17.
 */
@Data
@Entity
@Table(name = "activities")
public class Activity extends EntityBase {
    @Column
    private Long candidateId;

    @Column
    private Long jobId;

    @Column
    private int type;

    @Column
    private Long createBy;

    @Column
    private Date createdAt;

    @Transient
    @JsonProperty
    private List<ActivityMemo> activityMemos;
}
