package com.progerslifes.diplom.facades.impl;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.facades.HomePageFacade;
import com.progerslifes.diplom.facades.LikeFacade;
import com.progerslifes.diplom.services.AuthenticationService;
import com.progerslifes.diplom.services.PostService;
import com.progerslifes.diplom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomePageFacadeImpl implements HomePageFacade {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private LikeFacade likeFacade;

    @Override
    public List<Post> getPostsForHomePage() {
        List<Post> posts = postService.getPosts(userService.getUser(
                authenticationService.getAuthentication().getName()
        ).getSubscriptions());
        likeFacade.markPostsLikedByCurrentUser(posts);
        return posts;
    }
}
