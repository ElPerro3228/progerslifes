package com.progerslifes.diplom.facades;

import com.progerslifes.diplom.facades.dto.PostDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PostFacade {
    void savePost(PostDTO postDTO);
    void savePost(PostDTO postDTO, MultipartFile image) throws IOException;
}
