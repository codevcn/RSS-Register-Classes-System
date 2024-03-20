package com.example.demo.repositories;

import com.example.demo.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query(value = "select * from Admin where id = ?1", nativeQuery = true)
    Admin findByID(Integer id);

    @Query(value = "select * from Admin where accountID = ?1", nativeQuery = true)
    Admin findByAccountUsername(String username);

    @Query(
        value = "select * from Account where username = ?1 and u.deleted = 0",
        nativeQuery = true
    )
    Admin findAdminInfor(String username);
    // void deleteById(String id);
}
