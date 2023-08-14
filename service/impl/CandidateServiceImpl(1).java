package com.altomni.apn.service.impl;

import com.altomni.apn.model.Candidate;
import com.altomni.apn.model.CandidateCertificate;
import com.altomni.apn.model.CandidateCollege;
import com.altomni.apn.model.CandidateContact;
import com.altomni.apn.model.CandidateCustomizedCertificate;
import com.altomni.apn.model.CandidateCustomizedSkill;
import com.altomni.apn.model.CandidateNote;
import com.altomni.apn.model.CandidateSkill;
import com.altomni.apn.model.City;
import com.altomni.apn.model.Country;
import com.altomni.apn.model.Experience;
import com.altomni.apn.model.Skill;
import com.altomni.apn.model.enums.ContactType;
import com.altomni.apn.repository.common.CityRepo;
import com.altomni.apn.repository.common.CountryRepo;
import com.altomni.apn.repository.vendor.CandidateRepo;
import com.altomni.apn.service.CandidateCollegeService;
import com.altomni.apn.service.CandidateContactService;
import com.altomni.apn.service.CandidateNoteService;
import com.altomni.apn.service.CandidateSearchRequest;
import com.altomni.apn.service.CandidateService;
import com.altomni.apn.service.CandidateSkillService;
import com.altomni.apn.service.CertificateService;
import com.altomni.apn.service.ExperienceService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.Utils;
import com.altomni.apn.service.WithIncludes;
import com.altomni.apn.service.CandidateSortType;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alfred Yuan on 4/20/17.
 */
@Slf4j
@Service("candidateService")
public class CandidateServiceImpl implements CandidateService {
    @Autowired private CandidateRepo candidateRepo;
    @Autowired private CountryRepo countryRepo;
    @Autowired private CityRepo cityRepo;
    @Autowired private CandidateSkillService candidateSkillService;
    @Autowired private ExperienceService experienceService;
    @Autowired private CandidateContactService candidateContactService;
    @Autowired private CandidateCollegeService candidateCollegeService;
    @Autowired private CandidateNoteService candidateNoteService;
    @Autowired private CertificateService certificateService;

    @Override
    public ListWithIncludes<Candidate> findAll(Pageable pageable) {
        Page<Candidate> page = candidateRepo.findAll(pageable);
        List<Candidate> candidates = page.getContent();

        List<City> cities = Utils.findForeignEntities(candidates,
                c -> c.getAddress() != null ? c.getAddress().getCity() : null,
                ids -> cityRepo.findByIdIn(ids));
        List<Country> countries = Utils.findForeignEntities(candidates,
                Candidate::getNationality, ids -> countryRepo.findByIdIn(ids));

        ListWithIncludes<Candidate> listWithIncludes = new ListWithIncludes<>(candidates);

        listWithIncludes.setPageNumber(page.getNumber()); // TODO: page
        listWithIncludes.setPageSize(page.getSize());
        listWithIncludes.setCount(page.getNumberOfElements());
        listWithIncludes.setTotal(page.getTotalElements());

        listWithIncludes.getIncludes().includeCities(new ListWithIncludes<>(cities));
        listWithIncludes.getIncludes().includeCountries(new ListWithIncludes<>(countries));

        return listWithIncludes;
    }

