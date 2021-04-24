package com.progerslifes.diplom.controllers;

import com.progerslifes.diplom.facades.user.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FollowerController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping("/follow")
    public String followUser(@RequestParam("username") String username) {
        userFacade.follow(username);
        return "redirect:/profile/"+username;
    }

    @DeleteMapping("/follow")
    public String dontFollowUser(@RequestParam("username") String username) {
        userFacade.dontFollow(username);
        return "redirect:/profile/"+username;
    }
}
