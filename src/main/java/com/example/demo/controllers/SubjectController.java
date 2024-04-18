package com.example.demo.controllers;

import com.example.demo.DTOs.response.SubjectResDTO;
import com.example.demo.DTOs.response.SubjectResDTO.*;
import com.example.demo.models.Subject;
import com.example.demo.repositories.SubjectRepository;
import com.example.demo.services.SubjectService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/subjects/")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    // @GetMapping("{input}")
    // public ResponseEntity<GetSubjectInfoResDTO> getSubjectInfo(@PathVariable
    // String input)
    // throws CustomBaseException {
    // Subject subject = subjectService.getSubjectInfo(null, input);
    // Major major = subject.getMajor();
    // return ResponseEntity.ok(
    // new GetSubjectInfoResDTO(
    // subject.getId(),
    // subject.getName(),
    // subject.getCreditsCount(),
    // new MajorOfSubjectResDTO(major.getId(), major.getName())
    // )
    // );
    // }

    @GetMapping("get-subject/{id}")
    public ResponseEntity<GetSubjectResDTO> getSubject(@PathVariable("id") Long id) {
        Subject subject = subjectRepository.findSubjectbyid(id);
        GetSubjectResDTO subjectInfoDTO = new GetSubjectResDTO(
            subject.getId(),
            subject.getSubjectCode(),
            subject.getName(),
            subject.getCreditsCount(),
            subject.getCreatedAt(),
            subject.getUpdatedAt(),
            subject.getMajor(),
            subject.getDeleted()
        );
        return ResponseEntity.ok(subjectInfoDTO);
    }

    @PostMapping("create-subject")
    public ResponseEntity<CreateSubjectInfoResDTO> CreateSubject(
        @RequestBody CreateSubjectInfoResDTO CreateSubjectInfo
    ) {
        Subject createsubject = subjectService.createSubject(CreateSubjectInfo);
        CreateSubjectInfoResDTO subjectInfoDTO = new CreateSubjectInfoResDTO(
            createsubject.getId(),
            createsubject.getName(),
            createsubject.getSubjectCode(),
            createsubject.getCreditsCount(),
            createsubject.getCreatedAt(),
            createsubject.getUpdatedAt(),
            createsubject.getMajor()
        );
        return ResponseEntity.ok(subjectInfoDTO);
    }

    @GetMapping("get-all-subject")
    public ResponseEntity<List<GetSubjectResDTO>> getAllSubjects() {
        List<Subject> allSubjects = subjectRepository.findAll();
        List<Subject> ListAllSubject = new ArrayList<>();

        for (Subject subject : allSubjects) {
            if (subject.getDeleted() == false) {
                ListAllSubject.add(subject);
            }
        }
        List<GetSubjectResDTO> subjectInfoList = ListAllSubject.stream()
            .map(
                subject ->
                    new GetSubjectResDTO(
                        subject.getId(),
                        subject.getSubjectCode(),
                        subject.getName(),
                        subject.getCreditsCount(),
                        subject.getCreatedAt(),
                        subject.getUpdatedAt(),
                        subject.getMajor(),
                        subject.getDeleted()
                    )
            )
            .collect(Collectors.toList());
        return ResponseEntity.ok(subjectInfoList);
    }

    @PutMapping("update-subject/{id}")
    public ResponseEntity<GetSubjectResDTO> updateSubject(
        @PathVariable("id") Long id,
        @RequestBody SubjectResDTO.GetSubjectResDTO updatedSubjectInfo
    ) {
        System.out.println("<<<" + updatedSubjectInfo);
        Subject updatedSubject = subjectService.updateSubject(id, updatedSubjectInfo);
        GetSubjectResDTO updatedSubjectResponse = new GetSubjectResDTO(
            updatedSubject.getId(),
            updatedSubject.getSubjectCode(),
            updatedSubject.getName(),
            updatedSubject.getCreditsCount(),
            updatedSubject.getCreatedAt(),
            updatedSubject.getUpdatedAt(),
            updatedSubject.getMajor(),
            updatedSubject.getDeleted()
        );
        return ResponseEntity.ok(updatedSubjectResponse);
    }

    @PutMapping("hide-subject/{id}")
    public ResponseEntity<String> hideSubject(@PathVariable String id) {
        subjectService.hideSubject(id);
        return ResponseEntity.ok("Subject hidden successfully");
    }
    // GetMapping("get-subject/{id}")

    // public ResponseEntity<SubjectAndAllMajorsInfoDTO> getSubject(
    // @PathVariable("id") String id) {
    // Subject subject = subjectService.getSubjectById(id);
    // GetAllSubjectInfoResDTO subjectInfoDTO = new GetAllSubjectInfoResDTO(
    // subject.getId(),
    // subject.getName(),
    // subject.getUpdatedAt(),
    // subject.getCreditsCount(),
    // new MajorOfSubjectResDTO(subject.getMajor().getId(),
    // subject.getMajor().getName()));
    // List<Major> majors = subjectService.getAllMajors();
    // List<MajorOfSubjectResDTO> majorList = majors.stream()
    // .map(major -> new MajorOfSubjectResDTO(
    // major.getId(),
    // major.getName()))
    // .collect(Collectors.toList());
    // SubjectAndAllMajorsInfoDTO subjectAndAllMajorsInfoDTO = new
    // SubjectAndAllMajorsInfoDTO(subjectInfoDTO,
    // majorList);
    // return ResponseEntity.ok(subjectAndAllMajorsInfoDTO);
    // }
}
