package com.example.demo.repositories;

import com.example.demo.models.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MajorRepository extends JpaRepository<Major, String> {
    @Query(
        value = "select * from Major where id = ?1",
        nativeQuery = true
    )
    Major findByID(String id);
}
