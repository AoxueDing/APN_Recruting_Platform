package com.altomni.apn.controller;

import com.altomni.apn.model.CandidateNote;
import com.altomni.apn.service.CandidateNoteService;
import com.altomni.apn.service.ListWithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alfred Yuan on 4/23/17.
 */
@Slf4j
@RestController
public class CandidateNoteController {
    @Autowired
    private CandidateNoteService candidateNoteService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/candidates/{candidateId}/notes", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<CandidateNote>> getCandidateNotes(@PathVariable("candidateId") Long candidateId) {
        AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        log.debug("get candidate notes:{} by user:{} ", candidateId, userInfo.getUserId());

        ListWithIncludes<CandidateNote> candidateNotes = candidateNoteService.getCandidateNotes(candidateId, userInfo.getUserId());
        if (CollectionUtils.isEmpty(candidateNotes.getData())) {
            log.debug("no candidate notes:{} by user:{}", candidateId, userInfo.getUserId());
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(candidateNotes);
    }


    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/candidates/{candidateId}/notes", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CandidateNote> createCandidateNote(@PathVariable("candidateId")Long candidateId, @RequestBody CandidateNote candidateNote) {
        log.debug("create candidate note");

        CandidateNote retCandidateNote = candidateNoteService.createCandidateNote(candidateNote);

        if (retCandidateNote == null) {
            log.debug("create candidate note failed");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(retCandidateNote);
    }
}