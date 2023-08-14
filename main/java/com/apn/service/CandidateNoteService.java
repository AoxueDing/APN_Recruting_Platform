package com.altomni.apn.service;

import com.altomni.apn.model.CandidateNote;

/**
 * Created by Alfred Yuan on 4/23/17.
 */
public interface CandidateNoteService {
    ListWithIncludes<CandidateNote> getCandidateNotes(Long candidateId, Long userId);

    CandidateNote createCandidateNote(CandidateNote candidateNote);
}