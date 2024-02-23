package com.example.demo.repositories;

import com.example.demo.models.CreditDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditDetailRepository extends JpaRepository<CreditDetail, Integer> {}
