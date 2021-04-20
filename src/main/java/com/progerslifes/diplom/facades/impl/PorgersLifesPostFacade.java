package com.progerslifes.diplom.facades.impl;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.facades.PostFacade;
import com.progerslifes.diplom.facades.ProfilePageFacade;
import com.progerslifes.diplom.facades.converters.PostConverter;
import com.progerslifes.diplom.facades.dto.PostDTO;
import com.progerslifes.diplom.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PorgersLifesPostFacade implements PostFacade {

    @Autowired
    private PostConverter postConverter;
    @Autowired
    private ProfilePageFacade profilePageFacade;
    @Autowired
    private PostService postService;

    @Override
    public void savePost(PostDTO postDTO) {
        Post post = postConverter.convert(postDTO);
        post.setUser(profilePageFacade.getUser());
        postService.save(post);
    }
}
