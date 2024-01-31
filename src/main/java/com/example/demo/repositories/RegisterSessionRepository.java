package com.example.demo.repositories;

import com.example.demo.models.RegisterSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterSessionRepository extends JpaRepository<RegisterSession, String> {}
