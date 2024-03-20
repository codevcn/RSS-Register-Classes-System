package com.example.demo.controllers;

import com.example.demo.DTOs.response.AdminResDTOs;
import com.example.demo.DTOs.response.SubjectResDTO;
import com.example.demo.DTOs.response.AdminResDTOs.GetAdminInfoResDTO;
import com.example.demo.DTOs.response.StudentResDTOs.GetStudentInfoResDTO;
import com.example.demo.DTOs.response.SubjectResDTO.CreateSubjectInfoResDTO;
import com.example.demo.DTOs.response.SubjectResDTO.CreditOfSubjectResDTO;
import com.example.demo.DTOs.response.SubjectResDTO.GetAllSubjectInfoResDTO;
import com.example.demo.DTOs.response.SubjectResDTO.GetSubjectInfoResDTO;
import com.example.demo.DTOs.response.SubjectResDTO.MajorOfSubjectResDTO;
import com.example.demo.models.Admin;
import com.example.demo.models.Major;
import com.example.demo.models.Student;
import com.example.demo.models.Subject;
import com.example.demo.services.SubjectService;
import com.example.demo.utils.exceptions.CustomBaseException;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/subjects/")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("{input}")
    public ResponseEntity<GetSubjectInfoResDTO> getSubjectInfo(@PathVariable String input) throws CustomBaseException {
        Subject subject = subjectService.getSubjectInfo(null, input);
        Major major = subject.getMajor();
        return ResponseEntity.ok(new GetSubjectInfoResDTO(subject.getId(), subject.getName(), subject.getCreditsCount(),
                new MajorOfSubjectResDTO(major.getId(), major.getName())));
    }

    @GetMapping("get-subject/{id}")
    public ResponseEntity<GetAllSubjectInfoResDTO> getSubject(
            @PathVariable("id") String id) {
        Subject subject = subjectService.getSubjectById(id);
        GetAllSubjectInfoResDTO subjectInfoDTO = new GetAllSubjectInfoResDTO(
                subject.getId(),
                subject.getName(),
                subject.getUpdatedAt(),
                subject.getCreditsCount(),
                new MajorOfSubjectResDTO(subject.getMajor().getId(), subject.getMajor().getName()));
        return ResponseEntity.ok(subjectInfoDTO);
    }

    @PutMapping("create-subject")       
    public ResponseEntity<CreateSubjectInfoResDTO> CreateSubject(
        @RequestBody SubjectResDTO.CreateSubjectInfoResDTO CreateSubjectInfo) {
                System.out.println(">>>>>"+CreateSubjectInfo);
        Subject createsubject = subjectService.createSubject(CreateSubjectInfo);
        CreateSubjectInfoResDTO subjectInfoDTO = new CreateSubjectInfoResDTO(
                createsubject.getId(),
                createsubject.getName(),
                createsubject.getCreatedAt(),
                createsubject.getUpdatedAt(),
                createsubject.getCreditsCount(),
                new CreditOfSubjectResDTO(createsubject.getCredit().getId()),
                new MajorOfSubjectResDTO(createsubject.getMajor().getId(), createsubject.getMajor().getName()));
        return ResponseEntity.ok(subjectInfoDTO);
    }

    @GetMapping("get-all-subject")
    public ResponseEntity<List<GetAllSubjectInfoResDTO>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<GetAllSubjectInfoResDTO> subjectInfoList = subjects.stream()
                .map(subject -> new GetAllSubjectInfoResDTO(
                        subject.getId(),
                        subject.getName(),
                        subject.getUpdatedAt(),
                        subject.getCreditsCount(),
                        new MajorOfSubjectResDTO(subject.getMajor().getId(), subject.getMajor().getName())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(subjectInfoList);
    }

    @PutMapping("update-subject/{id}")
    public ResponseEntity<GetAllSubjectInfoResDTO> updateSubject(
            @PathVariable("id") String id,
            @RequestBody SubjectResDTO.GetAllSubjectInfoResDTO updatedSubjectInfo) {
        Subject updatedSubject = subjectService.updateSubject(id, updatedSubjectInfo);
        GetAllSubjectInfoResDTO updatedSubjectResponse = new GetAllSubjectInfoResDTO(
                updatedSubject.getId(),
                updatedSubject.getName(),
                updatedSubject.getUpdatedAt(),
                updatedSubject.getCreditsCount(),
                new MajorOfSubjectResDTO(updatedSubject.getMajor().getId(), updatedSubject.getMajor().getName()));
        return ResponseEntity.ok(updatedSubjectResponse);
    }

    @PutMapping("hide-subject/{id}")
    public ResponseEntity<String> hideSubject(@PathVariable String id) {
        subjectService.hideSubject(id);
        return ResponseEntity.ok("Subject hidden successfully");
    }

    @GetMapping("get-all-major")
    public ResponseEntity<List<MajorOfSubjectResDTO>> MajorOfSubject() {
        List<Major> majors = subjectService.getAllMajors();
        List<MajorOfSubjectResDTO> majorList = majors.stream()
                .map(major -> new MajorOfSubjectResDTO(
                        major.getId(),
                        major.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(majorList);
    }

//     GetMapping("get-subject/{id}")
//         public ResponseEntity<SubjectAndAllMajorsInfoDTO> getSubject(
//                         @PathVariable("id") String id) {
//                 Subject subject = subjectService.getSubjectById(id);
//                 GetAllSubjectInfoResDTO subjectInfoDTO = new GetAllSubjectInfoResDTO(
//                                 subject.getId(),
//                                 subject.getName(),
//                                 subject.getUpdatedAt(),
//                                 subject.getCreditsCount(),
//                                 new MajorOfSubjectResDTO(subject.getMajor().getId(), subject.getMajor().getName()));               
//                 List<Major> majors = subjectService.getAllMajors();
//                 List<MajorOfSubjectResDTO> majorList = majors.stream()
//                                 .map(major -> new MajorOfSubjectResDTO(
//                                                 major.getId(),
//                                                 major.getName()))
//                                 .collect(Collectors.toList());
//         SubjectAndAllMajorsInfoDTO subjectAndAllMajorsInfoDTO = new SubjectAndAllMajorsInfoDTO(subjectInfoDTO, majorList);
//         return ResponseEntity.ok(subjectAndAllMajorsInfoDTO);
//         }
}
