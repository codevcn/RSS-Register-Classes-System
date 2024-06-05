package com.example.demo.repositories;

import com.example.demo.models.RegisterSession;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegisterSessionRepository extends JpaRepository<RegisterSession, Long> {
    @Query(value = "SELECT * FROM RegisterSession WHERE regSessCode = ?1", nativeQuery = true)
    Optional<RegisterSession> findByRegSessCode(String regSessCode);

    @Query(value = "SELECT * FROM RegisterSession WHERE CURRENT_TIMESTAMP BETWEEN beginTime AND endTime",
        nativeQuery = true)
    List<RegisterSession> findByCurrentlyOpenings();
}
