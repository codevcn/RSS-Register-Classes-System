package com.example.demo.controllers;

import com.example.demo.DTOs.response.StudentResDTOs.GetStudentInfoResDTO;
import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("get-student-info")
    public ResponseEntity<GetStudentInfoResDTO> getStudentInfo(
        @NonNull HttpServletRequest httpServletRequest
    ) {
        Student student = studentService.getStudentInfo(httpServletRequest);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new GetStudentInfoResDTO(
                    student.getId(),
                    student.getPhone(),
                    student.getFullName(),
                    student.getBirthday(),
                    student.getGender(),
                    student.getMajor().getName()
                    //student.getAccount().getUsername(),
                    //student.getAccount().getRole().getId()
                )
            );
    }

    @GetMapping("get-all-student")
    public ResponseEntity<List<GetStudentInfoResDTO>> getAllStudents() {
        System.out.printf(">>> run here 1\n");
        List<Student> students = studentService.getAllStudents();
        System.out.printf(">>> run here 2\n");
        for(Student student : students){
            System.out.print(student.getAccount());
        }
        System.out.printf(">>> run here 3\n");
        List<GetStudentInfoResDTO> studentInfoList = students.stream()
            .map(student -> new GetStudentInfoResDTO(
                    student.getId(),
                    student.getPhone(),
                    student.getFullName(),
                    student.getBirthday(),
                    student.getGender(),
                    student.getMajor().getName()
                    //student.getAccount().getUsername(),
                    //student.getAccount().getRole().getId()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(studentInfoList);
    }

}
