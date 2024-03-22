package com.example.demo.controllers;

import com.example.demo.DTOs.response.StudentResDTOs.GetStudentInfoResDTO;
import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.status(HttpStatus.OK).body(
            new GetStudentInfoResDTO(
                student.getStudentCode(),
                student.getPhone(),
                student.getFullName(),
                student.getBirthday(),
                student.getGender(),
                student.getMajor().getName()
            )
        );
    }

    @GetMapping("get-all-student")
    public ResponseEntity<List<GetStudentInfoResDTO>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<GetStudentInfoResDTO> studentInfoList = students
            .stream()
            .map(
                student ->
                    new GetStudentInfoResDTO(
                        student.getStudentCode(),
                        student.getPhone(),
                        student.getFullName(),
                        student.getBirthday(),
                        student.getGender(),
                        student.getMajor().getName()
                    )
            )
            .collect(Collectors.toList());
        return ResponseEntity.ok(studentInfoList);
    }

    // @GetMapping("get-student/{studentID}")
    // public ResponseEntity<GetStudentInfoResDTO> getSelectedStudentInfo(
    //     @PathVariable("studentID") String studentID
    // ) {
    //     Student student = studentService.getStudentById(studentID);
    //     GetStudentInfoResDTO studentInfoDTO = new GetStudentInfoResDTO(
    //         student.getStudentCode(),
    //         student.getPhone(),
    //         student.getFullName(),
    //         student.getBirthday(),
    //         student.getGender(),
    //         student.getMajor().getName()
    //     );
    //     return ResponseEntity.ok(studentInfoDTO);
    // }

    // @PutMapping("update-student/{id}")
    // public ResponseEntity<GetStudentInfoResDTO> updateStudent(
    //     @PathVariable("id") String id,
    //     @RequestBody StudentResDTOs.GetStudentInfoResDTO updatedStudentInfo
    // ) {
    //     System.out.println("ID của sinh viên cần cập nhật: " + id);
    //     Student updatedStudent = studentService.updateStudent(id, updatedStudentInfo);
    //     if (updatedStudent != null) {
    //         GetStudentInfoResDTO updatedStudentResponse = new GetStudentInfoResDTO(
    //             updatedStudent.getStudentCode(),
    //             updatedStudent.getPhone(),
    //             updatedStudent.getFullName(),
    //             updatedStudent.getBirthday(),
    //             updatedStudent.getGender(),
    //             updatedStudent.getMajor().getName()
    //         );
    //         return ResponseEntity.ok(updatedStudentResponse);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    @PutMapping("/hide-student/{id}")
    public ResponseEntity<String> hideStudent(@PathVariable String id) {
        return ResponseEntity.ok("Student hidden successfully");
    }
}
