package com.example.demo.repositories;

import com.example.demo.models.StudentSubject;
import com.example.demo.models.primaryKeys.StudentSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject, StudentSubjectId> {}