    @Override
    public WithIncludes<Candidate> findById(Long id) {
        Candidate candidate = candidateRepo.findOne(id);
        if (candidate == null) return null;

        List<Skill> skills = candidateSkillService.findByCandidateId(id);
        candidate.setSkills(skills.stream().map(Skill::getName).collect(Collectors.toList()));

        Map<ContactType, CandidateContact> contactMap = candidateContactService.getContacts(id);
        if (contactMap.get(ContactType.EMAIL) != null) {
            candidate.setEmail(contactMap.get(ContactType.EMAIL).getContact());
        }
        if (contactMap.get(ContactType.HOME_PHONE) != null) {
            candidate.setHomePhone(contactMap.get(ContactType.HOME_PHONE).getContact());
        }
        if (contactMap.get(ContactType.WORK_PHONE) != null) {
            candidate.setWorkPhone(contactMap.get(ContactType.WORK_PHONE).getContact());
        }
        if (contactMap.get(ContactType.FAX) != null) {
            candidate.setFax(contactMap.get(ContactType.FAX).getContact());
        }

        City city = Utils.findForeignEntity(candidate,
                c -> c.getAddress() != null ? c.getAddress().getCity() : null,
                fid -> cityRepo.findOne(fid));
        Country country = Utils.findForeignEntity(candidate, Candidate::getNationality, fid -> countryRepo.findOne(fid));

        ListWithIncludes<Experience> experiences = experienceService.findByCandidateId(id);

        WithIncludes<Candidate> withIncludes = new WithIncludes<>(candidate);
        withIncludes.getIncludes().include(city);
        withIncludes.getIncludes().include(country);

        withIncludes.getIncludes().mergeFrom(experiences.getIncludes());
        candidate.setExperiences(experiences.getData());

        return withIncludes;
    }

    @Override
    public Candidate update(Candidate candidate) {
        return null;
    }

    @Override
    @Transactional
    public Candidate create(Candidate candidate) {
        log.debug("create candidate");

        String email = candidate.getEmail();
        CandidateContact contact = candidateContactService.getContact(ContactType.EMAIL.code, email);
        if (contact != null && contact.getTenantId().equals(candidate.getTenantId())) {
            return update(candidate);
        }

        Candidate newCandidate = candidateRepo.save(candidate);

        // create contacts
        List<CandidateContact> contacts = genContacts(newCandidate.getId(), candidate);
        candidateContactService.batchInsertContact(contacts);


        // create experiences
        List<Experience> experiences = candidate.getExperiences();
        experienceService.saveAllExperience(experiences);

        // create educations
        List<CandidateCollege> candidateColleges = candidate.getEducations();
        candidateCollegeService.saveAllCandidateCollege(candidateColleges);

        // create skills
        List<CandidateSkill> standardSkills = genCandidateSkills(newCandidate.getId(), candidate.getSkillIds());
        candidateSkillService.batchInsertCandidateSkills(standardSkills);

        List<String> customizedSkills = candidate.getSkills();
        String customizedSkillsStr = customizedSkills.stream().collect(Collectors.joining(","));
        candidateSkillService.insertCustomizedSkills(new CandidateCustomizedSkill(newCandidate.getId(), customizedSkillsStr));

        // create certificates
        List<CandidateCertificate> standardCertificates = genCandidateCertificates(newCandidate.getId(), candidate.getCertificateIds());
        certificateService.saveCertificates(standardCertificates);

        List<CandidateCustomizedCertificate> customizedCertificates = genCandidateCustomizedCertificates(newCandidate.getId(), candidate.getCertificates());
        certificateService.saveCustomizedCertificates(customizedCertificates);

        // create note
        if (!StringUtils.isEmpty(candidate.getNote())) {
            CandidateNote note = CandidateNote.builder()
                    .candidateId(newCandidate.getId())
                    .tenantId(newCandidate.getTenantId())
                    .note(candidate.getNote())
                    .createBy(newCandidate.getCreatedBy())
                    .build();
            candidateNoteService.createCandidateNote(note);
        }

        return newCandidate;
    }

    @Override
    public ListWithIncludes<Candidate> search(CandidateSearchRequest searchRequest) {
        CandidateSortType candidateSortType = CandidateSortType.getSortType(searchRequest.getSort());
        if (candidateSortType == null) {
            candidateSortType = CandidateSortType.NAME_ASC;
        }
        List<Candidate> candidates = candidateRepo.search(searchRequest.getUserId(), searchRequest.getName(),
                searchRequest.getEmail(), searchRequest.getPhone(), searchRequest.getTitle(), searchRequest.getCompany(), candidateSortType.dbField, candidateSortType.sortIn);

        long total = candidates.size();
        candidates = candidates.subList(searchRequest.getStart(), searchRequest.getStart() + searchRequest.getNum());
        ListWithIncludes<Candidate> listWithIncludes = new ListWithIncludes<>(candidates);
        listWithIncludes.setTotal(total);

        return listWithIncludes;
    }


