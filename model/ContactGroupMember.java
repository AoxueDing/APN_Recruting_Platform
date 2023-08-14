package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alfred Yuan on 4/28/17.
 */
@Data
@Entity
@Table(name = "contact_groups_members")
public class ContactGroupMember extends EntityBase {
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "contact_id")
    private Long contactId;
}
