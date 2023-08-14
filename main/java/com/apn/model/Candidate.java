package com.altomni.apn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Alfred Yuan on 4/19/17.
 */

@Data
@Entity
@Table(name = "candidates")
public class Candidate extends EntityBase {
    @Column
    private Long referredBy;

    @Column
    private Boolean selfReferred;

    @Column
    private Long tenantId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String gender;

    @Column
    private String photo;

    @Column
    private Integer birthyear;

    @Column
    private Integer birthmonth;

    @Column
    private Integer birthday;

    @Column
    private Long nationality;

    @Column
    private String degree;

    @Embedded
    private Address address;

    @Column
    private Long workAuth;

    @Column
    private Date availableDate;

    @Column
    private Integer available;

    @Column
    private Integer validated;

    @Column
    private Long currentSalary;

    @Column
    private Long expectedSalary;

    @Column
    private Integer workSince;

    @Column
    private String originalResume;

    @Column
    private Integer blackListed;

    @Column
    private Date createdAt;

    @Column
    private Long createdBy;

    @Column
    private Date updatedAt;

    @Column
    private Long updatedBy;

    @Transient
    private List<String> skills;
    @Transient
    private List<Long> skillIds;
    @Transient
    private List<Experience> experiences;
    @Transient
    private List<CandidateCollege> educations;
    @Transient
    private String note;
    @Transient
    private List<Long> certificateIds;
    @Transient List<String> certificates;

    @Transient
    @JsonIgnore
    private String email;

    @Transient
    @JsonIgnore
    private String homePhone;

    @Transient
    @JsonIgnore
    private String workPhone;

    @Transient
    @JsonIgnore
    private String fax;

    @Transient
    @JsonIgnore
    private String cellPhone;

    @Transient
    @JsonIgnore
    private String linkedin;

    @Transient
    @JsonIgnore
    private String dice;

    @Transient
    @JsonIgnore
    private String wechat;

    @Transient
    @JsonIgnore
    private String twitter;

    @JsonProperty("contacts")
    public Map<String, String> getContacts() {
        Map<String, String> contacts = Maps.newHashMap();
        if (!StringUtils.isEmpty(email)) contacts.put("email", email);
        if (!StringUtils.isEmpty(homePhone)) contacts.put("home", homePhone);
        if (!StringUtils.isEmpty(workPhone)) contacts.put("work", workPhone);
        if (!StringUtils.isEmpty(fax)) contacts.put("fax", fax);
        if (!StringUtils.isEmpty(cellPhone)) contacts.put("cell", cellPhone);
        if (!StringUtils.isEmpty(linkedin)) contacts.put("linkedin", linkedin);
        if (!StringUtils.isEmpty(dice)) contacts.put("dice", dice);
        if (!StringUtils.isEmpty(wechat)) contacts.put("wechat", dice);
        if (!StringUtils.isEmpty(twitter)) contacts.put("twitter", twitter);

        return contacts;
    }

    @JsonProperty("contacts")
    public void setContacts(Map<String, String> contacts) {
        email = contacts.get("email");
        homePhone = contacts.get("home");
        workPhone = contacts.get("work");
        fax = contacts.get("fax");
        cellPhone = contacts.get("cell");
        linkedin = contacts.get("linkedin");
        dice = contacts.get("dice");
        wechat = contacts.get("wechat");
        twitter = contacts.get("twitter");
    }


    // for search api
    @Column
    private String fullName;

    @Column
    private String title;

    @Column
    private String company;

    @Column
    private String emails;

    @Column
    private String phones;
}
