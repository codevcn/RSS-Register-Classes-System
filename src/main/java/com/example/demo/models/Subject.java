package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "Subject")
public class Subject {

    @Id
    @Column(nullable = false, length = 20)
    private String id;

    @Column(nullable = false, length = 30)
    private String name;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "majorID", nullable = false)
    private Major major;

    @Column(nullable = false)
    private Long creditsCount;

    @ToString.Exclude
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "creditID", referencedColumnName = "id", nullable = false)
    private CreditDetail credit;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Column(nullable = false)
    private Boolean deleted;

    @ToString.Exclude
    @ManyToMany(mappedBy = "subjects")
    @JsonManagedReference
    private Set<Teacher> teachers;

    @ToString.Exclude
    @OneToMany(mappedBy = "subject")
    @JsonManagedReference
    private Set<SubjectSchedule> subjectSchedules;
}
