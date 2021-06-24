package com.progerslifes.diplom.facades.impl;

import com.progerslifes.diplom.entity.Post;
import com.progerslifes.diplom.facades.PostFacade;
import com.progerslifes.diplom.facades.ProfilePageFacade;
import com.progerslifes.diplom.facades.converters.PostConverter;
import com.progerslifes.diplom.facades.dto.PostDTO;
import com.progerslifes.diplom.facades.user.UserFacade;
import com.progerslifes.diplom.services.PostService;
import com.progerslifes.diplom.services.UploadService;
import com.progerslifes.diplom.utils.EmptyMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class PorgersLifesPostFacade implements PostFacade {

    @Autowired
    private PostConverter postConverter;
    @Autowired
    private ProfilePageFacade profilePageFacade;
    @Autowired
    private PostService postService;
    @Autowired
    private UploadService uploadService;
    @Value("${user.profile.picture.dir}")
    private String imagesDir;

    @Override
    public void savePost(PostDTO postDTO) {
        Post post = postConverter.convert(postDTO);
        post.setUser(profilePageFacade.getUser());
        postService.save(post);
    }

    @Override
    public void savePost(PostDTO postDTO, MultipartFile image) throws IOException {
        Post post = postConverter.convert(postDTO);
        if (image == null) {
            image = EmptyMultipartFile.EMPTY_IMAGE;
        }
        if ((!image.isEmpty()) && (image.getSize() != 0)) {
            savePostPicture(image, post);
        }
        if (postDTO.getAncestorId() > 0) {
            Post ancestor = postService.getPostById(postDTO.getAncestorId());
            post.setAncestor(ancestor);
        }
        post.setUser(profilePageFacade.getUser());
        postService.save(post);
    }

    private void savePostPicture(MultipartFile image, Post post) throws IOException {
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        uploadService.saveFile(imagesDir, imageName, image);
        post.setPicturePath(uploadService.getPath(imagesDir, imageName));
    }

    @Override
    public void deletePost(int postId) {
        postService.deletePost(postId);
    }
}
