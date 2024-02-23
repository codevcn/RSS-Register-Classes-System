package com.example.demo.configs.security;

import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentService studentService;

    @Override
    public CustomUserDetails loadUserByUsername(String studentID) throws UsernameNotFoundException {
        Student student = studentService.findByStudentID(studentID);
        if (student == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(student);
    }
}
