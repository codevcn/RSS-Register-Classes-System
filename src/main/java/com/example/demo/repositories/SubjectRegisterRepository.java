package com.example.demo.repositories;

import com.example.demo.models.SubjectRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SubjectRegisterRepository extends JpaRepository<SubjectRegister, Long> {

    @Query(value = "SELECT * FROM SubjectRegister WHERE regSessID = ?1", nativeQuery = true)
    List<SubjectRegister> findByRegSessID(Long regSessID);
}
