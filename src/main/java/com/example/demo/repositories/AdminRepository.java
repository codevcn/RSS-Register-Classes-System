package com.example.demo.repositories;

import com.example.demo.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,String> {
    @Query(
        value = "select u from Admin u where u.id = ?1 and u.deleted = 0",
        nativeQuery = true
    )
    Admin findByID(String idcard);

    @Query(
        value = "select u from Admin u where u.accountID = ?1 and u.deleted = 0",
        nativeQuery = true
    )
    Admin findByAccountUsername(String username);

    void deleteById(String id);
}
