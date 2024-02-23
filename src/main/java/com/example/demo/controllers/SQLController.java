package com.example.demo.controllers;

import com.example.demo.models.Major;
import com.example.demo.models.Student;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class SQLController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping
    public ResponseEntity<Major> home() {
        System.out.printf("\n>>> run this sql controller \n");
        System.out.println();

        // // read
        // System.out.println();
        // System.out.println();
        // System.out.println("--------------------------------");
        List<Student> students = studentRepository.findAll();
        // System.out.printf("\n>>> ( \n");
        // System.out.print(students);
        // System.out.printf("\n>>> ) \n");

        return ResponseEntity.status(HttpStatus.OK).body(students.get(0).getMajor());
    }
}
