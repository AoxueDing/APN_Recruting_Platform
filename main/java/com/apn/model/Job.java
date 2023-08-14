package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Alfred Yuan on 4/21/17.
 */
@Data
@Entity
@Table(name = "jobs")
public class Job extends EntityBase {
    @Column(name = "title")
    private String title;

    @Column(name = "code")
    private String code;

    @Column(name = "overview")
    private String overview;

    @Column(name = "responsibility")
    private String responsibility;

    @Column(name = "requirement")
    private String requirement;

    @Column(name = "division")
    private String division;

    @Column(name = "minSalary")
    private Integer minSalary;

    @Column(name = "maxSalary")
    private Integer maxSalary;

    @Column(name = "minSalary2")
    private Integer minSalary2;

    @Column(name = "maxSalary2")
    private Integer maxSalary2;

    @Column(name = "currency")
    private String currency;

    @Column(name = "unit")
    private String unit;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "city")
    private Long city;

    @Column(name = "province")
    private Long province;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "address")
    private String address;

    @Column(name = "domain")
    private Long domain;

    @Column(name = "jobType")
    private String jobType;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "postingCompany")
    private Long postingCompany;

    @Column(name = "company")
    private Long company;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "status")
    private Integer status;

    @Column(name = "originalJD")
    private String originalJD;

    @Column(name = "tags")
    private String tags;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "createdBy")
    private Long createdBy;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "updatedBy")
    private Long updatedBy;

    @Column(name = "deletedAt")
    private Date deletedAt;

    @Column(name = "deletedBy")
    private Long deletedBy;
}