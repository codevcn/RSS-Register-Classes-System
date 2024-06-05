package com.example.demo.repositories;

import com.example.demo.models.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {

    @Query(value = "SELECT * FROM StudentClass WHERE majorID = ?1", nativeQuery = true)
    List<StudentClass> findByMajorID(Long majorID);
}

