package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.CandidateNote;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/23/17.
 */
public interface CandidateNoteRepo extends PagingAndSortingRepository<CandidateNote, Long> {
    List<CandidateNote> findByCandidateIdOrderByCreateAtDesc(Long candidateId);
}