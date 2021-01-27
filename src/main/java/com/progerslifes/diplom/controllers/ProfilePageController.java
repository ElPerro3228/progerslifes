package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilePageController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        String username = authenticationFacade.getAuthentication().getName();
        model.addAttribute("username", username);
        return "profile";
    }
}
