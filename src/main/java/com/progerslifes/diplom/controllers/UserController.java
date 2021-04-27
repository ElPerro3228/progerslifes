package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.user.UserFacade;
import com.progerslifes.diplom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserFacade userFacade;

    @GetMapping("/profile/{username}")
    public String getProfileNY(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.getUser(username));
        model.addAttribute("isFollowing", userFacade.isFollowing(username));
        return "profileNY";
    }
}
