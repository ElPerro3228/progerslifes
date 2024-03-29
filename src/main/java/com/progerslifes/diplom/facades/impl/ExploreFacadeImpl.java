package com.progerslifes.diplom.facades.impl;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.facades.ExploreFacade;
import com.progerslifes.diplom.facades.LikeFacade;
import com.progerslifes.diplom.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExploreFacadeImpl implements ExploreFacade {

    @Autowired
    private PostService postService;

    @Autowired
    private LikeFacade likeFacade;

    @Value("${page.explore.size}")
    private int size;

    @Override
    public List<Post> getMostRelevantPosts() {
        List<Post> posts = postService.getMostRelevantPosts();
        likeFacade.markPostsLikedByCurrentUser(posts);
        return posts;
    }
}
