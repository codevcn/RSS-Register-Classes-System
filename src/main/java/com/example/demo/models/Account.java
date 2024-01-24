package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDcard")
    private String IDcard;

    private String password;
    private String role;

    public Account() {}

    public Account(String iDcard, String password, String role) {
        IDcard = iDcard;
        this.password = password;
        this.role = role;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String iDcard) {
        IDcard = iDcard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
