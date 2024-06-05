package com.example.demo.services;

import com.example.demo.models.Major;
import com.example.demo.repositories.MajorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorService {

    @Autowired
    private MajorRepository majorRepository;

    // public List<Major> getAllMajors() {
    // return (List<Student>) majorRepository.findAll();
    // }

    // public Major getMajorInfo(@NonNull HttpServletRequest httpServletRequest) {
    // String id = httpServletRequest.getUserPrincipal().getName();
    // return studentRepository.findMajors(id);
    // }

    public List<Major> getAllMajors() {
        return (List<Major>) majorRepository.findAll();
    }
}
