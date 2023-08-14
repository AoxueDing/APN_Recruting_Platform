package com.altomni.apn.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WithIncludes<T> {
    private final T data;
    private Includes includes = new Includes();
}
