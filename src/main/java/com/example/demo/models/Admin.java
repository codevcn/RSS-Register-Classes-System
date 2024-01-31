package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    private String IDcard;

    private String fullName;
    private String birthday;
    private String gender;
    private String createdAt;

    @Column(nullable = true)
    private String account;

    public Admin() {}

    public Admin(String iDcard, String fullName, String birthday, String gender) {
        IDcard = iDcard;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String iDcard) {
        IDcard = iDcard;
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
}
