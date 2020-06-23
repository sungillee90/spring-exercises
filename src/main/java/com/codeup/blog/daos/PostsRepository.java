package com.codeup.blog.daos;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {

}
