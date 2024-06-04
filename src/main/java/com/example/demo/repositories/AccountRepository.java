package com.example.demo.repositories;

import com.example.demo.models.Account;
import com.example.demo.models.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Modifying
    @Query(
        value = "INSERT INTO Account (username, password, roleID, createdAt, updatedAt, deleted) " +
            "VALUES (:username, :password, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0)",
        nativeQuery = true
    )
    void saveAccount(@Param("username") String username, @Param("password") String password);

    @Query(
        value = "SELECT id FROM Account WHERE username = :username",
        nativeQuery = true
    )
    Long findAccountIdByUsername(@Param("username") String username);

    @Query(
        value = "SELECT * FROM Account a WHERE a.id = ?1 AND a.deleted = 0",
        nativeQuery = true
    )
    Account findByStudentAccountID(Long accountID);

}
