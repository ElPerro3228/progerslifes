package com.progerslifes.diplom.facades.converters.user;

import com.progerslifes.diplom.entity.Like;
import com.progerslifes.diplom.facades.converters.GenericConverter;
import com.progerslifes.diplom.facades.dto.LikesDTO;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter implements GenericConverter<Like, LikesDTO> {
    @Override
    public LikesDTO apply(Like like) {
        LikesDTO likesDTO = new LikesDTO();
        likesDTO.setId(like.getId());
        likesDTO.setPostId(like.getPost().getId());
        likesDTO.setUserId(like.getUser().getId());
        return likesDTO;
    }
}
