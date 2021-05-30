package com.progerslifes.diplom.facades;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.facades.dto.LikesDTO;

import java.util.List;

public interface LikeFacade {

    LikesDTO saveLike(int postId);

    LikesDTO deleteLike(int postId);

    void markPostsLikedByCurrentUser(List<Post> posts);

}
