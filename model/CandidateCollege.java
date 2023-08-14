package com.altomni.apn.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
@Table (name="candidate_colleges")
public class CandidateCollege extends EntityBase {
    @Column(name="candidateId")
    private Long candidateId;
    @Column(name = "collegeId")
    private Long collegeId;
    @Column(name = "startDate")
    private Date startDate;
    @Column(name="endDate")
    private Date endDate;
    @Column(name="degree")
    private Long degree;
    @Column(name="majorId")
    private Long majorId;
}
