package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String doPost() {
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String doPostById(@PathVariable long id) {
        return "View an individual(id =" + id + ")post";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreate() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String create() {
        return "create a new post";
    }

}
