package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.ExploreFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExploreController {

    @Autowired
    private ExploreFacade exploreFacade;

    @GetMapping("/explore")
    public String getExplorePage(Model model) {
        model.addAttribute("posts", exploreFacade.getMostRelevantPosts());
        return "explore";
    }

}
