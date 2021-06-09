package com.progerslifes.diplom.entity.listeners;

import com.progerslifes.diplom.entity.Like;
import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.entity.User;

import javax.persistence.PreRemove;

public class PostLikesListener {

    @PreRemove
    private void dismissChildAndParent(Like like) {
        User user = like.getUser();
        Post post = like.getPost();
        user.getLikes().remove(like);
        post.getLikes().remove(like);
        like.setUser(null);
        like.setPost(null);
    }

}
