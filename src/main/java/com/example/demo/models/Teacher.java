package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Date;
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
@Table(name = "Teacher")
@JsonIgnoreProperties(value = { "majors", "subjects", "subjectSchedules" })
public class Teacher {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 12, unique = true)
    private String teacherCode;

    @Column(nullable = false, length = 15)
    private String phone;

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

    @Column(nullable = false)
    private Boolean deleted;

    @ManyToMany
    @JoinTable(
        name = "TeacherMajor",
        joinColumns = @JoinColumn(name = "teacherID"),
        inverseJoinColumns = @JoinColumn(name = "majorID")
    )
    @JsonManagedReference
    private Set<Major> majors;

    @ManyToMany
    @JoinTable(
        name = "TeacherSubject",
        joinColumns = @JoinColumn(name = "teacherID"),
        inverseJoinColumns = @JoinColumn(name = "subjectID")
    )
    @JsonManagedReference
    private Set<Subject> subjects;

    @ToString.Exclude
    @OneToMany(mappedBy = "teacher")
    @JsonManagedReference
    private Set<SubjectSchedule> subjectSchedules;
}
