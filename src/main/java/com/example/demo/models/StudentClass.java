package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;
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
@Table(name = "StudentClass")
@JsonIgnoreProperties(value = {"subjectSchedules", "students"})
public class StudentClass {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 11, unique = true)
    String code;

    @Column(nullable = false, length = 50)
    String name;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "majorID", nullable = false)
    private Major major;

    @ToString.Exclude
    @OneToMany(mappedBy = "studentClass")
    @JsonManagedReference
    private Set<Student> students;

    @ToString.Exclude
    @OneToMany(mappedBy = "studentClass")
    @JsonManagedReference
    private Set<SubjectSchedule> subjectSchedules;
}
