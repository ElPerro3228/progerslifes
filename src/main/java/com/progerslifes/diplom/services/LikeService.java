package com.progerslifes.diplom.services;

import com.progerslifes.diplom.entity.Like;

public interface LikeService {
    Like save(Like like);

    void delete(Like like);

    Like getLikeByPostAndUser(int postId, int userId);
}
