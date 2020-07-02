package com.codeup.blog.controllers;

import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.daos.UserRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // Dependency injection
    private PostsRepository postsDao;
    private UserRepository usersDao;
    private final EmailService emailService;
    // Dependency injection
    public PostController(PostsRepository postsRepository, UserRepository userRepository, EmailService emailService) {
        this.postsDao = postsRepository;
        this.usersDao = userRepository;
        this.emailService = emailService;
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
        Post post = postsDao.getOne(id);
        model.addAttribute("postId", id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreateForm(Model viewModel) {
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create( @ModelAttribute Post postToBeSaved
//            //        name att in create html   Place holder Java
//            @RequestParam(value = "title") String title,
//            @RequestParam(value = "body") String body
    ) {
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        Post newPost = new Post(title, body, currentUser);
        postToBeSaved.setOwner(currentUser);
        Post savePost = postsDao.save(postToBeSaved);
        emailService.prepareAndSend(savePost, "A new post has been creating", "An post has been created with the id of " + savePost.getId());
        return "redirect:/posts/" + savePost.getId();
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(Model model, @PathVariable long id) {
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@ModelAttribute Post postToEdit) {
        User currentUser = usersDao.getOne(3L);
        postToEdit.setOwner(currentUser);
        // find a post
//        Post foundPost = postsDao.getOne(id); // SELECT * FROM posts WHERE id = ?
//        // edit the post
////        foundPost.setTitle("This is cool title Testing");
//            // To make it dynamic
//        foundPost.setTitle(title);
//        foundPost.setBody(body);
        // save the post

        postsDao.save(postToEdit); // UPDATE posts SET title = ?
        return "redirect:/posts/" + postToEdit.getId();
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
