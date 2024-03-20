package com.example.demo.models.keys;

import com.example.demo.models.RegisterSession;
import com.example.demo.models.Subject;
import com.example.demo.models.SubjectSchedule;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class SubjectRegisterKey implements Serializable {

    @Column(name = "subjectID", nullable = false)
    private Subject subjectID;

    @Column(name = "regSessID", nullable = false)
    private RegisterSession regSessID;

    @Column(name = "subjectScheduleID", nullable = false)
    private SubjectSchedule subjectScheduleID;
}
