package com.progerslifes.diplom.facades.impl;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.entity.User;
import com.progerslifes.diplom.services.AuthenticationService;
import com.progerslifes.diplom.facades.ProfilePageFacade;
import com.progerslifes.diplom.services.PostService;
import com.progerslifes.diplom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProgersLifesProfilePageFacade implements ProfilePageFacade {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    @Override
    public User getUser() {
        User user = userService.getUser(authenticationService.getAuthentication().getName());
        List<Post> posts = postService.getPosts(user);
        user.setPosts(posts);
        return user;
    }
}
