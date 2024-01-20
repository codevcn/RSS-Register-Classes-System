// package com.example.demo.models;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "users")
// public class User {

//     @Id
//     private String email;

//     private String fullName;

//     private String birthday;

//     private String gender;

//     private String address;

//     private String avatar;

//     private String password;

//     private String role;

//     private String createdAt;

//     public User() {}

//     public User(
//         String email,
//         String fullName,
//         String birthday,
//         String gender,
//         String address,
//         String avatar,
//         String password,
//         String role,
//         String createdAt
//     ) {
//         this.email = email;
//         this.fullName = fullName;
//         this.birthday = birthday;
//         this.gender = gender;
//         this.address = address;
//         this.avatar = avatar;
//         this.password = password;
//         this.role = role;
//         this.createdAt = createdAt;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public String getFullName() {
//         return fullName;
//     }

//     public String getBirtday() {
//         return birthday;
//     }

//     public String getGender() {
//         return gender;
//     }

//     public String getAddress() {
//         return address;
//     }

//     public String getAvatar() {
//         return avatar;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public String getRole() {
//         return role;
//     }

//     public String getCreatedAt() {
//         return createdAt;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public void setFullName(String fullName) {
//         this.fullName = fullName;
//     }

//     public void setBirtday(String birthday) {
//         this.birthday = birthday;
//     }

//     public void setGender(String gender) {
//         this.gender = gender;
//     }

//     public void setAddress(String address) {
//         this.address = address;
//     }

//     public void setAvatar(String avatar) {
//         this.avatar = avatar;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public void setRole(String role) {
//         this.role = role;
//     }

//     public void setCreatedAt(String createdAt) {
//         this.createdAt = createdAt;
//     }

//     @Override
//     public String toString() {
//         return (
//             "User[email=" +
//             email +
//             ", fullName=" +
//             fullName +
//             ", birthday=" +
//             birthday +
//             ", gender=" +
//             gender +
//             ", address=" +
//             address +
//             ", avatar=" +
//             avatar +
//             ", password=" +
//             password +
//             ", role=" +
//             role +
//             ", createdAt=" +
//             createdAt +
//             "]"
//         );
//     }
// }
