package com.altomni.apn.model.enums;

/**
 * Created by Alfred Yuan on 5/2/17.
 */
public enum ErrorCode {
    EMPTY_STR(1001, "empty string"),
    INVALID_STR(1002, "invalid string"),

    INVALID_PHONE(1011, "invalid phone"),

    INVALID_EMAIL(1021, "invalid email"),

    INVALID_DATE(1031, "invalid date"),
    END_DATE_LT_BEGIN_DATE(1032, "end date less than begin date"),

    INVALID_LENGTH(1041, "invalid length");


    public int value;
    public String desc;

    ErrorCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
