package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Subject")
public class Subject {

    @Id
    private String id;

    private String name;
    private String createdAt;
    private String teacherID;
    private String departmentID;
    private String beginDate;
    private String endDate;

    public Subject() {}

    public Subject(
        String id,
        String name,
        String createdAt,
        String teacherID,
        String departmentID,
        String beginDate,
        String endDate
    ) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.teacherID = teacherID;
        this.departmentID = departmentID;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
