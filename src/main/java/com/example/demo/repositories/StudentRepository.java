package com.example.demo.repositories;

import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
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

    @Query(
        value = "select * from Account where username = ?1 and u.deleted = 0",
        nativeQuery = true
    )
    Student findStudentInfor(String username);

    @Query(
        value = "select *" + " from Student u" + " where u.accountID = ?1" + " and u.deleted = 0",
        nativeQuery = true
    )
    Student findByAccountID(Long accountID);

    @Query("SELECT s FROM Student s WHERE s.deleted = false")
    List<Student> findByDeletedFalse();

}
