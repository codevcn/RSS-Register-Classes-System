package com.example.demo.repositories;

import com.example.demo.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(
        value = "select * from Account u where u.username = ?1 and u.deleted = 0",
        nativeQuery = true
    )
    Account findByUsername(String username);

    @Query(value = "select * from Account where id = ?1 and deleted = 0", nativeQuery = true)
    Account findByID(Long id);

    @Query(value = "select * from Account where id = ?1", nativeQuery = true)
    Account findAllbyID(Long id);

    @Query(
        value = "SELECT IDcard FROM Admin\r\n" + //
        "UNION\r\n" + //
        "SELECT IDCard FROM Student;\r\n" + //
        "",
        nativeQuery = true
    )
    java.util.List<String> findAllIdCards();
}
