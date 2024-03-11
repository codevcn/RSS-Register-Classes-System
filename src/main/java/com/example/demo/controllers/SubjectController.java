package com.example.demo.controllers;

import com.example.demo.DTOs.response.SubjectResDTO.GetSubjectInfoResDTO;
import com.example.demo.DTOs.response.SubjectResDTO.MajorOfSubjectResDTO;
import com.example.demo.models.Major;
import com.example.demo.models.Subject;
import com.example.demo.services.SubjectService;
import com.example.demo.utils.exceptions.CustomBaseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/{input}")
    public ResponseEntity<GetSubjectInfoResDTO> getSubjectInfo(@PathVariable String input) throws CustomBaseException {
        Subject subject = subjectService.getSubjectInfo(null, input);
        Major major = subject.getMajor();
        return ResponseEntity.ok(new GetSubjectInfoResDTO(subject.getId(), subject.getName(), subject.getCreditsCount(), new MajorOfSubjectResDTO(major.getId(), major.getName())));
    }
}
