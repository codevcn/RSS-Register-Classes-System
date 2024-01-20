// package com.example.demo.repositories;

// import com.example.demo.models.Post;
// import java.util.List;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface PostRepository extends JpaRepository<Post, Integer> {
//     @Query(
//         value = "SELECT post.mainContent, category.description " +
//         "FROM post " +
//         "INNER JOIN category ON post.categoryID = category.aliasID " +
//         "WHERE category.aliasID = :category_alias",
//         nativeQuery = true
//     )
//     public List<Post> findPostsByCategory(@Param("category_alias") String category_alias);

//     @Modifying
//     @Query(value = "UPDATE post SET title = :new_title WHERE id = :post_id", nativeQuery = true)
//     void changePostTitle(@Param("post_id") int post_id, @Param("new_title") String new_title);
// }
