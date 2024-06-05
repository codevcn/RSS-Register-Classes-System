package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "RegisterSession")
@JsonIgnoreProperties(value = {"subjectRegisters", "subjectSchedules"})
public class RegisterSession {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp beginTime;

    @Column(nullable = false)
    private Timestamp endTime;

    @Column(nullable = false, length = 50, unique = true)
    private String regSessCode;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "registerSession")
    private Set<SubjectRegister> subjectRegisters;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "registerSession")
    private Set<RegisterReceipt> registerReceipts;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "registerSession")
    private Set<SubjectSchedule> subjectSchedules;
}
