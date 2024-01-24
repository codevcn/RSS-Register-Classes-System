package com.example.demo.models.primaryKeys;

import java.io.Serializable;

public class StudentSubjectId implements Serializable {

    private String studentID;
    private String subjectID;

    public StudentSubjectId(String studentID, String subjectID) {
        this.studentID = studentID;
        this.subjectID = subjectID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentID == null) ? 0 : studentID.hashCode());
        result = prime * result + ((subjectID == null) ? 0 : subjectID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        StudentSubjectId other = (StudentSubjectId) obj;
        if (studentID == null) {
            if (other.studentID != null) return false;
        } else if (!studentID.equals(other.studentID)) return false;
        if (subjectID == null) {
            if (other.subjectID != null) return false;
        } else if (!subjectID.equals(other.subjectID)) return false;
        return true;
    }
}
