// package com.example.demo.services;

// import org.springframework.dao.DataAccessException;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;

// @Service
// public class TestService {

//     public ResponseEntity<String> todo() {
//         String str1 = "abc";
//         String str2 = "ABC";

//         int result = str1.compareTo(str2);

//         if (result < 0) {
//             throw new DataAccessException("VCN data access exception!!!") {};
//         } else if (result > 0) {
//             throw new RuntimeException("VCN runtime exception!!!") {};
//         }

//         return ResponseEntity.status(HttpStatus.OK).body("OK from vcn test service");
//     }
// }
