package com.example.demo.models;

import com.example.demo.models.keys.SubjectRegisterKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "SubjectRegister")
public class SubjectRegister {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long slot;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ManyToOne
    @MapsId("studentID")
    @JoinColumn(name = "studentID")
    private Subject subject;

    @ManyToOne
    @MapsId("regSessID")
    @JoinColumn(name = "regSessID")
    private RegisterSession registerSession;

    @ManyToOne
    @MapsId("subjectScheduleID")
    @JoinColumn(name = "subjectScheduleID")
    private SubjectSchedule subjectSchedule;

    @EmbeddedId
    private SubjectRegisterKey subjectRegisterKey;
}
