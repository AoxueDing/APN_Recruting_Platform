package com.altomni.apn.service.impl;

import com.altomni.apn.model.CandidateNote;
import com.altomni.apn.repository.vendor.CandidateNoteRepo;
import com.altomni.apn.service.CandidateNoteService;
import com.altomni.apn.service.ListWithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alfred Yuan on 4/23/17.
 */
@Slf4j
@Service("candidateNoteService")
public class CandidateNoteServiceImpl implements CandidateNoteService {
    @Autowired
    private CandidateNoteRepo candidateNoteRepo;

    @Override
    public ListWithIncludes<CandidateNote> getCandidateNotes(Long candidateId, Long userId) {
        log.debug("get candidate notes:{} by user:{} ", candidateId, userId);
        try {
            List<CandidateNote> candidateNotes = candidateNoteRepo.findByCandidateIdOrderByCreateAtDesc(candidateId);

            // self notes + other's visible notes
            candidateNotes = candidateNotes.stream().filter(candidateNote -> candidateNote.getCreateBy().equals(userId) || candidateNote.getVisibility() == 0).collect(Collectors.toList());
            return new ListWithIncludes<>(candidateNotes);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }

    @Override
    public CandidateNote createCandidateNote(CandidateNote candidateNote) {
        log.debug("create candidate note");
        try {
            return candidateNoteRepo.save(candidateNote);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
}