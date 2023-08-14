package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by JIALIN on 5/2/2017.
 */

@Data
@Entity
@Table(name="favorites")
public class Favorite extends EntityBase{
    @Column(name = "userId")
    private Long userId;
    @Column(name = "entityId")
    private Long entityId;
    @Column(name = "entityName")
    private String entityName;
    @Column(name = "type")
    private Long type;
    @Column(name = "createdAt")
    private Date createdAt;
    @Column(name = "deletedAt")
    private Date deletedAt;
    @Column(name = "flag")
    private int flag;
}
