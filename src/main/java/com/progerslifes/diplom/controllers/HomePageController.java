package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.dto.PostDTO;
import com.progerslifes.diplom.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "home";
    }

}
