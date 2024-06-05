package com.example.demo.repositories;

import com.example.demo.models.ReceiptSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReceiptSubjectRepository extends JpaRepository<ReceiptSubject, Long> {
    @Query(value = "SELECT * FROM ReceiptSubject WHERE receiptID = ?1", nativeQuery = true)
    List<ReceiptSubject> findByRegisterReceiptID(Long registerReceiptID);
}
