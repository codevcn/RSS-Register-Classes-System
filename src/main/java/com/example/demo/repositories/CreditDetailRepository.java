package com.example.demo.repositories;

import com.example.demo.models.CreditDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreditDetailRepository extends JpaRepository<CreditDetail, Long> {
    @Query(value = "SELECT * FROM CreditDetail WHERE id = ?1", nativeQuery = true)
    CreditDetail findByMajorBlockID(Long majorBlockID);
}
