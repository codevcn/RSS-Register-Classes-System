package com.example.demo.controllers;

import com.example.demo.DTOs.response.MajorResDTO.GetMajorInfoResDTO;
import com.example.demo.DTOs.response.StudentClassResDTO.GetStudentClassInfoResDTO;
import com.example.demo.models.Major;
import com.example.demo.models.StudentClass;
import com.example.demo.services.StudentClassService;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("studentClass")
public class StudentClassesController {

    @Autowired
    private StudentClassService studentClassService;

    @GetMapping("get-all-studentclass")
    public ResponseEntity<List<GetStudentClassInfoResDTO>> MajorOfSubject() {
        List<StudentClass> studentClasses = studentClassService.getAllStudentClasses();
        System.err.println("studentClasses:>>>>>>"+ studentClasses);
        List<GetStudentClassInfoResDTO> studentClassList = studentClasses
            .stream()
            .map(
                studentclass ->
                    new GetStudentClassInfoResDTO(studentclass.getId(), studentclass.getCode(), studentclass.getName())
            )
            .collect(Collectors.toList());
        return ResponseEntity.ok(studentClassList);
    }
    
}
