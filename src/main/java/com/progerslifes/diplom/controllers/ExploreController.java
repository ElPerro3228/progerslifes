package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.ExploreFacade;
import com.progerslifes.diplom.facades.user.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExploreController {

    @Autowired
    private ExploreFacade exploreFacade;
    @Autowired
    private UserFacade userFacade;

    @GetMapping("/explore")
    public String getExplorePage(Model model) {
        model.addAttribute("posts", exploreFacade.getMostRelevantPosts());
        model.addAttribute("currentUser", userFacade.getCurrentUser());
        return "explore";
    }

}
