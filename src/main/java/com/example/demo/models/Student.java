package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Student")
@JsonIgnoreProperties(value = {"cancelRegisters"})
public class Student {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 12, unique = true)
    private String studentCode;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 30)
    private String fullName;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(nullable = false)
    private Long year;

    @Column(nullable = false, length = 12)
    private String idcard;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "majorID", nullable = false)
    private Major major;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Column(nullable = false)
    private Boolean deleted;

    @ToString.Exclude
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "accountID", referencedColumnName = "id", nullable = true, unique = true)
    private Account account;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "classID", nullable = false)
    private StudentClass studentClass;
}
