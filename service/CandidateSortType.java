package com.altomni.apn.service;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by Alfred Yuan on 5/8/17.
 */
public enum CandidateSortType {
    NAME_ASC(1, "name_asc", "fullName", "ASC"),
    NAME_DSC(2, "name_dsc", "fullName", "DSC"),

    EMAIL_ASC(3, "email_asc", "emails", "ASC"),
    EMAIL_DSC(4, "email_dsc", "emails", "DSC"),

    PHONE_ASC(5, "phone_asc", "phones", "ASC"),
    PHONE_DSC(6, "phone_dsc", "phones", "DSC"),

    TITLE_ASC(7, "title_asc", "title", "ASC"),
    TITLE_DSC(8, "title_dsc", "title", "DSC"),

    COMPANY_ASC(9, "company_asc", "company", "ASC"),
    COMPANY_DSC(10, "company_dsc", "company", "DSC");

    public int type;
    public String name;
    public String dbField;
    public String sortIn;

    CandidateSortType(int type, String name, String dbField, String sortIn) {
        this.type = type;
        this.name = name;
        this.dbField = dbField;
        this.sortIn = sortIn;
    }

    private static Map<String, CandidateSortType> values = Maps.newHashMap();
    static {
        for (CandidateSortType candidateSortType : CandidateSortType.values()) {
            values.put(candidateSortType.name, candidateSortType);
        }
    }

    public static CandidateSortType getSortType(String name) {
        return values.get(name);
    }
}
