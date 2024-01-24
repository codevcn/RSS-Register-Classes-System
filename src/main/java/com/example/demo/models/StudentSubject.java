package com.example.demo.models;

import com.example.demo.models.primaryKeys.StudentSubjectId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "StudentSubject")
@IdClass(StudentSubjectId.class)
public class StudentSubject {

    @Id
    private String studentID;

    @Id
    private String subjectID;

    public StudentSubject() {}

    public StudentSubject(String studentID, String subjectID) {
        this.studentID = studentID;
        this.subjectID = subjectID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }
}
