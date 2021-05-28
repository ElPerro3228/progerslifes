package com.progerslifes.diplom.facades.converters.user;

import com.progerslifes.diplom.entity.Like;
import com.progerslifes.diplom.facades.converters.GenericConverter;
import com.progerslifes.diplom.facades.dto.LikeDTO;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter implements GenericConverter<Like, LikeDTO> {
    @Override
    public LikeDTO apply(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(like.getId());
        likeDTO.setPostId(like.getPost().getId());
        likeDTO.setUserId(like.getUser().getId());
        return likeDTO;
    }
}
