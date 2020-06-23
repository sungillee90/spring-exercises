package com.codeup.blog.controllers;

import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // Dependency injection
    private PostsRepository postsDao;
    // Dependency injection
    public PostController(PostsRepository postsRepository) {
        postsDao = postsRepository;
    }

    @GetMapping("/posts")
//    @ResponseBody
    public String doPost(Model model) {
        List<Post> postsList = postsDao.findAll();
//        postsList.add(new Post("First","first blog post"));
//        postsList.add(new Post("Second","second blog post"));
//        postsList.add(new Post("Third","third blog post"));
//        postsList.add(new Post("Fourth","fourth blog post"));

        model.addAttribute("noPostsFound", postsList.size() == 0);
        model.addAttribute("posts", postsList);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String doPostById(@PathVariable long id, Model model) {
        model.addAttribute("postId", id);
        model.addAttribute("post", new Post("Testing Purpose","This is my first post made"));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreateForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String create() {
        Post newPost = new Post("dependency Injection Test","Testing");
        postsDao.save(newPost);
        return "create a new post";
    }

//    @PutMapping("/posts/{id}/edit")
//    @ResponseBody
//    public String update(@PathVariable long id) {
//        // find a post
//        Post foundPost = postsDao.getOne(id); // SELECT * FROM posts WHERE id = ?
//        // edit the post
//        foundPost.setTitle("This is cool title Testing");
//        // save the post
//        postsDao.save(foundPost);
//        return "post updated";
//    }


//    @DeleteMapping("/posts/{id}/delete")
//    @ResponseBody
//    public String destroy(@PathVariable long id) {
//        postsDao.deleteById(id);
//        return "post Successfully deleted";
//    }



}
