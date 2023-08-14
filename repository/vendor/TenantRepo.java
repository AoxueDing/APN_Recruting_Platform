package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.Tenant;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by JIALIN on 5/4/2017.
 */
public interface TenantRepo extends PagingAndSortingRepository<Tenant, Long> {
}
