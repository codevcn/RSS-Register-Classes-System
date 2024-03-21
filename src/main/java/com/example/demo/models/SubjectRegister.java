package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(
    name = "SubjectRegister",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "studentID", "regSessID", "subjectScheduleID" })
    }
)
public class SubjectRegister {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long slot;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "studentID")
    private Subject subject;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "regSessID")
    @JsonBackReference
    private RegisterSession registerSession;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "subjectScheduleID")
    @JsonBackReference
    private SubjectSchedule subjectSchedule;
}
