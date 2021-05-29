package com.progerslifes.diplom.facades;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.facades.dto.LikeDTO;

import java.util.List;

public interface LikeFacade {

    LikeDTO saveLike(int postId);

    void deleteLike(int postId);

    void markPostsLikedByCurrentUser(List<Post> posts);

}
