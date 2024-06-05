package com.example.demo.repositories;

import com.example.demo.models.RegisterReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegisterReceiptRepository extends JpaRepository<RegisterReceipt, Long> {
    @Query(value = "SELECT * FROM RegisterReceipt WHERE regSessID = ?1 AND studentID = ?2", nativeQuery = true)
    RegisterReceipt findForResultOfNewTerm(Long regSessID, Long studentID);
}
