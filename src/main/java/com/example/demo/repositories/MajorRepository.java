package com.example.demo.repositories;

import com.example.demo.models.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Long> {}
