package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.ContactGroupMember;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/28/17.
 */
public interface ContactGroupMemberRepo extends PagingAndSortingRepository<ContactGroupMember, Long> {
    List<ContactGroupMember> findByContactId(Long contactId);
}
