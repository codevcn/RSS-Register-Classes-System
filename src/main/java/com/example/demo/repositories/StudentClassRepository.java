package com.example.demo.repositories;

import com.example.demo.models.StudentClass;
import com.example.demo.models.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
    
}
