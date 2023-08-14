package com.altomni.apn.service;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Alfred Yuan on 5/5/17.
 */
@Data
@Builder
public class CandidateSearchRequest {
    private Long userId;

    private Long tenantId;

    private String name;

    private String email;

    private String phone;

    private String title;

    private String company;

    private String sort;

    private Integer start;

    private Integer num;
}
