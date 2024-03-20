package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "SubjectSlot")
public class SubjectSlot {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)     
    private Long id;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "subjectID", referencedColumnName = "id", nullable = false, unique = true)
    @JsonBackReference
    private Subject subject;

    @Column(nullable = false)
    private Long slot;

    @Column(nullable = false)
    private Timestamp createdAt;
}
