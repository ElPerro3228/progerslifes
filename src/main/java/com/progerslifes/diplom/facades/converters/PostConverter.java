package com.progerslifes.diplom.facades.converters;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.facades.dto.PostDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PostConverter implements GenericConverter<PostDTO, Post> {
    @Override
    public Post apply(PostDTO postDTO) {
        Post post = new Post();
        post.setText(postDTO.getText());
        post.setCreationDate(new Date());
        return post;
    }
}
