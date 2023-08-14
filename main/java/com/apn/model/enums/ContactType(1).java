package com.altomni.apn.model.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by Alfred Yuan on 5/2/17.
 */
public enum ContactType {
    EMAIL(1, "email"),
    HOME_PHONE(2, "homephone"),
    WORK_PHONE(3, "workphone"),
    FAX(4, "fax"),
    CELL_PHONE(5, "cellphone"),
    WECHAT(6, "wechat"),
    LINKEDIN(7, "linkedin"),
    GITHUB(8, "github"),
    FACEBOOK(9, "facebook"),
    TWITTER(10, "twitter"),
    WEIBO(11, "weibo"),
    DICE(12, "dice"),
    LIEPIN(13, "liepin");

    public int code;

    public String name;

    ContactType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private static Map<Integer, ContactType> values = Maps.newHashMap();
    static {
        for (ContactType contactType : ContactType.values()) {
            values.put(contactType.code, contactType);
        }
    }

    public static ContactType getContactTypeByCode(Integer code) {
        return values.get(code);
    }
}
