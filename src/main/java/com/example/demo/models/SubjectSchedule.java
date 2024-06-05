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
import jakarta.persistence.UniqueConstraint;
import java.sql.Timestamp;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "SubjectSchedule",
    uniqueConstraints = {@UniqueConstraint(
        columnNames = {"beginDate", "endDate", "dayOfWeek", "startingSession", "teacherID", "regSessID"})})
@JsonIgnoreProperties(value = {"receiptSubjects"})
public class SubjectSchedule {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate beginDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, length = 30)
    private String teamGroup;

    @Column(nullable = false, length = 30)
    private String partGroup;

    @Column(nullable = false)
    private Long dayOfWeek;

    @Column(nullable = false)
    private Long startingSession;

    @Column(nullable = false)
    private Long numberOfSessions;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Column(nullable = false)
    private Long slotsCount;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "subjectID", nullable = false)
    @JsonBackReference
    private Subject subject;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "teacherID", nullable = false)
    @JsonBackReference
    private Teacher teacher;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "regSessID", nullable = false)
    private RegisterSession registerSession;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "classID", nullable = false)
    private StudentClass studentClass;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "roomID", nullable = false)
    private Room room;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "subjectSchedule")
    private Set<ReceiptSubject> receiptSubjects;
}
