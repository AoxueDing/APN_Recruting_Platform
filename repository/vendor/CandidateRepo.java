package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.Candidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/20/17.
 */
public interface CandidateRepo extends PagingAndSortingRepository<Candidate, Long> {
    @Query(value = "SELECT * FROM candidates WHERE createBy=?1 " +
            "AND " +
            "(CASE WHEN (fullName IS NOT NULL AND LENGTH(fullName)>0) THEN fullName LIKE '%?2%' ELSE 1=1 END)" +
            "AND" +
            "(CASE WHEN (emails IS NOT NULL AND LENGTH(emails)>0) THEN emails LIKE '%?3%' ELSE 1=1 END)" +
            "AND" +
            "(CASE WHEN (phones IS NOT NULL AND LENGTH(phones)>0) THEN phones LIKE '%?4%' ELSE 1=1 END)" +
            "AND" +
            "(CASE WHEN (title IS NOT NULL AND LENGTH(title)>0) THEN title LIKE '%?5%' ELSE 1=1 END)" +
            "AND" +
            "(CASE WHEN (company IS NOT NULL AND LENGTH(company)>0) THEN company LIKE '%?6%' ELSE 1=1 END)" +
            "ORDER BY ?7 ?8",
    nativeQuery = true)
    List<Candidate> search(Long userId, String name, String email, String phone, String title, String company, String sortField, String sortIn);
}
