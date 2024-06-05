package com.example.demo.repositories;

import com.example.demo.models.Major;
import com.example.demo.models.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MajorRepository extends JpaRepository<Major, Long> {
    @Query("SELECT m.id, m.name FROM Major m")
    List<Object[]> findAllMajors();

    @Query(value = "select m.id, m.name from Major m", nativeQuery = true)
    Student findMajors(String id);

    @Query(value = "select * from Major where id = ?1", nativeQuery = true)
    Major findByID(String id);

    @Query(value = "select * from Major", nativeQuery = true)
    List<Major> findMajors();
}
