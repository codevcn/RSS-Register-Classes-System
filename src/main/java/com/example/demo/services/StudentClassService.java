package com.example.demo.services;

import com.example.demo.models.StudentClass;
import com.example.demo.repositories.StudentClassRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentClassService {

    @Autowired
    private StudentClassRepository studentClassRepository;

    public List<StudentClass> getAllStudentClasses() {
        return (List<StudentClass>) studentClassRepository.findAll();
    }
    
}