    private static List<CandidateContact> genContacts(Long candidateId, Candidate candidate) {
        List<CandidateContact> contacts = Lists.newArrayList();

        // email
        contacts.add(CandidateContact.builder()
                .candidateId(candidateId)
                .tenantId(candidate.getTenantId())
                .contact(candidate.getEmail())
                .type(ContactType.EMAIL.code).build());

        // home phone
        if (!StringUtils.isEmpty(candidate.getHomePhone())) {
            contacts.add(CandidateContact.builder()
                    .candidateId(candidateId)
                    .tenantId(candidate.getTenantId())
                    .contact(candidate.getHomePhone())
                    .type(ContactType.HOME_PHONE.code).build());
        }

        // work phone
        if (!StringUtils.isEmpty(candidate.getWorkPhone())) {
            contacts.add(CandidateContact.builder()
                    .candidateId(candidateId)
                    .tenantId(candidate.getTenantId())
                    .contact(candidate.getWorkPhone())
                    .type(ContactType.WORK_PHONE.code).build());
        }

        // fax
        if (!StringUtils.isEmpty(candidate.getFax())) {
            contacts.add(CandidateContact.builder()
                    .candidateId(candidateId)
                    .tenantId(candidate.getTenantId())
                    .contact(candidate.getFax())
                    .type(ContactType.FAX.code).build());
        }

        // cellPhone
        if (!StringUtils.isEmpty(candidate.getCellPhone())) {
            contacts.add(CandidateContact.builder()
                    .candidateId(candidateId)
                    .tenantId(candidate.getTenantId())
                    .contact(candidate.getCellPhone())
                    .type(ContactType.CELL_PHONE.code).build());
        }

        // linkedin
        if (!StringUtils.isEmpty(candidate.getLinkedin())) {
            contacts.add(CandidateContact.builder()
                    .candidateId(candidateId)
                    .tenantId(candidate.getTenantId())
                    .contact(candidate.getLinkedin())
                    .type(ContactType.LINKEDIN.code).build());
        }

        // dice
        if (!StringUtils.isEmpty(candidate.getDice())) {
            contacts.add(CandidateContact.builder()
                    .candidateId(candidateId)
                    .tenantId(candidate.getTenantId())
                    .contact(candidate.getDice())
                    .type(ContactType.DICE.code).build());
        }

        // wechat
        if (!StringUtils.isEmpty(candidate.getWechat())) {
            contacts.add(CandidateContact.builder()
                    .candidateId(candidateId)
                    .tenantId(candidate.getTenantId())
                    .contact(candidate.getWechat())
                    .type(ContactType.WECHAT.code).build());
        }

        // twitter
        if (!StringUtils.isEmpty(candidate.getTwitter())) {
            contacts.add(CandidateContact.builder()
                    .candidateId(candidateId)
                    .tenantId(candidate.getTenantId())
                    .contact(candidate.getTwitter())
                    .type(ContactType.TWITTER.code).build());
        }

        return contacts;
    }

    private static List<CandidateSkill> genCandidateSkills(Long candidateId, List<Long> skillIds) {
        return skillIds.stream().map(skillId -> new CandidateSkill(candidateId, skillId)).collect(Collectors.toList());
    }

    private static List<CandidateCertificate> genCandidateCertificates(Long candidateId, List<Long> certificateIds) {
        return certificateIds.stream().map(certificateId -> new CandidateCertificate(candidateId, certificateId)).collect(Collectors.toList());
    }

    private static List<CandidateCustomizedCertificate> genCandidateCustomizedCertificates(Long candidateId, List<String> certificates) {
        return certificates.stream().map(certificate -> new CandidateCustomizedCertificate(candidateId, certificate)).collect(Collectors.toList());
    }
}
