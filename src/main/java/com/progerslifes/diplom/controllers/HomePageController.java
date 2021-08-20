package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.HomePageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @Autowired
    private HomePageFacade homePageFacade;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("posts", homePageFacade.getPostsForHomePage());
        return "home";
    }

}
