package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Admin")
public class Admin {

    @Id
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 12)
    private String idcard;

    @Column(nullable = false, length = 30)
    private String fullName;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "accountID", referencedColumnName = "username", nullable = true)
    @JsonBackReference
    private Account account;
}
