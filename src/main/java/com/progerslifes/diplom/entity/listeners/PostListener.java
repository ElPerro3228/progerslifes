package com.progerslifes.diplom.entity.listeners;

import com.progerslifes.diplom.entity.Post;

import javax.persistence.PreRemove;

public class PostListener {

    @PreRemove
    private void dismissChildAndParent(Post post) {
        post.setUser(null);
    }

}
