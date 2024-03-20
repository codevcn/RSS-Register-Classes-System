package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.sql.Timestamp;
import java.time.LocalDate;
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
@Table(
    name = "SubjectSchedule",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "beginDate", "endDate", "subjectID", "dayOfWeek", "startingSession", "teacherID"
            }
        )
    }
)
public class SubjectSchedule {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate beginDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "subjectID", nullable = false)
    @JsonBackReference
    private Subject subject;

    @Column(nullable = false, length = 30)
    private String teamGroup;

    @Column(nullable = false, length = 30)
    private String className;

    @Column(nullable = false)
    private Long dayOfWeek;

    @Column(nullable = false)
    private Long startingSession;

    @Column(nullable = false)
    private Long numberOfSessions;

    @Column(nullable = false, length = 4)
    private String roomName;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "teacherID", nullable = false)
    @JsonBackReference
    private Teacher teacher;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "subjectSchedule")
    private Set<SubjectRegister> subjectRegisters;
}
