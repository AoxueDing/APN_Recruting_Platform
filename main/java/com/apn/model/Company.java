package com.altomni.apn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jianhui on 4/22/17.
 */

@Data
@Entity @Table(name = "companies")
public class Company extends EntityBase {
    @Column private Long parentId;
    @Column private Long type;
    @Column private String name;
    @Column private String bizName;
    @Column private String zipcode;
    @Column private String phone;
    @Column private String fax;
    @Column private String url;
    @Column private String address;
    @Column private String logo;
    @Column private Long industry;
    @Column private Long country;
    @Column private Long size;
    @Column private String description;
    @Column private Long from;
    @Column private Long status;

    @JsonProperty("parentId")
    public String getParentIdString() {
        return parentId.toString();
    }

    @JsonProperty("parentId")
    public void setParentIdString(String parentIdStr) {
        parentId = Long.parseLong(parentIdStr);
    }

    @JsonProperty("industry")
    public String getIndustryString() {
        return industry.toString();
    }

    @JsonProperty("industry")
    public void setIndustryString(String industry) {
        this.industry = Long.parseLong(industry);
    }

    @JsonProperty("country")
    public String getCountryString() {
        return country.toString();
    }

    @JsonProperty("country")
    public void setCountryString(String country) {
        this.country = Long.parseLong(country);
    }

}
