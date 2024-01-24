// package com.example.demo.models;

// import com.example.demo.models.primaryKeys.TeacherSubjectId;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.IdClass;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "TeacherSubject")
// @IdClass(TeacherSubjectId.class)
// public class TeacherSubject {

//     @Id
//     private String teacherID;

//     @Id
//     private String subjectID;

//     public TeacherSubject() {}

//     public TeacherSubject(String teacherID, String subjectID) {
//         this.teacherID = teacherID;
//         this.subjectID = subjectID;
//     }

//     public String getStudentID() {
//         return teacherID;
//     }

//     public void setStudentID(String teacherID) {
//         this.teacherID = teacherID;
//     }

//     public String getSubjectID() {
//         return subjectID;
//     }

//     public void setSubjectID(String subjectID) {
//         this.subjectID = subjectID;
//     }
// }
