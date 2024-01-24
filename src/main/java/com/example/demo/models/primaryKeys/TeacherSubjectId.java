package com.example.demo.models.primaryKeys;

import java.io.Serializable;

public class TeacherSubjectId implements Serializable {

    private String teacherID;
    private String subjectID;

    public TeacherSubjectId(String teacherID, String subjectID) {
        this.teacherID = teacherID;
        this.subjectID = subjectID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((teacherID == null) ? 0 : teacherID.hashCode());
        result = prime * result + ((subjectID == null) ? 0 : subjectID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        TeacherSubjectId other = (TeacherSubjectId) obj;
        if (teacherID == null) {
            if (other.teacherID != null) return false;
        } else if (!teacherID.equals(other.teacherID)) return false;
        if (subjectID == null) {
            if (other.subjectID != null) return false;
        } else if (!subjectID.equals(other.subjectID)) return false;
        return true;
    }
}
