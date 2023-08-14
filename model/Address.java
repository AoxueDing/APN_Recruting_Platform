package com.altomni.apn.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * Created by jianhui on 4/25/17.
 */

@Data @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class Address {
    @Transient
    private Long country; // TODO: not in db yet
    @Column private Long city;
    @Column private String zipcode;

    @JsonProperty("country")
    public String getCountryString() {
        return country != null ? country.toString() : null;
    }

    @JsonProperty("country")
    public void setCountryString(String str) {
        country = Long.parseLong(str);
    }

    @JsonProperty("city")
    public String getCityString() {
        return city != null ? city.toString() : null;
    }

    @JsonProperty("city")
    public void setCityString(String str) {
        city = Long.parseLong(str);
    }
}
