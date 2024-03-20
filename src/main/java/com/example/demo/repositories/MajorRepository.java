package com.example.demo.repositories;

import com.example.demo.models.Major;
import com.example.demo.models.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MajorRepository extends JpaRepository<Major, String> {
    @Query("SELECT m.id, m.name FROM Major m")
    List<Object[]> findAllMajors();

    @Query(value = "select m.id, m.name from Major m", nativeQuery = true)
    Student findMajors(String id);
}
