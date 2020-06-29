package com.codeup.blog.controllers;

import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
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
        model.addAttribute("post", new Post("Testing Purpose","This is my first post made", null));
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
        Post newPost = new Post("dependency Injection Test","Testing", null);
        postsDao.save(newPost);
        return "create a new post";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(Model model, @PathVariable long id) {
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String update(@PathVariable long id,
                         @RequestParam(name = "title") String title,
                         @RequestParam(name = "body") String body) {
        // find a post
        Post foundPost = postsDao.getOne(id); // SELECT * FROM posts WHERE id = ?
        // edit the post
//        foundPost.setTitle("This is cool title Testing");
            // To make it dynamic
        foundPost.setTitle(title);
        foundPost.setBody(body);
        // save the post
        postsDao.save(foundPost); // UPDATE posts SET title = ?
        return "post updated";
    }


    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String destroy(@PathVariable long id) {
        postsDao.deleteById(id);
        return "post Successfully deleted";
    }

    @GetMapping("/search")
    public String searchResults(Model model, @RequestParam(name = "term") String term) {

        List<Post> posts = postsDao.searchByTitleLike(term);

        model.addAttribute("posts", posts);
        return "/posts/index";
    }


}
