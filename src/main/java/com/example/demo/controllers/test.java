package com.example.demo.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {
    public static void main(String[] args) {
        String original_password = "test123";
        test test = new test(); // Tạo một instance của lớp Test
        String encoded_password = test.encodePassword(original_password); // Gọi phương thức encodePassword
        System.out.println("Encoded password: " + encoded_password);
    }

    private String encodePassword(String original_password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(original_password);
    }
}
