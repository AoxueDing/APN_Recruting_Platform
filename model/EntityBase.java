package com.altomni.apn.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by jianhui on 4/22/17.
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@MappedSuperclass
public abstract class EntityBase {
    @Id @Column private Long id;

    @JsonProperty("id")
    public String getIdString() {
        return id.toString();
    }

    @JsonProperty("id")
    public void setIdString(String idStr) {
        id = Long.parseLong(idStr);
    }
}
