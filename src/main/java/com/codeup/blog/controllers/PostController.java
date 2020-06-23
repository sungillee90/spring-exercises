package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/posts")
//    @ResponseBody
    public String doPost(Model model) {
        ArrayList<Post> postsList = new ArrayList<>();
        postsList.add(new Post("First","first blog post"));
        postsList.add(new Post("Second","second blog post"));
        postsList.add(new Post("Third","third blog post"));
        postsList.add(new Post("Fourth","fourth blog post"));
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
        return "create a new post";
    }

}
