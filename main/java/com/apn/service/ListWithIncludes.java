package com.altomni.apn.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Created by jianhui on 4/22/17.
 */


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListWithIncludes<T> {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer count;
    private Long total;
    private final List<T> data;
    Includes includes = new Includes();
}
