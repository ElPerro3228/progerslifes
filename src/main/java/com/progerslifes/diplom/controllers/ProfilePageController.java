package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.facades.AuthenticationFacade;
import com.progerslifes.diplom.facades.impl.ProgersLifesProfilePageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilePageController {

    @Autowired
    private ProgersLifesProfilePageFacade progersLifesProfilePageFacade;

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        User user = progersLifesProfilePageFacade.getUser();
        model.addAttribute("user", user);
        return "profile";
    }
}
