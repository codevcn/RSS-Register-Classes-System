package com.example.demo.repositories;

import com.example.demo.models.TeacherMajor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TeacherMajorRepository extends JpaRepository<TeacherMajor, Long> {
    @Query(value = "SELECT * FROM TeacherMajor WHERE majorID = ?1", nativeQuery = true)
    List<TeacherMajor> findByMajorID(Long majorID);
}
