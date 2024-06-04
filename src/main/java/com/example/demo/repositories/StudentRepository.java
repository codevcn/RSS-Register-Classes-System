package com.example.demo.repositories;

import com.example.demo.models.Account;
import com.example.demo.models.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(
        value = "select u" + " from Student u" + " where u.id = ?1" + " and u.deleted = 0",
        nativeQuery = true
    )
    Student findByID(String id);

    @Query(
        value = "SELECT * FROM Student s WHERE s.id = ?1 AND s.deleted = 0",
        nativeQuery = true
    )
    Student findStudent(String id);

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

    @Transactional
    @Modifying
    @Query(
        value = "INSERT INTO Student (studentCode, phone, fullName, birthday, gender, classID, majorID, createdAt, updatedAt, deleted, IDCard, year) " +
        "VALUES (:studentCode, :phone, :fullName, :birthday, :gender, :classID, :majorID, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, :idcard, YEAR(GETDATE()))",
        nativeQuery = true
    )
    void saveStudent(
        @Param("studentCode") String studentCode,
        @Param("phone") String phone,
        @Param("fullName") String fullName,
        @Param("birthday") String birthday,
        @Param("idcard") String idcard,
        @Param("gender") String gender,
        @Param("classID") Long classID,
        @Param("majorID") Long majorID
        // @Param("accountID") Long accountID
    );

    @Query(
        value = "SELECT rr.studentID, s.id, s.subjectCode, s.name, s.creditsCount, rs.receiptID, rs.id " +
            "FROM ReceiptSubject rs " +
            "INNER JOIN Subject s ON rs.subjectID = s.id " +
            "INNER JOIN RegisterReceipt rr ON rs.receiptID = rr.id ",
        nativeQuery = true
    )
    List<Object[]> getAllRegistrations();

    @Query(
        value = "SELECT s.* FROM Student s JOIN Account a ON s.accountID = a.id WHERE a.username = ?1 AND a.deleted = 0",
        nativeQuery = true
    )
    Student findStudentByUserName(String username);

}
