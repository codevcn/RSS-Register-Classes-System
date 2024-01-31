package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    private String IDcard;

    private String phone;
    private String createdAt;
    private String fullName;
    private String birthday;
    private String gender;
    private String departmentID;

    @Column(nullable = true)
    private String account;

    public Student() {}

    public Student(
        String iDcard,
        String phone,
        String createdAt,
        String fullName,
        String birthday,
        String gender,
        String departmentID
    ) {
        IDcard = iDcard;
        this.phone = phone;
        this.createdAt = createdAt;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.departmentID = departmentID;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String iDcard) {
        IDcard = iDcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
