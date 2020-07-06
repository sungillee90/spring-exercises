package com.codeup.blog.daos;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {
    // Hibernate QL method NOT MySQL
    @Query("FROM Post AS a WHERE a.title LIKE %:term%")
    List<Post> searchByTitleLike(@Param("term") String term);

    // First result that matches the title
    // Query methods
    Post findFirstByTitle(String title); // SELECT * FROM posts WHERE title = ? LIMIT = 1

}
