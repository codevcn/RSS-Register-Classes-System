package com.example.demo.repositories;

import com.example.demo.models.ReceiptSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

public interface ReceiptSubjectRepository extends JpaRepository<ReceiptSubject, Long> {
    @Query(value = "SELECT * FROM ReceiptSubject WHERE receiptID = ?1", nativeQuery = true)
    List<ReceiptSubject> findByRegisterReceiptID(Long registerReceiptID);

    @Query(value = "SELECT * FROM ReceiptSubject WHERE id = ?", nativeQuery = true)
    ReceiptSubject findCourse(Long id);

    @Query(value = "SELECT id FROM ReceiptSubject;", nativeQuery = true)
    List<Long> getAllCourseIds();

    // @Query(
    // value = "DELETE FROM ReceiptSubject WHERE id = ?1",
    // nativeQuery = true
    // )
    // void deleteCourseById(Long id);
    @Modifying
    @Query(value = "DELETE FROM ReceiptSubject WHERE id = :id")
    void deleteCourseById(@Param("id") Long id);

}
