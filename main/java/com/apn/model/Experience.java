package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table (name="candidate_experiences")
public class Experience extends EntityBase {
    @Column(name = "candidateId")
    private Long candidateId;
    @Column(name = "companyId")
    private Long companyId;
    @Column(name = "clientId")
    private Long clientId;
    @Column(name="startDate")
    private Date startDate;
    @Column(name="endDate")
    private Date endDate;
    @Column(name="title")
    private String title;
    @Column(name="salary")
    private Integer salary;
    @Column(name="bonus")
    private Integer bonus;
    @Column(name="stock")
    private Integer stock;
    @Column(name="currency")
    private String currency;
    @Column(name="city")
    private Long city;
    @Column(name="company2")
    private String company2;
    @Column(name="client2")
    private String client2;
    @Column(name="createdAt")
    private Date createdAt;
    @Column(name="createdBy")
    private Long createdBy;
    @Column(name="updatedAt")
    private Date updatedAt;
    @Column(name="updatedBy")
    private Long updatedBy;
    @Column(name="deletedAt")
    private Date deletedAt;
    @Column(name="deletedBy")
    private Long deletedBy;
}