// package com.example.demo.controllers;

// import com.example.demo.models.Post;
// import com.example.demo.models.User;
// import com.example.demo.repositories.PostRepository;
// import com.example.demo.repositories.UserRepository;
// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("sql")
// public class SQLController {

//     @Autowired
//     UserRepository userRepository;

//     @Autowired
//     PostRepository postRepository;

//     @GetMapping
//     public ResponseEntity<List<User>> home() {
//         System.out.printf("\n>>> run this sql controller \n");
//         System.out.println();

//         // read
//         System.out.println();
//         System.out.println();
//         System.out.println("--------------------------------");
//         List<User> users = userRepository.findAll();
//         System.out.printf("\n>>> ( \n");
//         System.out.print(users);
//         System.out.printf("\n>>> ) \n");

//         System.out.println();
//         System.out.println();
//         System.out.println("--------------------------------");
//         List<Post> posts = postRepository.findPostsByCategory("tech");
//         System.out.printf("\n>>> ( \n");
//         System.out.print(posts);
//         System.out.printf("\n>>> ) \n");

//         return ResponseEntity.status(HttpStatus.OK).body(users);
//     }
// }
