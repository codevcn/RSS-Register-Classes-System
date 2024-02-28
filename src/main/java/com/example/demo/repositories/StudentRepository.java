package com.example.demo.repositories;

import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query(
        value = "select u" + " from Student u" + " where u.id = ?1" + " and u.deleted = 0",
        nativeQuery = true
    )
    Student findByID(String id);

    @Query(
        value = "select u" + " from Student u" + " where u.accountID = ?1" + " and u.deleted = 0",
        nativeQuery = true
    )
    Student findByAccountUsername(String username);
}
