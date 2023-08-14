package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.Certificate;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Alfred Yuan on 5/3/17.
 */
public interface CertificateRepo extends PagingAndSortingRepository<Certificate, Long> {
}
