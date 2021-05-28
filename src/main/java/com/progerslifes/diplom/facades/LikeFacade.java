package com.progerslifes.diplom.facades;

import com.progerslifes.diplom.facades.dto.LikeDTO;

public interface LikeFacade {

    LikeDTO saveLike(int postId);

    void deleteLike(int postId);

}
